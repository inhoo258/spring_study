package study.spring.emp.file.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import study.spring.emp.file.model.FileVO;

@Repository
public class FileRepository implements IFileRepository {

	@Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbcTemplate;

	// nvl - > null value 들어가서 널값이면 널값이 나왔을때 값을 적어줌
	@Override
	public int getMaxFileId() {
		String sql = "select nvl(max(file_Id), 0) from files";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public void uploadFile(FileVO file) {
		String sql = "insert into files values (? , ? , ? , ? , ? , sysdate , ?)";
		jdbcTemplate.update(sql, file.getFileId(), file.getDirectoryName(), file.getFileName(), file.getFileSize(),
				file.getFileContentType(), file.getFileDate());

	}

	@Override
	public FileVO getFile(int fileId) {
		String sql = "select file_id , directory_name , file_name , file_size ,file_content_type , file_upload_date , file_data  from files where file_id = ?";
		return jdbcTemplate.queryForObject(sql, new RowMapper<FileVO>() {

			@Override
			public FileVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				FileVO file = new FileVO();
				file.setFileId(rs.getInt(1));
				file.setDirectoryName(rs.getString(2));
				file.setFileName(rs.getString(3));
				file.setFileSize(rs.getLong(4));
				file.setFileContentType(rs.getString(5));
				file.setFileUploadDate(rs.getTimestamp(6));
				file.setFileDate(rs.getBytes(7));
				return file;
			}
		}, fileId);
	}

	@Override
	public List<FileVO> getFileList() {
		String sql = "select file_id , directory_name , file_name , file_size ,file_content_type , file_upload_date from files";
		return jdbcTemplate.query(sql, new RowMapper<FileVO>() {

			@Override
			public FileVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				FileVO file = new FileVO();
				file.setFileId(rs.getInt("file_Id"));
				file.setDirectoryName(rs.getString("directory_name"));
				file.setFileName(rs.getString("file_name"));
				file.setFileSize(rs.getLong("file_size"));
				file.setFileContentType(rs.getString("file_content_type"));
				file.setFileUploadDate(rs.getTimestamp("file_upload_date"));
				return file;
			}
		});
	}

	@Override
	public List<FileVO> getDirectoryFileList(String name) {
		String sql = "select file_id , directory_name , file_name , file_size ,file_content_type , file_upload_date from files where directory_name = ?";
		return jdbcTemplate.query(sql, new RowMapper<FileVO>() {

			@Override
			public FileVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				FileVO file = new FileVO();
				file.setFileId(rs.getInt("file_Id"));
				file.setDirectoryName(rs.getString("directory_name"));
				file.setFileName(rs.getString("file_name"));
				file.setFileSize(rs.getLong("file_size"));
				file.setFileContentType(rs.getString("file_content_type"));
				file.setFileUploadDate(rs.getTimestamp("file_upload_date"));
				return file;
			}

		},name);
	}
	
	
	
	

}
