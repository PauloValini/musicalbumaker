create table if not exists album (
    id bigserial not null,
    title varchar(50) not null,
    artist varchar(50) not null,
    release_date date not null,
    constraint pk_album primary key (id)
    );