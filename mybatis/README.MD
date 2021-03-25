### Mysql数据
```mysql

create database java;
use java;

create table city
(
    id        int auto_increment
        primary key,
    city_name varchar(20) null
);

INSERT INTO java.city (id, city_name) VALUES (1, 'changsha');
INSERT INTO java.city (id, city_name) VALUES (2, 'shanghai');
INSERT INTO java.city (id, city_name) VALUES (3, 'wuhan');

```