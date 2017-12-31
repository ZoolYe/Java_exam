package junit.test;

import org.junit.jupiter.api.Test;

import zool.java.exam.bean.Student;
import zool.java.exam.dao.StudentDao;
import zool.java.exam.exception.StudentNotExistException;

public class StudentDaoTest {

	@Test
	public void testAdd() {
		StudentDao studentDao = new StudentDao();
		Student student = new Student();
		student.setExamid("6248");
		student.setGrade(98);
		student.setIdcard("7788");
		student.setLocation("湖北");
		student.setName("我是你大爷");
		studentDao.add(student);
	}
	
	@Test
	public void testFind() {
		
		StudentDao studentDao = new StudentDao();
		Student student = studentDao.find("123");
		
		String examid = student.getExamid();
		double grade = student.getGrade();
		String idcare = student.getIdcard();
		String location = student.getLocation();
		String name = student.getName();
		
		System.out.println(examid);
		System.out.println(idcare);
		System.out.println(name);
		System.out.println(location);
		System.out.println(grade);
		
	}
	
	@Test
	public void testDelete() throws StudentNotExistException {
		StudentDao studentDao = new StudentDao();
		studentDao.delete("张三");
	}
	
}
