## 一个卡主的sql
    insert into a
    select *
    from b      join  c on b.id=c.id
           left join  a on b.id=a.id
    where a.id is null;

b join c改成 b inner join c 就好了

## hive jdbc 连接异常： root is not allowed to impersonate
[参考1](https://blog.csdn.net/yunyexiangfeng/article/details/60867563)

在hadoop的配置文件core-site.xml增加如下配置，重启hdfs

    <property>
            <name>hadoop.proxyuser.xxx.hosts</name>
            <value>*</value>
        </property>
        <property>
            <name>hadoop.proxyuser.xxx.groups</name>
            <value>*</value>
        </property>

[参考2](https://blog.csdn.net/github_38358734/article/details/77522798)
