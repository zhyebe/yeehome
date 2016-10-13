package entity;

import java.io.Serializable;

/**
 * 学生的答案实例对象
 * 每个对象对应一道题的答案
 * @author Array
 * @see entity
 * @since JDK1.6,J2EE5.0,Tomcat7
 * @version 1.4.3
 */
public class StudentAnswer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int student_id;//对应学生表的id
	private int questionNo;
	private String exam_date;
	private String answer;
	private int examNo;//考试场次号
	public StudentAnswer(){
		
	}
	public int getId() {
		return student_id;
	}
	public void setId(int id) {
		this.student_id = id;
	}
	public int getQuestionNo() {
		return questionNo;
	}
	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}
	public String getExam_date() {
		return exam_date;
	}
	public void setExam_date(String exam_date) {
		this.exam_date = exam_date;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getExamNo() {
		return examNo;
	}
	public void setExamNo(int examNo) {
		this.examNo = examNo;
	}
	public StudentAnswer(int id, int questionNo, String exam_date,
			String answer, int examNo) {
		super();
		this.student_id = id;
		this.questionNo = questionNo;
		this.exam_date = exam_date;
		this.answer = answer;
		this.examNo = examNo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + examNo;
		result = prime * result + student_id;
		result = prime * result + questionNo;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentAnswer other = (StudentAnswer) obj;
		if (examNo != other.examNo)
			return false;
		if (student_id != other.student_id)
			return false;
		if (questionNo != other.questionNo)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "StudentAnswer [id=" + student_id + ", questionNo=" + questionNo
				+ ", exam_date=" + exam_date + ", answer=" + answer
				+ ", examNo=" + examNo + "]";
	}
	
	
	
}
