package personal.service;

import java.util.List;

import personal.beans.Question;

public interface IQuestionService {

	/**
	 * 将excle文件中的数据导入数据库
	 * @param absolutePath excle文件在服务器的绝对路径
	 */
	void importQuestionsFromExcle(String absolutePath);
	
	/**
	 * 获取所有题目信息，分页
	 * @param start
	 * @param limit
	 * @return
	 */
	List<Question> getQuestions(Integer start, Integer limit);
	
	/**
	 * 获取试题数量（分页）
	 * @return
	 */
	Integer getCount();

}
