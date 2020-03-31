package com.shopping01JPA.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
@Entity
@Table(name="basket")
public class basketVO {
	@Column(name = "userId")
	private int userId;
	@Id
	@Column(name = "proId")
	private int productId;
	@Transient
	private String productName;
	@Transient
	private String productImg;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	@Override
	public String toString() {
		return "basketVO [userId=" + userId + ", productId=" + productId + "]";
	}
	

}
