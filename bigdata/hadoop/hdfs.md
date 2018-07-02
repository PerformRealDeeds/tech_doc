hdfs 显示所有的hdfs命令
hdfs getconf

[hdfs dfs](http://hadoop.apache.org/docs/current/hadoop-project-dist/hadoop-common/FileSystemShell.html)
### put 本地文件或者标准输入 -> hdfs文件
Usage: `hadoop fs -put [-f] [-p] [-l] [-d] [ - | <localsrc1> .. ]. <dst>`

Copy single src, or multiple srcs from local file system to the destination file system. Also reads input from stdin and writes to destination file system if the source is set to “-”

Copying fails if the file already exists, unless the -f flag is given.

Options:

-p : Preserves access and modification times, ownership and the permissions. (assuming the permissions can be propagated across filesystems)
-f : Overwrites the destination if it already exists.
-l : Allow DataNode to lazily persist the file to disk, Forces a replication factor of 1. This flag will result in reduced durability. Use with care.
-d : Skip creation of temporary file with the suffix ._COPYING_.
Examples:

    hadoop fs -put localfile /user/hadoop/hadoopfile
    hadoop fs -put -f localfile1 localfile2 /user/hadoop/hadoopdir
    hadoop fs -put -d localfile hdfs://nn.example.com/hadoop/hadoopfile
    hadoop fs -put - hdfs://nn.example.com/hadoop/hadoopfile Reads the input from stdin.
Exit Code:

Returns 0 on success and -1 on error.
