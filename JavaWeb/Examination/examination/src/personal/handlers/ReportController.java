package personal.handlers;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import personal.service.IReportService;

@Controller
public class ReportController {

	@Resource(name="reportService")
	private IReportService reportService;
	
	@RequestMapping("/report.do")
	public void printReport(String sid, String eid, String path){
		reportService.exportReport(sid, eid, path);
	}
}
