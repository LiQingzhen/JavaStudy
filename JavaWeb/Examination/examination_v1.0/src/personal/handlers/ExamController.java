package personal.handlers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import personal.beans.Exam;
import personal.beans.Report;
import personal.service.IExamService;
import personal.service.IReportService;

@Controller
public class ExamController {

	@Autowired
	@Qualifier("examService")
	private IExamService examService;
	@Autowired
	@Qualifier("reportService")
	private IReportService reportService;
	
	/**
	 * 考试信息分页展示
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getExamsInfo.do", method = RequestMethod.POST, produces={"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
	@ResponseBody
	public String getExamsInfo(int page, int limit){
		List<Exam> exams = examService.getExamsInfo((page-1)*limit, limit);
		
		JSONArray data = JSONArray.parseArray(JSON.toJSONString(exams));
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", examService.getCount());
		result.put("code", 0);
		result.put("msg", "");
		result.put("data", data);
		JSONObject jsonObject = new JSONObject(result);
		
		return jsonObject.toJSONString();
	}
	
	/**
	 * 创建考试信息
	 * @param name
	 * @param start
	 * @param time
	 * @param paper
	 * @return
	 */
	@RequestMapping(value = "/createExam.do", method = RequestMethod.POST, produces={"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
	@ResponseBody
	public String createExam(String name, String start, String time, String paper){
		// 信息入库时间作为exam_id
		Exam exam = new Exam();
		exam.setName(name);
		exam.setStart(start);
		exam.setTime(time);
		exam.setScore(100.0);	// 将分数写死为100分
		exam.setPaper(paper);
		
		examService.addExam(exam);		
		exam = null;
		return "success";
	}
	
	@RequestMapping(value = "/joinPaper.do", method = RequestMethod.POST, produces={"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
	@ResponseBody
	public String exam(String start, String time){
		Date date = null;
		Long nowTime = null;
		Long left = null;
		Long right = null;
		Integer temp = Integer.parseInt(time);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			// 日期格式String --> Date --> Long 方便比较
			date = dateFormat.parse(start);
			left = date.getTime();	// 考试开始时间
			right = left + temp * 60 * 1000;	// 考试结束时间
			
			// 获取当前时间long
			nowTime = new Date(System.currentTimeMillis()).getTime();
			
			// 判断能否进入考试
			if (nowTime < left) {
				return "early";
			}else if(nowTime < right){
				return "ok";
			}			
		} catch (ParseException e) {
			e.printStackTrace();
		}finally {
			date = null;
			nowTime = null;
			left = null;
			right = null;
			temp = null;
		}
		return "late";
	}
	
	@RequestMapping(value = "/markPaper.do", produces={"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
	@ResponseBody
	public String mark(HttpServletRequest request){
		Double score = 0.0;
		String answer = null;
		String yourAnswer = null;
		String examId = request.getParameter("examId");
		// 选择题
		for(int i = 1; i <= 20; i++){
			answer = request.getParameter("choice_answer_"+i);
			yourAnswer = request.getParameter("choice_"+i);
			if(yourAnswer != null && yourAnswer.equals(answer)){
				score += 2.0;
			}
		}
		// 判断题judge
		answer = null;
		yourAnswer = null;
		for(int i = 1; i <= 10; i++){
			answer = request.getParameter("judge_answer_"+i);
			yourAnswer = request.getParameter("judge_"+i);
			if(yourAnswer != null && yourAnswer.equals(answer)){
				score += 1.0;
			}
		}
		// 填空题
		for(int i = 1; i <= 10; i++){
			answer = request.getParameter("blank_answer_"+i);
			yourAnswer = request.getParameter("blank_"+i);
			if(yourAnswer != null && yourAnswer.equals(answer)){
				score += 2.0;
			}
		}
		// 正式考试
		if(!"no".equals(examId)){
			Report report = new Report();
			report.setEid(examId);
			report.setSid(request.getParameter("stuId"));
			report.setScore(score);
			reportService.addReport(report);
		}
		
//		System.out.println(score);
		// 编程题
		return ""+score;
	}
}
