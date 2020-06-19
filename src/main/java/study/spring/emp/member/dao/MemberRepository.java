package study.spring.emp.member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import study.spring.emp.member.model.MemVO;

@Repository
public class MemberRepository implements IMemberRepository {

	@Autowired
	MyJdbcTemplate mjt;

	@Override
	public void insertAuth(String userId) {
		String sql = "insert into authorities values(?,?)";
		mjt.update(sql, userId, "ROLE_USER");
	}

	@Override
	public void insertMember(MemVO mem) {
		String sql = "insert into member values(?,?,?,?,?,?)";
		mjt.update(sql, mem.getUserId(), mem.getName(), mem.getPassword(), mem.getEmail(), mem.getAddress(),
				mem.getEnabled());

	}

	@Override
	public MemVO getMember(String userId) {
		String sql = "select m.userid, name, password, email,address, enabled, authority from member m join authorities au on m.userid = au.userid where m.userid=?";
		return mjt.query(sql, new ResultSetExtractor<MemVO>() {

			@Override
			public MemVO extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					MemVO mem = new MemVO();
					mem.setUserId(rs.getString("userid"));
					mem.setName(rs.getString("name"));
					mem.setPassword(rs.getString("password"));
					mem.setEmail(rs.getString("email"));
					mem.setAddress(rs.getString("address"));
					mem.setEnabled(rs.getInt("enabled"));
					mem.setAuth(rs.getString("authority"));
					return mem;
				} else {
					return null;
				}
			}

		}, userId);
	}

	@Override
	public String getPassword(String userId) {
		String sql = "select password from member where userid=?";
		return mjt.queryForNullableObject(sql, String.class, userId);
	}

	@Override
	public MemVO getInfo(String userId) {
		String sql = "select m.userid, name, email, password , address, enabled, authority from member m join authorities au on m.userid = au.userid where m.userid=?";

		return mjt.query(sql, new ResultSetExtractor<MemVO>() {

			@Override
			public MemVO extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					MemVO mem = new MemVO();
					mem.setUserId(rs.getString("userid"));
					mem.setName(rs.getString("name"));
					mem.setEmail(rs.getString("email"));
					mem.setPassword(rs.getString("password"));
					mem.setAddress(rs.getString("address"));
					mem.setEnabled(rs.getInt("enabled"));
					mem.setAuth(rs.getString("authority"));
					return mem;
				} else {
					return null;
				}
			}

		}, userId);
	}

	@Override
	public void updateMember(MemVO member) {
		String sql = "update member set name=? , email=? , address=? where userid = ?";
		mjt.update(sql, member.getName(), member.getEmail(), member.getAddress(), member.getUserId());

	}

	@Override
	public void enabledupdate(String enabled, String userId) {
		String sql = "update member set enabled=? where userid=?";
		mjt.update(sql, enabled, userId);
	}

	@Override
	public List<MemVO> memberList() {
		String sql = "select m.userid, name, email,address, enabled, authority from member m join authorities au on m.userid = au.userid";
		return mjt.query(sql, new RowMapper<MemVO>() {

			@Override
			public MemVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemVO member = new MemVO();
				member.setUserId(rs.getString(1));
				member.setName(rs.getString(2));
				member.setEmail(rs.getString(3));
				member.setAddress(rs.getString(4));
				member.setEnabled(Integer.parseInt(rs.getString(5)));
				member.setAuth(rs.getString(6));
				return member;
			}

		});
	}

	@Override
	public void masterUpdate(MemVO member) {
		String sql = "update authorities set authority=? where userid=?";
		mjt.update(sql, member.getAuth(), member.getUserId());
	}

	@Override
	public void memberDelete(String userId) {
		String sql = "delete member where userid=?";
		mjt.update(sql, userId);

	}

	@Override
	public void authDelete(String userId) {
		String sql = "delete authorities where userid=?";
		mjt.update(sql, userId);
	}

	@Override
	public List<MemVO> selectList(String select) {
		String sql = "select m.userid, name, email,address, enabled, authority from member m join authorities au on m.userid = au.userid where m.USERID like '%"+select+"%' or m.NAME like '%"+select+"%'";
		return mjt.query(sql, new RowMapper<MemVO>() {

			@Override
			public MemVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemVO member = new MemVO();
				member.setUserId(rs.getString(1));
				member.setName(rs.getString(2));
				member.setEmail(rs.getString(3));
				member.setAddress(rs.getString(4));
				member.setEnabled(Integer.parseInt(rs.getString(5)));
				member.setAuth(rs.getString(6));
				return member;
			}
			
		});
	}

}
