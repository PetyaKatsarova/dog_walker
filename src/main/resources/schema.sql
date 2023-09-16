drop table if exists dog_table;
drop table if exists customer_table;

create table if not exists customer_table
(
    id    int auto_increment not null primary key,
    name  varchar(50)        not null,
    email varchar(50)        not null
);

create table if not exists dog_table
(
    id       int auto_increment not null primary key,
    name     varchar(50)        not null,
    breed    varchar(50)        not null,
    owner_fk int                not null,
    foreign key (owner_fk) references customer_table (id)
);
