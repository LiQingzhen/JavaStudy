package personal.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import personal.dao.ITempReportDao;
import personal.service.IReportService;
import personal.service.ITempReportService;

public class TestReportDao {

	private IReportService reportService;
	private ITempReportService tempReportService;
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		reportService = (IReportService) ac.getBean("reportService");
		tempReportService = (ITempReportService) ac.getBean("tempReportService");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		//tempReportService.getReports("2015551501");
	}

}
