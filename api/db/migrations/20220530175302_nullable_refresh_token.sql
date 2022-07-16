-- migrate:up
alter table discord_users
    alter column refresh_token drop not null;


-- migrate:down
alter table discord_users
    alter column refresh_token set not null;

