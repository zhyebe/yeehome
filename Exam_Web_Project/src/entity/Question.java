package entity;

import java.io.Serializable;

/**
 * 题目表
 * 用来保存单个题目
 * @author Array
 * @see entity
 * @since JDK1.6,J2EE5.0,Tomcat7
 * @version 1.4.3
 *
 */
public class Question implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int questionNo;
	
	private String question;
	private int score;
	private String answer;
	public Question(int questionNo, String question, int score, String answer) {
		super();
		this.questionNo = questionNo;
		this.question = question;
		this.score = score;
		this.answer = answer;
	}
	public Question(){
		
	}
	public int getQuestionNo() {
		return questionNo;
	}
	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Question other = (Question) obj;
		if (questionNo != other.questionNo)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Question [questionNo=" + questionNo + ", question=" + question
				+ ", score=" + score + ", answer=" + answer + "]";
	}
	
}
