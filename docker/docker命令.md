<pre>
docker                 #查看所有命令
docker -v / --version / info  #显示docker信息
docker run  hello-world #执行docker镜像
docker image ls #查看镜像
docker container ls 
docker attach container_id # 进入一个正在运行的容器
docker build -t friendlyhello .  # Create image using this directory's Dockerfile
docker run -p 4000:80 friendlyhello  # Run "friendlyname" mapping port 4000 to 80
docker run -d -p 4000:80 friendlyhello         # Same thing, but in detached mode
docker container ls                                # List all running containers
docker container ls -a            # List all containers, even those not running 
docker ps -a                      # 列出所有的镜像，包括没有运行的
docker container stop <hash>           # Gracefully stop the specified container
docker container kill <hash>         # Force shutdown of the specified container
docker container rm <hash>        # Remove specified container from this machine
docker container rm $(docker container ls -a -q)         # Remove all containers
docker container run hello-world      # 运行hello-world镜像，本地没有时从仓库下载
docker container  prune               # Remove all stopped containers  1个容器占用不到1MB。 删除了十几个，释放了10MB
docker image ls -a                             # List all images on this machine
docker image rm <image id>            # Remove specified image from this machine
docker image rm $(docker image ls -a -q)   # Remove all images from this machine
docker login             # Log in this CLI session using your Docker credentials
docker tag <image> username/repository:tag  # Tag <image> for upload to registry
docker push username/repository:tag            # Upload tagged image to registry
docker run username/repository:tag                   # Run image from a registry
</pre>