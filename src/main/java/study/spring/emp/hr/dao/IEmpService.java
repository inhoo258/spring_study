package study.spring.emp.hr.dao;

import java.util.List;
import java.util.Map;

import study.spring.emp.hr.model.DeptVO;
import study.spring.emp.hr.model.EmpVO;

public interface IEmpService {

	int getEmpCount();
	int getEmpCount(int deptId);
	List<EmpVO> getEmpList();
	EmpVO getEmpInfo(int empId);
	void updateEmp(EmpVO emp);
	void insertEmp(EmpVO emp);
	void deleteEmp(int empId);
	void deleteJobHistory(int empId);
	List<Map<String, Object>> getAllDeptId();
	List<Map<String, Object>> getAllJobId();
	List<Map<String, Object>> getAllManagerId();
	List<Map<String , Object>> getDeptCount();
	List<Map<String, Object>> getMax_salary();
	
	List<DeptVO> getEmpName();
	List<EmpVO> getName(String name);
	List<EmpVO> getMaxSalary();

	List<Map<String, Object>> getName(int number);
	
	int get_managerCnt(int empId);
	int get_deptCnt(int empId);
	
	void deleteDeptsManager(int empId);
	void deleteMangerId(int empId);
	
	List<EmpVO> getdeptsalary_avg();
	List<EmpVO> getPlusInfo(int empId);
}
