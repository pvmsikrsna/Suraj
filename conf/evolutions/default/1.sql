# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table Users (
  Id                        bigint auto_increment not null,
  EmailID                   varchar(255),
  Password                  varchar(255),
  Authorization_Token       varchar(255),
  constraint pk_Users primary key (Id))
;

create table UserProfile (
  id                        bigint auto_increment not null,
  Users_Id                  bigint,
  FirstName                 varchar(255),
  LastName                  varchar(255),
  DOB                       datetime(6),
  Gender                    varchar(255),
  constraint uq_UserProfile_Users_Id unique (Users_Id),
  constraint pk_UserProfile primary key (id))
;

alter table UserProfile add constraint fk_UserProfile_user_1 foreign key (Users_Id) references Users (Id) on delete restrict on update restrict;
create index ix_UserProfile_user_1 on UserProfile (Users_Id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table Users;

drop table UserProfile;

SET FOREIGN_KEY_CHECKS=1;

