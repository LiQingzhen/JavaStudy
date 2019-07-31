package personal.service;

import java.util.List;

import personal.beans.TempReport;
import personal.dao.ITempReportDao;

public class TempReportServiceImpl implements ITempReportService {

	private ITempReportDao tempReportDao;
	
	public ITempReportDao getTempReportDao() {
		return tempReportDao;
	}

	public void setTempReportDao(ITempReportDao tempReportDao) {
		this.tempReportDao = tempReportDao;
	}

	@Override
	public List<TempReport> getReports(String sid, Integer start, Integer size) {
		return tempReportDao.selectReportsByStudentId(sid, start, size);
	}

}
