package personal.method_interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyMethodInterceptor implements MethodInterceptor {

	// 环绕通知：目标方法前后都执行
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("前");
		Object result = invocation.proceed();
		System.out.println("后");
		// 此处可以修改目标方法的返回值
		if (result != null) {
			result = ((String) result).toUpperCase();
		}
		return result;
	}

}
