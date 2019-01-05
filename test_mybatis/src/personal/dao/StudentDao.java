package personal.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import personal.beans.Student;
import personal.util.MybatisUtil;

public class StudentDao implements IStudentDao {
	
	private SqlSession session = null;
	
	@Override
	public void insertStudent(Student student) {
		try {
			// 获取SqlSession对象，默认关闭自动提交
			session = MybatisUtil.getSqlSession();
			// 执行相关操作
			session.insert("personal.dao.IStudentDao.insertStudent", student); // 对应mapper.xml文件中的insert标签，参数1-mapper.xml中insert标签内的id（全称）
			// 事务提交（阅读源码）
			session.commit();
		} finally {
			if (session != null) {
				// 一般事务出现异常需要回滚，mybatis将其封装到了SqlSession.close()中
				session.close();
			}
		}
	}

	@Override
	public void deleteStudentById(Integer id) {
		try {
			session = MybatisUtil.getSqlSession();
			session.delete("deleteStudentById", id);
			session.commit();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public void updateStudent(Student student) {
		try {
			session = MybatisUtil.getSqlSession();
			session.update("updateStudent", student);
			session.commit();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<Student> selectAllStudents() {
		try {
			List<Student> students = null;
			session = MybatisUtil.getSqlSession();
			students = session.selectList("selectAllStudents");
			return students;	// finally语句块仍会执行
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public Student selectStudentById(Integer id) {
		try {
			session = MybatisUtil.getSqlSession();
			Student student = session.selectOne("selectStudentById", id);
			return student;	
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	

	@Override
	public List<Student> selectStudentByName(String name) {
		try {
			session = MybatisUtil.getSqlSession();
			List<Student> students = session.selectList("selectStudentByName", name);
			return students;	
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	
}
