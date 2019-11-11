package com.cyn.service;

import com.cyn.bean.UserAddress;

import java.util.List;

/**
 * 文件描述
 *
 * @ProductName: Hundsun HEP
 * @ProjectName: dubbo-service-consumer
 * @Package: com.cyn.service
 * @Description: note
 * @Author: hspcadmin
 * @CreateDate: 2019/11/8 14:59
 * @UpdateUser: hspcadmin
 * @UpdateDate: 2019/11/8 14:59
 * @UpdateRemark: The modified content
 * @Date
 * @Version: 1.0
 * <p>
 * Copyright © 2019 Hundsun Technologies Inc. All Rights Reserved
 **/
public interface OrderService {
    /**
     *
     * @param userId
     */
    List<UserAddress> initOrder(String userId);
}
