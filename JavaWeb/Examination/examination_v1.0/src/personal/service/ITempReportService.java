package personal.service;

import java.util.List;

import personal.beans.TempReport;

public interface ITempReportService {

	/**
	 * 获取该学生所有成绩
	 * @param sid
	 * @return
	 */
	List<TempReport> getReports(String sid, Integer start, Integer size);
}
