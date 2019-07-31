package personal.dao;

import java.util.List;

import personal.beans.Exam;

public interface IExamDao {

	void insertExam(Exam exam);
	
	void deleteExamById(String id);
	
	void updateExam(Exam exam);
	
	Exam selectExamById(String id);
	
	List<Exam> selectAllExams();
}
