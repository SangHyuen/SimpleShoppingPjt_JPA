package com.shopping01JPA.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping01JPA.domain.resultUserVO;
import com.shopping01JPA.domain.returnVO;
import com.shopping01JPA.domain.tokenVO;
//import com.shopping01.domain.tokenVO;
import com.shopping01JPA.domain.userVO;
import com.shopping01JPA.repository.tokenRepository;
import com.shopping01JPA.repository.userRepository;


//오프셋과 리미트를 사용해서 페이징 처리

@Service
public class userService {
	
	@Autowired
	userRepository userRepo;
	
	@Autowired
	tokenRepository tokenRepo;

	public userVO selectUserbyEmail(String Email){
		userVO resultVO = new userVO();
		resultVO = userRepo.findByEmail(Email);
		return resultVO;
	}
	public tokenVO selectUserbyToken(String userToken) {
		tokenVO resultVO = tokenRepo.findBytoken(userToken);
		return resultVO;
	}

	
	public returnVO loginUser(userVO loginVO) {
		returnVO ReVO = new returnVO();
		userVO loginUser = new userVO();
		loginUser = selectUserbyEmail(loginVO.getEmail());
		resultUserVO reuser = new resultUserVO();
		if (loginUser != null) {
			if (loginUser.getPassword().equals(loginVO.getPassword())) {
				reuser.setUserId(loginUser.getUserId());
				reuser.setName(loginUser.getName());
				reuser.setEmail(loginUser.getEmail());
				reuser.setBirth(loginUser.getBirth());
				reuser.setGender(loginUser.getGender());
				ReVO.setCode("200");
				ReVO.setMessage("환영합니다 " + loginUser.getName() + "님");
				ReVO.setData(reuser);
			} else {
				ReVO.setCode("비밀번호 오류");
				ReVO.setMessage("로그인 정보가 올바르지 않습니다.");
			}
		} else {
			ReVO.setCode("No User");
			ReVO.setMessage("사용자 정보가 없습니다.");
		}
		return ReVO;
	}


	public returnVO addUser(userVO adduserVO) {
		returnVO reVO = new returnVO();
		String birth = adduserVO.getBirth();
		String age = birth.substring(0, 4);
	
		int intage = Integer.parseInt(age);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int userAge = year - intage;
		userVO checkUser = new userVO();
		resultUserVO reuser = new resultUserVO();
		checkUser = selectUserbyEmail(adduserVO.getEmail());
		
		if (checkUser != null) {
			reVO.setCode("Account Error");
			reVO.setMessage("Email중복입니다.");
		} else if (!adduserVO.getPassword().equals(adduserVO.getPasswordCheck())) {
			reVO.setCode("Password Error");
			reVO.setMessage("비밀번호가 일치하지 않습니다.");
		} else if (userAge < 7) {
			reVO.setCode("Age Error");
			reVO.setMessage("7세 미만은 가입할 수 없습니다.");
		} else {
			try {
				userVO JPAuserVO = new userVO();
				JPAuserVO.setEmail(adduserVO.getEmail());
				JPAuserVO.setName(adduserVO.getName());
				JPAuserVO.setBirth(adduserVO.getBirth());
				JPAuserVO.setPassword(adduserVO.getPassword());
				JPAuserVO.setGender(adduserVO.getGender());
				JPAuserVO.setTimestampField(Calendar.getInstance());

				reVO.setCode("200");
				reVO.setMessage("회원가입성공");
				
				userVO commitVO = userRepo.save(JPAuserVO);
				
				checkUser = selectUserbyEmail(adduserVO.getEmail());
				
				tokenVO JPAtokenVO = new tokenVO();
				JPAtokenVO.setUserId(checkUser.getUserId());
				JPAtokenVO.setToken();
			
				tokenVO tokenResult = tokenRepo.save(JPAtokenVO);
				
			} catch (Exception e) {
				System.out.println(e);
			}
			
			reuser.setName(adduserVO.getName());
			reuser.setEmail(adduserVO.getEmail());
			reuser.setBirth(adduserVO.getBirth());
			reuser.setGender(adduserVO.getGender());
			reVO.setData(reuser);
		}

		return reVO;
	}



}
