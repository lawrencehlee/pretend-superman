create view users as
select id,
       email,
       raw_user_meta_data -> 'sub'  as discord_id,
       raw_user_meta_data -> 'name' as discord_full_name,
       created_at
from auth.users;