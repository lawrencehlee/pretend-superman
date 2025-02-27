create table members
(
    user_id      uuid references auth.users (id) on delete cascade,
    community_id int references communities (id) on delete cascade,
    role         varchar(255) not null,
    created_at   timestamp with time zone default now() not null,
    primary key (user_id, community_id)
);

alter table communities
    enable row level security;
alter table members
    enable row level security;

create policy "Users can create communities" ON communities for all to authenticated with check (true);
create policy "Users can join communities" ON members for insert to authenticated with check (true);
create policy "Users can see communities" ON communities for select to authenticated using (true);
create policy "Members can leave communities" ON members for delete using (auth.uid() = user_id);
create policy "Users can see community members" ON members for select to authenticated using (true);
create policy "Admins can update communities" ON communities for update with check (
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