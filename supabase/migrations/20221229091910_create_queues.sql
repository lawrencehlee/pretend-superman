create table queues
(
    id               integer primary key generated always as identity,
    community_id     integer references communities (id) on delete cascade,
    name             varchar(255)                                                        not null,
    num_teams        integer                                                             not null,
    players_per_team integer                                                             not null,
    num_players      integer generated always as ( num_teams * players_per_team ) stored not null,
    algorithm        varchar(255)                                                        not null,
    settings         jsonb                                                               not null default '{}'::json,
    created_at       timestamp with time zone                                            not null default now()
);

comment on column queues.num_teams is '0 indicates FFA, 1 indicates co-op';

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