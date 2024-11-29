--create the table
create table if not exists music (
    id serial not null,
    title varchar(50) not null,
    music_writer varchar(50) not null,
    constraint pk_music primary key (id)
);

