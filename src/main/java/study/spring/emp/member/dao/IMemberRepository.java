package study.spring.emp.member.dao;

import java.util.List;

import study.spring.emp.member.model.MemVO;

public interface IMemberRepository {
	void insertMember(MemVO mem);
	void insertAuth(String userId);
	MemVO getMember(String userId);
	String getPassword(String userId);
	MemVO getInfo(String userId);
	void updateMember(MemVO member);
	void enabledupdate(String enabled , String userId);
	List<MemVO> memberList();	
	void masterUpdate(MemVO member);
	void memberDelete(String userId);
	void authDelete(String userId);
	List<MemVO> selectList(String select);
	
}
