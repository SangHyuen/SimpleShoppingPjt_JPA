package com.shopping01JPA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping01JPA.domain.tokenVO;
import com.shopping01JPA.domain.userVO;


@Repository
public interface tokenRepository extends JpaRepository<tokenVO, Long> {
	tokenVO findBytoken(String token);

}
