## 用sqoop从mysql向hive导数时,主键为空时不要在hive转成空串
 如果有脏数据,空串容易造成死锁, null会导致sqoop不能插入到mysql,抛出异常,不会对mysql造成影响.