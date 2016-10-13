package com.yeehome.entity;

import java.io.Serializable;

public class Note_Type implements Serializable{

	/**
	 * 
	 */
	

	public static final String code1="favorites";
	public static final String code2="recycle";
	public static final String code3="action";
	public static final String code4="push";
	public static final String code5="normal";
	
	private static final long serialVersionUID = 1L;
	private String cn_note_type_id;
	private String cn_note_type_code;
	private String cn_note_type_name;
	private String cn_note_type_desc;
	public Note_Type() {
	}
	public Note_Type(String cn_note_type_id, String cn_note_type_code,
			String cn_note_type_name, String cn_note_type_desc) {
		super();
		this.cn_note_type_id = cn_note_type_id;
		this.cn_note_type_code = cn_note_type_code;
		this.cn_note_type_name = cn_note_type_name;
		this.cn_note_type_desc = cn_note_type_desc;
	}
	public String getCn_note_type_id() {
		return cn_note_type_id;
	}
	public void setCn_note_type_id(String cn_note_type_id) {
		this.cn_note_type_id = cn_note_type_id;
	}
	public String getCn_note_type_code() {
		return cn_note_type_code;
	}
	public void setCn_note_type_code(String cn_note_type_code) {
		this.cn_note_type_code = cn_note_type_code;
	}
	public String getCn_note_type_name() {
		return cn_note_type_name;
	}
	public void setCn_note_type_name(String cn_note_type_name) {
		this.cn_note_type_name = cn_note_type_name;
	}
	public String getCn_note_type_desc() {
		return cn_note_type_desc;
	}
	public void setCn_note_type_desc(String cn_note_type_desc) {
		this.cn_note_type_desc = cn_note_type_desc;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((cn_note_type_code == null) ? 0 : cn_note_type_code
						.hashCode());
		result = prime
				* result
				+ ((cn_note_type_desc == null) ? 0 : cn_note_type_desc
						.hashCode());
		result = prime * result
				+ ((cn_note_type_id == null) ? 0 : cn_note_type_id.hashCode());
		result = prime
				* result
				+ ((cn_note_type_name == null) ? 0 : cn_note_type_name
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
		Note_Type other = (Note_Type) obj;
		if (cn_note_type_code == null) {
			if (other.cn_note_type_code != null)
				return false;
		} else if (!cn_note_type_code.equals(other.cn_note_type_code))
			return false;
		if (cn_note_type_desc == null) {
			if (other.cn_note_type_desc != null)
				return false;
		} else if (!cn_note_type_desc.equals(other.cn_note_type_desc))
			return false;
		if (cn_note_type_id == null) {
			if (other.cn_note_type_id != null)
				return false;
		} else if (!cn_note_type_id.equals(other.cn_note_type_id))
			return false;
		if (cn_note_type_name == null) {
			if (other.cn_note_type_name != null)
				return false;
		} else if (!cn_note_type_name.equals(other.cn_note_type_name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Note_Type [cn_note_type_id=" + cn_note_type_id
				+ ", cn_note_type_code=" + cn_note_type_code
				+ ", cn_note_type_name=" + cn_note_type_name
				+ ", cn_note_type_desc=" + cn_note_type_desc + "]";
	}
	
	
}
