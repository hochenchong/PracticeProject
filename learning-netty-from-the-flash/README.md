## 《跟闪电侠学Netty：Netty 即时聊天实战与底层原理》

### 前言
作者提供的代码库：[https://github.com/lightningMan/flash-netty](https://github.com/lightningMan/flash-netty)

---

### BIO，NIO，Netty 简单例子
* [bio](src/main/java/hochenchong/test/bio)
* [nio](src/main/java/hochenchong/test/nio)
* [netty](src/main/java/hochenchong/test/netty)

### 第 6 章 客户端与服务端双向通信
* [chapter6](src/main/java/hochenchong/chapter/chapter6)
* 在客户端连接成功之后，向服务端写一段数据；服务端收到数据之后打印，并向客户端返回一段数据
* 客户端[FirstClientHandler](src/main/java/hochenchong/chapter/chapter6/FirstClientHandler.java)和服务端[FirstServerHandler](src/main/java/hochenchong/chapter/chapter6/FirstServerHandler.java)的 Handler 都继承 ChannelInboundHandlerAdapter，重写部分代码
* 传输数据，通过没使用其它编解码器，使用默认的 ByteBuf 传输数据

### 第 7 章 数据载体 ByteBuf 的介绍
* [chapter7](src/main/java/hochenchong/chapter/chapter7)
* ByteBuf 是一个字节容器，由读指针（readerIndex）和写指针（writerIndex）划分为三个区域
  * 废弃字节：readerIndex 之前的
  * 可读字节：readerIndex 到 writerIndex
  * 可写字节：writerIndex 之后
* 扩容机制：capacity 用完后，如果还未达到 maxCapacity（最大可以占多少字节），则进行扩容，容量是 2 的幂次方
* read 和 write 方法会改变读写指针，get 和 set 不会改变读写指针

### 第 8 章节 客户端与服务端通信协议编解码
* [protocol](src/main/java/hochenchong/protocol)
* 通信协议设计
  * 魔数：4 字节
  * 版本号：1 字节
  * 序列化算法：1 字节
  * 指令：1 字节
  * 数据长度： 4 字节
  * 数据：N 字节
* 自定义编解码类：[PacketCodeC](src/main/java/hochenchong/protocol/PacketCodeC.java)
* 序列化：[Serializer](src/main/java/hochenchong/serialize/Serializer.java)

### 第 9 章节 实现客户端登录
* [chapter9](src/main/java/hochenchong/chapter/chapter9)
* 复用上一章定义的编解码
* 客户端连接到服务器时，发送登录信息
* 服务器获取到登录信息时，校验数据，并返回结果
* 客户端输出服务器的结果

### 第 10 章　实现客户端与服务端收发消息
* [chapter10](src/main/java/hochenchong/chapter/chapter10)
* 客户端从命令行输入内容，服务器响应消息

### 第 11 章　Pipeline 与 ChannelHandler
* 处理流程：数据流入 - 解码 - 业务处理，回包 - 编码 - 数据流出
* Netty 通过责任链设计模式来组织代码逻辑，支持逻辑的动态添加和删除
* Pipeline
  * 一个 channel 对应一个 Pipeline
  * 双向链表结构，每个节点是一个 ChannelHandler 对象
* ChannelHandler 两个子接口
  * ChannelInboundHandler
    * inboundHandler 的事件通常只会传播到下一个 inboundHandler
    * 执行顺序与添加的顺序保持一致
  * ChannelOutboundHandler
    * outboundHandler 的事件通常只会传播到下一个 outboundHandler
    * 执行顺序与添加的顺序相反

### 第 12 章　构建客户端与服务端的 Pipeline
* [chapter12](src/main/java/hochenchong/chapter/chapter12)
* 使用内置的 Handler 简化开发，专注于逻辑
* ByteToMessageDecoder 与 MessageToByteEncoder，自定义编解码，不用自己去分配 ByteBuf，便于 Netty 管理内存的分配与释放
* SimpleChannelInboundHandler 简化开发，实现类型判断与对象传递，减少大量的 if else 类型判断与类型转换

### 第 13 章　拆包/粘包理论与解决方案
* [chapter13](src/main/java/hochenchong/chapter/chapter13)
* 在大多数情况下，Netty 底层是通过 TCP 协议来进行数据传输的。可能会出现粘包或者半包现象
  * 将 [chapter6](src/main/java/hochenchong/chapter/chapter6) 里的 FirstClientHandler 发送消息改为 for 循环 1000 次出现了现象
* Netty 自带了几种类型的解码器
  * 固定长度的拆包器 FixedLengthFrameDecoder
  * 行拆包器 LineBasedFrameDecoder
  * 分隔符拆包器 DelimiterBasedFrameDecoder
  * 基于长度域的拆包器 LengthFieldBasedFrameDecoder（最通用）
* 自定义解码器：[CustomLengthFieldBasedFrameDecoder](src/main/java/hochenchong/chapter/chapter13/CustomLengthFieldBasedFrameDecoder.java)
  * 继承 LengthFieldBasedFrameDecoder，重写 decode 方法
  * 拒接非本协议的消息
  * 使用命令行连接：`telnet 127.0.0.1 8000`，然后随便发个消息 `send x`，连接就被关闭了

### 第 14 章　ChannelHandler 的生命周期
* [chapter14](src/main/java/hochenchong/chapter/chapter14)
* ChannelHandler 的生命周期
  * 连接时：handlerAdded -> channelRegistered -> channelActive -> channelRead -> channelReadComplete
  * 关闭连接：channelInactive -> channelUnregistered -> handlerRemoved
* ChannelInitializer 本质上也是 ChannelInboundHandlerAdapter
  * 在被调用 handlerAdded 和 channelRegistered 都尝试调用了重写的 `initChannel` 方法来添加设置的处理器
  * initChannel 方法使用 putIfAbsent() 方法来防止被调用多次

### 第 15 章　使用 ChannelHandler 的热插拔实现客户端身份校验
* [chapter15](src/main/java/hochenchong/chapter/chapter15)
* 通过 [AuthHandler](src/main/java/hochenchong/chapter/chapter15/AuthHandler.java) 对登录状态进行校验
  * 校验成功后，则移除该校验模块，避免重复校验
  * 校验失败，则直接关闭连接

### 第 16 章　客户端互聊的原理与实现
* [chapter16](src/main/java/hochenchong/chapter/chapter16)
* 绑定信息，以此实现两个客户端通过对方的 id 进行互聊
* 客户端和服务器建立联系，但 Channel 不是同一个，是对应的关系。即服务器的 channel 与客户端的 channel 不是同一个对象，但彼此映射

### 第 17 章　群聊的发起与通知
* [chapter17](src/main/java/hochenchong/chapter/chapter17)
* ChannelGroup：多个 Channel 操作聚合一起，批量读写操作。

### 第 18 章　群聊的成员管理
* [chapter18](src/main/java/hochenchong/chapter/chapter18)
* 群成员的管理：加入，退出，获取成员列表

### 第 19 章　群聊消息的收发及 Netty 性能优化
* [chapter19](src/main/java/hochenchong/chapter/chapter19)
* 对于服务器，每次有新的客户端连接，都会调用 ChannelInitializer 的 initChannel() 方法，里面的 Handler 都会被创建一遍 
* 优化：
* 对于无状态的 Handler，使用单例模式，减少大量 Handler 对象创建
  * Handler 被共享，必须加上 @ChannelHandler.Sharable 注解，不然会报错
* 合并平行 Handler，[IMReqHandler](src/main/java/hochenchong/chapter/chapter19/server/IMReqHandler.java)
* 耗时长的操作，丢到线程池去处理

### 第 20 章 心跳与空闲检测
* [chapter20](src/main/java/hochenchong/chapter/chapter20)
* 服务器与客户端，使用 IdleStateHandler 进行空闲检测，发送心跳维持连接

---

### 后记

开始时间：2024-08-19

结束时间：