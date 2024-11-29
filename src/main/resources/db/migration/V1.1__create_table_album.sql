create table if not exists album (
    id serial not null,
    title varchar(50) not null,
    artist varchar(50) not null,
    release_date date not null,
    music_id bigint not null,
    constraint pk_album primary key(id),
    constraint fk_album_music foreign key (music_id) references music(id)
)

