drop table if exists cart_products cascade;
drop table if exists carts cascade;
drop table if exists categories cascade;
drop table if exists orders cascade;
drop table if exists orders_details cascade;
drop table if exists products cascade;
drop table if exists products_categories cascade;
drop table if exists users cascade;
drop sequence if exists SEQ_CART;
drop sequence if exists SEQ_CATEGORY;
drop sequence if exists SEQ_DETAILS_ORDER;
drop sequence if exists SEQ_ORDER;
drop sequence if exists SEQ_PRODUCT;
drop sequence if exists SEQ_USER;
create sequence SEQ_CART start 1 increment 1;
create sequence SEQ_CATEGORY start 1 increment 1;
create sequence SEQ_DETAILS_ORDER start 1 increment 1;
create sequence SEQ_ORDER start 1 increment 1;
create sequence SEQ_PRODUCT start 1 increment 1;
create sequence SEQ_USER start 1 increment 1;
create table cart_products
(
    cart_id    int8 not null,
    product_id int8 not null
);
create table carts
(
    id      int8 not null,
    user_id int8,
    primary key (id)
);
create table categories
(
    id    int8 not null,
    title varchar(255),
    primary key (id)
);
create table orders
(
    id        int8 not null,
    adress    varchar(255),
    created   timestamp,
    status    varchar(255),
    total_sum numeric(19, 2),
    updated   timestamp,
    user_id   int8,
    primary key (id)
);
create table orders_details
(
    id         int8 not null,
    amount     numeric(19, 2),
    price      numeric(19, 2),
    order_id   int8,
    product_id int8,
    details_id int8 not null,
    primary key (id)
);
create table products
(
    id    int8 not null,
    price numeric(19, 2),
    title varchar(255),
    primary key (id)
);
create table products_categories
(
    product_id  int8 not null,
    category_id int8 not null
);
create table users
(
    id       int8    not null,
    archive  boolean not null,
    email    varchar(255),
    name     varchar(255),
    password varchar(255),
    role     varchar(255),
    cart_id  int8,
    primary key (id)
);
alter table if exists orders_details
    add constraint UK_kk6y3pyhjt6kajomtjbhsoajo unique (details_id);
alter table if exists cart_products
    add constraint FKdayy17at10up1qqwlri9cocb3 foreign key (product_id) references products;
alter table if exists cart_products
    add constraint FKbilp3o9irlsvmbot68kfpthom foreign key (cart_id) references carts;
alter table if exists carts
    add constraint FKb5o626f86h46m4s7ms6ginnop foreign key (user_id) references users;
alter table if exists orders
    add constraint FK32ql8ubntj5uh44ph9659tiih foreign key (user_id) references users;
alter table if exists orders_details
    add constraint FK5o977kj2vptwo70fu7w7so9fe foreign key (order_id) references orders;
alter table if exists orders_details
    add constraint FKs0r9x49croribb4j6tah648gt foreign key (product_id) references products;
alter table if exists orders_details
    add constraint FKgvp1k7a3ubdboj3yhnawd5m1p foreign key (details_id) references orders_details;
alter table if exists products_categories
    add constraint FKqt6m2o5dly3luqcm00f5t4h2p foreign key (category_id) references categories;
alter table if exists products_categories
    add constraint FKtj1vdea8qwerbjqie4xldl1el foreign key (product_id) references products;
alter table if exists users
    add constraint FKdv26y3bb4vdmsr89c9ppnx85w foreign key (cart_id) references carts;