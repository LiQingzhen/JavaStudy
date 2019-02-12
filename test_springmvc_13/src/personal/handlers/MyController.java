package personal.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller	// 表示这是一个处理器
public class MyController {
/*
	@RequestMapping(value={"/my.do", "/first.do"})	
	public String doFirst() {
		// 返回值为ModelAndView已经接触，当请求经处理器处理后没有数据，仅仅是页面的跳转
		// 此时可以规定返回值为String
		// 框架默认将其作为"内部视图资源名"，并跳转到该视图
		// 注意：使用该方法时要将springmvc.xml中的视图解析器删掉
		return "/WEB-INF/jsp/welcome.jsp";
	}
*/
	@RequestMapping("/second.do")	
	public String doSecond() {
		// 内外部视图资源均可以跳转到
//		return "mine";
		return "baidu";
	}
}
