package personal.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import personal.service.SomeService;
import personal.service.SomeServiceImpl;

public class MyTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test01() {
		SomeService service = new SomeServiceImpl();
		service.doSomeService();
	}

	@Test
	public void test02() {
		// ctrl + T ：查看接口/类的继承关系
		// 创建容器对象 ；所有的Service层对象均注入到applicationContext.xml
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 【和test01对比】 只需知道接口，不必关系具体实现（即接口的实现类不会出现在View层的代码中），如此即实现了Service层和View层的解耦（将这里假设为为View层）
		SomeService service = (SomeService) ac.getBean("myService");
		service.doSomeService();
	}
	
	// ApplicationContext和BeanFactory的区别：
	// 1)ApplicationContext在创建容器的同时进行初始化——将applicationContext.xml文件中的所有bean初始化（在堆中new出来）
	// 		缺点：占用系统资源
	//		优点：响应速度快
	// 2)BeanFactory在使用到某个对象时才对该对象进行初始化工作
	//		优缺点与ApplicationContext的互补
	
	@Test
	public void test03() {
		// 使用BeanFactory容器
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		SomeService service = (SomeService) bf.getBean("myService");
		service.doSomeService();
	}
	
	@Test
	public void test04() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		SomeService service = (SomeService) ac.getBean("myService");
		SomeService service2 = (SomeService) ac.getBean("myService");
		System.out.println("service == service2 ?" + (service == service2)); // false
	}
}
