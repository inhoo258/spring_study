package study.spring.emp.member.service;

import study.spring.emp.member.model.MemVO;

public interface IMemService {
	void insertMember(MemVO member);	
	MemVO getMember(String userId);
	String getPassword(String userId);
	MemVO getInfo(String userId);	
	void updateMember(MemVO member);
}
