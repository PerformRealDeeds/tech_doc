* [shell编码风格](http://zh-google-styleguide.readthedocs.io/en/latest/contents/)
* [shell 谷歌规范](https://google.github.io/styleguide/shell.xml)


## 模板:
    #!/bin/bash
    #
    # Perform hot backups of Oracle databases.

    export PATH='/usr/xpg4/bin:/usr/bin:/opt/csw/bin:/opt/goog/bin'

    #######################################
    # Cleanup files from the backup dir
    # Globals:
    #   BACKUP_DIR
    #   ORACLE_SID
    # Arguments:
    #   None
    # Returns:
    #   None
    #######################################
    cleanup() {
    ...
    }