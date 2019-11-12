package com.cyn.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cyn.bean.UserAddress;
import com.cyn.service.UserService;
import com.cyn.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

	//@Autowired
	/**
	 * 从注册中心发现
	 * version:使用暴露接口的哪个版本。* 的话将随机使用
	 * stub：先调用本地存根
	 * @Reference(url="127.0.0.1:20881") dubbo直接连接的方式
	 * @Reference(loadbalance = "") 负载均衡
	 */
	@Reference(version = "*",stub = "com.cyn.service.impl.UserServiceStub")
	UserService userService;

	/**
	 * @HystrixCommand 出错时调用的方法
	 * @param userId
	 */
	@HystrixCommand(fallbackMethod = "hello")
	@Override
	public List<UserAddress> initOrder(String userId) {
		System.out.println("用户id "+userId);
		List<UserAddress> addressList  = userService.getUserAddressList(userId);
		for (UserAddress userAddress:
		addressList) {
			System.out.println(userAddress.getUserAddress());
		}
		return addressList;
	}

	public List<UserAddress> hello(String userId) {
		return Arrays.asList(new UserAddress(10,"测试地址","1","测试","测试","Y"));
	}


}
