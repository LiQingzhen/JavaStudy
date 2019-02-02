package personal.jdbc.dao;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import personal.jdbc.beans.Student;

public class StudentDaoImpl extends JdbcDaoSupport implements IStudentDao {
	
	@Override
	public void insertStudent(Student student) {
		String sql = "insert into students(name, age) values(?, ?)";
		this.getJdbcTemplate().update(sql, student.getName(), student.getAge());
	}

	@Override
	public void deleteStudent(Integer id) {
		String sql = "delete from students where id = ?";
		this.getJdbcTemplate().update(sql, id);
	}

	@Override
	public void updateStudent(Student student) {
		String sql = "update students set name = ?, age = ? where id = ?";
		this.getJdbcTemplate().update(sql, student.getName(), student.getAge(), student.getId());
	}

	@Override
	public List<Student> selectAllStudents() {
		String sql = "select id, name, age from students";
		return this.getJdbcTemplate().query(sql, new StudentRowMapper());
	}

	@Override
	public Student selectStudentById(Integer id) {
		String sql = "select id, name, age from students where id = ?";
		return this.getJdbcTemplate().queryForObject(sql, new StudentRowMapper(), id);
	}

}
