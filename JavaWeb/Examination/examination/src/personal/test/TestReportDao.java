package personal.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import personal.service.IReportService;

public class TestReportDao {

	private IReportService reportService;
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		reportService = (IReportService) ac.getBean("reportService");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		reportService.exportReport("2015551122", "2019123401", "E:/");
	}

}
