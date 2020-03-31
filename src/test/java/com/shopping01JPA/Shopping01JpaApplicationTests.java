package com.shopping01JPA;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.shopping01JPA.domain.productVO;
import com.shopping01JPA.repository.productRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class Shopping01JpaApplicationTests {
	@Autowired
	productRepository prodrepo;
//	@Test
//	void contextLoads() {
//	}
////	
//	@Test
//	public void test1() {
//		int proId = 1;
//		Pageable paging = PageRequest.of(0, 4);
//		
////		List<productVO> pagedprod = prodrepo.findALLProduct(paging);
//		Page<productVO> pagedprod = prodrepo.findALL(paging);
//		List<productVO> listprod =  pagedprod.getContent();
//		listprod = pagedprod.getContent();
//		for (productVO productVO :listprod) {
//			System.out.println("test1 . productVO = "+productVO.toString() );
//		}
//	}
//	@Test
//	public void test2() {
//		productVO prodvo = prodrepo.findByCost(25000);
//		System.out.println(prodvo.toString());
//	}
//	@Test
//	public void test3() {
//		Pageable paging = PageRequest.of(0, 4);
//		List<productVO> oList = prodrepo.findAll(paging);
////		System.out.println(prodvo.toString());
//		for (productVO productVO : oList) {
//			System.out.println(productVO.toString());
//		}
//	}

}
