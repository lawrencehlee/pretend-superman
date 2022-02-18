create table users
(
    id       serial
        constraint users_pk primary key,
    username varchar(255) not null
);

create table communities
(
    id   serial
        constraint communities_pk
            primary key,
    slug varchar(50) not null,
    name text        not null
);

create unique index communities_slug_uindex
    on communities (slug);

create table users_communities
(
    user_id      int not null
        constraint users_communities_users_id_fk
            references users (id)
            on delete cascade,
    community_id int not null
        constraint users_communities_communities_id_fk
            references communities (id)
            on delete cascade,
    constraint users_communities_pk
        primary key (user_id, community_id)
);

