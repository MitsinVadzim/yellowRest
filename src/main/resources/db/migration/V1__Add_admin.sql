  insert into usr (id, username, active, last_visit, email)
  values (1, 'admin', true, '2019-03-12', 'admin@gmail.com');

  insert into user_role (user_id, roles)
  values (1, 'USER'), (1, 'ADMIN');