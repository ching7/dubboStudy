package com.cyn;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * 文件描述
 *
 * @ProductName: Hundsun HEP
 * @ProjectName: springboot-dubbo
 * @Package: PACKAGE_NAME
 * @Description:
 *
 * @EnableHystrix 开启服务容错功能
 *
 * @Author: hspcadmin
 * @CreateDate: 2019/11/11 14:15
 * @UpdateUser: hspcadmin
 * @UpdateDate: 2019/11/11 14:15
 * @UpdateRemark: The modified content
 * @Date
 * @Version: 1.0
 * <p>
 * Copyright © 2019 Hundsun Technologies Inc. All Rights Reserved
 **/
@EnableDubbo
@EnableHystrix
@SpringBootApplication
public class AppProvider {
    public static void main(String[] args) {
        SpringApplication.run(AppProvider.class,args);
    }
}
