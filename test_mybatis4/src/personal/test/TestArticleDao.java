package personal.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import personal.beans.Article;
import personal.dao.IArticleDao;
import personal.util.MybatisUtil;

public class TestArticleDao {
	
	private IArticleDao dao;
	private SqlSession session = MybatisUtil.getSqlSession();
	
	@Before
	public void setUp() throws Exception {
		dao = session.getMapper(IArticleDao.class);
	}

	@After
	public void tearDown() throws Exception {
		if(session != null){
			session.close();
		}
	}
	
	@Test
	public void testSelectChildrenByParent() {
		List<Article> article = dao.selectChildrenByParent(2);
		System.out.println(article);
	}
	
}
