package personal.dao;

import java.util.List;

import personal.beans.Article;

public interface IArticleDao {
	
	List<Article> selectChildrenByParent(Integer pid);
}
