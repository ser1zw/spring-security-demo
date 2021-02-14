drop table if exists users;
create table users (
  id integer not null auto_increment,
  name varchar(100) not null,
  login_id varchar(100) not null,
  password varchar(100) not null,
  primary key (id),
  constraint uk_users_login_id unique (login_id)
);

-- password = 'password'
insert into users (id, name, login_id, password)
values (1, 'Administrator', 'admin', '$2a$10$4lbvk74cm.KGWFcbECBQsuG0/gkDrTXpXRUgPTT7FxHAhGmfzokmy');
