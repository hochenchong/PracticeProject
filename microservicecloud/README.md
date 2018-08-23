## SpringCloud Demo 项目说明
### 前言
> 这个项目是跟着尚硅谷的 SpringCloud 教学视频的练习 Demo，以此来了解 SpringCloud 的一些知识。教学视频可以在尚硅谷的视频下载中进行下载学习。

---

### 本地 hosts 文件新增以下配置
```
# microservicecloud
127.0.0.1       eureka7001.com
127.0.0.1       eureka7002.com
127.0.0.1       eureka7003.com
127.0.0.1       myzuul.com
127.0.0.1       config-3344.com
127.0.0.1       client-config.com
```

---

### 项目介绍
```
microservicecloud ：父工程，打包方式为 pom，该 maven 工程下的都是 maven 模块，打包方式为 jar
	- microservicecloud-api ：提取出来的公共 api 
	- microservicecloud-eureka-7001 ：Eureka 服务器，端口 7001
	- microservicecloud-eureka-7002 ：Eureka 服务器，端口 7002
	- microservicecloud-eureka-7003 ：Eureka 服务器，端口 7003
	- microservicecloud-provider-dept-8001 ：服务提供者，端口 8001
	- microservicecloud-provider-dept-8002 ：服务提供者，端口 8002
	- microservicecloud-provider-dept-8003 ：服务提供者，端口 8003
	- microservicecloud-consumer-dept-80 : 服务消费者，端口 80，使用 Ribbon + RestTemplate 方式进行服务调用
	- microservicecloud-consumer-dept-feign ：服务消费者，端口 80，使用 Feign 进行接口调用（对比 microservicecloud-consumer-dept-80 项目）
	- microservicecloud-provider-dept-hystrix-8001 ：服务提供者，端口 8001，使用 Hystrix 断路器
	- microservicecloud-consumer-hystrix-dashboard ：服务监控，端口 9001
	- microservicecloud-zuul-gateway-9527 ：Zuul 路由网关，端口 9527
	- microservicecloud-config-3344 ： Config 服务端，端口 3344
	- microservicecloud-config-client-3355 ： Config 客户端，端口 3355
	- microservicecloud-config-eureka-client-7001 ： Config 版本的 Eureka 服务器，端口 7001
	- microservicecloud-config-dept-client-8001 ： Config 版的服务提供者，端口 8001
```

---

### Eureka 服务注册与发现
> 用途：服务注册与发现

#### 对比 zookeeper
##### CAP 理论
> * C(一致性)
> * A(可用性)
> * P(分区容错性)

##### Eureka 保证 AP
> 优先保证可用性。Eureka 各个节点都是平等的，几个节点挂掉不会影响正常节点的工作，剩余的节点依然可以提供注册和查询服务。

##### zookeeper 保证 CP
> zookeeper 会出现这样一种情况，当 master 节点因为网络故障与其他节点失去联系时，剩余节点会重新进行 leader 选举。问题在于，选举 leader 的时间太长，30 ~ 120s，且选举期间整个zookeeper 集群都是不可用的，这就导致在选举期间注册服务瘫痪。在云部署的环境下，因网络问题使得zookeeper 集群失去 master 节点是较大概率会发生的事，虽然服务能够最终恢复，但是漫长的选举时间导致的注册长期不可用是不能容忍的。


##### 结论
>  Eureka 可以很好的应对因网络故障导致部分节点失去联系的情况，而不会像 zookeeper 那样使整个注册服务瘫痪。

---

### Ribbon 负载均衡

---

### Feign 负载均衡

---

### Hystrix 断路器

---

### Zuul 路由网关

---

### SpringCloud Config 分布式配置中心

---