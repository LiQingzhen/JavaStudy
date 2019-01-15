package personal.beans;

public class Teacher {

	private Integer id;
	private String name;
	private Integer age;
	private String teach;
	
	
	public Teacher() {}
	
	public Teacher(String name, Integer age, String teach) {
		this.name = name;
		this.age = age;
		this.teach = teach;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getTeach() {
		return teach;
	}
	public void setTeach(String teach) {
		this.teach = teach;
	}
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", age=" + age + ", teach=" + teach + "]";
	}
	
}
