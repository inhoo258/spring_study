package study.spring.emp.hr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import study.spring.emp.hr.model.DeptVO;
import study.spring.emp.hr.model.EmpVO;

@Repository
public class EmpRepository implements IEmpRepository {
	
	@Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbcTemplate;

	private class EmpMapper implements RowMapper<EmpVO> {
		public EmpVO mapRow(ResultSet rs, int count) throws SQLException {
			EmpVO emp = new EmpVO();
			emp.setEmployeeId(rs.getInt("employee_id"));
			emp.setFirstName(rs.getString("first_name"));
			emp.setLastName(rs.getString("last_name"));
			emp.setEmail(rs.getString(4));
			emp.setPhoneNumber(rs.getString(5));
			emp.setHireDate(rs.getDate(6));
			emp.setJobId(rs.getString(7));
			emp.setSalary(rs.getDouble(8));
			emp.setCommissionPct(rs.getDouble(9));
			emp.setManagerId(rs.getInt(10));
			emp.setDepartmentId(rs.getInt(11));
			return emp;
		}
	}

	@Override
	public int getEmpCount() {
		String sql = "select count(*) from employees";
		return jdbcTemplate.queryForObject(sql, Integer.class); // Integer.class 래퍼클래스
		// queryForObject 결과값이 하나일때 사용 (하나의 객체를 리턴할때)
	}

	@Override
	public int getEmpCount(int deptId) {
		String sql = "select count(*) from employees where department_id=?";
		return jdbcTemplate.queryForObject(sql, Integer.class, deptId);
	}

	@Override
	public List<EmpVO> getEmpList() {
		String sql = "select * from employees";
		return jdbcTemplate.query(sql, new EmpMapper());
	}

	@Override
	public EmpVO getEmpInfo(int empId) {
		String sql = "select * from employees where employee_id=?";
		return jdbcTemplate.queryForObject(sql, new EmpMapper(), empId);
	}

	@Override
	public void deleteEmp(int empId) {
		String sql = "delete from employees where employee_id=?";
		jdbcTemplate.update(sql, empId);
	}

	@Override
	public void deleteJobHistory(int empId) {
		String sql = "delete from job_history where employee_id=?";
		jdbcTemplate.update(sql, empId);
	}

	@Override
	public void updateEmp(EmpVO emp) {
		String sql = "update employees set first_name=?, last_name=?,"
				+ "email=?, phone_number=?, hire_date=?, job_id=?," + "salary=?, commission_pct=?, manager_id=?,"
				+ "department_id=? where employee_id=?";
		jdbcTemplate.update(sql, emp.getFirstName(), emp.getLastName(), emp.getEmail(), emp.getPhoneNumber(),
				emp.getHireDate(), emp.getJobId(), emp.getSalary(), emp.getCommissionPct(), emp.getManagerId(),
				emp.getDepartmentId(), emp.getEmployeeId());
	}

	@Override
	public void insertEmp(EmpVO emp) {
		String sql = "insert into employees " + "values(?,?,?,?,?,sysdate,?,?,?,?,?)";
		jdbcTemplate.update(sql, emp.getEmployeeId(), emp.getFirstName(), emp.getLastName(), emp.getEmail(),
				emp.getPhoneNumber(), emp.getJobId(), emp.getSalary(), emp.getCommissionPct(), emp.getManagerId(),
				emp.getDepartmentId());
	}

	@Override
	public List<Map<String, Object>> getAllDeptId() {
		String sql = "select department_id as departmentId, department_name as departmentName from departments";
		return jdbcTemplate.queryForList(sql);
		// queryForList 결과가 2개만 나올때 자주 사용함 ..
	}

