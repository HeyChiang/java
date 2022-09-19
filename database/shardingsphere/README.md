# 创建shardingSphere需要的数据表
```sql
# 创建第一个库的分片表
create database if not exists db1;
use db1;

CREATE TABLE t_order_0 (
    id BIGINT PRIMARY KEY,
    create_time timestamp NULL DEFAULT NULL,
    user_id  BIGINT NOT NULL
);

CREATE TABLE t_order_item_0 (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    create_time timestamp NULL DEFAULT NULL,
    order_id BIGINT NOT NULL,
    user_id  BIGINT NOT NULL
);

CREATE TABLE t_order_1 (
   id BIGINT PRIMARY KEY,
   create_time timestamp NULL DEFAULT NULL,
   user_id  BIGINT NOT NULL
);

CREATE TABLE t_order_item_1 (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    create_time timestamp NULL DEFAULT NULL,
    order_id BIGINT NOT NULL,
    user_id  BIGINT NOT NULL
);

# 创建第一个数据库的分片表
create database if not exists db2;
use db2;

CREATE TABLE t_order_0 (
   id BIGINT PRIMARY KEY,
   create_time timestamp NULL DEFAULT NULL,
   user_id  BIGINT NOT NULL
);

CREATE TABLE t_order_item_0 (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    create_time timestamp NULL DEFAULT NULL,
    order_id BIGINT NOT NULL,
    user_id  BIGINT NOT NULL
);

CREATE TABLE t_order_1 (
   id BIGINT PRIMARY KEY,
   create_time timestamp NULL DEFAULT NULL,
   user_id  BIGINT NOT NULL
);

CREATE TABLE t_order_item_1 (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    create_time timestamp NULL DEFAULT NULL,
    order_id BIGINT NOT NULL,
    user_id  BIGINT NOT NULL
);
```