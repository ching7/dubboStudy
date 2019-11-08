package com.cyn.bean;

import java.io.Serializable;

/**
 * 文件描述
 *
 * @ProductName: Hundsun HEP
 * @ProjectName: dubbo-service-provider
 * @Package: com.cyn.bean
 * @Description: note
 * @Author: hspcadmin
 * @CreateDate: 2019/11/8 14:53
 * @UpdateUser: hspcadmin
 * @UpdateDate: 2019/11/8 14:53
 * @UpdateRemark: The modified content
 * @Date
 * @Version: 1.0
 * <p>
 * Copyright © 2019 Hundsun Technologies Inc. All Rights Reserved
 **/
public class UserAddress implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String UserAddress;
    private String orderNo;
    private String userName;
    private String userTel;
    private String isOne;

    public UserAddress(Integer id, String userAddress, String orderNo, String userName, String userTel, String isOne) {
        this.id = id;
        UserAddress = userAddress;
        this.orderNo = orderNo;
        this.userName = userName;
        this.userTel = userTel;
        this.isOne = isOne;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserAddress() {
        return UserAddress;
    }

    public void setUserAddress(String userAddress) {
        UserAddress = userAddress;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getIsOne() {
        return isOne;
    }

    public void setIsOne(String isOne) {
        this.isOne = isOne;
    }
}