	@Override
	public List<Map<String, Object>> getAllJobId() {
		String sql = "select job_id as jobId, job_title as jobTitle from jobs";
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public List<Map<String, Object>> getAllManagerId() {
		String sql = "select employee_id as managerId, first_name||' '||last_name as managerName "
				+ "from employees where employee_id in (select distinct manager_id from employees)";
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public List<DeptVO> getDeptName() {
		String sql = "select * from departments";
		return jdbcTemplate.query(sql, new RowMapper<DeptVO>() {

			@Override
			public DeptVO mapRow(ResultSet rs, int count) throws SQLException {
				DeptVO dept = new DeptVO();
				dept.setDepartmentId(rs.getInt("department_id"));
				dept.setDepartmentName(rs.getString("department_name"));
				dept.setManagerId(rs.getInt(3));
				dept.setLocationId(rs.getInt(4));
				return dept;
			}
		});
	}

	@Override
	public List<Map<String, Object>> getMax_salary() {
		String sql = "select job_id, max_salary from jobs";
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public List<EmpVO> getName(String name) {
		String sql = "select * from employees where upper(first_name || last_name) like upper('%" + name + "%')";
		return jdbcTemplate.query(sql, new EmpMapper());
	}

	@Override
	public List<Map<String, Object>> getName(int number) {
		String sql = "select first_name||' '||last_name as name from employees where employee_id = " + number;
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public List<Map<String, Object>> getDeptCount() {
		String sql = "select department_id as DeptId , count(*) as cnt from employees group by department_id order by DeptId";
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public List<EmpVO> getMaxSalary() {
		String sql = "select * FROM EMPLOYEES where (salary , DEPARTMENT_ID) in (select max(salary) ,department_id from employees group by DEPARTMENT_ID)";
		return jdbcTemplate.query(sql, new EmpMapper());
	}

	@Override
	public int get_managerCnt(int empId) {
		String sql = "select count(*) as cnt from EMPLOYEES where MANAGER_ID=" + empId;
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public int get_deptCnt(int empId) {
		String sql = "select count(*) as deptcnt from DEPARTMENTS where MANAGER_ID=" + empId;
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	// from dual

	@Override
	public void deleteDeptsManager(int empId) {
		String sql = "UPDATE DEPARTMENTS set MANAGER_ID = null where MANAGER_ID = " + empId;
	}

	@Override
	public void deleteMangerId(int empId) {
		String sql = "UPDATE employees set MANAGER_ID = null where MANAGER_ID = " + empId;

	}

	@Override
	public List<EmpVO> getdeptsalary_avg()	 {
		String sql = "select emp.EMPLOYEE_ID , emp.first_name , emp.last_name , emp.email , emp.phone_number , emp.hire_date , emp.job_id , emp.salary , emp.commission_pct , emp.manager_id , emp.department_id  from (select department_id as deptid ,  avg(salary) as avg from employees group by department_id) avgs , employees emp where avgs.deptid = emp.department_id and emp.salary > avgs.avg order by department_id asc";
		return jdbcTemplate.query(sql, new EmpMapper()); 
	}

	@Override
	public List<EmpVO> getPlusInfo(int empId) {
		String sql = "select emp.EMPLOYEE_ID as employee_id , emp.FIRST_NAME , emp.LAST_NAME , emp.EMAIL , emp.PHONE_NUMBER , emp.HIRE_DATE , job.JOB_TITLE , emp.JOB_ID , emp.SALARY , emp.COMMISSION_PCT , manager_name , emp.MANAGER_ID , depts.DEPARTMENT_NAME , emp.DEPARTMENT_ID from EMPLOYEES emp left join jobs job on emp.job_id = job.JOB_ID left join departments depts on emp.DEPARTMENT_ID = depts.DEPARTMENT_ID, (select first_name||' '||last_name as manager_name from employees where employee_id = (select manager_id from EMPLOYEES where employee_id = "+ empId +")) where employee_id = " +empId;
			
		return jdbcTemplate.query(sql, new RowMapper<EmpVO>() {

			@Override
			public EmpVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				EmpVO emp = new EmpVO();
				emp.setEmployeeId(rs.getInt(1));
				emp.setFirstName(rs.getString(2));
				emp.setLastName(rs.getString(3));
				emp.setEmail(rs.getString(4));
				emp.setPhoneNumber(rs.getString(5));
				emp.setHireDate(rs.getDate(6));
				emp.setJobId(rs.getString(7)+"("+rs.getString(8)+")");
				emp.setSalary(rs.getDouble(9));
				emp.setCommissionPct(rs.getDouble(10));
				emp.setManagerId(rs.getString(11) + "(" + rs.getInt(12) + ")");
				emp.setDepartmentId(rs.getString(13) + "(" + rs.getInt(14) +")");
				return emp;
			}
		});
	}

}
