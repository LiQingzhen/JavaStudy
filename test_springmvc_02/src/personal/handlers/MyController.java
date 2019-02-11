package personal.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class MyController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		return new ModelAndView("mine"); // 访问http://localhost:8080/test_springmvc_01/my.do 会跳转到内部视图资源对象
		return new ModelAndView("baidu");	// 跳转到百度
	}

}
