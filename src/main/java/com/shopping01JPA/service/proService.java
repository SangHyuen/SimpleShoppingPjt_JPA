package com.shopping01JPA.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.loading.PrivateClassLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.shopping01JPA.domain.bannerVO;
import com.shopping01JPA.domain.basketVO;
import com.shopping01JPA.domain.productInfoVO;
import com.shopping01JPA.domain.productVO;
import com.shopping01JPA.domain.returnVO;
import com.shopping01JPA.domain.tokenVO;
import com.shopping01JPA.repository.bannerRepository;
import com.shopping01JPA.repository.basketRepository;
import com.shopping01JPA.repository.productRepository;
import com.shopping01JPA.repository.productInfoRepository;
import com.shopping01JPA.repository.tokenRepository;

@Repository
public class proService {

	@Autowired
	private userService userService;

	@Autowired
	private productRepository prodRepo;

	@Autowired
	private basketRepository basketRepo;
	@Autowired
	private bannerRepository bannerRepo;
	@Autowired
	private productInfoRepository proInfoRepo;

	int bannerPageNum = 1;
	int proPageNum = 0;

	public bannerVO selectBanner(bannerVO banVO) {
		bannerVO result = new bannerVO();
		result = bannerRepo.findBybannerId(banVO.getBannerId());
		return result;
	}

	public List<productVO> selectProduct(int pageNum) {
		System.out.println("num = " + pageNum);
		List<productVO> resultList = new ArrayList<productVO>();
		Pageable paging = PageRequest.of(pageNum, 6);
		resultList = prodRepo.findAll(paging);

		return resultList;
	}

	public List<basketVO> selectBasket(Integer userId) {
		List<basketVO> resultList = new ArrayList<basketVO>();
		resultList = basketRepo.findAllByuserId(userId);
		return resultList;
	}

	public int insertBasket(basketVO insertBasketVO) {
		int result;
		try {
			basketVO resultVO = new basketVO();
			resultVO.setUserId(insertBasketVO.getUserId());
			resultVO.setProductId(insertBasketVO.getProductId());

			basketVO commitVO = basketRepo.save(resultVO);
			result = 1;
		} catch (Exception e) {
			System.out.println(e);
			result = 0;
		}
		return result;
	}

	public int deleteBasket(basketVO deleteBasketVO) {
		int result;
		try {
			basketVO resultVO = new basketVO();
			resultVO.setUserId(deleteBasketVO.getUserId());
			resultVO.setProductId(deleteBasketVO.getProductId());

			basketRepo.delete(resultVO);
			result = 1;
		} catch (Exception e) {
			System.out.println(e);
			result = 0;
		}
		return result;
	}

	public List<productInfoVO> selectAllProductInfo() {
		List<productInfoVO> resultList = new ArrayList<productInfoVO>();
		resultList = proInfoRepo.findAll();
		return resultList;
	}


	public List<basketVO> selectBasketInfo(Integer userId) {
		List<basketVO> resultList = new ArrayList<basketVO>();
		resultList = basketRepo.findAllByuserId(userId);
		List<productInfoVO> infoList = new ArrayList<productInfoVO>();
		infoList = proInfoRepo.findAll();
		if (infoList == null) {
			basketVO error = new basketVO();
			error.setProductName("장바구니에 담은 목록이 없습니다.");
			return resultList;
		}
		for (basketVO basketVO : resultList) {
			for (productInfoVO productInfoVO : infoList) {
				if (productInfoVO.getProductId() == basketVO.getProductId()) {
					basketVO.setProductName(productInfoVO.getProductName());
					basketVO.setProductImg(productInfoVO.getProductImg());
				}
			}
		}
		return resultList;
	}

	public basketVO checkBasket(String token, Integer proId) {

		basketVO result = new basketVO();
		tokenVO selectToken = new tokenVO();
		selectToken = userService.selectUserbyToken(token);
		Integer userId = selectToken.getUserId();
		result.setUserId(userId);
		result.setProductId(proId);

		return result;
	}

	public returnVO returnBanner(returnVO ReVO) {
		bannerVO BannerVO = new bannerVO();
		if (bannerPageNum > 5) {
			bannerPageNum = 1;
		}
		BannerVO.setBannerId(bannerPageNum);
		BannerVO = selectBanner(BannerVO);
		bannerPageNum = bannerPageNum + 1;

		ReVO.setCode("200");
		ReVO.setMessage("Banner");
		ReVO.setData(BannerVO);

		return ReVO;
	}

