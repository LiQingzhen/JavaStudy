package personal.handlers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import personal.beans.TempReport;
import personal.service.IReportService;
import personal.service.ITempReportService;

@Controller
public class ReportController {
	
	@Autowired
	@Qualifier("reportService")
	private IReportService reportService;
	
	@Autowired
	@Qualifier("tempReportService")
	private ITempReportService tempReportService;
	
	public IReportService getReportService() {
		return reportService;
	}

	public void setReportService(IReportService reportService) {
		this.reportService = reportService;
	}

	/**
	 * 打印成绩单
	 * @param sid
	 * @param eid
	 * @param path
	 * @return
	 */
	@RequestMapping(value = "/report.do", method = RequestMethod.POST)
	public String printReport(String sid, String eid, String path){
		reportService.exportReport(sid, eid, path);
		return "student/print.jsp";
	}
	
	/**
	 * 获取该学生所有成绩
	 * @param sid
	 * @param page 页码
	 * @param limit
	 * @return
	 */
	// @ResponseBody:当返回值不是View页面（而是其他数据如String,Json等）时使用；produces[处理中文编码]
	@RequestMapping(value = "/getReports.do", produces={"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
	@ResponseBody
	public String  getReports(int page, int limit, String sid){
		List<TempReport> tempReports = tempReportService.getReports(sid, (page-1)*limit, limit);
		JSONArray data = JSONArray.parseArray(JSON.toJSONString(tempReports));
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", reportService.getCountById(sid));
		result.put("code", 0);
		result.put("msg", "");
		result.put("data", data);
		JSONObject jsonObject = new JSONObject(result);
		
		return jsonObject.toJSONString();
	}
}
