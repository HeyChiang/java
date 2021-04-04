本项目的运行要先在nacos里添加 nacos-cloud-demo-dev.yml 文件，代码如下
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