	public returnVO ProductList(String userToken, int pageNum) {
//page넘버를 파라메터로 받아와서 해당 페이지 범위 출력하는 알고리즘.
		returnVO ReVO = new returnVO();
		productVO proVO = new productVO();
		tokenVO selectToken = new tokenVO();
		selectToken = userService.selectUserbyToken(userToken);
		Integer userId = new Integer(0);
		if (selectToken == null) {
			userId = 0;
		} else {
			userId = selectToken.getUserId();
		}
		List<basketVO> basketList = new ArrayList<basketVO>();
		List<productVO> resultList = new ArrayList<productVO>();
		if (pageNum == 1) {
			proPageNum = 0;
		} else {
			proPageNum = (pageNum - 1);
		}

		resultList = selectProduct(proPageNum);
		if (userId != null) {
			basketList = selectBasket(userId);
			for (basketVO basVO : basketList) {
				for (productVO indexVO : resultList) {
					proPageNum = indexVO.getProductId();
					if (indexVO.getBasket() == null) {
						indexVO.setBasket("장바구니담기");
					}
					if (basVO.getProductId() == indexVO.getProductId()) {
						indexVO.setBasket("장바구니보기");
					}
				}
			}
		}

		ReVO.setCode("200");
		ReVO.setMessage("proList");
		ReVO.setData(resultList);
		return ReVO;
	}

	public returnVO InsertBasketVO(basketVO paramVO) {
		returnVO ReVO = new returnVO();
		int result;
		result = insertBasket(paramVO);
		if (result == 1) {
			ReVO.setCode("200");
			ReVO.setMessage("Success");
			ReVO.setData("입력완료");
		} else {
			ReVO.setCode("500");
			ReVO.setMessage("Error");
			ReVO.setData("입력실패, 잠시후 다시 시도하여 주십시오");
		}
		return ReVO;
	}

	public returnVO DeleteBasketVO(basketVO paramVO) {
		returnVO ReVO = new returnVO();
		int result;
		result = deleteBasket(paramVO);
		if (result == 1) {
			ReVO.setCode("200");
			ReVO.setMessage("Success");
			ReVO.setData("삭제완료");
		} else {
			ReVO.setCode("500");
			ReVO.setMessage("Error");
			ReVO.setData("삭제실패, 잠시후 다시 시도하여 주십시오");
		}
		return ReVO;
	}

	public returnVO ProductInfo(int proId, String userToken) {

		returnVO ReVO = new returnVO();
		List<basketVO> basketList = new ArrayList<basketVO>();
		List<productInfoVO> resultInfoList = new ArrayList<productInfoVO>();
		List<productInfoVO> returnInfo = new ArrayList<productInfoVO>();
		
		tokenVO selectToken = new tokenVO();
		selectToken = userService.selectUserbyToken(userToken);
		Integer userId = selectToken.getUserId();
		basketList = selectBasket(userId);//토큰으로 사용자의 장바구니 리스트 조회
		
		resultInfoList = selectAllProductInfo();// 전체 물품 리스트
		//전체 물품 리스트 중 현재 선택한 물품번호에 맞는 리스트 생성
		for (productInfoVO indexVO : resultInfoList) {
			if (indexVO.getProductId() == proId) {
				returnInfo.add(indexVO);
			}
		}

		for (basketVO basVO : basketList) {
			for (productInfoVO indexVO : returnInfo) {
				if (indexVO.getBasket() == null) {
					indexVO.setBasket("장바구니담기");
				}
				if (basVO.getProductId() == indexVO.getProductId()) {
					indexVO.setBasket("장바구니보기");
				}
			}
		}

		ReVO.setCode("200");
		ReVO.setMessage("proList");
		ReVO.setData(returnInfo);
		return ReVO;

	}

	public returnVO BasketInfo(String userToken) {
		tokenVO user = new tokenVO();
		user = userService.selectUserbyToken(userToken);
		Integer userId = user.getUserId();
		List<basketVO> resultList = new ArrayList<basketVO>();
		List<basketVO> returnList = new ArrayList<basketVO>();
		returnVO ReVO = new returnVO();
		resultList = selectBasketInfo(userId);
		for (basketVO indexVO : resultList) {
			if (indexVO.getUserId() == userId) {
				returnList.add(indexVO);
			}
		}
		if (resultList != null) {
			ReVO.setCode("200");
			ReVO.setMessage("Success");
			ReVO.setData(returnList);
		} else {
			ReVO.setCode("500");
			ReVO.setMessage("Error");
			ReVO.setData("조회실패, 잠시후 다시 시도하여 주십시오");
		}
		return ReVO;
	}

}
