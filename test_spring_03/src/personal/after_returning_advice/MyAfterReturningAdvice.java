package personal.after_returning_advice;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class MyAfterReturningAdvice implements AfterReturningAdvice {

	// 后置通知：目标方法执行后执行
	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("后置通知！");
		if(returnValue != null){
			// 参数returnValue是目标方法的返回值，切片中无法修改该返回值
			// 所以控制台的输出仍然是小写的
			returnValue = ((String) returnValue).toUpperCase();
			System.out.println("returnValue = " + returnValue);
		}
	}

}
