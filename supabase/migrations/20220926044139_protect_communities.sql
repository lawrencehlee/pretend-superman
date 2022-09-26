create table members
(
    user_id      uuid references auth.users (id) on delete cascade,
    community_id int references communities (id) on delete cascade,
    role         varchar(255),
    created_at   timestamp with time zone default now(),
    primary key (user_id, community_id)
);

alter table communities
    enable row level security;
alter table members
    enable row level security;

create policy "Users can create communities" ON communities for insert;
create policy "Users can join communities" ON members for insert;
create policy "Users can leave communities" ON members for delete;
create policy "Users can see their communities" ON communities for select using (
        auth.uid() in (select user_id
                       from members
                       where community_id = id)
    );
create policy "Admins can update communities" ON communities for update using (
        auth.uid() in (select user_id
                       from members
                       where community_id = id
                         and role = 'ADMIN')
    );
create policy "Admins can delete communities" ON communities for delete using (
        auth.uid() in (select user_id
                       from members
                       where community_id = id
                         and role = 'ADMIN')
    );
create policy "Members can see other members" ON members for select using (
        community_id in (select community_id
                         from members
                         where user_id = auth.uid())
    );
