package com.shopping01JPA.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
@Entity
@Table(name="product")
public class productVO {
	
	@Id
	@Column(name = "proId")
	private int productId;
	@Column(name = "proname")
	private String productName;
	@Column(name = "img")
	private String productImg;
	private int cost;
	private int sailCost;
	private String basket;
	@Column(name = "created_At")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Calendar createdAt;
	

	public java.util.Calendar getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(java.util.Calendar createdAt) {
		this.createdAt = createdAt;
	}
	public String getBasket() {
		return basket;
	}
	public void setBasket(String basket) {
		this.basket = basket;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
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
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getSailCost() {
		return sailCost;
	}
	public void setSailCost(int sailCost) {
		this.sailCost = sailCost;
	}
	
	@Override
	public String toString() {
		return "productVO [productId=" + productId + ", productName=" + productName + ", productImg=" + productImg
				+ ", cost=" + cost + ", sailCost=" + sailCost  + "]";
	}
	
}
