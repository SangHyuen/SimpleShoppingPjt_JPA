package com.shopping01JPA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping01JPA.domain.basketVO;

public interface basketRepository extends JpaRepository<basketVO, Long> {
	List<basketVO> findAllByuserId(int Id);


}
