create database java_web;

use java_web;

drop table user;

create table user(id int primary key auto_increment, name varchar(50), age int, birth datetime);

insert into user(name,age,birth) values("test1",12,"2005-10-16"),("test2",13,"2005-10-16");