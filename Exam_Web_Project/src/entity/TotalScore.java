package entity;

import java.io.Serializable;

/**
 * 总分实体类，对应某一学生某次考试的总分
 * @author Array
 * @see entity
 * @since JDK1.6,J2EE5.0,Tomcat7
 * @version 1.4.3
 */
public class TotalScore implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int score;
	private int student_id;
	private String exam_date;
	private String examNo;
	public TotalScore(){
		
	}
	public TotalScore(int score, int student_id, String exam_date, String examNo) {
		super();
		this.score = score;
		this.student_id = student_id;
		this.exam_date = exam_date;
		this.examNo = examNo;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	public String getExam_date() {
		return exam_date;
	}
	public void setExam_date(String exam_date) {
		this.exam_date = exam_date;
	}
	public String getExamNo() {
		return examNo;
	}
	public void setExamNo(String examNo) {
		this.examNo = examNo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((examNo == null) ? 0 : examNo.hashCode());
		result = prime * result
				+ ((exam_date == null) ? 0 : exam_date.hashCode());
		result = prime * result + score;
		result = prime * result + student_id;
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
		TotalScore other = (TotalScore) obj;
		if (examNo == null) {
			if (other.examNo != null)
				return false;
		} else if (!examNo.equals(other.examNo))
			return false;
		if (exam_date == null) {
			if (other.exam_date != null)
				return false;
		} else if (!exam_date.equals(other.exam_date))
			return false;
		if (score != other.score)
			return false;
		if (student_id != other.student_id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TotalScore [score=" + score + ", student_id=" + student_id
				+ ", exam_date=" + exam_date + ", examNo=" + examNo + "]";
	}
	
	
}
