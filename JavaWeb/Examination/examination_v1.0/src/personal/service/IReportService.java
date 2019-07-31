package personal.service;

import personal.beans.Report;

public interface IReportService {

	/**
	 * 打印成绩单（2015XXXXXX_report.txt）
	 * @param sid 学号
	 * @param eid 考试号
	 * @param txtPath 文件导出路径，举例：E:/mydata/
	 */
	void exportReport(String sid, String eid, String txtPath);
	
	/**
	 * 获取某学生参加考试数
	 * @param id
	 * @return
	 */
	Integer getCountById(String id);

	/**
	 * 添加成绩
	 * @param report
	 */
	void addReport(Report report);
	

}
