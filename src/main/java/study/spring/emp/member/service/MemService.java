package study.spring.emp.member.service;

import java.util.List;

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

	@Transactional("txManager")
	@Override
	public void updateMember(MemVO member) {
		memRepository.updateMember(member);
	}

	@Transactional("txManager")
	@Override
	public void enabledupdate(String enabled, String userId) {
		memRepository.enabledupdate(enabled, userId);
	}

	@Override
	public List<MemVO> memberList() {
		return memRepository.memberList();
	}

	@Transactional("txManager")
	@Override
	public void masterUpdate(MemVO member) {
		memRepository.updateMember(member);
		memRepository.masterUpdate(member);
	}

	@Transactional("txManager")
	@Override
	public void member_auth_Delete(String userId) {
		memRepository.authDelete(userId);
		memRepository.memberDelete(userId);
	}

	@Override
	public List<MemVO> selectList(String select) {
		return memRepository.selectList(select);
	}

	
	


}
