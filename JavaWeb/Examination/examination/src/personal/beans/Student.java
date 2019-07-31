package personal.beans;

import java.sql.Timestamp;

public class Student {
	
	private String id;
	
	private String name;
	
	private String pwd;
	
	private Integer sex;	// 0-female 1-male
	
	private String grade;
	
	private String classs;
	
	private String major;
	
	private String mail;
	
	private String phone;
	
	private String address;
	
	private Timestamp rdate;
	
	public Student() {
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

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getClasss() {
		return classs;
	}

	public void setClasss(String classs) {
		this.classs = classs;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getRdate() {
		return rdate;
	}

	public void setRdate(Timestamp l) {
		this.rdate = l;
	}
		
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", pwd=" + pwd + ", sex=" + sex + ", grade=" + grade
				+ ", classs=" + classs + ", major=" + major + ", mail=" + mail + ", phone=" + phone + ", address="
				+ address + ", rdate=" + rdate + "]";
	}

}
