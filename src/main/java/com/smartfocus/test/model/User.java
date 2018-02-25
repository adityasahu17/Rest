package com.smartfocus.test.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * User POJO
 *
 * @author nitesh
 */
@XmlRootElement
public class User {

	private long userId;

	private String name;

	public User() {
	}

	public User(long userId, String name) {
		this.userId = userId;
		this.name = name;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User{" + "userId=" + userId + ", name='" + name + '\'' + '}';
	}
}
