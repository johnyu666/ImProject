use test;
drop table if exists clients;

create table clients (id int auto_increment primary key ,loginName varchar(20),password varchar(20),nickName varchar(20));