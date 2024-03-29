# dubboStudy 

***

## 1、dubbo-service

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

* dubbo的高可用

  * zookeeper宕机与dubbo直连

    ~~~properties
    现象：zookeeper注册中心宕机，还可以消费dubbo暴露的服务。
    原因：
    健壮性
    - 监控中心宕掉不影响使用，只是丢失部分采样数据
    - 数据库宕掉后，注册中心仍能通过缓存提供服务列表查询，但不能注册新服务
    - 注册中心对等集群，任意一台宕掉后，将自动切换到另一台
    - 注册中心全部宕掉后，服务提供者和服务消费者仍能通过本地缓存通讯
    - 服务提供者无状态，任意一台宕掉后，不影响使用
    - 服务提供者全部宕掉后，服务消费者应用将无法使用，并无限次重连等待服务提供者恢复
    高可用：通过设计，减少系统不能提供服务的时间；
    
    ~~~

* dubbo负载均衡机制

  * 负载均衡机制

    ~~~properties
    @Reference(loadbalance = "") 负载均衡
    
    Random LoadBalance
    随机，按权重设置随机概率。
    在一个截面上碰撞的概率高，但调用量越大分布越均匀，而且按概率使用权重后也比较均匀，有利于动态调整提供者权重。
    
    RoundRobin LoadBalance
    轮循，按公约后的权重设置轮循比率。
    存在慢的提供者累积请求的问题，比如：第二台机器很慢，但没挂，当请求调到第二台时就卡在那，久而久之，所有请求都卡在调到第二台上。
    
    LeastActive LoadBalance
    最少活跃调用数，相同活跃数的随机，活跃数指调用前后计数差。
    使慢的提供者收到更少请求，因为越慢的提供者的调用前后计数差会越大。
    
    ConsistentHash LoadBalance
    一致性 Hash，相同参数的请求总是发到同一提供者。
    当某一台提供者挂时，原本发往该提供者的请求，基于虚拟节点，平摊到其它提供者，不会引起剧烈变动。算法参见：http://en.wikipedia.org/wiki/Consistent_hashing
    缺省只对第一个参数 Hash，如果要修改，请配置 <dubbo:parameter key="hash.arguments" value="0,1" />
    缺省用 160 份虚拟节点，如果要修改，请配置 <dubbo:parameter key="hash.nodes" value="320" />
    
    ~~~

