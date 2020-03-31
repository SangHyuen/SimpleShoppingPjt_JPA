package com.shopping01JPA.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
@Entity
@Table(name="banner")
public class bannerVO {
	@Id
	private int bannerId;
	private String coment;


	public int getBannerId() {
		return bannerId;
	}
	public void setBannerId(int id) {
		this.bannerId = id;
	}
	public String getComent() {
		return coment;
	}
	public void setComent(String coment) {
		this.coment = coment;
	}
	@Override
	public String toString() {
		return "bannerVO [bannerid=" + bannerId + ", coment=" + coment + "]";
	}

}
