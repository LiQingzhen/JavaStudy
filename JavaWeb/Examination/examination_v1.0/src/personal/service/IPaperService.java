package personal.service;

import java.util.List;
import java.util.Set;

import personal.beans.Paper;
import personal.beans.Question;

public interface IPaperService {

	/**
	 * 将试卷导出为.docx文件（paper_id.docx）
	 * @param id 试卷id
	 * @param docPath 导出路径，举例 E:/mydata/
	 */
	void exportPaperById(Integer id, String docPath);
	
/**
 * 遗传算法自动生成符合要求的试卷（知识点覆盖，难度系数）
 * @param epoint 期望知识点
 * @param ep 期望难度系数
 * @param cs 选择题分数
 * @param ca 选择题数量
 * @param bs 填空题分数
 * @param ba 填空题数量
 * @param js 判断题分数
 * @param ja 判断题数量
 * @param ps 编程题分数
 * @param pa 编程题数量
 * @return
 */
	Paper createPaper(Set<String> epoint, Double ep, Double cs, Integer ca, Double bs, Integer ba, Double js, Integer ja, Double ps, Integer pa);
	
	/**
	 * 试卷入库
	 * @param paper
	 */
	void addPaper(Paper paper);
	
	/**
	 * 获取最新试卷
	 * @return
	 */
	Integer getNewPaperId();
	
	/**
	 * 试卷分页展示（带权限）
	 * @return
	 */
	List<Paper> getPapersInfo(Integer start, Integer limit, Integer flag);
	Integer getCount(Integer flag);

	/**
	 * 分页展示-无权限
	 * @param start
	 * @param limit
	 * @return
	 */
	List<Paper> getPapersInfo2(Integer start, Integer limit);
	Integer getCount2();
	
	/**
	 * 获取试卷详情
	 * @param paperId
	 * @return
	 */
	List<Question> getPaperChoice(Integer paperId);
	List<Question> getPaperBlank(Integer paperId);
	List<Question> getPaperJudge(Integer paperId);
	List<Question> getPaperProgram(Integer paperId);
	
	Paper getPaperById(Integer id);
	
	/**
	 * 论巡，过期试卷对考生开放权限
	 * @param date
	 */
	void changeLimit(Integer id);
}
