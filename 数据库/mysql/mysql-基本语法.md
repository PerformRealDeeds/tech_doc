## 更新

    update tableA
    set  colA='valueA'
        ,colB='valueB'  -- 注意是逗号,不是and
    where colC='valueC';

## crt_tm，upd_tm自动更新
ALTER TABLE `test`.`table1` 
CHANGE COLUMN `crt_tm` `crt_tm` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,
CHANGE COLUMN `upd_tm` `upd_tm` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ;



