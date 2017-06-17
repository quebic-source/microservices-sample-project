package com.quebic.auth.api.service;

import com.quebic.auth.api.model.User;
import com.quebic.common.service.GenericService;

import rx.Single;

public interface SellerService extends GenericService<User>{
	Single<User> registerOtherSeller(User seller);
}
