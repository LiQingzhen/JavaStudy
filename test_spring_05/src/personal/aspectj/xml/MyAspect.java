package personal.aspectj.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

// 切面
public class MyAspect {

	// 前置通知
	public void before() {
		System.out.println("执行前置通知");
	}

	public void before(JoinPoint joinPoint) {
		// joinPoint为切入点
		System.out.println("执行前置通知，joinPoint=" + joinPoint);
	}

	// 后置通知
	public void afterReturning(Object result) {
		System.out.println("执行后置通知，result=" + result);
	}

	// 环绕通知
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
	public void afterThrowing(Exception exception) {
		System.out.println("执行异常通知，exception=" + exception.getMessage());
	}

	// 最终通知
	public void after() {
		System.out.println("执行最终通知");
	}
}
