create table source
(
    type   varchar(255)    not null,
    active boolean not null,
    primary key (type)
);

create table action
(
    id          varchar(255),
    date        timestamp,
    type        varchar(255),
    username    varchar(255),
    source_type varchar(255),
    primary key (id)
);

alter table if exists action
    add constraint action_source_fk
        foreign key (source_type)
            references source;