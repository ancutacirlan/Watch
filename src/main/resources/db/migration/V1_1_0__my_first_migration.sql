
create table role (
    id serial primary key not null,
    name varchar not null unique ,
    is_admin boolean default false
);

create table users(
    id serial primary key not null,
    username varchar unique not null,
    email varchar not null,
    password varchar not null,
    role_id integer not null,
    foreign key (role_id) references role(id)
);

create table category (
  id serial primary key not null,
  name varchar not null unique
);

create table movie (
    id serial primary key not null unique,
    title varchar not null unique,
    trailer_url varchar not null unique,
    original_source_url varchar not null unique,
    cover_url varchar not null unique,
    imdbld varchar not null ,
    imdb_score float ,
    description varchar,
    release_date timestamp not null
);

create table movie_category(
    movie_id integer,
    category_id integer,
    primary key (movie_id,category_id)
);

insert into role(name,is_admin) values ('ROLE_ADMIN',true);
insert into role(name,is_admin) values ('ROLE_USER',false);

insert into category(name)values('Action');
insert into category(name)values('Adventure');
insert into category(name)values('Animation');
insert into category(name)values('Biography');
insert into category(name)values('Comedy');
insert into category(name)values('Crime');
insert into category(name)values('Documentary');
insert into category(name)values('Drama');
insert into category(name)values('Family');
insert into category(name)values('Fantasy');
insert into category(name)values('Film Noir');
insert into category(name)values('History');
insert into category(name)values('Horror');
insert into category(name)values('Music');
insert into category(name)values('Musical');
insert into category(name)values('Mystery');
insert into category(name)values('Romance');
insert into category(name)values('Sci-Fi');
insert into category(name)values('Short Film');
insert into category(name)values('Sport');
insert into category(name)values('Superhero');
insert into category(name)values('Thriller');
insert into category(name)values('War');
insert into category(name)values('Western');
