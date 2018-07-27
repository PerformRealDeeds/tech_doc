#单条命令（远程免密执行）

    expect -c "
     spawn scp /path/to/file  usr@10.11.12.13:$remote_dir
     expect {
         \"*password\"  {send \"2wsx#EDC\r\";} #\r必须
     }
    "

#执行多条命令

    expect -c "
      set timeout 600 # -1表示不设置超时
      spawn ssh hdp_usr@10.79.32.52 \" cd $remote_dir; tar -xzvf    all.tar.gz; rm -rf $prefix*; \"
      expect{
          \"*password\" {send \"your_password\r\";}
      }
      expect eof
    "
