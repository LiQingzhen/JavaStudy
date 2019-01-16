package personal.beans;

import java.util.List;

public class Article {

	private Integer id;
	private String title;
	private String content;
	
	private List<Article> children;
	
	public Article() {}
	public Article(String title, String content) {
		this.title = title;
		this.content = content;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", content=" + content + ", child=" + children + "]";
	}
	
}
