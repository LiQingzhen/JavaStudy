package personal.throws_advice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import personal.throws_advice.myexception.UserException;

public class MyTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test01() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("personal/throws_advice/applicationContext.xml");
		ISomeService someService = (ISomeService) ac.getBean("proxyService");
		try {
			// 正确
			someService.login("LiQingzhen", "123456");
		} catch (UserException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test02() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("personal/throws_advice/applicationContext.xml");
		ISomeService someService = (ISomeService) ac.getBean("proxyService");
		try {
			// 用户名异常
			someService.login("LiQingzhen01", "123456");
		} catch (UserException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test03() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("personal/throws_advice/applicationContext.xml");
		ISomeService someService = (ISomeService) ac.getBean("proxyService");
		try {
			// 密码异常
			someService.login("LiQingzhen", "1230456");
		} catch (UserException e) {
			e.printStackTrace();
		}
	}
}
