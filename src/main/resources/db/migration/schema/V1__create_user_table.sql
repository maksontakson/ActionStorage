create table usr
(
    username    varchar(255)
        constraint username_pk not null,
    email       varchar(255),
    description varchar
);