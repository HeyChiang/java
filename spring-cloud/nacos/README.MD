# 服务配置
本项目的运行要先在nacos【配置管理-配置列表】里添加 nacos-cloud-demo-dev.yml 文件，代码如下
```yaml
server:
  port: 8080
spring:
  cloud:
    nacos:
      config:
        server-addr: '127.0.0.1:8848'
      discovery:
        server-addr: '127.0.0.1:8848'
        username: nacos
        password: nacos
  application:
    name: nacos-cloud-demo

custome:
  test: 'zhangsan'
```
# 使用
有nacos以后，不需要知道服务提供者的ip，可以动态域名解析、配置、限流、服务健康检查、回滚配置。

元数据：描述数据的数据，说明数据储存的位置、数据的映射关系等。

文档：https://nacos.io/zh-cn/docs/quick-start-spring-boot.html
## 服务器提供者
```yaml
spring:
  cloud:
    nacos:
      discovery:
        # 指定 nacos server的地址
        server-addr: localhost:8848
        # 命名空间
        namespace: 0f1fe6bf-c63b-41d0-8dab-3243e825ba61
        # 集群名称
        cluster-name: NJ
        
  application:
    #服务名称，尽量用中划线 -
    name: user-center
```
## 服务订阅者
```java
//查询id对应服务的所有实例的信息
List<ServiceInstance> instances = this.discoveryClient.getInstances("user-center");

//查询当前服务发现组件注册了哪些微服务
List<String> services = this.discoveryClient.getServices();
```
## 负载均衡
Spring Cloud：https://spring-cloud-alibaba-group.github.io/github-pages/hoxton/en-us/index.html#_start_a_consumer_application
通过spring cloud 框架提供的 LoadBalancerClient 可以负载均衡的选择服务提供者。

## 共享多个配置文件
https://spring-cloud-alibaba-group.github.io/github-pages/hoxton/en-us/index.html#_support_custom_groups