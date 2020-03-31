package com.shopping01JPA.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Component
@Entity
@Table(name="user")
public class userVO {
	
	@Id
	private int userId;
	@Column(name = "user_name")
	private String name;
	private String email;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String password;
	@Transient
	private String passwordCheck;
	private String birth;
	private String gender;
	@Column(name = "created_At")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Calendar timestampField;
	
	
	public String getPasswordCheck() {
		return passwordCheck;
	}
	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public java.util.Calendar getTimestampField() {
		return timestampField;
	}
	public void setTimestampField(java.util.Calendar timestampField) {
		this.timestampField = timestampField;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth.substring(0, 10);
	}
	@Override
	public String toString() {
		return "userVO [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", birth=" + birth + ", gender=" + gender + "]";
	}
	

}
