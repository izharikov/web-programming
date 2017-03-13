DROP TABLE sporstman_obj_ref_tbl;
DROP TABLE sportsman_obj_tbl;
DROP TYPE sportsman_ty;


CREATE OR REPLACE TYPE sportsman_ty AS OBJECT (
  FIRST_NAME       VARCHAR2(40),
  LAST_NAME        VARCHAR2(40),
  DATE_OF_BIRTHDAY DATE,
MEMBER FUNCTION is_recordsman(sp_id IN NUMBER)
  RETURN NUMBER
);
/

CREATE OR REPLACE TYPE BODY sportsman_ty AS
MEMBER FUNCTION is_recordsman(sp_id IN NUMBER)
    RETURN NUMBER IS
    l_count INTEGER;
    BEGIN
      SELECT COUNT(*)
      INTO l_count
      FROM SPORTSMAN_RECORD_REL
      WHERE SPORTSMAN_ID = sp_id;
      IF l_count > 0
      THEN
        RETURN 1;
      ELSE
        RETURN 0;
      END IF;

    END;
END;
/
CREATE TABLE sportsman_obj_tbl OF sportsman_ty;


INSERT INTO sportsman_obj_tbl (FIRST_NAME, LAST_NAME, DATE_OF_BIRTHDAY)
VALUES ('John', 'Black', to_date('11-12-1991', 'dd-mm-yyyy'));
INSERT INTO sportsman_obj_tbl (FIRST_NAME, LAST_NAME, DATE_OF_BIRTHDAY)
VALUES ('Ivan', 'Ivanov', to_date('11-10-1989', 'dd-mm-yyyy'));
INSERT INTO sportsman_obj_tbl (FIRST_NAME, LAST_NAME, DATE_OF_BIRTHDAY)
VALUES ('Tom', 'Green', to_date('1-3-1996', 'dd-mm-yyyy'));
INSERT INTO sportsman_obj_tbl (FIRST_NAME, LAST_NAME, DATE_OF_BIRTHDAY)
VALUES ('Kho', 'Hu', to_date('29-7-1992', 'dd-mm-yyyy'));







/
CREATE TABLE sporstman_obj_ref_tbl (
  id            NUMBER PRIMARY KEY,
  sportsman_obj REF sportsman_ty
);







/
INSERT INTO sporstman_obj_ref_tbl SELECT
                                    1,
                                    ref(s)
                                  FROM sportsman_obj_tbl s
                                  WHERE LAST_NAME = 'Black';

INSERT INTO sporstman_obj_ref_tbl SELECT
                                    2,
                                    ref(s)
                                  FROM sportsman_obj_tbl s
                                  WHERE LAST_NAME = 'Ivanov';
INSERT INTO sporstman_obj_ref_tbl SELECT
                                    3,
                                    ref(s)
                                  FROM sportsman_obj_tbl s
                                  WHERE LAST_NAME = 'Green';
INSERT INTO sporstman_obj_ref_tbl SELECT
                                    4,
                                    ref(s)
                                  FROM sportsman_obj_tbl s
                                  WHERE LAST_NAME = 'Hu';

SELECT *
FROM sportsman_obj_tbl;

SELECT
  id,
  deref(sportsman_obj).FIRST_NAME,
  deref(sportsman_obj).LAST_NAME,
  deref(sportsman_obj).is_recordsman(s.id)
FROM sporstman_obj_ref_tbl s;