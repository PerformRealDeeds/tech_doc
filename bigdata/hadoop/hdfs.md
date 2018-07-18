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




cp
Usage: hadoop fs -cp [-f] [-p | -p[topax]] URI [URI ...] <dest>

Copy files from source to destination. This command allows multiple sources as well in which case the destination must be a directory.

‘raw.*’ namespace extended attributes are preserved if (1) the source and destination filesystems support them (HDFS only), and (2) all source and destination pathnames are in the /.reserved/raw hierarchy. Determination of whether raw.* namespace xattrs are preserved is independent of the -p (preserve) flag.

Options:

The -f option will overwrite the destination if it already exists.
The -p option will preserve file attributes [topx] (timestamps, ownership, permission, ACL, XAttr). If -p is specified with no arg, then preserves timestamps, ownership, permission. If -pa is specified, then preserves permission also because ACL is a super-set of permission. Determination of whether raw namespace extended attributes are preserved is independent of the -p flag.
Example:

    hadoop fs -cp /user/hadoop/file1 /user/hadoop/file2
    hadoop fs -cp /user/hadoop/file1 /user/hadoop/file2 
    
    /user/hadoop/dir
Exit Code:

Returns 0 on success and -1 on error.