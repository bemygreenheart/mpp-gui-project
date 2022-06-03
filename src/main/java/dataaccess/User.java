package dataaccess;

import business.Address;
import business.Person;

import java.io.Serializable;

final public class User extends Person implements Serializable {

	private static final long serialVersionUID = 5147265048973262104L;

	private String id;
	private String username;
	private String password;
	private Auth authType;

	User(String id, String username, String password, Auth authType, String fname, String lname, String tel, Address address) {
		super(fname, lname, tel, address);
		this.id = id;
		this.password = password;
		this.username = username;
		this.authType = authType;
	}

	public String getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}
	public Auth getAuthType() {
		return authType;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public String toString() {
		return "[" + id + ":" + password + ", " + ", " + username + ", " + authType.toString() + "]";
	}

}
