package study.spring.emp.hr;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import study.spring.emp.hr.dao.IEmpService;

public class EmpMain {
	public static void main(String[] args) {
		AbstractApplicationContext context = new GenericXmlApplicationContext("app-config.xml");
		IEmpService empService = context.getBean("empService", IEmpService.class);
//		System.out.println(empService.getEmpCount());
		System.out.println(empService.getEmpCount(30));
//		System.out.println(empService.getEmpList());
//		System.out.println(empService.getAllManagerId());
//		System.out.println(empService.getEmpName());
//		System.out.println(empService.jobsId());
//		System.out.println(empService.getName("st"));
//		System.out.println(empService.getName(104));
		
//		StandardPBEStringEncryptor enc = new StandardPBEStringEncryptor();
//		enc.setPassword("leeinho");
		
//		여기는 한번쓰고 지워도 상관없음 jdbc properties에 한번적어주면 지우면됨!!
//		System.out.println(enc.encrypt("jdbc:log4jdbc:oracle:thin:@localhost:1521/xepdb1"));
//		System.out.println(enc.encrypt("hr"));
//		System.out.println(enc.encrypt("hr"));
		
		

		context.close();

	}
}
