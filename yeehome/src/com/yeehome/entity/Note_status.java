package com.yeehome.entity;

import java.io.Serializable;

/**
 * 
 * @author Administrator
 *
 */
public class Note_status implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cn_note_status_id;
	private String cn_note_status_code;
	private String cn_note_status_name;
	public Note_status(){
		
	}
	public Note_status(String cn_note_status_id, String cn_note_status_code,
			String cn_note_status_name) {
		super();
		this.cn_note_status_id = cn_note_status_id;
		this.cn_note_status_code = cn_note_status_code;
		this.cn_note_status_name = cn_note_status_name;
	}
	@Override
	public String toString() {
		return "Note_status [cn_note_status_id=" + cn_note_status_id
				+ ", cn_note_status_code=" + cn_note_status_code
				+ ", cn_note_status_name=" + cn_note_status_name + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((cn_note_status_code == null) ? 0 : cn_note_status_code
						.hashCode());
		result = prime
				* result
				+ ((cn_note_status_id == null) ? 0 : cn_note_status_id
						.hashCode());
		result = prime
				* result
				+ ((cn_note_status_name == null) ? 0 : cn_note_status_name
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
		Note_status other = (Note_status) obj;
		if (cn_note_status_code == null) {
			if (other.cn_note_status_code != null)
				return false;
		} else if (!cn_note_status_code.equals(other.cn_note_status_code))
			return false;
		if (cn_note_status_id == null) {
			if (other.cn_note_status_id != null)
				return false;
		} else if (!cn_note_status_id.equals(other.cn_note_status_id))
			return false;
		if (cn_note_status_name == null) {
			if (other.cn_note_status_name != null)
				return false;
		} else if (!cn_note_status_name.equals(other.cn_note_status_name))
			return false;
		return true;
	}
	public String getCn_note_status_id() {
		return cn_note_status_id;
	}
	public void setCn_note_status_id(String cn_note_status_id) {
		this.cn_note_status_id = cn_note_status_id;
	}
	public String getCn_note_status_code() {
		return cn_note_status_code;
	}
	public void setCn_note_status_code(String cn_note_status_code) {
		this.cn_note_status_code = cn_note_status_code;
	}
	public String getCn_note_status_name() {
		return cn_note_status_name;
	}
	public void setCn_note_status_name(String cn_note_status_name) {
		this.cn_note_status_name = cn_note_status_name;
	}
	
}
