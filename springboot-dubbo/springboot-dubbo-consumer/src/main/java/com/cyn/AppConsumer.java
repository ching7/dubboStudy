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
 * @Description: note
 * @Author: hspcadmin
 * @CreateDate: 2019/11/11 11:25
 * @UpdateUser: hspcadmin
 * @UpdateDate: 2019/11/11 11:25
 * @UpdateRemark: The modified content
 * @Date
 * @Version: 1.0
 * <p>
 * Copyright © 2019 Hundsun Technologies Inc. All Rights Reserved
 **/

/**
 * @EnableDubbo 主要用于包扫描
 *
 * springboot整合dubbo三种方式
 * 1、添加dubbo-starter启动器，再applicatio.properties配置属性，使用@Service暴露和@Reference查找服务
 * 2、使用dubbo.xml配置文件
 *  1）导入dubbo-starter启动器,使用@ImportResource导入dubbo配置
 * 3、使用注解API的方式
 *  将每一个组件手动加入spring容器中
 */
@EnableHystrix
@EnableDubbo
@SpringBootApplication
public class AppConsumer {
    public static void main(String[] args) {
        SpringApplication.run(AppConsumer.class,args);
    }
}
