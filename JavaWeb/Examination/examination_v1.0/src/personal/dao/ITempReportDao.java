package personal.dao;

import java.util.List;

import personal.beans.TempReport;

public interface ITempReportDao {

	List<TempReport> selectReportsByStudentId(String sid, Integer start, Integer size);
}
