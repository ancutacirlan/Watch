
create table role(
    id serial primary key,
    name varchar not null,
    is_Admin boolean
);

create table users(
    id serial primary key not null,
    username varchar not null,
    email varchar not null,
    password varchar not null,
    role_id integer,
    foreign key (role_id) references role(id)
);

create table categories (
  id serial primary key not null,
  name varchar not null
);

create table movies (
    id serial primary key not null,
    title varchar,
    trailerurl varchar,
    original_source_url varchar,
    cover_url varchar,
    imdbld varchar not null ,
    imdb_score float ,
    description varchar,
    release_date timestamp not null
);

create table movies_categories(
    movies_id integer,
    categories_id integer,
    primary key (movies_id,categories_id)
);

insert into role(name,is_Admin) values ('ROLE_ADMIN',true);
insert into role(name,is_Admin) values ('ROLE_USER',true);
insert into categories(name)values('Action');
insert into categories(name)values('Adventure');
insert into categories(name)values('Animation');
insert into categories(name)values('Biography');
insert into categories(name)values('Comedy');
insert into categories(name)values('Crime');
insert into categories(name)values('Documentary');
insert into categories(name)values('Drama');
insert into categories(name)values('Family');
insert into categories(name)values('Fantasy');
insert into categories(name)values('Film Noir');
insert into categories(name)values('History');
insert into categories(name)values('Horror');
insert into categories(name)values('Music');
insert into categories(name)values('Musical');
insert into categories(name)values('Mystery');
insert into categories(name)values('Romance');
insert into categories(name)values('Sci-Fi');
insert into categories(name)values('Short Film');
insert into categories(name)values('Sport');
insert into categories(name)values('Superhero');
insert into categories(name)values('Thriller');
insert into categories(name)values('War');
insert into categories(name)values('Western');
