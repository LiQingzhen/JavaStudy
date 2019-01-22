package personal.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import personal.service.ServiceFactory;
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
		// 使用工厂模式解耦和
		SomeService service = new ServiceFactory().getSomeService();
		service.doSomeService();
	}

	@Test
	public void test02() {
		// Spring中的工厂模式
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		SomeService service = (SomeService) ac.getBean("myService");
		service.doSomeService();
	}
	
}
