package com.shopping01JPA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.shopping01JPA.domain.userVO;

@Repository
public interface userRepository extends JpaRepository<userVO, Long> {
	
	userVO findByEmail(String email);
   
}
