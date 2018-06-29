* docker需要开启cpu虚拟化，开启一直按BIOS,安全->安全管理->虚拟化
* docker-ce要求win10-professional或以上版本，低版本的只能安装docker-toolbox了。
* [docker官方文档](https://docs.docker.com/get-started/)
* [docker windows阿里云安装包](http://mirrors.aliyun.com/docker-toolbox/windows/docker-for-windows/stable/)
* [docker安装教程](https://blog.csdn.net/hao_kkkkk/article/details/79853752)
* [docker安装教程](http://www.runoob.com/docker/windows-docker-install.html)

* 如果已经有了git，docker-toolboox安装时可以不勾选git选项，安装成功后在桌面快捷方式中改变git的安装路径

## docker 资料
[Docker 入门教程](http://www.ruanyifeng.com/blog/2018/02/docker-tutorial.html)

----------------------

[docker国内镜像](https://blog.csdn.net/bwlab/article/details/50542261)

[docker阿里云镜像](https://cr.console.aliyun.com/?spm=5176.1971733.2.28.666a5aaamTvXRV#/accelerator)  注册阿里云账号，里面有安装docker教程

    windows

    安装或升级Docker

    推荐您安装Docker Toolbox。 
    Toolbox的介绍和帮助： mirrors.aliyun.com/help/docker-toolbox 
    Windows系统的安装文件目录： mirrors.aliyun.com/docker-toolbox/windows

    快速开始

    # 创建一台安装有Docker环境的Linux虚拟机，指定机器名称为default
    docker-machine create -d virtualbox default

    # 查看机器的环境配置，并配置到本地。然后通过Docker客户端访问Docker服务。
    docker-machine env default
    eval "$(docker-machine env default)"
    docker info
    配置Docker加速器

    您可以使用如下的脚本将mirror的配置添加到docker daemon的启动参数中。

    docker-machine ssh default "echo    'EXTRA_ARGS=\"--registry-mirror=https://xxxxxxxxx.mirror.aliyuncs.com\"' | sudo tee -a  /var/lib/boot2docker/profile"
    docker-machine restart default 


    D:\Program\Git\bin\bash.exe --login -i "D:\Program\Docker Toolbox\start.sh"


docker info 命令Registry显示是否是阿里云。


###  运行ubuntu bash
docker run -it ubuntu bash 

### 运行docker hello-world
docker container run hello-world
### docker hello-world 运行步骤
 1. The Docker client contacted the Docker daemon.
 2. The Docker daemon pulled the "hello-world" image from the Docker Hub.
    (amd64)
 3. The Docker daemon created a new container from that image which runs the
    executable that produces the output you are currently reading.
 4. The Docker daemon streamed that output to the Docker client, which sent it
    to your terminal.


docker pull registry.cn-hangzhou.aliyuncs.com/kaibb/hadoop
docker run -it registry.cn-hangzhou.aliyuncs.com/kaibb/hadoop bash 

[hadoop-docker](https://hub.docker.com/r/sequenceiq/hadoop-docker/)

