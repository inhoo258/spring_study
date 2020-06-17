package study.spring.emp.hr.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import study.spring.emp.hr.dao.IEmpService;
import study.spring.emp.hr.model.EmpVO;

@Controller
//@RequestMapping("hr") // 이렇게 걸어주면 아래서 getmapping 에서 안걸어줘도댐
public class EmpController {
	// @RequestParm value에 기본값이 있고 없고가 있을때 쓰면좋음
	//
	@Autowired
	IEmpService empService;

//	@Autowired
//	EmpValidator validator1;
//	
//	@InitBinder
//	public void InitBinder(WebDataBinder binder) {
//		binder.setValidator(validator1);
//	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "hr/delete")
	public String empDelete(@RequestParam(value = "empId") int empId) {
		System.out.println(empId);
		empService.deleteDeptsManager(empId);
		empService.deleteMangerId(empId);
		empService.deleteEmp(empId);
		return "redirect:/hr/list";
	}
	// 삭제 메서드================================================================
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "hr/delete")
	public String empDalete(@RequestParam(value = "empId") int employeeId, Model model) {
		System.out.println(employeeId);
		model.addAttribute("emp", empService.getEmpInfo(employeeId));
		model.addAttribute("cnt", empService.get_managerCnt(employeeId));
		model.addAttribute("deptcnt", empService.get_deptCnt(employeeId));
		return "hr/delete";
	}

	// ========================================================================

	// 수정하는 메서드=============================================================
	@PostMapping(value = "hr/update")
	public String empUpdate(EmpVO emp) {
		empService.updateEmp(emp);
		return ("redirect:/hr/" + emp.getEmployeeId());
	}

	@GetMapping(value = "hr/update")
	public String empUpdate(Model model, @RequestParam(value = "empId") int employeeId) {
		model.addAttribute("emp", empService.getEmpInfo(employeeId));
		model.addAttribute("jobList", empService.getAllJobId());
		model.addAttribute("manList", empService.getAllManagerId());
		model.addAttribute("deptList", empService.getAllDeptId());
		model.addAttribute("message", "update");
		return "hr/insert";
	}

	// ========================================================================

	// id 클릭시 view 페이지 이동====================================================
	@GetMapping(value = "hr/{employeeId}")
	public String empView(Model model, @PathVariable int employeeId) {
		// pathvariable 경로를 int employeeId 에 저장하는대 변환 해서 넣어줌 int로
		model.addAttribute("emp", empService.getEmpInfo(employeeId));
		return "hr/view";
	}

	@GetMapping(value = "hr/insert") // 메소드의 경로
	public void empInsert(Model model) {
		model.addAttribute("emp", new EmpVO());
		model.addAttribute("jobList", empService.getAllJobId());
		model.addAttribute("manList", empService.getAllManagerId());
		model.addAttribute("deptList", empService.getAllDeptId());
		model.addAttribute("message", "insert");
//		return "hr/insert"; // 페이지명
	}

	// void 메소드의 경로 명이랑
	// return 의 view 페이지의 경로가 같으면 void로 정의해도됨
	// 굳이 String 으로 리턴해줄필요없음

	@PostMapping(value = "hr/insert")
	public String empInsert(@ModelAttribute("emp") @Valid EmpVO emp, BindingResult result, Model model) {
		if (result.hasErrors()) {
//			model.addAttribute("emp",emp);
//			model.addAttribute("errors" , result);	맵핑하면서 모델에서 알아서 저장이됨
			model.addAttribute("jobList", empService.getAllJobId());
			model.addAttribute("manList", empService.getAllManagerId());
			model.addAttribute("deptList", empService.getAllDeptId());
			model.addAttribute("message", "insert");
			return "hr/insert";
		}

		empService.insertEmp(emp);
		return "redirect:/hr/list";
	}
	// =========================================================================

	// 총 사원수 or 부서별 인원수======================================================
	@GetMapping(value = "hr/cnt") // 주소창에 입력되는 값
	public String empCount(@RequestParam(value = "deptId", required = false, defaultValue = "0") int deptId,
			Model model) { // model ->
		if (deptId == 0) {
			model.addAttribute("count", empService.getEmpCount());
		} else {
			model.addAttribute("count", empService.getEmpCount(deptId));
		}
		return "hr/count"; // .jsp 찾아가기위해 리턴값 넣어주는 곳
	}
	// =========================================================================

	// 부서별 총 인원수 =================================================
	@GetMapping(value = "hr/dept_cnt")
	public String deptCnt(Model model) {
		model.addAttribute("dept_cnt", empService.getDeptCount());
		return "hr/deptCnt";
	}
	// =============================================================

	// mainPage 띄우기 =====================================
	@GetMapping(value = "hr/mainPage")
	public String mainPage(Model model) {
		model.addAttribute("msglist", "list");
		model.addAttribute("msgmax", "max");
		model.addAttribute("msgavg", "avg");
		return "hr/mainPage";
	}
	// ===================================================

	// list 띄우기 =========================================
	@GetMapping(value = "hr/list")
	public void empList(Model model) {
		model.addAttribute("list", empService.getEmpList());
		model.addAttribute("msg", "list");
//		return "hr/list";
	}

	@GetMapping(value = "hr/max")
	public String maxsalary(Model model) {
		model.addAttribute("list", empService.getMaxSalary());
		model.addAttribute("msg", "max");
		return "hr/list";
	}

	@GetMapping(value = "hr/avg")
	public String avg(Model model) {
		model.addAttribute("list", empService.getdeptsalary_avg());
		model.addAttribute("msg", "avg");
		return "hr/list";
	}
	// ===================================================

	// ====================================================
	// RuntimeException 일때
	// 레포지토리에서 오류시 데이터엑세스오류가뜸
//	@ExceptionHandler(RuntimeException.class)
//	public String runtimeException(HttpServletRequest request, Exception ex, Model model) {
//		model.addAttribute("url", request.getRequestURI());
//		model.addAttribute("exception", ex);
//		return "error/runtime";
//	}
	

	// JSON ==========================================================

	@RequestMapping(value = "/hr/json/list")
	public @ResponseBody List<EmpVO> getAllEmployees() {
		List<EmpVO> empList = empService.getEmpList();
		return empList;
	}

	@RequestMapping(value = "/hr/json/{employeeId}")
	public @ResponseBody EmpVO getEmployees(@PathVariable int employeeId) {
		EmpVO emp = empService.getEmpInfo(employeeId);
		return emp;
	}

	// ===============================================================

}
