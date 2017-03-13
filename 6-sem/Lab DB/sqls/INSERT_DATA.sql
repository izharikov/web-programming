insert into catalog_tbl (id, name) values (1, 'catalog1');
insert into catalog_tbl (id, name) values (2, 'catalog2');


insert into book_tbl (id, catalog_id, name, count_of_books)
  values ( 1, 1, 'Test book #1 (cat 1)', 1000);

insert into book_tbl (id, catalog_id, name, count_of_books)
values ( 2, 1, 'Test book #2 (cat 1)', 999);

insert into book_tbl (id, catalog_id, name, count_of_books)
values ( 3, 2, 'Test book #3 (cat 2)', 1000);

insert into author_tbl (id, name) values(1, 'Mark Tven');
insert into author_tbl(id, name) values (2, 'Jack London');

update book_tbl set autor_id = 1 where id = 1;
update book_tbl set autor_id = 1 where id = 2;
update book_tbl set autor_id = 2 where id = 3;