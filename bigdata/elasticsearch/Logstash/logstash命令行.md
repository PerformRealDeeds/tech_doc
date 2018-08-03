[官网文档](https://www.elastic.co/guide/en/logstash/5.6/running-logstash-command-line.html)


 
从命令行编辑运行Logstash
要从命令行运行Logstash，请使用以下命令：

`bin / logstash [options]`
凡options有命令行可以指定控制Logstash执行的标志。bin目录的位置因平台而异。请参阅Logstash目录布局以查找bin\logstash系统上的位置。

以下示例运行Logstash并加载mypipeline.conf文件中定义的Logstash配置：

`bin / logstash -f mypipeline.conf`
您在命令行中设置的任何标志都会覆盖Logstash 设置文件中的相应设置，但设置文件本身不会更改。它仍然是后续Logstash运行的原样。

在测试Logstash时，指定命令行选项很有用。但是，在生产环境中，我们建议您使用Logstash 设置文件来控制Logstash执行。使用设置文件可以更轻松地指定多个选项，并且它为您提供了一个可版本化的文件，您可以使用该文件为每次运行一致地启动Logstash。

命令行标志编辑
Logstash具有以下标志。您可以使用该--help标志显示此信息。

--node.name NAME
指定此Logstash实例的名称。如果没有给出值，则默认为当前主机名。
-f, --path.config CONFIG_PATH
从特定文件或目录加载Logstash配置。如果给出了目录，则该目录中的所有文件将按字典顺序连接，然后作为单个配置文件进行解析。不支持多次指定此标志。如果多次指定此标志，则Logstash将使用最后一次出现（例如，与之-f foo -f bar 相同-f bar）。

您可以指定通配符（globs），任何匹配的文件将按上述顺序加载。例如，您可以使用通配符功能按名称加载特定文件：

bin / logstash --debug -f'/ tmp / {one，two，three}'
使用此命令，Logstash串接三个配置文件/tmp/one，/tmp/two以及 /tmp/three和他们解析到一个单一的配置。

-e, --config.string CONFIG_STRING
使用给定的字符串作为配置数据。与配置文件的语法相同。如果未指定任何输入，则以下内容用作默认输入： input { stdin { type => stdin } }如果未指定输出，则以下内容用作默认输出：output { stdout { codec => rubydebug } }。如果您希望同时使用两个默认值，请使用空字符串作为-e标志。默认值为nil。
-w, --pipeline.workers COUNT
设置要运行的管道工程数。此选项设置将并行执行管道的过滤器和输出阶段的工作器数。如果发现事件正在备份，或者CPU未饱和，请考虑增加此数量以更好地利用机器处理能力。默认值是主机CPU核心的数量。
-b, --pipeline.batch.size SIZE
管道要使用的批处理大小。此选项定义在尝试执行其过滤器和输出之前，单个工作线程将从输入收集的最大事件数。默认值为125个事件。较大的批量通常更有效，但代价是增加了内存开销。您可能必须通过设置LS_HEAP_SIZE 变量来有效地使用该选项来增加JVM堆大小。
-u, --pipeline.batch.delay DELAY_IN_MS
创建管道批次时，轮询下一个事件需要等待多长时间。此选项定义在将较小的批处理分派给筛选器和工作程序之前轮询下一个事件时等待的时间长度（以毫秒为单位）。默认值为250毫秒。
--pipeline.unsafe_shutdown
强制Logstash在关闭期间退出，即使内存中仍有飞行事件。默认情况下，Logstash将拒绝退出，直到所有已接收的事件都被推送到输出。启用此选项可能会导致关闭期间数据丢失。
--path.data PATH
这应该指向一个可写目录。Logstash将在需要存储数据时使用此目录。插件也可以访问此路径。默认值是dataLogstash home 下的目录。
-p, --path.plugins PATH
找到自定义插件的路径。可以多次给出该标志以包括多个路径。插件预计将在一个特定的目录层次结构： PATH/logstash/TYPE/NAME.rb其中TYPE是inputs，filters，outputs，或codecs，并且NAME是插件的名称。
-l, --path.logs PATH
将Logstash内部日志写入的目录。
--log.level LEVEL
设置Logstash的日志级别。可能的值是：

fatal：记录应用程序中止后通常会出现的非常严重的错误消息
error：记录错误
warn：日志警告
info：log verbose info（这是默认值）
debug：日志调试信息（面向开发人员）
trace：记录除调试信息之外的更细粒度的消息
--config.debug
将完全编译的配置显示为调试日志消息（您还必须已--log.level=debug启用）。警告：日志消息将包含作为纯文本传递给插件配置的任何密码选项，并可能导致明文密码出现在您的日志中！
-i, --interactive SHELL
下降到shell而不是正常运行。有效的shell是“irb”和“pry”。
-V, --version
发出Logstash及其好友的版本，然后退出。
-t, --config.test_and_exit
检查配置是否有效语法，然后退出。请注意，使用此标志不会检查grok模式的正确性。Logstash可以从目录中读取多个配置文件。如果将此标志组合在一起--log.level=debug，Logstash将记录组合​​的配置文件，使用它来自的源文件注释每个配置块。
-r, --config.reload.automatic
监视配置更改并在配置更改时重新加载。注意：使用SIGHUP手动重新加载配置。默认值为false。
--config.reload.interval RELOAD_INTERVAL
以秒为单位轮询配置位置以进行更改的频率。默认值为每3秒。
--http.host HTTP_HOST
Web API绑定主机。此选项指定度量标准REST端点的绑定地址。默认值为“127.0.0.1”。
--http.port HTTP_PORT
Web API http端口。此选项指定度量标准REST端点的绑定端口。默认值为9600-9700。此设置接受格式9600-9700的范围。Logstash将获取第一个可用端口。
--log.format FORMAT
指定Logstash是否应以JSON格式（每行一个事件）或纯文本（使用Ruby的Object＃inspect）编写自己的日志。默认为“plain”。
--path.settings SETTINGS_DIR
设置包含logstash.yml 设置文件的目录以及log4j日志记录配置。这也可以通过LS_SETTINGS_DIR环境变量设置。默认值是configLogstash home 下的目录。
-h, --help
打印帮助