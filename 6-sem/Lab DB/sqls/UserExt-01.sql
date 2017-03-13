# alter table form_tbl drop FOREIGN KEY FK1C40F9C3B01D0488;
#
# drop table lib_tbl;
#
# alter table user_tbl add column role INT DEFAULT 0;

# CREATE TABLE form_tbl (
#   id         INT PRIMARY KEY AUTO_INCREMENT,
#   book_id    INT,
#   lib_id     INT,
#   start_date DATE,
#   end_date   DATE,
#   user_id    INT,
#   FOREIGN KEY form_tbl_book_fk (book_id) REFERENCES book_tbl (id),
#   FOREIGN KEY form_tbl_lib_fk (lib_id) REFERENCES user_tbl (id),
#   FOREIGN KEY form_tbl_user_fk (user_id) REFERENCES user_tbl (id)
# );
alter table user_tbl drop column role;
alter table user_tbl add column role VARCHAR(10) DEFAULT 'USUAL';