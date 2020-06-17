package study.spring.emp.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import study.spring.emp.hr.model.EmpVO;

@Component
public class EmpValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return EmpVO.class.isAssignableFrom(clazz);
	}
	// 유효성 검사할 객체인지 아닌지 검증함
	// isAssignableFrom 로 상속이나 확장된 객체까지 확인해줌

	@Override
	public void validate(Object target, Errors errors) {
		EmpVO emp = (EmpVO) target;
		if (emp.getEmployeeId() < 207) {
			errors.rejectValue("employeeId", "emp.employeeId", "허용된 값(207)보다 큰값을 입력하세요.");
		}
		
		
	}
	// rejectValue 첫번째 멤버변수 , 폼에적은 값 , 어떤에러인지 적어줌

}