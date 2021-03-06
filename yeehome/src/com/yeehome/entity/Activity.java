package com.yeehome.entity;

import java.io.Serializable;
/**
 * 实体类 Activity
 * @author Administrator
 * @version 1.0;
 * @since JDK 1.6 j2ee 6 Tomcat 8

 */
public class Activity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cn_activity_id;
	private String cn_activity_title;
	private String cn_activity_body;
	private Integer cn_activity_end_time;
	public Activity(){
		
	}
	public String getCn_activity_id() {
		return cn_activity_id;
	}
	public void setCn_activity_id(String cn_activity_id) {
		this.cn_activity_id = cn_activity_id;
	}
	public String getCn_activity_title() {
		return cn_activity_title;
	}
	public void setCn_activity_title(String cn_activity_title) {
		this.cn_activity_title = cn_activity_title;
	}
	public String getCn_activity_body() {
		return cn_activity_body;
	}
	public void setCn_activity_body(String cn_activity_body) {
		this.cn_activity_body = cn_activity_body;
	}
	public Integer getCn_activity_end_time() {
		return cn_activity_end_time;
	}
	public void setCn_activity_end_time(Integer cn_activity_end_time) {
		this.cn_activity_end_time = cn_activity_end_time;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((cn_activity_body == null) ? 0 : cn_activity_body.hashCode());
		result = prime
				* result
				+ ((cn_activity_end_time == null) ? 0 : cn_activity_end_time
						.hashCode());
		result = prime * result
				+ ((cn_activity_id == null) ? 0 : cn_activity_id.hashCode());
		result = prime
				* result
				+ ((cn_activity_title == null) ? 0 : cn_activity_title
						.hashCode());
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
		Activity other = (Activity) obj;
		if (cn_activity_body == null) {
			if (other.cn_activity_body != null)
				return false;
		} else if (!cn_activity_body.equals(other.cn_activity_body))
			return false;
		if (cn_activity_end_time == null) {
			if (other.cn_activity_end_time != null)
				return false;
		} else if (!cn_activity_end_time.equals(other.cn_activity_end_time))
			return false;
		if (cn_activity_id == null) {
			if (other.cn_activity_id != null)
				return false;
		} else if (!cn_activity_id.equals(other.cn_activity_id))
			return false;
		if (cn_activity_title == null) {
			if (other.cn_activity_title != null)
				return false;
		} else if (!cn_activity_title.equals(other.cn_activity_title))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Activity [cn_activity_id=" + cn_activity_id
				+ ", cn_activity_title=" + cn_activity_title
				+ ", cn_activity_body=" + cn_activity_body
				+ ", cn_activity_end_time=" + cn_activity_end_time + "]";
	}
	public Activity(String cn_activity_id, String cn_activity_title,
			String cn_activity_body, Integer cn_activity_end_time) {
		super();
		this.cn_activity_id = cn_activity_id;
		this.cn_activity_title = cn_activity_title;
		this.cn_activity_body = cn_activity_body;
		this.cn_activity_end_time = cn_activity_end_time;
	}
	
}
