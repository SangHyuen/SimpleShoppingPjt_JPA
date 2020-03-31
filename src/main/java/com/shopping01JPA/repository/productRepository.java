package com.shopping01JPA.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.shopping01JPA.domain.productVO;

public interface productRepository extends CrudRepository<productVO, Integer> {
	//productVO findAllById(String Id);

	List<productVO> findAll(Pageable pageable);


}
