-- migrate:up
create table users
(
    id       bigserial primary key,
    username varchar(255) not null
);

-- migrate:down
drop table users;

