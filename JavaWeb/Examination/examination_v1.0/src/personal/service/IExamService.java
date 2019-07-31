package personal.service;

import java.util.List;

import personal.beans.Exam;

public interface IExamService {

	/**
	 * 获取所有考试信息，用于前段分页展示
	 * @param page
	 * @param limit
	 * @return
	 */
	List<Exam> getExamsInfo(Integer page, Integer limit);
	
	/**
	 * 用于分页
	 * @return
	 */
	Integer getCount();
	
	/**
	 * 添加考试信息
	 * @param exam
	 * @return
	 */
	void addExam(Exam exam);
	
	Exam getExamById(String id);
	
	List<Exam> getExamDim(String date);
}
