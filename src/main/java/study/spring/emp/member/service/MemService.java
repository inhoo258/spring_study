package study.spring.emp.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import study.spring.emp.member.dao.IMemberRepository;
import study.spring.emp.member.model.MemVO;

@Service
public class MemService implements IMemService{
	
	@Autowired
	IMemberRepository memRepository;
	

	@Transactional("txManager")
	public void insertMember(MemVO member) {
		memRepository.insertMember(member);
		memRepository.insertAuth(member.getUserId());
	}

	@Override
	public MemVO getMember(String userId) {
		return memRepository.getMember(userId);
	}

	@Override
	public String getPassword(String userId) {
		return memRepository.getPassword(userId);
	}

	@Override
	public MemVO getInfo(String userId) {
		return memRepository.getInfo(userId);
	}

//	@Transactional("txManager")
	@Override
	public void updateMember(MemVO member) {
		memRepository.updateMember(member);
	}
	
	


}
