package zool.java.exam.dao;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import zool.java.exam.bean.Student;
import zool.java.exam.exception.StudentNotExistException;
import zool.java.exam.utils.XmlUtils;

public class StudentDao {
	
	public void add(Student student) {
		
		try {
			Document document = XmlUtils.getDocument();
			//������װѧ����Ϣ�ı�ǩ
			Element student_Tag = document.createElement("student");
			//����ѧ����ǩ��������Ϣ
			student_Tag.setAttribute("idcard", student.getIdcard());
			student_Tag.setAttribute("examid", student.getExamid());
			
			//�������ڷ�װѧ�����������ڵأ��ɼ��ı�ǩ
			Element name = document.createElement("name");
			Element location = document.createElement("location");
			Element grade = document.createElement("grade");
			
			//���ǩ������ı���Ϣ
			name.setTextContent(student.getName());
			location.setTextContent(student.getLocation());
			grade.setTextContent(student.getGrade()+"");
			
			//�������ı�ǩ��ӵ�����ǩ��
			student_Tag.appendChild(name);
			student_Tag.appendChild(location);
			student_Tag.appendChild(grade);
			
			//��ȡxml�ĵ��еĸ��ڵ㣬��ѧ���ڵ���ӽ�ȥ
			Node exam_Node = document.getElementsByTagName("exam").item(0);
			exam_Node.appendChild(student_Tag);
			
			//����Ӻ�����ݣ�����д��xml�ļ���
			XmlUtils.writer2Xml(document);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public Student find(String examid) {
		try {
			Document document = XmlUtils.getDocument();
			//��ȡstudent���еĽڵ�
			NodeList list = document.getElementsByTagName("student");
			//����student�ڵ�
			for(int i=0;i<list.getLength(); i++) {
				Element student_node = (Element) list.item(i);
				//��ȡ�ڵ������ֵ���ж��Ƿ����
				if(student_node.getAttribute("examid").equals(examid)) {
					//new Student���󣬽����ҵ������ݷ�װ��student�����ٷ���
					Student student = new Student();
					student.setExamid(examid);
					student.setIdcard(student_node.getAttribute("idcard"));
					String name = student_node.getElementsByTagName("name").item(0).getTextContent();
					String location = student_node.getElementsByTagName("location").item(0).getTextContent();
					String grade = student_node.getElementsByTagName("grade").item(0).getTextContent();
					student.setName(name);
					student.setLocation(location);
					student.setGrade(Double.parseDouble(grade));
					return student;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
	}
	
	public void delete(String name) throws StudentNotExistException {
		try {
			
			Document document = XmlUtils.getDocument();
			NodeList name_list = document.getElementsByTagName("name");
			for(int i=0; i<name_list.getLength();i++) {
				Node name_node = name_list.item(i);
				if(name_node.getTextContent().equals(name)) {
					Node examNode = name_node.getParentNode().getParentNode();
					examNode.removeChild(name_node.getParentNode());
					XmlUtils.writer2Xml(document);
					return;
				}
			}
			
			throw new StudentNotExistException(name+"ѧ��������");
			
		} catch (StudentNotExistException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
