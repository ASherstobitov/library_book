create table books
(
    id        bigserial auto_increment primary key,
    book_name varchar(250),
    author    varchar(250),
    isbn    varchar(25),
    year      int
);

create table authors
(
    id          bigserial auto_increment primary key,
    author_name varchar(250)
);

create table books_authors
(
    book_id   bigint references books (id),
    author_id bigint references authors (id),
    primary key (book_id, author_id)
);