* dubbo服务降级

  * 合理调整服务器资源，对一些服务和页面有策略的不处理或换种简单的方式处理，从而释放服务器资源以保证核心交易正常运作或高效运作
  
  * dubbo 提供两种降级方式
  
    1、调用远程服务时，客户端强制返回为空
  
    直接在dubbo管理台，屏蔽服务（consumer客户端设置屏蔽）![服务屏蔽](image/dubbo.jpg)
  
    2、调用远程服务失败（超时，不可达等）时，客户端直接返回为空(consumer客户端设置容错）
  
    ![服务容错](image/dubbo2.jpg)
  
    
  
    ~~~java
    可以通过服务降级功能临时屏蔽某个出错的非关键服务，并定义降级后的返回策略。
    向注册中心写入动态配置覆盖规则：
    RegistryFactory registryFactory = ExtensionLoader.getExtensionLoader(RegistryFactory.class).getAdaptiveExtension();
    Registry registry = registryFactory.getRegistry(URL.valueOf("zookeeper://10.20.153.10:2181"));
    registry.register(URL.valueOf("override://0.0.0.0/com.foo.BarService?category=configurators&dynamic=false&application=foo&mock=force:return+null"));
    其中：
    - mock=force:return+null 表示消费方对该服务的方法调用都直接返回 null 值，不发起远程调用。用来屏蔽不重要服务不可用时对调用方的影响。
    - 还可以改为 mock=fail:return+null 表示消费方对该服务的方法调用在失败后，再返回 null 值，不抛异常。用来容忍不重要服务不稳定时对调用方的影响。
    
    ~~~

* 集群容错

  ~~~java
  在集群调用失败时，Dubbo 提供了多种容错方案，缺省为 failover 重试。
  集群容错模式
  Failover Cluster
  失败自动切换，当出现失败，重试其它服务器。通常用于读操作，但重试会带来更长延迟。可通过 retries="2" 来设置重试次数(不含第一次)。
  
  重试次数配置如下：
  <dubbo:service retries="2" />
  或
  <dubbo:reference retries="2" />
  或
  <dubbo:reference>
      <dubbo:method name="findFoo" retries="2" />
  </dubbo:reference>
  
  Failfast Cluster
  快速失败，只发起一次调用，失败立即报错。通常用于非幂等性的写操作，比如新增记录。
  
  Failsafe Cluster
  失败安全，出现异常时，直接忽略。通常用于写入审计日志等操作。
  
  Failback Cluster
  失败自动恢复，后台记录失败请求，定时重发。通常用于消息通知操作。
  
  Forking Cluster
  并行调用多个服务器，只要一个成功即返回。通常用于实时性要求较高的读操作，但需要浪费更多服务资源。可通过 forks="2" 来设置最大并行数。
  
  Broadcast Cluster
  广播调用所有提供者，逐个调用，任意一台报错则报错 [2]。通常用于通知所有提供者更新缓存或日志等本地资源信息。
  
  集群模式配置
  按照以下示例在服务提供方和消费方配置集群模式
  <dubbo:service cluster="failsafe" />
  或
  <dubbo:reference cluster="failsafe" />
  
  ~~~

* 整合Hystrix进行服务服务容错，熔断机制

  * 导入依赖

  ~~~xml
  <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>
                  spring-cloud-starter-netflix-hystrix
        </artifactId>
  </dependency>
  ~~~

  * 启用Hystrix
    - provider,consumer 启动类添加注解@EnableHystrix
    - 服务器接口实现方法上添加注解@HystrixCommand
    - 调用者调用方法添加注解@HystrixCommand(fallbackMethod = "hello")、fallbackMethod为出错时调用的方法（熔断机制）

## 3、Dubbo原理

* RPC框架原理

  ![](image/RPC.jpg)

  一次完整的RPC调用流程（同步调用，异步另说）如下： 

  1）服务消费方（client）调用以本地调用方式调用服务； 

  2）client stub接收到调用后负责将方法、参数等组装成能够进行网络传输的消息体； 

  3）client stub找到服务地址，并将消息发送到服务端； 

  4）server stub收到消息后进行解码； 

  5）server stub根据解码结果调用本地的服务； 

  6）本地服务执行并将结果返回给server stub； 

  7）server stub将返回结果打包成消息并发送至消费方； 

  8）client stub接收到消息，并进行解码； 

  9）服务消费方得到最终结果。

  RPC框架的目标就是要这些步骤都封装起来，这些细节对用户来说是透明的，不可见的。

* netty通讯原理

  待添加

* dubbo原理

  参考官网流程图

  

  ![](image/dubbo3.jpg)

  ~~~properties
  - config 配置层：对外配置接口，以 ServiceConfig, ReferenceConfig 为中心，可以直接初始化配置类，也可以通过 spring 解析配置生成配置类
  
  - proxy 服务代理层：服务接口透明代理，生成服务的客户端 Stub 和服务器端 Skeleton, 以 ServiceProxy 为中心，扩展接口为 ProxyFactory
  
  - registry 注册中心层：封装服务地址的注册与发现，以服务 URL 为中心，扩展接口为 RegistryFactory, Registry, RegistryService
  
  - cluster 路由层：封装多个提供者的路由及负载均衡，并桥接注册中心，以 Invoker 为中心，扩展接口为 Cluster, Directory, Router, LoadBalance
  
  - monitor 监控层：RPC 调用次数和调用时间监控，以 Statistics 为中心，扩展接口为 MonitorFactory, Monitor, MonitorService
  
  - protocol 远程调用层：封装 RPC 调用，以 Invocation, Result 为中心，扩展接口为 Protocol, Invoker, Exporter
  
  - exchange 信息交换层：封装请求响应模式，同步转异步，以 Request, Response 为中心，扩展接口为 Exchanger, ExchangeChannel, ExchangeClient, ExchangeServer
  
  - transport 网络传输层：抽象 mina 和 netty 为统一接口，以 Message 为中心，扩展接口为 Channel, Transporter, Client, Server, Codec
  
  - serialize 数据序列化层：可复用的一些工具，扩展接口为 Serialization, ObjectInput, ObjectOutput, ThreadPool
  
  ~~~

  服务暴露

  ![](image/dubbo4.jpg)

  服务引用

  ![](image/dubbo5.jpg)

  服务调用

  ![](image/dubbo6.jpg)