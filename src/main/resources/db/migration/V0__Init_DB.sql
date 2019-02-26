create sequence hibernate_sequence start 1 increment 1;

create table record (
                       id int8 not null,
                       filename varchar(255),
                       date varchar(255),
                       distance int4 not null,
                       time float8 not null,
                       user_id int8,
                       primary key (id)
);

create table user_role (
                         user_id int8 not null,
                         roles varchar(255)
);

create table usr (
                   id int8 not null,
                   active boolean not null,
                   email varchar(255),
                   password varchar(255) not null,
                   username varchar(255) not null,
                   primary key (id)
);

alter table if exists record
  add constraint record_user_fk
    foreign key (user_id) references usr;

alter table if exists user_role
  add constraint user_role_user_fk
    foreign key (user_id) references usr;