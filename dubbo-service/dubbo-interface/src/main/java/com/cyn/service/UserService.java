package com.cyn.service;


import com.cyn.bean.UserAddress;

import java.util.List;

/**
 * 文件描述
 *
 * @ProductName: Hundsun HEP
 * @ProjectName: dubbo-service-provider
 * @Package: com.cyn.service
 * @Description: note
 * @Author: hspcadmin
 * @CreateDate: 2019/11/8 14:54
 * @UpdateUser: hspcadmin
 * @UpdateDate: 2019/11/8 14:54
 * @UpdateRemark: The modified content
 * @Date
 * @Version: 1.0
 * <p>
 * Copyright © 2019 Hundsun Technologies Inc. All Rights Reserved
 **/
public interface UserService {
    /**
     *
     * @param userId
     * @return
     */
    List<UserAddress> getUserAddressList(String userId);
}
