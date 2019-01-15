package personal.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import personal.beans.Student;
import personal.dao.IStudentDao;
import personal.util.MybatisUtil;

public class TestStudentDao {
	
	private IStudentDao dao;
	private SqlSession session = MybatisUtil.getSqlSession();
	
	@Before
	public void setUp() throws Exception {
		dao = session.getMapper(IStudentDao.class);
	}

	@After
	public void tearDown() throws Exception {
		if(session != null){
			session.close();
		}
	}
	
	@Test
	public void testSelectStudentById() {
		Student student = dao.selectStudentById(2);
		System.out.println(student);
	}
	
}
