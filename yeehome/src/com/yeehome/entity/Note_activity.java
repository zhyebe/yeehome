package com.yeehome.entity;

import java.io.Serializable;

/**
 * 
 * @author Administrator
 *
 */
public class Note_activity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cn_note_activity_id;
	private String cn_activity_id;
	private String cn_note_id;
	private Integer cn_note_activity_up;
	private Integer cn_note_activity_down;
	private String cn_note_activity_title;
	private String cn_note_activity_body;
	public Note_activity(){
		
	}
	public Note_activity(String cn_note_activity_id, String cn_activity_id,
			String cn_note_id, Integer cn_note_activity_up,
			Integer cn_note_activity_down, String cn_note_activity_title,
			String cn_note_activity_body) {
		super();
		this.cn_note_activity_id = cn_note_activity_id;
		this.cn_activity_id = cn_activity_id;
		this.cn_note_id = cn_note_id;
		this.cn_note_activity_up = cn_note_activity_up;
		this.cn_note_activity_down = cn_note_activity_down;
		this.cn_note_activity_title = cn_note_activity_title;
		this.cn_note_activity_body = cn_note_activity_body;
	}
	@Override
	public String toString() {
		return "Note_activity [cn_note_activity_id=" + cn_note_activity_id
				+ ", cn_activity_id=" + cn_activity_id + ", cn_note_id="
				+ cn_note_id + ", cn_note_activity_up=" + cn_note_activity_up
				+ ", cn_note_activity_down=" + cn_note_activity_down
				+ ", cn_note_activity_title=" + cn_note_activity_title
				+ ", cn_note_activity_body=" + cn_note_activity_body + "]";
	}
	public String getCn_note_activity_id() {
		return cn_note_activity_id;
	}
	public void setCn_note_activity_id(String cn_note_activity_id) {
		this.cn_note_activity_id = cn_note_activity_id;
	}
	public String getCn_activity_id() {
		return cn_activity_id;
	}
	public void setCn_activity_id(String cn_activity_id) {
		this.cn_activity_id = cn_activity_id;
	}
	public String getCn_note_id() {
		return cn_note_id;
	}
	public void setCn_note_id(String cn_note_id) {
		this.cn_note_id = cn_note_id;
	}
	public Integer getCn_note_activity_up() {
		return cn_note_activity_up;
	}
	public void setCn_note_activity_up(Integer cn_note_activity_up) {
		this.cn_note_activity_up = cn_note_activity_up;
	}
	public Integer getCn_note_activity_down() {
		return cn_note_activity_down;
	}
	public void setCn_note_activity_down(Integer cn_note_activity_down) {
		this.cn_note_activity_down = cn_note_activity_down;
	}
	public String getCn_note_activity_title() {
		return cn_note_activity_title;
	}
	public void setCn_note_activity_title(String cn_note_activity_title) {
		this.cn_note_activity_title = cn_note_activity_title;
	}
	public String getCn_note_activity_body() {
		return cn_note_activity_body;
	}
	public void setCn_note_activity_body(String cn_note_activity_body) {
		this.cn_note_activity_body = cn_note_activity_body;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cn_activity_id == null) ? 0 : cn_activity_id.hashCode());
		result = prime
				* result
				+ ((cn_note_activity_body == null) ? 0 : cn_note_activity_body
						.hashCode());
		result = prime
				* result
				+ ((cn_note_activity_down == null) ? 0 : cn_note_activity_down
						.hashCode());
		result = prime
				* result
				+ ((cn_note_activity_id == null) ? 0 : cn_note_activity_id
						.hashCode());
		result = prime
				* result
				+ ((cn_note_activity_title == null) ? 0
						: cn_note_activity_title.hashCode());
		result = prime
				* result
				+ ((cn_note_activity_up == null) ? 0 : cn_note_activity_up
						.hashCode());
		result = prime * result
				+ ((cn_note_id == null) ? 0 : cn_note_id.hashCode());
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
		Note_activity other = (Note_activity) obj;
		if (cn_activity_id == null) {
			if (other.cn_activity_id != null)
				return false;
		} else if (!cn_activity_id.equals(other.cn_activity_id))
			return false;
		if (cn_note_activity_body == null) {
			if (other.cn_note_activity_body != null)
				return false;
		} else if (!cn_note_activity_body.equals(other.cn_note_activity_body))
			return false;
		if (cn_note_activity_down == null) {
			if (other.cn_note_activity_down != null)
				return false;
		} else if (!cn_note_activity_down.equals(other.cn_note_activity_down))
			return false;
		if (cn_note_activity_id == null) {
			if (other.cn_note_activity_id != null)
				return false;
		} else if (!cn_note_activity_id.equals(other.cn_note_activity_id))
			return false;
		if (cn_note_activity_title == null) {
			if (other.cn_note_activity_title != null)
				return false;
		} else if (!cn_note_activity_title.equals(other.cn_note_activity_title))
			return false;
		if (cn_note_activity_up == null) {
			if (other.cn_note_activity_up != null)
				return false;
		} else if (!cn_note_activity_up.equals(other.cn_note_activity_up))
			return false;
		if (cn_note_id == null) {
			if (other.cn_note_id != null)
				return false;
		} else if (!cn_note_id.equals(other.cn_note_id))
			return false;
		return true;
	}
	
}
