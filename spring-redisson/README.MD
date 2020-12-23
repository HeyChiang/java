### 介绍
本人将展示在从阿里云Centos 8.2版本搭建redis 6.0.6集群到使用全过程。

### 搭建环境
######  1.首先下载解压新版本的redis，我用的是redis 6.0，下面命令是下载、解压、编译。
> wget http://download.redis.io/releases/redis-6.0.6.tar.gz
tar xzf redis-6.0.6.tar.gz
cd redis-6.0.6
make

###### 2.编译完成以后，进入到解压后的 src 目录，通过如下命令检查启动是否正常
>src/redis-server

######  3.启动没有问题，配置集群redis.conf。
>port 7000
cluster-enabled yes
cluster-config-file nodes.conf
cluster-node-timeout 5000
appendonly yes
daemonize yes

3.1 如果你需要远程访问redis，需要增加下面的配置。第一行是允许远程连接，第二行是也许所有的ip连接。
>protected-mode no
>bind 0.0.0.0

3.2 如果redis访问需要设置密码，在配置文件里设置下面两个参数。
* masterauth：主要是针对master对应的slave节点设置的，在slave节点数据同步的时候用到。
* requirepass：对登录权限做限制，每个节redis.conf可以是不同的（推荐相同，降低运维成本）。

######  4.复制redis-6.0.6文件夹，分别到7000、7001..等文件夹中
>mkdir cluster-test
cd cluster-test
mkdir 7000 7001 7002 7003 7004 7005

######  5.分别启动他们
>cd 7000
src/redis-server ./redis.conf

###### 6.创建集群节点，前三个为master，后三个为slave
1.注意，如果是远程连接的，这里的ip需要用真实的服务器ip，而不是127.0.0.1
2.部署在阿里云上，需要修改node.conf文件里的ip，有一个会自动变成内网ip
>./redis-cli --cluster create 127.0.0.1:7000 127.0.0.1:7001 127.0.0.1:7002 127.0.0.1:7003 127.0.0.1:7004 127.0.0.1:7005 --cluster-replicas 1

######  7.测试是否成功，在7000端口设置test值，在7005获取看是否成功。
一定要加-c命令，它是启动集群模式的意思。
>src/redis-cli -c -p 7000
>set test haha
>src/redis-cli -c -p 7005
>get test

另外进入src目录，还可以通过以下命令来查看集群的情况。
>./redis-cli -p 7000 cluster nodes

