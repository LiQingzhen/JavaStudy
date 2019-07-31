package personal.beans;

public class Exam {

	private String id;
	
	private String name;
	
	private String start;
	
	private String time;
	
	private String point;
	
	private Double score;
	
	private String paper;

	public Exam() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String getPaper() {
		return paper;
	}

	public void setPaper(String paper) {
		this.paper = paper;
	}

	@Override
	public String toString() {
		return "Exam [id=" + id + ", name=" + name + ", start=" + start + ", time=" + time + ", point=" + point
				+ ", score=" + score + ", paper=" + paper + "]";
	}
	
}
