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
			//创建封装学生信息的标签
			Element student_Tag = document.createElement("student");
			//设置学生标签的属性信息
			student_Tag.setAttribute("idcard", student.getIdcard());
			student_Tag.setAttribute("examid", student.getExamid());
			
			//创建用于封装学生姓名，所在地，成绩的标签
			Element name = document.createElement("name");
			Element location = document.createElement("location");
			Element grade = document.createElement("grade");
			
			//向标签中添加文本信息
			name.setTextContent(student.getName());
			location.setTextContent(student.getLocation());
			grade.setTextContent(student.getGrade()+"");
			
			//将创建的标签添加到父标签中
			student_Tag.appendChild(name);
			student_Tag.appendChild(location);
			student_Tag.appendChild(grade);
			
			//获取xml文档中的根节点，将学生节点添加进去
			Node exam_Node = document.getElementsByTagName("exam").item(0);
			exam_Node.appendChild(student_Tag);
			
			//将添加后的数据，重新写到xml文件中
			XmlUtils.writer2Xml(document);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public Student find(String examid) {
		try {
			Document document = XmlUtils.getDocument();
			//获取student所有的节点
			NodeList list = document.getElementsByTagName("student");
			//遍历student节点
			for(int i=0;i<list.getLength(); i++) {
				Element student_node = (Element) list.item(i);
				//获取节点的属性值，判断是否相等
				if(student_node.getAttribute("examid").equals(examid)) {
					//new Student对象，将查找到的数据封装进student对象，再返回
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
			
			throw new StudentNotExistException(name+"学生不存在");
			
		} catch (StudentNotExistException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
