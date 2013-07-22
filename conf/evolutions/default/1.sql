# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table post (
  id                        varchar(255) not null,
  title                     varchar(255),
  url                       varchar(255),
  category                  integer,
  create_date               timestamp,
  constraint ck_post_category check (category in (0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19)),
  constraint pk_post primary key (id))
;

create table user (
  id                        bigint not null,
  username                  varchar(255),
  password                  varchar(255),
  cookie                    varchar(255),
  constraint pk_user primary key (id))
;

create sequence post_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists post;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists post_seq;

drop sequence if exists user_seq;

