-- 用户表
create table t_user (
	id int primary key auto_increment,
    username varchar(40) not null,
    password varchar(255) not null
);

-- 角色表
create table t_role(
	id int primary key auto_increment,
    role_name varchar(40) not null
);

-- 权限表
create table t_permission(
	id int primary key auto_increment,
    prefix varchar(40) not null
);

-- 用户角色对应关系表
create table t_user_role (
	id int primary key auto_increment,
    user_id int not null,
    role_id int not null
);

-- 角色权限对应关系表
create table t_role_permission(
	id int primary key auto_increment,
    role_id int not null,
    per_id int not null
);