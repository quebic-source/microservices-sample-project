package com.quebic.product.api.service.impl;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quebic.common.security.SecurityUtil;
import com.quebic.common.service.impl.GenericServiceImpl;
import com.quebic.product.api.dao.ProductDao;
import com.quebic.product.api.model.Product;
import com.quebic.product.api.service.ProductService;

import rx.Single;

@Service
public class ProductServiceImpl extends GenericServiceImpl<Product> implements ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	@PostConstruct
	void init() {
		init(Product.class, productDao);
	}
	
	@Override
	public Single<Product> add(Product product) {
		String sellerId = SecurityUtil.getAuthUserDetails().getUserId();
		product.setSellerId(sellerId);
		return super.add(product);
	}
	
	@Override
	public Single<Product> edit(Product product) {
		String sellerId = SecurityUtil.getAuthUserDetails().getUserId();
		product.setSellerId(sellerId);
		return super.edit(product);
	}
	
}
