package com.shopping01JPA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shopping01JPA.domain.returnVO;
import com.shopping01JPA.domain.userVO;
import com.shopping01JPA.service.userService;

@RestController
@RequestMapping(value = "/*")
public class userController {
	


	   @Autowired	
	   userVO userVO;
	   
	   @Autowired
	   userService userService;
	   
	   @RequestMapping(value = "/signup", method = RequestMethod.POST)
	   public ResponseEntity<returnVO> signInfo(@RequestBody userVO signVO) {
	      returnVO reVO = new returnVO();
	      ResponseEntity<returnVO> resEntity;


	      reVO = userService.addUser(signVO);
	      resEntity = new ResponseEntity<returnVO>(reVO, HttpStatus.OK);
	      return resEntity;
	   }
	   
	   @RequestMapping(value = "/login", method = RequestMethod.GET)
	   public ResponseEntity<returnVO> userInfo(@RequestBody userVO loginVO) {
	      returnVO reVO = new returnVO();
	      ResponseEntity<returnVO> resEntity;

	      reVO = userService.loginUser(loginVO);
	      resEntity = new ResponseEntity<returnVO>(reVO, HttpStatus.OK);
	      return resEntity;
	   }



}
