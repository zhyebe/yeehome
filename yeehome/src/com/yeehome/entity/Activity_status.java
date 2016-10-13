package com.yeehome.entity;

import java.io.Serializable;

/**
 * 实体类 Activity_status
 * @author Administrator
 *
 */
public class Activity_status implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cn_activity_status_id;
	private String cn_activity_id;
	private String cn_activity_status_code;
	private String cn_activity_status_name;
	@Override
	public String toString() {
		return "Activity_status [cn_activity_status_id="
				+ cn_activity_status_id + ", cn_activity_id=" + cn_activity_id
				+ ", cn_activity_status_code=" + cn_activity_status_code
				+ ", cn_activity_status_name=" + cn_activity_status_name + "]";
	}
	public Activity_status(){
		
	}
	public String getCn_activity_status_id() {
		return cn_activity_status_id;
	}
	public void setCn_activity_status_id(String cn_activity_status_id) {
		this.cn_activity_status_id = cn_activity_status_id;
	}
	public String getCn_activity_id() {
		return cn_activity_id;
	}
	public void setCn_activity_id(String cn_activity_id) {
		this.cn_activity_id = cn_activity_id;
	}
	public String getCn_activity_status_code() {
		return cn_activity_status_code;
	}
	public void setCn_activity_status_code(String cn_activity_status_code) {
		this.cn_activity_status_code = cn_activity_status_code;
	}
	public String getCn_activity_status_name() {
		return cn_activity_status_name;
	}
	public void setCn_activity_status_name(String cn_activity_status_name) {
		this.cn_activity_status_name = cn_activity_status_name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cn_activity_id == null) ? 0 : cn_activity_id.hashCode());
		result = prime
				* result
				+ ((cn_activity_status_code == null) ? 0
						: cn_activity_status_code.hashCode());
		result = prime
				* result
				+ ((cn_activity_status_id == null) ? 0 : cn_activity_status_id
						.hashCode());
		result = prime
				* result
				+ ((cn_activity_status_name == null) ? 0
						: cn_activity_status_name.hashCode());
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
		Activity_status other = (Activity_status) obj;
		if (cn_activity_id == null) {
			if (other.cn_activity_id != null)
				return false;
		} else if (!cn_activity_id.equals(other.cn_activity_id))
			return false;
		if (cn_activity_status_code == null) {
			if (other.cn_activity_status_code != null)
				return false;
		} else if (!cn_activity_status_code
				.equals(other.cn_activity_status_code))
			return false;
		if (cn_activity_status_id == null) {
			if (other.cn_activity_status_id != null)
				return false;
		} else if (!cn_activity_status_id.equals(other.cn_activity_status_id))
			return false;
		if (cn_activity_status_name == null) {
			if (other.cn_activity_status_name != null)
				return false;
		} else if (!cn_activity_status_name
				.equals(other.cn_activity_status_name))
			return false;
		return true;
	}
	public Activity_status(String cn_activity_status_id, String cn_activity_id,
			String cn_activity_status_code, String cn_activity_status_name) {
		super();
		this.cn_activity_status_id = cn_activity_status_id;
		this.cn_activity_id = cn_activity_id;
		this.cn_activity_status_code = cn_activity_status_code;
		this.cn_activity_status_name = cn_activity_status_name;
	}
	
	
}
