# docker 运行mysql
[docker-mysql安装使用文档](https://hub.docker.com/_/mysql/)

docker拉取镜像命令

    docker pull mysql

[运行mysql](https://blog.csdn.net/u013710784/article/details/78772117)

    docker run --name=mysql -e MYSQL_ROOT_PASSWORD=root -v /app/mysql:/var/lib/mysql -d mysql
    --name=mysql 这个属性是为启动的MySQL容器设置一个别名
    -d 以守护进程方式运行 (后台运行)
    -e MYSQL_ROOT_PASSWORD在启动MySQL的时候设置密码,这里密码以root演示
    -v /app/mysql/:/var/lib/mysql 通过Volume把容器内的文件映射到物理机

查看MySQL的日志
$ docker logs mysql
1
连接MySQL服务器
$ docker exec -it mysql mysql -u root -p
1
提示输入密码的时候，输入刚刚创建的时候指定的密码就ok了。现在你就能随意使用的MySQL服务器咯。

修改 MySQL密码：$ ALTER USER 'root'@'localhost' IDENTIFIED BY '新密码';

容器管理
$ docker restart mysql
$ docker stop mysql
$ docker start mysql
$ docker rm mysql




## 问题
###  Access denied for user 'root'@'localhost' (using password: NO)
参考[mysql8 文档](https://dev.mysql.com/doc/refman/8.0/en/set-password.html)
修改用户密码
With a FOR user clause, the statement sets the password for the named account, which must exist:

    SET PASSWORD FOR 'jeffrey'@'localhost' = 'auth_string';

With no FOR user clause, the statement sets the password for the current user:

    SET PASSWORD = 'auth_string';

