package personal.method_before_advice;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class MyMethodBeforeAdvice implements MethodBeforeAdvice {

	// 这是切面，spring通过代理将切面织入目标方法，（实现解耦和）
	// 前置通知：在目标方法执行前执行
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("前置通知！");
	}

}
