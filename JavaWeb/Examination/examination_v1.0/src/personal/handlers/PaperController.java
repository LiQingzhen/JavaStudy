package personal.handlers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import personal.beans.Exam;
import personal.beans.Paper;
import personal.beans.Question;
import personal.beans.TempChoice;
import personal.service.IExamService;
import personal.service.IPaperService;

@Controller
public class PaperController {

	@Autowired
	@Qualifier("paperService")
	IPaperService paperService;
	@Autowired
	@Qualifier("examService")
	IExamService examService;
	
	@RequestMapping(value = "/exportPaper.do", method = RequestMethod.POST, produces={"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
	@ResponseBody
	public String exportDocx(Integer id){
		paperService.exportPaperById(id, "E:/");
		return "success";
	}
	
	/**
	 * 自动组卷 并入库；返回试卷编号
	 * @param epoint
	 * @param ep
	 * @param cs
	 * @param ca
	 * @param bs
	 * @param ba
	 * @param js
	 * @param ja
	 * @param ps
	 * @param pa
	 * @return
	 */
	@RequestMapping(value = "/createPaper.do", method = RequestMethod.POST, produces={"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
	@ResponseBody
 	public String createPaper(String epoint, String ep, String cs, String ca, String bs, String ba, String js, String ja, String ps, String pa){
		// 参数格式化
		int sum = 0;
		sum = Integer.parseInt(cs)*Integer.parseInt(ca)+Integer.parseInt(bs)*Integer.parseInt(ba)+Integer.parseInt(js)*Integer.parseInt(ja)+Integer.parseInt(ps)*Integer.parseInt(pa);
		System.out.println(sum);
		if(sum != 100)
			return "总分不是100";
		String[] array = epoint.split(",");
		Set<String> points = new HashSet<String>();
		for(int i=0; i<array.length; i++){
			points.add(array[i]);
		}
		// 组卷
		Paper paper = paperService.createPaper(points, Double.parseDouble(ep), Double.parseDouble(cs), Integer.parseInt(ca), Double.parseDouble(bs), Integer.parseInt(ba), Double.parseDouble(js), Integer.parseInt(ja), Double.parseDouble(ps), Integer.parseInt(pa));
		// 入库
		paperService.addPaper(paper);
		paper = null;
		// 返回试卷编号
		return ""+paperService.getNewPaperId();
	}
	
	/**
	 * 学生端，试卷信息分页展示
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping(value="/getPapersInfo.do", method = RequestMethod.POST, produces={"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
	@ResponseBody
	public String getPapers(int page, int limit, @RequestParam(value="flag", required=false) Integer flag){
		List<Paper> papers = paperService.getPapersInfo((page-1)*limit, limit, flag);
		JSONArray data = JSONArray.parseArray(JSON.toJSONString(papers));
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", paperService.getCount(flag));
		result.put("code", 0);
		result.put("msg", "");
		result.put("data", data);
		JSONObject jsonObject = new JSONObject(result);
		
		return jsonObject.toJSONString();
	}
	
	/**
	 * 管理员端 试卷信息展示
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping(value="/getPapersInfo2.do", method = RequestMethod.POST, produces={"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
	@ResponseBody
	public String getPapers(int page, int limit){
		List<Paper> papers = paperService.getPapersInfo2((page-1)*limit, limit);
		JSONArray data = JSONArray.parseArray(JSON.toJSONString(papers));
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", paperService.getCount2());
		result.put("code", 0);
		result.put("msg", "");
		result.put("data", data);
		JSONObject jsonObject = new JSONObject(result);
		
		return jsonObject.toJSONString();
	}
	
	/**
	 * 页面跳转-模拟考试页面
	 * @param id 试卷id
	 * @return
	 */
	// 不指定method 即支持get又支持post
	@RequestMapping(value="/practisePaper.do", produces={"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
	public ModelAndView practiseExam(Integer id, String examId){
		Question question = null;
		ModelAndView mv = new ModelAndView();
		mv.setViewName("exam/exam.jsp");
		String time = "02:00:00";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		System.out.println(examId);
		if(!"no".equals(examId)){
			Exam exam = examService.getExamById(examId);
			Long end = null;
			try {
				end = dateFormat.parse(exam.getStart()).getTime() + Integer.parseInt(exam.getTime()) * 60 * 1000;
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Long nowTime = new Date(System.currentTimeMillis()).getTime();
			Long temp = (end - nowTime) / 1000;
			Long hour = null;
			Long min = null;
			Long second = null;
			// 小时
			hour = temp / 3600;
			time = "0"+hour.toString()+":";
			// 分钟
			min = (temp - hour * 3600) / 60;
			if(min.toString().length() == 1)
				time = time + "0"+ min.toString()+":";
			else
				time = time + min.toString()+":";
			// 秒
			second = temp - hour * 3600 - min * 60;
			if(second.toString().length() == 1)
				time = time + "0"+ second.toString();
			else
				time = time + second.toString();
		}
				
		// 题目信息的提取
		List<TempChoice> tempChoices = new ArrayList<TempChoice>();
		List<Question> choices = paperService.getPaperChoice(id);
		List<Question> blanks = paperService.getPaperBlank(id);
		List<Question> judges = paperService.getPaperJudge(id);
		for(int i = 0; i < choices.size(); i++){
			TempChoice tempChoice = new TempChoice();
			question = choices.get(i);			
			String details = question.getDetails();
			// 解析选择题 题干-选项
			tempChoice.setDetails(details.substring(0, details.indexOf("!@#$%")));
			tempChoice.setOptionA(details.substring(details.indexOf("A."), details.indexOf("B.")).substring(2));
			tempChoice.setOptionB(details.substring(details.indexOf("B."), details.indexOf("C.")).substring(2));
			tempChoice.setOptionC(details.substring(details.indexOf("C."), details.indexOf("D.")).substring(2));
			tempChoice.setOptionD(details.substring(details.indexOf("D.")).substring(2));
			tempChoice.setAnswer(question.getAnswer());
			
			tempChoices.add(tempChoice);
		}
		
		mv.addObject("choices", tempChoices);
		mv.addObject("blanks", blanks);
		mv.addObject("judges", judges);
		mv.addObject("time", time);
		mv.addObject("examId", examId);
		return mv;	
	}
}
