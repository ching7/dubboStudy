# dubboStudy 

***

## 1.dubbo-service

* dubbo基于spring-mvc架构的dubbo演示demo
* 基于idea的跨模块依赖demo
* 基于dubbo文档 [*服务最佳化实践-分包*](http://dubbo.apache.org/zh-cn/docs/user/best-practice.html) 原理，将服务接口、服务模型、服务异常等分模块

## 2.springboot-dubbo

* springboot整合dubbo基础demo

* 将springmvc中的xml配置改为springboot的properties配置

* @com.alibaba.dubbo.config.annotation.Service 暴露在注册中心上应用的接口服务

* @com.alibaba.dubbo.config.annotation.Reference 从注册中心加载接口服务到当前服务

* 生产者和服务者的pom文件需要注意

  * 都需要继承父类 spring-boot-starter-parent
  * 引入springboot启动器：spring-boot-starter 、dubbo启动器：dubbo-spring-boot-starter

* dubbo的示例(可以参考 [*dubbo文档示例*](http://dubbo.apache.org/zh-cn/docs/user/demos/preflight-check.html) )

  ~~~properties
  ## 服务名
  dubbo.application.name=springboot-dubbo-consumer
  ## 注册中心和地址
  dubbo.registry.address=127.0.0.1:2181
  # 注册中心不存在是否报错
  dubbo.registry.check=false
  dubbo.registry.protocol=zookeeper
  ## 与注册中心通讯的通讯协议和端口
  dubbo.protocol.name=dubbo
  dubbo.protocol.port=20879
  ## dubbo简单的服务监视器
  dubbo.monitor.protocol=registry
  # 下列属性也可在@Reference上用属性单独配置
  # 配置声明的优先级
  # 1）精确优先（方法级优先，接口次之，全局再次之）
  # 2）消费者优先（如果级别一致，则消费者优先，提供方次之）
  
  ## 启动检查-消费者启动时是否检查生产是否存在
  dubbo.consumer.check=false
  
  # 等待重试-消费者等待提供者的应答最大等待时间 默认是1000
  dubbo.consumer.timeout=3000
  # 消费者调用提供者重试次数(不包含首次)
  # 幂等【查，删，改数据】（设置重试次数），非幂等【新增】（不能设置） ，幂等-方法执行多次和执行一次效果相同
  dubbo.consumer.retries=3
  
  # 灰度升级-新老版本交替
  # 提供者暴露的接口加上 @Service(version="版本") ，消费者引用的接口加上@Reference(version="版本")
  
  # 本地存根-远程服务后，客户端通常只剩下接口，而实现全在服务器端，但提供方有些时候想在客户端也执行部分逻辑类似参数权限校验之类的
  # 消费者引用的接@Reference(stub="本地存根全路径")
  ~~~

  