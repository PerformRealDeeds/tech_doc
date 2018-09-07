## 获取jdbc连接
        String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/mysql?useUnicode=true&characterEncoding=UTF-8&createDatabaseIfNotExist=true";
		String user = "root";
		String password = "mysql0774mysql";
 
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, user, password);
        DatabaseMetaData meta = conn.getMetaData(); 

## jdbc执行sql
Statement stmt=conn.createStatement();
### jdbc 增删改
int rowCount =  stmt.executeUpdate(sql);
### jdbc 查询
    ResultSet rs = stmt.executeQuery(String sql);
    while(rs.next()){
        rs.getString(colIndex or colNm);
    }




## java jdbc去读元数据信息
[参考博客](https://www.cnblogs.com/lbangel/p/3487796.html)

### 获取指定库下所有的表
ResultSet rs = meta.getTables(null, "db_nm","%",new String[]{"TABLE"});  // 具体参数参考jdk文档
/*其中"%"就是表示*的意思，也就是任意所有的意思。其中m_TableName就是要获取的数据表的名字，如果想获取所有的表的名字，就可以使用"%"来作为参数了。*/
//table type. Typical types are "TABLE", "VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS", "SYNONYM".

## 获取指定库指定表的所有列
    ResultSet rs = meta.getColumns(null,"%", m_TableName,"%"); 
    while(rs.next()) { 
    　　columnName = rs.getString("COLUMN_NAME"); 
    　　columnType = rs.getString("TYPE_NAME"); 
    　　int datasize = rs.getInt("COLUMN_SIZE"); 
    　　int digits = rs.getInt("DECIMAL_DIGITS"); 
    　　int nullable = rs.getInt("NULLABLE"); 
    　　System.out.println(columnName+" "+columnType+" "+datasize+" "+digits+" "+ nullable); 
    }
