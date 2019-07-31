package personal.service;

import java.util.List;

import personal.beans.Exam;
import personal.dao.IExamDao;

public class ExamServiceImpl implements IExamService {

	private IExamDao examDao;

	public IExamDao getExamDao() {
		return examDao;
	}

	public void setExamDao(IExamDao examDao) {
		this.examDao = examDao;
	}
	
	@Override
	public List<Exam> getExamsInfo(Integer start, Integer limit) {		
		return examDao.selectAllExams(start, limit);
	}

	@Override
	public Integer getCount() {
		return examDao.selectCount();
	}

	@Override
	public void addExam(Exam exam) {
		examDao.insertExam(exam);
	}

	@Override
	public Exam getExamById(String id) {
		return examDao.selectExamById(id);
	}

	@Override
	public List<Exam> getExamDim(String date) {
		return examDao.selectExamDim(date);
	}

}
