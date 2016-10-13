package entity;

import java.io.Serializable;


/**
 * 实体类 老师
 * @author Array
 * @see entity
 * @since JDK1.6,J2EE5.0,Tomcat7
 * @version 1.4.3
 */
public class Teacher implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String username;
	private String realname;
	private String pwd;
	
	public Teacher(){
	}
	
	public Teacher(int id, String username,String realname, String pwd) {
		super();
		this.id = id;
		this.username = username;
		this.setRealname(realname);
		this.pwd = pwd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}



	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
		result = prime * result
				+ ((realname == null) ? 0 : realname.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		Teacher other = (Teacher) obj;
		if (id != other.id)
			return false;
		if (pwd == null) {
			if (other.pwd != null)
				return false;
		} else if (!pwd.equals(other.pwd))
			return false;
		if (realname == null) {
			if (other.realname != null)
				return false;
		} else if (!realname.equals(other.realname))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", username=" + username + ", realname="
				+ realname + ", pwd=" + pwd + "]";
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	
	
}