package study.spring.emp.hr.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import study.spring.emp.hr.model.DeptVO;
import study.spring.emp.hr.model.EmpVO;
@Service
public class EmpService implements IEmpService {
	//트랜잭션은 서비스 에서 걸어줌
	@Autowired
	IEmpRepository empRepository;

	@Override
	public int getEmpCount() {
		return empRepository.getEmpCount();
	}

	@Override
	public int getEmpCount(int deptId) {
		return empRepository.getEmpCount(deptId);
	}

	@Override
	public List<EmpVO> getEmpList() {
		return empRepository.getEmpList();
	}

	@Override
	public EmpVO getEmpInfo(int empId) {
		return empRepository.getEmpInfo(empId);
	}

	@Transactional("txManager")
	@Override
	public void updateEmp(EmpVO emp) {
		empRepository.deleteJobHistory(emp.getEmployeeId());	//트랜잭션 - 추가
		empRepository.updateEmp(emp);
	}

	@Transactional("txManager")
	@Override
	public void insertEmp(EmpVO emp) {
		empRepository.insertEmp(emp);
	}
	
	@Transactional("txManager")
	@Override
	public void deleteEmp(int empId) {
		empRepository.deleteJobHistory(empId);		//트랜잭션 - 추가
		empRepository.deleteEmp(empId);
	}

	@Override
	public void deleteJobHistory(int empId) {
		empRepository.deleteJobHistory(empId);
	}

	@Override
	public List<Map<String, Object>> getAllDeptId() {
		return empRepository.getAllDeptId();
	}

	@Override
	public List<Map<String, Object>> getAllJobId() {
		return empRepository.getAllJobId();
	}

	@Override
	public List<Map<String, Object>> getAllManagerId() {
		return empRepository.getAllManagerId();
	}

	@Override
	public List<DeptVO> getEmpName() {
		return empRepository.getDeptName();
	}

	@Override
	public List<Map<String, Object>> getMax_salary() {
		return empRepository.getMax_salary();
	}
//	@Transactional("txManager")	확인용
	@Override
	public List<EmpVO> getName(String name) {
		return empRepository.getName(name);
	}

	@Override
	public List<Map<String, Object>> getName(int number) {
		return empRepository.getName(number);
	}

	@Override
	public List<Map<String, Object>> getDeptCount() {
		return empRepository.getDeptCount();
	}

	@Override
	public List<EmpVO> getMaxSalary() {
		return empRepository.getMaxSalary();
	}

	@Override
	public int get_managerCnt(int empId) {
		return empRepository.get_managerCnt(empId);
	}

	@Override
	public int get_deptCnt(int empId) {
		return empRepository.get_deptCnt(empId);
	}

	@Transactional("txManager")
	@Override
	public void deleteDeptsManager(int empId) {
		empRepository.deleteJobHistory(empId);
		empRepository.deleteDeptsManager(empId);
		
	}
	
	@Transactional("txManager")
	@Override
	public void deleteMangerId(int empId) {
		empRepository.deleteJobHistory(empId);
		empRepository.deleteMangerId(empId);
		
	}

	@Override
	public List<EmpVO> getdeptsalary_avg() {
		return empRepository.getdeptsalary_avg();
	}

	@Override
	public List<EmpVO> getPlusInfo(int empId) {
		return empRepository.getPlusInfo(empId);
	}

	
}
