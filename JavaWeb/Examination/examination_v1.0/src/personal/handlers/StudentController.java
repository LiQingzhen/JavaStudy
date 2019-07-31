package personal.handlers;

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

import personal.beans.Student;
import personal.service.IStudentService;

@Controller
public class StudentController {

	@Autowired
	@Qualifier("studentService")
	private IStudentService studentService;
	
	/**
	 * 用户登录处理（student/admin）
	 * @param id
	 * @param pwd
	 * @return
	 */
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	@ResponseBody
	public String login(String id, String pwd, HttpServletRequest request){
		
		if("admin".equals(id) && "123456".equals(pwd)){
			// 管理员登录验证
			return "admin";		
		}else{
			// 考生登录验证
			Student student = studentService.findStudentById(id);
			if(student != null && pwd.equals(student.getPwd())){
				request.getSession().setAttribute("user", student.getName());
				request.getSession().setAttribute("id", id);
				return "student";
			}
			return "error";
		}		
	}
	
	/**
	 * 考生注册(管理员端)
	 * @param name
	 * @param id
	 * @param pwd
	 * @return
	 */
	@RequestMapping(value = "/signUp.do", method = RequestMethod.POST)
	@ResponseBody
	public String signUp(String name, String id, String pwd){
		Student student = new Student();
		student.setId(id);
		student.setPwd(pwd);
		student.setName(name);
		studentService.addStudent(student);
		return "success";
	}
	
	/**
	 * 注册-学生端
	 * @param name
	 * @param id
	 * @param pwd
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/signUp1.do", method = RequestMethod.POST)
	@ResponseBody
	public String signUp1(String name, String id, String pwd, HttpServletRequest request){
		Student student = new Student();
		student.setId(id);
		student.setPwd(pwd);
		student.setName(name);
		studentService.addStudent(student);
		request.getSession().setAttribute("user", name);
		request.getSession().setAttribute("id", id);
		return "success";
	}
	
	/**
	 * 页面跳转-考生注册/登录成功
	 * @return
	 */
	@RequestMapping(value = "/signUpSuccess.do", method = RequestMethod.GET)
	public String loginSuccess(){
		return "student/main.jsp";
	}
	
	/**
	 * 页面跳转-管理员登录成功
	 * @return
	 */
	@RequestMapping(value = "/loginSuccess.do", method = RequestMethod.GET)
	public String signUpSuccess(){
		return "admin/main.jsp";
	}
	
	/**
	 * 管理员重置考生密码
	 * @param id
	 * @param name
	 * @param pwd
	 * @return
	 */
	@RequestMapping(value = "/changePwd1.do", method = RequestMethod.POST)
	@ResponseBody
	public String changePwd1(String id){
		Student student = studentService.findStudentById(id);
		// 密码重置为1234
		student.setPwd("1234");
		studentService.alertStudent(student);
		return "success";
	}
	
	/**
	 * 考生修改登录密码
	 * @param id
	 * @param name
	 * @param pwd
	 * @return
	 */
	@RequestMapping(value = "/changePwd2.do", method = RequestMethod.POST)
	public String changePwd2(String id, String opwd, String pwd){
		Student student = studentService.findStudentById(id);
		// 符合修改密码条件
		if(student != null && opwd.equals(student.getPwd())){
			student.setPwd(pwd);
			studentService.alertStudent(student);
		}
		// 修改成功，重新登录
		return "login.jsp";
	}
	

	/**
	 * 删除考生（管理员端）
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/deleteStudent.do", method = RequestMethod.POST, produces={"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
	@ResponseBody
	public String deleteStudent(String id){	
		studentService.removeStudent(id);;
		return "success";
	}

	/**
	 * 所有考生信息（分页）
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getStudentsInfo.do", method = RequestMethod.POST, produces={"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
	@ResponseBody
	public String getStudentsInfo(int page, int limit){
		List<Student> students = studentService.findAllStudents((page-1)*limit, limit);
		JSONArray data = JSONArray.parseArray(JSON.toJSONString(students));
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", studentService.getCount());
		result.put("code", 0);
		result.put("msg", "");
		result.put("data", data);
		JSONObject jsonObject = new JSONObject(result);
		
		return jsonObject.toJSONString();
	}
	
}
