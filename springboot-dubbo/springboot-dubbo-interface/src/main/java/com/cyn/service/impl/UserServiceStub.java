package com.cyn.service.impl;


import com.cyn.bean.UserAddress;
import com.cyn.service.UserService;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 文件描述
 *
 * @ProductName: Hundsun HEP
 * @ProjectName: springboot-dubbo
 * @Package: com.cyn.service.impl
 * @Description: note
 * @Author: hspcadmin
 * @CreateDate: 2019/11/11 16:59
 * @UpdateUser: hspcadmin
 * @UpdateDate: 2019/11/11 16:59
 * @UpdateRemark: The modified content
 * @Date
 * @Version: 1.0
 * <p>
 * Copyright © 2019 Hundsun Technologies Inc. All Rights Reserved
 **/
public class UserServiceStub implements UserService {

    private final UserService userService;

    /**
     * dubbo 自动创建本地存根对象，自动传入远程userService代理
     * @param userService
     */
    public UserServiceStub(UserService userService) {
        this.userService = userService;
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public List<UserAddress> getUserAddressList(String userId) {
        System.out.println("调用本地存根---");
        if (!StringUtils.isEmpty(userId)) {
            return userService.getUserAddressList(userId);
        }
        return null;
    }
}
