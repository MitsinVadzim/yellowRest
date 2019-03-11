  create sequence hibernate_sequence start 1 increment 1;

  create table record (
                         id int8 not null,
                         date timestamp,
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
                     last_visit timestamp,
                     username varchar(255) not null,
                     userpic varchar(255),
                     primary key (id)
  );

  alter table if exists record
    add constraint record_user_fk
      foreign key (user_id) references usr;

  alter table if exists user_role
    add constraint user_role_user_fk
      foreign key (user_id) references usr;