package personal.service;

import java.util.Set;

import personal.beans.Paper;

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
	
}
