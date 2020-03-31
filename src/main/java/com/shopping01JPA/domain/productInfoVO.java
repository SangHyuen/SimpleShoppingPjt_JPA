package com.shopping01JPA.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
@Entity
@Table(name="proinfo")
public class productInfoVO {
	
	
	@Column(name = "proId")
	private int proId;
	private String proname;
	private String img;
	private String coment;
	
	@Id
	@Column(name = "infonum")
	private int infonum;
	@Transient
	private String basket;
	
	
	public String getBasket() {
		return basket;
	}
	public void setBasket(String basket) {
		this.basket = basket;
	}
	public int getProductId() {
		return proId;
	}
	public void setProductId(int productId) {
		this.proId = productId;
	}
	public String getProductName() {
		return proname;
	}
	public void setProductName(String productName) {
		this.proname = productName;
	}
	public String getProductImg() {
		return img;
	}
	public void setProductImg(String productImg) {
		this.img = productImg;
	}
	public String getComent() {
		return coment;
	}
	public void setComent(String coment) {
		this.coment = coment;
	}
	public int getInfoNum() {
		return infonum;
	}
	public void setInfoNum(int infoNum) {
		this.infonum = infoNum;
	}
	
	@Override
	public String toString() {
		return "productInfoVO [productId=" + proId + ", productName=" + proname + ", productImg=" + img
				+ ", coment=" + coment + ", infoNum=" + infonum + "]";
	}

	
	
}
