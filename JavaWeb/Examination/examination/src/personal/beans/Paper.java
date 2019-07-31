package personal.beans;

import java.sql.Timestamp;

public class Paper {

	private Integer id;
	
	private Boolean limit;	// true-学生可见
	
	private Timestamp mdate;
	
	private Double grade;	// 难度系数
	
	private String choice;
	
	private Double choiceScore;
	
	private String judge;
	
	private Double judgeScore;
	
	private String blank;
	
	private Double blankScore;
	
	private String program;
	
	private Double programScore;

	public Paper() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getLimit() {
		return limit;
	}

	public void setLimit(Boolean limit) {
		this.limit = limit;
	}

	public Timestamp getMdate() {
		return mdate;
	}

	public void setMdate(Timestamp mdate) {
		this.mdate = mdate;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getJudge() {
		return judge;
	}

	public void setJudge(String judge) {
		this.judge = judge;
	}

	public String getBlank() {
		return blank;
	}

	public void setBlank(String blank) {
		this.blank = blank;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}

	public Double getChoiceScore() {
		return choiceScore;
	}

	public void setChoiceScore(Double choiceScore) {
		this.choiceScore = choiceScore;
	}

	public Double getJudgeScore() {
		return judgeScore;
	}

	public void setJudgeScore(Double judgeScore) {
		this.judgeScore = judgeScore;
	}

	public Double getBlankScore() {
		return blankScore;
	}

	public void setBlankScore(Double blankScore) {
		this.blankScore = blankScore;
	}

	public Double getProgramScore() {
		return programScore;
	}

	public void setProgramScore(Double programScore) {
		this.programScore = programScore;
	}
}
