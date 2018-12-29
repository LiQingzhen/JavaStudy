import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {

	public static void main(String[] args) {
		Subject subject = new SubjectImp();
		
		// 只能代理实例化对象
		Subject subjectProxy = (Subject) Proxy.newProxyInstance(
				subject.getClass().getClassLoader(), 
				subject.getClass().getInterfaces(), 
				new InvocationHandler() {
					
					// proxy:指代被代理的目标类对象
					// method:指代被代理的目标类对象的方法
					// 存储方法中要用的参数
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						System.out.println("before rent house");
						System.out.println("Method:" + method);
						
						method.invoke(subject, args);
						
						System.out.println("after rent house");
						return null;
					}
				});
		
		// 这是系统返回的代理类名
		System.out.println(subjectProxy.getClass().getName() + "\n");
		
		// 动态代理本质上是通过代理对象调用目标类对象的方法，甚至是对这些方法做扩展、增强
		// Proxy.newProxyInstance返回的代理对象在调用方法时默认调用其中InvocationHandler对象的invoke()
		// 找到要目标类对象的方法，并传递相关参数，使用method.invoke(subject, args)调用目标类对象，也就是subject的方法
		subjectProxy.rent();
		System.out.println();
		subjectProxy.hello("Tim");

	}

}
