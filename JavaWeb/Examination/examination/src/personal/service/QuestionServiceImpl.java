package personal.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import personal.beans.Question;
import personal.dao.IQuestionDao;

public class QuestionServiceImpl implements IQuestionService {

	private IQuestionDao questionDao;
	
	public IQuestionDao getQuestionDao() {
		return questionDao;
	}

	public void setQuestionDao(IQuestionDao questionDao) {
		this.questionDao = questionDao;
	}
	
	@Override
	public void importQuestionsFromExcle(String absolutePath) {
		
		InputStream is = null;
		Workbook workbook = null;
		try {
			is = new FileInputStream(absolutePath);
			// 修改字符编码，解决中文乱码问题
//			WorkbookSettings workbookSettings = new WorkbookSettings();
//			workbookSettings.setEncoding("GBK");
			// 连接到Excle文件；仅考虑.xlsx
			workbook = new XSSFWorkbook(is);
			// 获取sheet的数量
			Integer sheets = workbook.getNumberOfSheets();
			// 题目类型
			String questionType;
			for(int i = 0; i < sheets; i++){
				// 获取到当前Sheet
				Sheet sheet = workbook.getSheetAt(i);
				switch(sheet.getSheetName()){
				case "choice":
					questionType = "0";
					break;
				case "blank":
					questionType = "1";
					break;
				case "judge":
					questionType = "2";
					break;
				default:
					questionType = "3";
				}
				// 逐行读取，第一行不读
				for(int r = 1; r < sheet.getPhysicalNumberOfRows(); r++){
					// 定位到当前行
					Row row = sheet.getRow(r);
					// 所有单元格内容均已字符串形式读取出来
					row.getCell(0).setCellType(CellType.STRING);
					String id = row.getCell(0).getStringCellValue();
					row.getCell(1).setCellType(CellType.STRING);
					String details = row.getCell(1).getStringCellValue();
					row.getCell(2).setCellType(CellType.STRING);
					String answer = row.getCell(2).getStringCellValue();
					row.getCell(3).setCellType(CellType.STRING);
					String point = row.getCell(3).getStringCellValue();
					row.getCell(4).setCellType(CellType.STRING);
					String grade = row.getCell(4).getStringCellValue();
					
					// 数据封装
					Question question = new Question();
					question.setId(id);
					question.setDetails(details);
					question.setAnswer(answer);
					question.setType(questionType);
					question.setPoint(point);
					question.setGrade(grade);
					
//					System.out.println(details);
					// 插入数据库
					questionDao.insertQuestion(question);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(workbook != null)
					workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
