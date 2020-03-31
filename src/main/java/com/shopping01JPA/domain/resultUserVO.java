package com.shopping01JPA.domain;

import org.springframework.stereotype.Component;

@Component
public class resultUserVO {

	private int userId;
	private String name;
	private String email;
	private String birth;
	private String gender;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "resultUserVO [userId=" + userId + ", name=" + name + ", email=" + email + ", birth=" + birth
				+ ", gender=" + gender + "]";
	}
	
	
	
}
