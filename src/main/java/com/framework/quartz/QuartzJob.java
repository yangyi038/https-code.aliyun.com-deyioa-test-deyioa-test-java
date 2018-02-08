package com.framework.quartz;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
public class QuartzJob implements Job {
	private static final Log log = LogFactory.getLog(QuartzJob.class);
	public void work() throws Exception{
    }
	
	
	 public static void main(String args[]) throws FileNotFoundException, IllegalAccessException, InvocationTargetException, IOException{
		 ApplicationContext ctx = new FileSystemXmlApplicationContext("/WebRoot/WEB-INF/applicationContext*.xml");
		}


	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		
	}

}
