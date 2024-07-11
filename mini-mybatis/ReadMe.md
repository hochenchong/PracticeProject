### 精简版的 MyBatis

#### 前言

主要拿来巩固一下 MyBatis 的知识，熟悉一下源码，流程

这里以 3.5.16 版本为例，官方教程：[https://mybatis.org/mybatis-3/zh_CN/index.html](https://mybatis.org/mybatis-3/zh_CN/index.html)



#### 官方使用例子

入门使用例子：[https://mybatis.org/mybatis-3/zh_CN/getting-started.html](https://mybatis.org/mybatis-3/zh_CN/getting-started.html)

```java
// 从 XML 中构建 SqlSessionFactory
String resource = "mybatis-config.xml";
InputStream inputStream = Resources.getResourceAsStream(resource);
SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
SqlSession sqlSession = sqlSessionFactory.openSession();

UserMapper mapper = sqlSession.getMapper(UserMapper.class);
User user = mapper.getUserById("1");
System.out.println(user);
```



#### Resources 类

用于从不同的地方读取数据，转换为流之类的



#### Configuration

保存着各种映射关系，数据库信息

内部持有 Environment 对象，存着不同环境 id 对应的事务工厂，以及 DataSource 数据源



---

#### SqlSessionFactory 相关

##### SqlSessionFactoryBuilder

SqlSessionFactory 建造者，根据不同的参数配置，不同的来源转化为 Configuration，来构造 SqlSessionFactory



##### DefaultSqlSessionFactory

默认的 SqlSessionFactory 实现类，用于获取默认的 SqlSession



---

#### SqlSession

主要提供的方法是 getMapper 获取动态代理对象

调用方法时，实际上是调用 Executor 来真正执行增删改查



---

#### Executor

真正的执行器，从 Configuration 中获取数据库信息，获取连接来增删改查



---

#### MappedStatement

对解析后的数据进行存储，包括 SQL 语句，输入参数，返回类型等



---

#### 后记

很多都进行了简化，将大体流程跑通

hochenchong

开始时间：2024-07-10

结束时间：2024-07-11