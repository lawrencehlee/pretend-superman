create table queues
(
    id           serial
        constraint queues_pk primary key,
    community_id integer      not null
        constraint queues_communities_id_fk
            references communities
            on delete cascade,
    slug         varchar(50)  not null,
    name         varchar(255) not null,
    constraint community_id_slug
        unique (community_id, slug)
);
