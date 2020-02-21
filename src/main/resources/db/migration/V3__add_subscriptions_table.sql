create table user_subscriptions
(
    user_id  bigint,
    subscribed_to  bigint,
    primary key (user_id, subscribed_to)
) engine = InnoDB;