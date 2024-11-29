--create the table
create table if not exists music (
    id serial not null,
    title varchar(50) not null,
    music_writer varchar(50) not null,
    album_id bigint,
    constraint pk_music primary key (id),
    constraint fk_music_album foreign key (album_id) references album(id) on delete cascade
    );

