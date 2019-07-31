package personal.dao;

import java.util.List;

import personal.beans.Paper;

public interface IPaperDao {

	void insertPaper(Paper paper);
	
	void deletePaperById(Integer id);
	
	void updatePaper(Paper paper);
	
	Paper selectPaperById(Integer id);
	
	List<Paper> selectAllPapers();
}
