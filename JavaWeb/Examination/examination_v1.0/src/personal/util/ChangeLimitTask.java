package personal.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import personal.beans.Exam;
import personal.service.IExamService;
import personal.service.IPaperService;

public class ChangeLimitTask extends TimerTask {

	Logger logger = LogManager.getLogger(this.getClass());
	ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

	private IExamService examService = null;

	private IPaperService paperService = null;
	
	private ServletContext context = null;
	
	private static boolean running = false;
	
	public ChangeLimitTask() {
		super();
		examService = (IExamService) ac.getBean("examService");
		paperService = (IPaperService) ac.getBean("paperService");
	}

	public ChangeLimitTask(ServletContext context) {
		super();
		this.context = context;
		examService = (IExamService) ac.getBean("examService");
		paperService = (IPaperService) ac.getBean("paperService");
	}

	@Override
	public void run() {
		String nowDate = null;
		Integer paperId = null;
		List<Exam> exams = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		nowDate = "%"+dateFormat.format(new Date(System.currentTimeMillis()-1000*60*60*24))+"%";
		context.log("正在开放前一天试卷权限");
		logger.info("nowDate:"+nowDate);
		 exams = examService.getExamDim(nowDate);
		if(exams != null){
			for(int i = 0; i < exams.size(); i++){
				if(!running){
					running = true;
					
					paperId = Integer.parseInt(exams.get(i).getPaper());
					paperService.changeLimit(paperId);
					
					running = false;
				}else{
					logger.info("修改权限任务正在进行");
				}
			}
		}
		context.log("试卷权限开放完毕");
	}

}
