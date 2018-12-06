package com.bbs;

import java.sql.Timestamp;

public class Article {

	private int id;
	private int parentId;	// 父节点id
	private int rootId;	// 根节点id
	private String title;	// 该节点标题
	private String content;	// 该节点内容
	private String parentContent;	// 父节点内容
	private Timestamp pDate;	// 时间属性
	private boolean isLeaf;	// 是否为叶子结点
	private int childNum;	// 当前节点的直接子节点数目
	private int grade;	// 节点所处级别
	
	
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	public int getRootId() {
		return rootId;
	}
	public void setRootId(int rootId) {
		this.rootId = rootId;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getParentContent() {
		return parentContent;
	}
	public void setParentContent(String parentContent) {
		this.parentContent = parentContent;
	}
	
	public Timestamp getPdate() {
		return pDate;
	}
	public void setPdate(Timestamp pDate) {
		this.pDate = pDate;
	}
	
	public boolean isLeaf() {
		return isLeaf;
	}
	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	public int getChildNum() {
		return childNum;
	}
	public void setChildNum(int childNum) {
		this.childNum = childNum;
	}
}
