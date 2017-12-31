package zool.java.exam.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import zool.java.exam.bean.Student;
import zool.java.exam.dao.StudentDao;
import zool.java.exam.exception.StudentNotExistException;

public class Main {

	public static void main(String[] args) {

		System.out.print("添加用户：（a） ");
		System.out.print("删除用户：（b） ");
		System.out.println("查找用户：（c） ");
		System.out.print("请输入操作类型：");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			
			String type = br.readLine();
			
			if(type.equals("a")) {
				
				System.out.print("请输入学生姓名：");
				String name = br.readLine();
				
				System.out.print("请输入准考证号：");
				String examid = br.readLine();
				
				System.out.print("请输入身份证证号：");
				String idcard = br.readLine();
				
				System.out.print("请输入学生所在地：");
				String location = br.readLine();
				
				System.out.print("请输入学生分数：");
				String grade = br.readLine();
				
				Student student = new Student();
				student.setExamid(examid);
				student.setGrade(Double.parseDouble(grade));
				student.setIdcard(idcard);
				student.setLocation(location);
				student.setName(name);
				
				StudentDao studentDao = new StudentDao();
				studentDao.add(student);
				
				System.out.println("添加成功！");
				
			}else if (type.equals("b")) {
				
				System.out.print("请输入要删除的学生姓名：");
				String name = br.readLine();
				StudentDao studentDao = new StudentDao();
				try {
					studentDao.delete(name);
					System.out.println("-----学生信息删除成功-----");
				} catch (StudentNotExistException e) {
					System.out.println("你要删除的学生不存在！，请检查学生的信息");
					return;
				}
				
			}else if (type.equals("c")) {
				
				System.out.print("请输入准考证信息：");
				String examid = br.readLine();
				StudentDao studentDao = new StudentDao();
				Student student = studentDao.find(examid);
				
				double grade = student.getGrade();
				String idcard = student.getIdcard();
				String location = student.getLocation();
				String name = student.getName();
				
				System.out.println();
				System.out.println("学生准考证："+examid);
				System.out.println("学生身份证："+idcard);
				System.out.println("学生姓名："+name);
				System.out.println("学生地址："+location);
				System.out.println("学生分数："+grade);
				
			}else {
				System.out.println("不支持你输入的类型！");
				return;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("对不起 程序出错了");
		}
	}

}
