package personal.aspectj.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect // 表示将该类视作切面
public class MyAspect {

	/**
	 * 指示切面的切入点 切入点表达式：execution(* *..) *表示0至多个任意字符
	 * ..用在方法参数中表示任意多个参数；用在包名后表示当前包及其子包路径 +用在类名后，表示当前类及其子类；用在接口后，表示当前接口及其实现类
	 * 
	 */

	// 前置通知
	@Before(value = "execution(* *..ISomeService.doFirst(..))")
	public void before() {
		// joinPoint为切入点
		System.out.println("执行前置通知");
	}

	@Before(value = "execution(* *..ISomeService.doFirst(..))")
	public void before(JoinPoint joinPoint) {
		// joinPoint为切入点
		System.out.println("执行前置通知，joinPoint=" + joinPoint);
	}

	// 后置通知
	@AfterReturning(value = "execution(* *..ISomeService.doSecond(..))", returning = "result") // 可以获取到目标方法的返回值,但无法修改
	public void afterReturning(Object result) {
		System.out.println("执行后置通知，result=" + result);
	}

	// 环绕通知
	@Around(value = "execution(* *..ISomeService.doSecond(..))")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) {
		Object result = null;
		try {
			System.out.println("执行环绕通知，目标方法执行前");
			result = proceedingJoinPoint.proceed(); // 可获取并修改目标方法的返回值
			System.out.println("执行环绕通知，目标方法执行后");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return result;
	}

	// 异常通知
	@AfterThrowing(value = "execution(* *..ISomeService.doThird(..))", throwing = "exception")
	public void afterThrowing(Exception exception) {
		System.out.println("执行异常通知，exception=" + exception.getMessage());
	}

	// 最终通知
	@After(value = "execution(* *..ISomeService.doThird(..))")
	public void after() {
		System.out.println("执行最终通知");
	}
}
