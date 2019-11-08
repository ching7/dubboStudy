package com.cyn.service.impl;


import com.cyn.bean.UserAddress;
import com.cyn.service.OrderService;
import com.cyn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	UserService userService;

	/**
	 * @param userId
	 */
	@Override
	public void initOrder(String userId) {
		System.out.println("用户id "+userId);
		List<UserAddress> addressList  = userService.getUserAddressList(userId);
		for (UserAddress userAddress:
		addressList) {
			System.out.println(userAddress.getUserAddress());
		}
	}
}
