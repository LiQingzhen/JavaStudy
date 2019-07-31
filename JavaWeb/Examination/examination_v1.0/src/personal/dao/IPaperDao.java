package personal.dao;

import java.util.List;

import personal.beans.Paper;

public interface IPaperDao {

	void insertPaper(Paper paper);
	
	void deletePaperById(Integer id);
	
	void updatePaper(Paper paper);
	
	Paper selectPaperById(Integer id);
	
	List<Paper> selectAllPapers();
	
	Integer selectNewPaperId();

	List<Paper> selectAllPapersInfo(Integer start, Integer limit, Integer flag);

	Integer selectCount(Integer flag);
	
	List<Paper> selectAllPapersInfo2(Integer start, Integer limit);

	Integer selectCount2();
	
	void updateLimit(Integer id);
}
