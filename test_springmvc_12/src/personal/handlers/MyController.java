package personal.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import personal.beans.Student;

@Controller	// 表示这是一个处理器
public class MyController {

	@RequestMapping(value="/my.do", method=RequestMethod.POST)	// 处理指定请求，可以是多个;指定提交方式post
	public ModelAndView doFirst(Student student) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "执行doFirst");
		mv.addObject("student", student );
		mv.setViewName("/WEB-INF/jsp/welcome.jsp");
		return mv;
	}

}
