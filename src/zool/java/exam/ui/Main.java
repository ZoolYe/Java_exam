package zool.java.exam.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import zool.java.exam.bean.Student;
import zool.java.exam.dao.StudentDao;
import zool.java.exam.exception.StudentNotExistException;

public class Main {

	public static void main(String[] args) {

		System.out.print("����û�����a�� ");
		System.out.print("ɾ���û�����b�� ");
		System.out.println("�����û�����c�� ");
		System.out.print("������������ͣ�");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			
			String type = br.readLine();
			
			if(type.equals("a")) {
				
				System.out.print("������ѧ��������");
				String name = br.readLine();
				
				System.out.print("������׼��֤�ţ�");
				String examid = br.readLine();
				
				System.out.print("���������֤֤�ţ�");
				String idcard = br.readLine();
				
				System.out.print("������ѧ�����ڵأ�");
				String location = br.readLine();
				
				System.out.print("������ѧ��������");
				String grade = br.readLine();
				
				Student student = new Student();
				student.setExamid(examid);
				student.setGrade(Double.parseDouble(grade));
				student.setIdcard(idcard);
				student.setLocation(location);
				student.setName(name);
				
				StudentDao studentDao = new StudentDao();
				studentDao.add(student);
				
				System.out.println("��ӳɹ���");
				
			}else if (type.equals("b")) {
				
				System.out.print("������Ҫɾ����ѧ��������");
				String name = br.readLine();
				StudentDao studentDao = new StudentDao();
				try {
					studentDao.delete(name);
					System.out.println("-----ѧ����Ϣɾ���ɹ�-----");
				} catch (StudentNotExistException e) {
					System.out.println("��Ҫɾ����ѧ�������ڣ�������ѧ������Ϣ");
					return;
				}
				
			}else if (type.equals("c")) {
				
				System.out.print("������׼��֤��Ϣ��");
				String examid = br.readLine();
				StudentDao studentDao = new StudentDao();
				Student student = studentDao.find(examid);
				
				double grade = student.getGrade();
				String idcard = student.getIdcard();
				String location = student.getLocation();
				String name = student.getName();
				
				System.out.println();
				System.out.println("ѧ��׼��֤��"+examid);
				System.out.println("ѧ�����֤��"+idcard);
				System.out.println("ѧ��������"+name);
				System.out.println("ѧ����ַ��"+location);
				System.out.println("ѧ��������"+grade);
				
			}else {
				System.out.println("��֧������������ͣ�");
				return;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("�Բ��� ���������");
		}
	}

}
