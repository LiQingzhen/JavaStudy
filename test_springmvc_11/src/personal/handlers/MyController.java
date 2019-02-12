package personal.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller	// 表示这是一个处理器
public class MyController {

	@RequestMapping(value={"/my.do", "/first.do"}, method=RequestMethod.POST)	// 处理指定请求，可以是多个;指定提交方式post
	public ModelAndView doFirst(@RequestParam("pname") String name, Integer age) {
		// @RequestParam 跟在对应方法参数前，将pname和name关联起来；如果请求中的参数名和方法中的参数相同，默认关联
		// Chrome会报错？
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "执行doFirst");
		mv.addObject("name", name);
		mv.addObject("age", age);
		mv.setViewName("/WEB-INF/jsp/welcome.jsp");
		return mv;
	}

	@RequestMapping("/second.do")	
	public ModelAndView doSecond(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "执行doSecond");
		mv.addObject("name", "xxx");
		mv.addObject("age", "xx");
		mv.setViewName("/WEB-INF/jsp/welcome.jsp");
		return mv;
	}
}
