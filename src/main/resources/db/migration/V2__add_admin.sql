insert into `usr` (id, username, password, active)
    values (1, "admin", "111111", 1);

insert into `usr_role` (user_id, roles)
    values (1, "ADMIN"), (1, "USER");