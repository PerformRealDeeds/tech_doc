-- 建表语句
select export_objects('','db.tbl');

-- 改表名  注意： rename后面不能带库名
alter table db.table rename to table1; 

-- 加列
alter table db.tbl add column col_nm int;

-- 日期、时间 加减
SELECT TIMESTAMPADD (MONTH, 2, (CURRENT_TIMESTAMP)) AS TodayPlusTwoMonths;

## 幂
select power(2,3); -- 8

## 自增主键
    CREATE SEQUENCE
    Defines a new named sequence number generator object. Along with AUTO_INCREMENT and IDENTITY sequences, you can use named sequences to set the default values of primary key columns. Sequences guarantee uniqueness, and avoid constraint enforcement problems and overhead. For more information about sequence types and their usage, see Sequences in the Administrator's Guide.

例子：

    CREATE SEQUENCE db.my_seq START 100;
    SELECT NEXTVAL('db.my_seq');
    GRANT ALL PRIVILEGES ON SEQUENCE db.my_seq TO Joe;
    CREATE TABLE customer(id INTEGER DEFAULT my_seq.NEXTVAL,
    lname VARCHAR(25), 
    fname VARCHAR(25),
    membership_card INTEGER
    );

