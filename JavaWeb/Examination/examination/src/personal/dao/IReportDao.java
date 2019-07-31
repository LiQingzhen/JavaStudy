package personal.dao;

import java.util.List;

import personal.beans.Report;

public interface IReportDao {

	void insertReport(Report report );
	
	void deleteReport(String sid, String eid);
	
	void updateReport(Report report);
	
	Report selectReportById(String sid, String eid);
	
	List<Report> selectAllReports();
}
