package personal.service;

public interface IQuestionService {

	/**
	 * 将excle文件中的数据导入数据库
	 * @param absolutePath excle文件的绝对路径
	 */
	void importQuestionsFromExcle(String absolutePath);

}
