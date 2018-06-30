[mybatis 入门](https://blog.csdn.net/qq_32166627/article/details/70741729)
[mybatis 官网](http://www.mybatis.org/mybatis-3/zh/getting-started.html)

在map中指定namespace的全限定名，接着不用实现dao类，由mybatis通过jdk动态代理实现每个方法。
<mapper namespace="com.jimmy.dao.UserMapper">

---

XML 映射配置文件
MyBatis 的配置文件包含了会深深影响 MyBatis 行为的设置（settings）和属性（properties）信息。文档的顶层结构如下：

* configuration 配置
    * properties 属性
    * settings 设置
    * typeAliases 类型别名
    * typeHandlers 类型处理器
    * objectFactory 对象工厂
    * plugins 插件
    * environments 环境
        * environment 环境变量
            * transactionManager 事务管理器
            * dataSource 数据源
    * databaseIdProvider 数据库厂商标识
    * mappers 映射器

note: 在配置文件中，设置的顺序和上面的顺序一致。

---

mybatis DefaultObjectFactory：通过class反射实例化。

---
所以，**如果你想连接两个数据库，就需要创建两个 SqlSessionFactory 实例**，每个数据库对应一个。而如果是三个数据库，就需要三个实例，依此类推，记起来很简单：

**每个数据库对应一个 SqlSessionFactory 实例**

---
如果你正在使用 Spring + MyBatis，则**没有必要**配置事务管理器， 因为 Spring 模块会使用自带的管理器来覆盖前面的配置。

---
## 别名
注意：别名设置的位置在environments前面,否则报错
### mybatis内置
| 别名       | 映射的类型 |
| ---------- | ---------- |
| _byte      | byte       |
| _long      | long       |
| _short     | short      |
| _int       | int        |
| _integer   | int        |
| _double    | double     |
| _float     | float      |
| _boolean   | boolean    |
| string     | String     |
| byte       | Byte       |
| long       | Long       |
| short      | Short      |
| int        | Integer    |
| integer    | Integer    |
| double     | Double     |
| float      | Float      |
| boolean    | Boolean    |
| date       | Date       |
| decimal    | BigDecimal |
| bigdecimal | BigDecimal |
| object     | Object     |
| map        | Map        |
| hashmap    | HashMap    |
| list       | List       |
| arraylist  | ArrayList  |
| collection | Collection |
| iterator   | Iterator   |

### 自定义别名
<typeAliases>
  <typeAlias alias="Author" type="domain.blog.Author"/>
  <typeAlias alias="Blog" type="domain.blog.Blog"/>
  <typeAlias alias="Comment" type="domain.blog.Comment"/>
  <typeAlias alias="Post" type="domain.blog.Post"/>
  <typeAlias alias="Section" type="domain.blog.Section"/>
  <typeAlias alias="Tag" type="domain.blog.Tag"/>
</typeAliases>
用于：

* javaType
* resltType

--- 
## mybatis插入无效
手动设置提交  `sqlSession.commit(); ` 

## 得到插入后生成的主键
mybatis插入记录成功后，mysql生成主键，mybatis把主键值设置到传入的po参数中的id属性中，加入两个配置`useGeneratedKeys="true"  keyProperty="id"`

    <insert id="insertAuthor" useGeneratedKeys="true"  keyProperty="id">
    insert into Author (username,password,email,bio)
    values (#{username},#{password},#{email},#{bio})
    </insert>

## 批量插入
    <insert id="insertAuthor" useGeneratedKeys="true"        keyProperty="id">
        insert into Author (username, password, email, bio) values
            <foreach item="item" collection="list" separator=",">
                (#{item.username}, #{item.password}, #{item.email}, #{item.bio})
            </foreach>
    </insert>

## 可重用的代码片段
    <sql id="userColumns"> ${alias}.id,${alias}.username,${alias}.password </sql>
    <select id="selectUsers" resultType="map">
    select
        <include refid="userColumns"><property name="alias" value="t1"/></include>,
        <include refid="userColumns"><property name="alias" value="t2"/></include>
    from some_table t1
        cross join some_table t2
    </select>

## 字符串替换
默认情况下,使用 #{} 格式的语法会导致 MyBatis 创建 PreparedStatement 参数并安全地设置参数（就像使用 ? 一样）。这样做更安全，更迅速，通常也是首选做法，不过有时你就是想直接在 SQL 语句中插入一个不转义的字符串。比如，像 ORDER BY，你可以这样来使用：`ORDER BY ${columnName}` 这里 MyBatis 不会修改或转义字符串。
NOTE: 用${}接受用户的输入，并将其用于语句中的参数是不安全的，会导致潜在的 SQL 注入攻击，因此要么不允许用户输入这些字段，要么自行转义并检验。

## 关联association

    <resultMap id="blogResult" type="Blog">
        <id property="id" column="blog_id" />
        <result property="title" column="blog_title"/>
        <association property="author" javaType="Author">
            <id property="id" column="author_id"/>
            <result property="username" column="author_username"/>
            <result property="password" column="author_password"/>
            <result property="email" column="author_email"/>
            <result property="bio" column="author_bio"/>
        </association>
    </resultMap>

关联元素处理**“有一个”**类型的关系。association相当于一个dto有一个dto的应用。例如：订单表有一个用户，订单的dto中有一个user属性，在sql中order join user表，resultMap中有association的配置，user的返回结果就会注入到order的user属性中。


## [集合的嵌套结果](http://www.mybatis.org/mybatis-3/zh/sqlmap-xml.html) 一对多的join，返回一的记录数

    <resultMap id="blogResult" type="Blog">
        <id property="id" column="blog_id" />
        <result property="title" column="blog_title"/>
        <collection property="posts" ofType="Post">
            <id property="id" column="post_id"/>
            <result property="subject" column="post_subject"/>
            <result property="body" column="post_body"/>
        </collection>
    </resultMap>
    <select id="selectBlog" resultMap="blogResult">
        select
        B.id as blog_id,
        B.title as blog_title,
        B.author_id as blog_author_id,
        P.id as post_id,
        P.subject as post_subject,
        P.body as post_body,
        from Blog B
        left outer join Post P on B.id = P.blog_id
        where B.id = #{id}
    </select>
* id：mysql的列名，其它意义和property相同
* property：java bean对象属性
* column:mysql列名
* ofType：java类型

Blog博客表**一对多**Post文章表，join后有多条记录，用上面的这个配置后，有多篇文章的博客记录就会到blog对象的posts属性中，**返回结果的记录数等于blog的记录数**。


## 缓存
会话(一级)缓存默认开启，二级缓存默认不开启。
在sql mapper中加入 `<cache/>` 开启二级缓存，注意用到的dto全部要实现java序列化接口。

一级缓存是SqlSession级别的缓存。在操作数据库时需要构造sqlSession对象，在对象中有一个数据结构（HashMap）用于存储缓存数据。不同的sqlSession之间的缓存数据区域（HashMap）是互相不影响的。
二级缓存是mapper级别的缓存，多个SqlSession去操作同一个Mapper的sql语句，多个SqlSession可以共用二级缓存，二级缓存是跨SqlSession的。

可用的收回策略有:
* LRU – 最近最少使用的:移除最长时间不被使用的对象。
* FIFO – 先进先出:按对象进入缓存的顺序来移除它们。
* SOFT – 软引用:移除基于垃圾回收器状态和软引用规则的对象。
* WEAK – 弱引用:更积极地移除基于垃圾收集器状态和弱引用规则的对象。

 默认的是 LRU。
每种缓存的接口 `org.mybatis.cache.Cache` 
策略模式：定义了算法族，分别封装起来，让它们之间可以相互替换。此模式让算法的变化独立于使用算法的客户。
每种缓存的实现，打断点进去看。

## 动态sql

* if
* choose (when, otherwise)
* trim (where, set)
* foreach

### if
    <select id="findActiveBlogLike"
        resultType="Blog">
    SELECT * FROM BLOG WHERE state = ‘ACTIVE’ 
    <if test="title != null">
        AND title like #{title}
    </if>
    <if test="author != null and author.name != null">
        AND author_name like #{author.name}
    </if>
    </select>

### choose 类似java的switch
    <select id="findActiveBlogLike"        resultType="Blog">
    SELECT * FROM BLOG WHERE state = ‘ACTIVE’
        <choose>
            <when test="title != null">
            AND title like #{title}
            </when>
            <when test="author != null and author.name != null">
            AND author_name like #{author.name}
            </when>
            <otherwise>
            AND featured = 1
            </otherwise>
        </choose>
    </select>
### where
    <select id="findActiveBlogLike"       resultType="Blog">
    SELECT * FROM BLOG 
        <where> 
            <if test="state != null">
                state = #{state}
            </if> 
            <if test="title != null">
                AND title like #{title}
            </if>
            <if test="author != null and author.name != null">
                AND author_name like #{author.name}
            </if>
        </where>
    </select>
where的等价形式 prefix指定了至少有字母才存在这个元素， prefixOverrides的数据指定了如果where 后面有and或or就去掉

    <trim prefix="WHERE" prefixOverrides="AND |OR ">
    ... 
    </trim>

### set
    <update id="updateAuthorIfNecessary">
        update Author
            <set>
            <if test="username != null">username=#{username},</if>
            <if test="password != null">password=#{password},</if>
            <if test="email != null">email=#{email},</if>
            <if test="bio != null">bio=#{bio}</if>
            </set>
        where id=#{id}
    </update>
set的等价形式
<trim prefix="SET" suffixOverrides=",">
  ...
</trim>

### foreach
    <select id="selectPostIn" resultType="domain.blog.Post">
        SELECT *
        FROM POST P
        WHERE ID in
            <foreach item="item" index="index" collection="list" open="(" separator=","    lose=")">
                    #{item}
            </foreach>
    </select>