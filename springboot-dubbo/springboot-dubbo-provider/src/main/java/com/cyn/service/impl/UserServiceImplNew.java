package com.cyn.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.cyn.bean.UserAddress;
import com.cyn.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author chenyn
 * @date 2019\11\11
 *
 * 1、将服务提供者注册到注册中心
 *		1）导入dubbo-starter依赖 （包括zk，dubbo，springbbot）
 * 2、让服务消费者去注册中心订阅服务提供者的服务地址
 */

/**
 * version:暴露接口的版本
 */
@Service(version = "2.0.0")
@Component
public class UserServiceImplNew implements UserService {

	/**
	 * @HystrixCommand 出现异常将由hystrix处理
	 *
	 * @param userId
	 * @return
	 */
	@HystrixCommand
	@Override
	public List<UserAddress> getUserAddressList(String userId) {
		//模拟接口出错
		int err = 2/0;
		System.out.println("UserServiceImpl.....new...");
		// TODO Auto-generated method stub
		UserAddress address1 = new UserAddress(1, "北京市昌平区宏福科技园综合楼3层", "1", "李老师", "010-56253825", "Y");
		UserAddress address2 = new UserAddress(2, "深圳市宝安区西部硅谷大厦B座3层（深圳分校）", "1", "王老师", "010-56253825", "N");
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Arrays.asList(address1,address2);
	}

}
