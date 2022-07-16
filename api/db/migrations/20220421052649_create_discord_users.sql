-- migrate:up
create table discord_users
(
    id            varchar(32) primary key,
    user_id       bigint       not null unique
        constraint discord_users_to_users
            references users (id) on delete cascade,
    refresh_token text         not null,
    username      varchar(255) not null,
    discriminator varchar(4)   not null,
    email         varchar(255)
);

-- migrate:down
drop table discord_users;