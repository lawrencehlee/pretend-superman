create table queues
(
    id           integer primary key generated always as identity,
    community_id integer references communities (id) on delete cascade,
    name         varchar(255)             not null,
    teams        jsonb                    not null,
    algorithm    varchar(255)             not null,
    settings     jsonb                    not null,
    created_at   timestamp with time zone not null default now(),
    unique (community_id, name)
);

alter table queues
    enable row level security;

create policy "Members can view queues" on queues for select using
    (auth.uid() in (select user_id
                    from members
                    where members.community_id = queues.community_id));
create policy "Admins can manage queues" on queues for all with check
    (auth.uid() in (select user_id
                    from members
                    where members.community_id = queues.community_id
                      and members.role = 'ADMIN'));