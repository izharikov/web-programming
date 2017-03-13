insert into author_tbl (id, name) values(1, 'Mark Tven');
insert into author_tbl(id, name) values (2, 'Jack London');

update book_tbl set autor_id = 1 where id = 1;
update book_tbl set autor_id = 1 where id = 2;
update book_tbl set autor_id = 2 where id = 3;