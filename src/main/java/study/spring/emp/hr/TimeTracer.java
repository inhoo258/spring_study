package study.spring.emp.hr;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeTracer {
	@Pointcut(value = "execution(* study..EmpService.*(..))")
	private void servicePointcut() {};
	
	@Around("servicePointcut()")
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("TimeTracer 실행");
		Signature s = joinPoint.getSignature();
		String methodName = s.getName();
		System.out.println("[Log]Before: " + methodName + " time check start");
		long startTime = System.nanoTime();
		Object result = null;

//		try {
			result = joinPoint.proceed(); // joinpoint 실행 HelloService say메소드 실행해줌
			System.out.println(result);
//		} catch (Exception e) {
//			System.out.println("[Log]Exception: " + e.getMessage());
//		} finally {
			System.out.println("[Log]Finally: " + methodName + " End.");
//		}
		long endTime = System.nanoTime();
		System.out.println("[Log]After: " + methodName + " time check end");
		System.out.println("[Log]" + methodName + "processing time is " + (endTime - startTime) + "ns");
		System.out.println("TimeTracer 종료");
		return result;

	}
}
