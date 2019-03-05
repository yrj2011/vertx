


drop table if exists cfg_db_version;

create table cfg_db_version(
  _version char(10) primary key
);

insert into cfg_db_version(_version) values ('0.0');