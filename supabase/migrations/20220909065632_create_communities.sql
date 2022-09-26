create table communities
(
    id         integer primary key generated always as identity,
    slug       varchar(48)  not null unique,
    name       varchar(255) not null,
    created_at timestamp with time zone default now()
);
