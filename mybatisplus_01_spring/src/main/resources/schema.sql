drop table if exists t_user;

create table t_user(
    id int auto_increment primary key ,
    name varchar(50),
    address varchar(128),
    birth datetime(6)
);