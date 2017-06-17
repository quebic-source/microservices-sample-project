package com.quebic.product.api.dao.impl;

import org.springframework.stereotype.Repository;
import com.quebic.common.dao.impl.GenericDaoImpl;
import com.quebic.product.api.dao.ProductDao;
import com.quebic.product.api.model.Product;

@Repository
public class ProductDaoImpl extends GenericDaoImpl<Product> implements ProductDao{
	
	public ProductDaoImpl() {
		super(Product.class);
	}
}
