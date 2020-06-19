package study.spring.emp.member.service;

import java.util.List;

import study.spring.emp.member.model.MemVO;

public interface IMemService {
	void insertMember(MemVO member);	
	MemVO getMember(String userId);
	String getPassword(String userId);
	MemVO getInfo(String userId);	
	void updateMember(MemVO member);
	void enabledupdate(String enabled , String userId);
	void masterUpdate(MemVO member);
	List<MemVO> memberList();
	void member_auth_Delete(String userId);
	List<MemVO> selectList(String select);
	
}

