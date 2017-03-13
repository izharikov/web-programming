DROP TABLE IF EXISTS book_tbl;
DROP TABLE IF EXISTS catalog_tbl;

# catalog tbl creation

CREATE TABLE catalog_tbl (
  id   INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(256) NOT NULL
);

# book tbl creation

CREATE TABLE book_tbl (
  id             INT PRIMARY KEY AUTO_INCREMENT,
  name           VARCHAR(256),
  catalog_id     INT NOT NULL,
  count_of_books INT,
  FOREIGN KEY book_catalog_fk(catalog_id) REFERENCES catalog_tbl (id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);

# user tbl create
CREATE TABLE user_tbl (
  id       INT PRIMARY KEY AUTO_INCREMENT,
  name     VARCHAR(256),
  password VARCHAR(256)
);

# librarian tbl create

CREATE TABLE lib_tbl (
  id   INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(256)
);

# form tbl
CREATE TABLE form_tbl (
  id         INT PRIMARY KEY AUTO_INCREMENT,
  book_id    INT,
  lib_id     INT,
  start_date DATE,
  end_date   DATE,
  user_id    INT,
  FOREIGN KEY form_tbl_book_fk (book_id) REFERENCES book_tbl (id),
  FOREIGN KEY form_tbl_lib_fk (lib_id) REFERENCES user_tbl (id),
  FOREIGN KEY form_tbl_user_fk (user_id) REFERENCES user_tbl (id)
);