## 将api包deploy后，只能在自己机器上从nexus私服拉包，别人的机器上不能下载包
原因： 发布api jar包到项目库时，要把父母项目也要deploy，不然别人下载api包时，解析不到父母项目的包，就不往下走了，表现为只能下载api依赖的一些pom文件，不能下载jar包。
解决：从父母项目deploy，不能只在api包下deploy。






