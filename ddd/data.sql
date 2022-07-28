create table `order`
(
    id           bigint auto_increment comment '自增长ID'
        primary key,
    total_price  decimal(8, 2) null comment '总金额',
    product_info varchar(200)  null comment '商品信息',
    coupon_info  varchar(100)  null comment '优惠券信息',
    user_id      bigint        null comment '用户ID',
    user_name    varchar(20)   null comment '用户名称',
    user_phone   varchar(20)   null comment '用户手机号',
    address      varchar(100)  null comment '收货地址',
    order_status tinyint       null comment '订单状态,0=待付款、1=待收货、2=已收货、3=已完成、-1=已取消'
)
    comment '订单表';

create table product
(
    id    bigint auto_increment comment '商品ID，自动增长'
        primary key,
    title varchar(100)           null comment '商品标题',
    price decimal(8, 2) unsigned null comment '商品价格',
    stock decimal(8, 2) unsigned null comment '商品库存'
)
    comment '商品信息';

create table user
(
    id        bigint auto_increment
        primary key,
    user_name varchar(20) null,
    password  varchar(20) null,
    phone     varchar(11) null comment '手机号',
    address   varchar(50) null comment '地址'
)
    comment '用户表';