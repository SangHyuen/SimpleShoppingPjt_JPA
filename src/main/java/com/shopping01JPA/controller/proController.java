package com.shopping01JPA.controller;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.apache.ibatis.annotations.Param;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shopping01JPA.domain.basketVO;
import com.shopping01JPA.domain.productVO;
import com.shopping01JPA.domain.returnVO;
import com.shopping01JPA.domain.tokenVO;
import com.shopping01JPA.repository.tokenRepository;
import com.shopping01JPA.service.proService;

@RestController
@RequestMapping(value = "/*")
public class proController {

	static Logger logger = LoggerFactory.getLogger(userController.class);

	@Autowired
	proService proService;


	@RequestMapping(value = "/clickbanner", method = RequestMethod.POST)
	public ResponseEntity<returnVO> banner() {
		returnVO reVO = new returnVO();
		ResponseEntity<returnVO> resEntity;

		reVO = proService.returnBanner(reVO);
		resEntity = new ResponseEntity<returnVO>(reVO, HttpStatus.OK);
		return resEntity;
	}

	@RequestMapping(value = "/proList", method = RequestMethod.GET)
	public ResponseEntity<returnVO> proList(@CookieValue(value = "Token", required = false) String userToken,
			@Param(value = "pageNum") int pageNum) {
		
		returnVO reVO = new returnVO();
		ResponseEntity<returnVO> resEntity;

		reVO = proService.ProductList(userToken,pageNum);
		resEntity = new ResponseEntity<returnVO>(reVO, HttpStatus.OK);
		return resEntity;
	}
	
	@RequestMapping(value = "/basket", method = RequestMethod.POST)
	public ResponseEntity<returnVO> insertBasket(@CookieValue(value = "Token") String userToken,
			@RequestBody productVO proVO) {
	
		basketVO insertBasketVO = new basketVO();
		Integer proID = proVO.getProductId();
		System.out.println(userToken);
		System.out.println("pID = " + proID);
		insertBasketVO = proService.checkBasket(userToken, proID);
		returnVO reVO = new returnVO();
		ResponseEntity<returnVO> resEntity;

		reVO = proService.InsertBasketVO(insertBasketVO);
		resEntity = new ResponseEntity<returnVO>(reVO, HttpStatus.OK);
		return resEntity;
	}
	

	@RequestMapping(value = "/basket", method = RequestMethod.DELETE)
	public ResponseEntity<returnVO> deleteBasket(@CookieValue(value = "Token") String userToken,
			@RequestBody productVO proVO) {
		basketVO deleteBasketVO = new basketVO();
		Integer proID = proVO.getProductId();
		deleteBasketVO = proService.checkBasket(userToken, proID);
		returnVO reVO = new returnVO();
		ResponseEntity<returnVO> resEntity;

		reVO = proService.DeleteBasketVO(deleteBasketVO);
		resEntity = new ResponseEntity<returnVO>(reVO, HttpStatus.OK);
		return resEntity;
	}

	@RequestMapping(value = "/proInfo", method = RequestMethod.GET)
	public ResponseEntity<returnVO> proInfo(@CookieValue(value = "Token") String userToken,
			@RequestBody productVO proVO) {
		int proID = proVO.getProductId();
		returnVO reVO = new returnVO();
		ResponseEntity<returnVO> resEntity;
		if (userToken .equals(null)) {
			reVO.setCode("404");
			reVO.setMessage("제품 상세보기는 로그인 하셔야 사용하실 수 있습니다");
			reVO.setData(null);
		} else {

			reVO = proService.ProductInfo(proID, userToken);
		}
		resEntity = new ResponseEntity<returnVO>(reVO, HttpStatus.OK);
		return resEntity;
	}
	@RequestMapping(value = "/basket", method = RequestMethod.GET)
	public ResponseEntity<returnVO> basketInfo(@CookieValue(value = "Token") String userToken) {
		
		returnVO reVO = new returnVO();
		ResponseEntity<returnVO> resEntity;

		reVO = proService.BasketInfo(userToken);
		resEntity = new ResponseEntity<returnVO>(reVO, HttpStatus.OK);
		return resEntity;
	}
	
}