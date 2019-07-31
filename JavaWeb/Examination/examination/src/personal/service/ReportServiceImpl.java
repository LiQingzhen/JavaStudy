package personal.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import personal.beans.Exam;
import personal.beans.Student;
import personal.dao.IExamDao;
import personal.dao.IReportDao;
import personal.dao.IStudentDao;

public class ReportServiceImpl implements IReportService {

	private IStudentDao studentDao;
	
	private IExamDao examDao;
	
	private IReportDao reportDao;
	
	public IStudentDao getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(IStudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public IExamDao getExamDao() {
		return examDao;
	}

	public void setExamDao(IExamDao examDao) {
		this.examDao = examDao;
	}

	public IReportDao getReportDao() {
		return reportDao;
	}

	public void setReportDao(IReportDao reportDao) {
		this.reportDao = reportDao;
	}

	@Override
	public void exportReport(String sid, String eid, String txtPath) {
		
		OutputStream os = null;
		BufferedOutputStream bos = null;
			
		Student student = studentDao.selectStudentById(sid);
		Exam exam = examDao.selectExamById(eid);
		Double score = reportDao.selectReportById(sid, eid).getScore();
		
		String line = "*-*-*-*-*-*-*-*-*-*-*-*-*-**-*-*-*-*-*-*\r\n";
		String info = "\r\n姓名：" + student.getName() + "\t[" + student.getId() + "]" + "\r\n"
				+ "班级：" + student.getGrade() + "级" + student.getClasss() + "\r\n"
				+ "科目：" + exam.getName() + "[" + exam.getId() + "]" + "\r\n"
				+ "分数：" + score + "\r\n\r\n";
		
		try {
			os = new FileOutputStream(txtPath + sid + "_report.txt");
			bos = new BufferedOutputStream(os, 1024);
			
			bos.write(line.getBytes());
			bos.write(info.getBytes());
			bos.write(line.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(bos != null)
					bos.close();
				if(os != null)
					os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
