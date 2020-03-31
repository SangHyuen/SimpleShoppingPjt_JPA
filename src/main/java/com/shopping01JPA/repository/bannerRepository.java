package com.shopping01JPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping01JPA.domain.bannerVO;

public interface bannerRepository extends JpaRepository<bannerVO, Integer>{
	bannerVO findBybannerId(int id);
}
