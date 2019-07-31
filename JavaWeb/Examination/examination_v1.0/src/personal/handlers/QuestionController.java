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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import personal.beans.Question;
import personal.service.IQuestionService;

@Controller
public class QuestionController {

	@Autowired
	@Qualifier("questionService")
	private IQuestionService questionService;
	
	public IQuestionService getQuestionService() {
		return questionService;
	}

	public void setQuestionService(IQuestionService questionService) {
		this.questionService = questionService;
	}

	/**
	 * 题目批量导入（Excle）
	 * @param path
	 * @return
	 */
	@RequestMapping(value="/import.do", produces={"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
	public ModelAndView importQuestions(String path){
		questionService.importQuestionsFromExcle(path);
		ModelAndView modelAndView = new ModelAndView("admin/questionImport.jsp");
		modelAndView.addObject("status", "success");
//		return "admin/questionImport.jsp";
		return modelAndView;
	}
	
	/**
	 * 题目信息分页展示
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping(value="/getQuestions.do", method = RequestMethod.POST, produces={"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
	@ResponseBody
	public String getQuestions(int page, int limit){
		List<Question> questions = questionService.getQuestions((page-1)*limit, limit);
		Question question = null;
		// 相关信息格式化处理
		for(int i=0; i<questions.size(); i++){
			question = questions.get(i);
			switch (question.getType()) {
			case "0":
				question.setType("选择题");
				questions.set(i, question);
				break;
			case "1":
				question.setType("填空题");
				questions.set(i, question);
				break;
			case "2":
				question.setType("判断题");
				questions.set(i, question);
				break;
			case "3":
				question.setType("编程题");
				questions.set(i, question);
				break;
			default:
				break;
			}
		}
		JSONArray data = JSONArray.parseArray(JSON.toJSONString(questions));
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", questionService.getCount());
		result.put("code", 0);
		result.put("msg", "");
		result.put("data", data);
		JSONObject jsonObject = new JSONObject(result);	
		
		return jsonObject.toJSONString();
	}
}
