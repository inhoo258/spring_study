package study.spring.emp.member.dao;

import study.spring.emp.member.model.MemVO;

public interface IMemberRepository {
	void insertMember(MemVO mem);

	void insertAuth(String userId);

	MemVO getMember(String userId);

	String getPassword(String userId);
	
	MemVO getInfo(String userId);
	void updateMember(MemVO member);
}
