## 更新

    update tableA
    set  colA='valueA'
        ,colB='valueB'  -- 注意是逗号,不是and
    where colC='valueC';

## [crt_tm，upd_tm自动更新](https://dev.mysql.com/doc/refman/5.7/en/timestamp-initialization.html)
    ALTER TABLE `test`.`table1` 
    CHANGE COLUMN `crt_tm` `crt_tm` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,
    CHANGE COLUMN `upd_tm` `upd_tm` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ;

    CREATE TABLE t1 (
    ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    dt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    );

## [myqsl 日期、时间 加减](https://dev.mysql.com/doc/refman/5.7/en/date-and-time-functions.html)
mysql> SELECT DATE_ADD('2000-12-31 23:59:59',    INTERVAL 1 SECOND);
        -> '2001-01-01 00:00:00'
mysql> SELECT DATE_ADD('2010-12-31 23:59:59',    INTERVAL 1 DAY);
        -> '2011-01-01 23:59:59'.


## 幂
select power(2,3); -- 8