###### 扩展知识
[官网文档](http://www.redis.cn/topics/cluster-tutorial.html)，官网里面有一些错误本文已经修复，但它还是值得参考的。

### 集群哨兵(sentinel )
作用有三个，监控redis服务的异常情况，可以自动发送通知告诉管理员服务异常，并且根据配置自动的故障转移，把一个从服务器切换成住服务。

##### 配置
```yml
sentinel monitor mymaster 127.0.0.1 6379 2
sentinel down-after-milliseconds mymaster 60000
sentinel failover-timeout mymaster 180000
sentinel parallel-syncs mymaster 1
```
第一行配置指示 Sentinel 去监视一个名为 mymaster 的主服务器， 这个主服务器的 IP 地址为 127.0.0.1 ， 端口号为 6379 ， 而将这个主服务器判断为失效至少需要 2 个 Sentinel 同意 （只要同意 Sentinel 的数量不达标，自动故障迁移就不会执行）。

注意， 无论你设置要多少个 Sentinel 同意才能判断一个服务器失效， 一个 Sentinel 都需要获得系统中多数（majority） Sentinel 的支持， 才能发起一次自动故障迁移， 并预留一个给定的配置纪元 （configuration Epoch ，一个配置纪元就是一个新主服务器配置的版本号）。

##### 启动
分别进入7000、7001、7002文件夹的src目录，开启哨兵
>src/redis-server sentinel.conf --sentinel

##### 测试
1.查看当前启动的redis服务
>ps -ef |grep redis

2.杀死其中一个redis master进程
>kill -9 6797

3.进入src目录，查看从服务是否自动切换成主服务
>./redis-cli -p 7000 cluster nodes

##### 扩展：脚本启动
###### 1.创建一个start-cluster文件。注意文件名要与我的一致，否则后果...也许没啥
```sh
#!/bin/bash
redis_path="/usr/local/software/redis/"
cluster_num=`ps aux | grep redis |  wc -l`

if [ "${cluster_num}" -le 0 ]
then
        echo "Success: Has killed all cluster progress."
else
		echo "kill a redis"
		pkill -9 redis
fi

echo "Success: start to delete cluster files.."

cd ${redis_path}
rm -rf ${redis_path}700*/appendonly.aof
rm -rf ${redis_path}700*/dump.rdb
rm -rf ${redis_path}700*/nodes*
rm -rf ${redis_path}nodes*
rm -rf ${redis_path}appendonly.aof

echo "Success: have deleted cluster files and start redis-server"

cd ${redis_path}7000/
src/redis-server ${redis_path}7000/redis.conf
cd ${redis_path}7001/
src/redis-server ${redis_path}7001/redis.conf
cd ${redis_path}7002/
src/redis-server ${redis_path}7002/redis.conf
cd ${redis_path}7003/
src/redis-server ${redis_path}7003/redis.conf
cd ${redis_path}7004/
src/redis-server ${redis_path}7004/redis.conf
cd ${redis_path}7005/
src/redis-server ${redis_path}7005/redis.conf

echo "Success: redis-servers have completed!"
```
###### 2.授权该文件为可执行文件
```sh
chmod +x start-cluster
```
3.如果是在window上编写的，再设置下文件格式，否则无法执行。

```sh
vim start-cluster
```
进入文本编辑器，使用:set ff查看当前文件格式，如果格式非unix，就使用:set ff=unix更改文件格式。

###### 3.执行完成以后可以查看下redis的状态
```sh
ps -ef | grep redis
```
##### 清除cluster脚本
```sh
#!/bin/bash
redis_path="/usr/local/software/redis/"
cluster_num=`ps aux | grep redis |  wc -l`

if [ "${cluster_num}" -le 0 ]
then
        echo "Success: Has killed all cluster progress."
else
		echo "Success: kill a redis"
		pkill -9 redis
fi

echo "Success: start to delete cluster files.."

cd ${redis_path}
rm -rf ${redis_path}700*/appendonly.aof
rm -rf ${redis_path}700*/dump.rdb
rm -rf ${redis_path}700*/nodes*
rm -rf ${redis_path}nodes*
rm -rf ${redis_path}appendonly.aof

echo "Success: have cleared all cluster files！"
```

##### 重启脚本
```sh
#!/bin/bash
redis_path="/usr/local/software/redis/"
cluster_num=`ps aux | grep redis |  wc -l`

if [ "${cluster_num}" -le 0 ]
then
        echo "Success: Has killed all cluster progress."
else
		echo "kill a redis"
		pkill -9 redis
fi

echo "----------start redis-server----------"

cd ${redis_path}7000/
src/redis-server ${redis_path}7000/redis.conf
cd ${redis_path}7001/
src/redis-server ${redis_path}7001/redis.conf
cd ${redis_path}7002/
src/redis-server ${redis_path}7002/redis.conf
cd ${redis_path}7003/
src/redis-server ${redis_path}7003/redis.conf
cd ${redis_path}7004/
src/redis-server ${redis_path}7004/redis.conf
cd ${redis_path}7005/
src/redis-server ${redis_path}7005/redis.conf

echo "Success: redis-servers have completed!"
```
### Java分布式锁

##### 方式一：SpringBoot的yml配置
1.首先引入redisson依赖
```xml
<dependencies>
    <dependency>
        <groupId>org.redisson</groupId>
        <artifactId>redisson-spring-boot-starter</artifactId>
        <version>3.14.0</version>
    </dependency>
</dependencies>
```

2.配置yml文件，host、port和cluster选其一配置
```yml
spring:
  redis:
    database: 0
    # host: 120.79.8.153
    # port: 7000
    password:
    timeout: 10000
    cluster:
      nodes:
        - 120.79.8.153:7000
        - 120.79.8.153:7001
        - 120.79.8.153:7002
        - 120.79.8.153:7003
        - 120.79.8.153:7004
        - 120.79.8.153:7005
    sentinel:
      master: mymaster
      nodes: 120.79.8.153:7005,120.79.8.153:7001,120.79.8.153:7002
```

##### 方式二：yaml文件配置
1.dependency同方式一，在resouce目录创建redisson.yaml文件

```yml
clusterServersConfig:
  idleConnectionTimeout: 10000
  connectTimeout: 10000
  timeout: 3000
  retryAttempts: 3
  retryInterval: 1500
  failedSlaveReconnectionInterval: 3000
  failedSlaveCheckInterval: 60000
  password: *
  subscriptionsPerConnection: 1000 # set 1000 when you test reduce stock，default 5
  clientName: null
  loadBalancer: !<org.redisson.connection.balancer.RoundRobinLoadBalancer> {}
  subscriptionConnectionMinimumIdleSize: 1
  subscriptionConnectionPoolSize: 1000 # set 1000 when you test reduce stock，default 50
  slaveConnectionMinimumIdleSize: 24
  slaveConnectionPoolSize: 64
  masterConnectionMinimumIdleSize: 24
  masterConnectionPoolSize: 64
  readMode: "SLAVE"
  subscriptionMode: "SLAVE"
  nodeAddresses:
    - "redis://120.79.8.153:7000"
    - "redis://120.79.8.153:7001"
    - "redis://120.79.8.153:7002"
    - "redis://120.79.8.153:7003"
    - "redis://120.79.8.153:7004"
    - "redis://120.79.8.153:7005"
  scanInterval: 1000
  pingConnectionInterval: 0
  keepAlive: false
  tcpNoDelay: false
threads: 2
nettyThreads: 32
codec: !<org.redisson.codec.MarshallingCodec> {}
transportMode: "NIO"
```
[官网详细配置](https://github.com/redisson/redisson/wiki/2.-%E9%85%8D%E7%BD%AE%E6%96%B9%E6%B3%95)

2.创建Java文件引用配置
```java
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * 通过配置类启动redis
 *
 * @author Chiang
 */
@Configuration
public class RedissonSpringDataConfig {

    @Bean
    public RedissonConnectionFactory redissonConnectionFactory(RedissonClient redisson) {
        return new RedissonConnectionFactory(redisson);
    }

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson(@Value("classpath:/redisson.yaml") Resource configFile) throws IOException {
        Config config = Config.fromYAML(configFile.getInputStream());
        return Redisson.create(config);
    }

}
```