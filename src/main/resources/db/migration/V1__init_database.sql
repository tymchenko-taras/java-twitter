create table message
(
    id       bigint not null auto_increment,
    filename varchar(255),
    tag      varchar(255),
    text     varchar(1000),
    user_id  bigint,
    primary key (id)
) engine = InnoDB;

create table usr
(
    id              bigint not null auto_increment,
    activation_hash varchar(255),
    active          bit,
    email           varchar(255),
    password        varchar(255),
    username        varchar(255),
    primary key (id)
) engine = InnoDB;

create table usr_role
(
    user_id bigint not null,
    roles   varchar(255)
) engine = InnoDB;

alter table message
    add constraint message_usr_fk foreign key (user_id) references usr (id);

alter table usr_role
    add constraint usr_role_usr_fk foreign key (user_id) references usr (id);