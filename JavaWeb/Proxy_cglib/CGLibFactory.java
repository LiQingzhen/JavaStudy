import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CGLibFactory implements MethodInterceptor {
	
	private Object object;
	
	public CGLibFactory(Object object) {
		this.object = object;
	}

	public Object createProxy(){
		// 创建增强器
		Enhancer enhancer = new Enhancer();
		// CGLib实现动态代理原理是利用目标类的子类做代理，所以要创建代理必须知道其父类
		enhancer.setSuperclass(object.getClass());
		// 设置回调接口对象
		enhancer.setCallback(this);
		// 返回代理对象
		return enhancer.create();
	}

	// 当使用创建出的代理对象调用方法时，隐式地调用intercept()
	// 一开始其中的参数名不够直观，最好是按住Ctrl键鼠标单击MethodInterceptor连接源码
	// cglib源码下载：https://github.com/cglib/cglib
	// cglib源码本地：cglib-master.zip
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("before rent house");
		System.out.println("Method:" + method);
		
		method.invoke(object, args);
		
		System.out.println("after rent house");
		return null;
	}

}
