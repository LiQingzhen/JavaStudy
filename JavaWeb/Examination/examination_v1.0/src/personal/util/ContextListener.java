package personal.util;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
	
	private Timer timer = null;
	
	public void contextInitialized(ServletContextEvent ce){
		timer = new Timer(true);
		ce.getServletContext().log("ContextListener Start Successed !!!");
		// 论巡/30mins
		timer.schedule(new ChangeLimitTask(ce.getServletContext()), 1000, 1000*60*30);
		ce.getServletContext().log("任务调度表已添加");
	}
	
	public void contextDestroyed(ServletContextEvent ce){
		timer.cancel();
		ce.getServletContext().log("ContextListener Stop Successed !!!");
	}

}
