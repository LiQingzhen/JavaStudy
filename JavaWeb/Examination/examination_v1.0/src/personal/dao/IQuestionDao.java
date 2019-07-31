package personal.dao;

import java.util.List;

import personal.beans.Question;

public interface IQuestionDao {
	
	void insertQuestion(Question question);
	
	void deleteQuestionById(String id);
	
	void updateQuestion(Question question);
	
	Question selectQuestionById(String id);
	
	List<Question> selectAllQuestions(Integer start, Integer limit);
	
	List<Question> selectAllChoiceQuestions();
	
	List<Question> selectAllBlankQuestions();
	
	List<Question> selectAllJudgeQuestions();
	
	List<Question> selectAllProgramQuestions();

	Integer selectCount();
}
