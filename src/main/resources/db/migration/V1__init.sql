create table book
(
    id      bigint auto_increment primary key,
    author  varchar(255) null,
    name    varchar(255) null,
    user_id bigint       null
);

create index FKpsgrhw51v8on5lbyoo5qdjypq
    on book (user_id);

create table user
(
    id   bigint auto_increment primary key,
    name varchar(255) null
);

insert into user(id, name)
VALUES (1, 'Roma');
insert into user(id, name)
VALUES (2, 'Dima');
insert into user(id, name)
VALUES (3, 'Maks');
insert into user(id, name)
VALUES (4, 'Oleg');
insert into user(id, name)
VALUES (5, 'Roma');

insert into book(id, name, author, user_id)
VALUES (1, 'Anna Karenina', 'Lev Tolstoi', 1);
insert into book(id, name, author, user_id)
VALUES (2, 'Evgenij Onegin', 'Alexandr Pushkin', 1);
insert into book(id, name, author, user_id)
VALUES (3, 'Idiot', 'Fedor Dostoevskij', null);
insert into book(id, name, author, user_id)
VALUES (4, 'Mertvye dushy', 'Nikolaj Gogol', null);
insert into book(id, name, author, user_id)
VALUES (5, 'Faust', 'Iogann Gete', 4);