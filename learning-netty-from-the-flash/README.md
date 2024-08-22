## 《跟闪电侠学Netty：Netty 即时聊天实战与底层原理》

### 前言
作者提供的代码库：[https://github.com/lightningMan/flash-netty](https://github.com/lightningMan/flash-netty)

---

### BIO，NIO，Netty 简单例子
* [bio](src/main/java/hochenchong/bio)
* [nio](src/main/java/hochenchong/nio)
* [netty](src/main/java/hochenchong/netty)

### 第 6 章 客户端与服务端双向通信
* [chapter6](src/main/java/hochenchong/chapter6)
* 在客户端连接成功之后，向服务端写一段数据；服务端收到数据之后打印，并向客户端返回一段数据
* 客户端[FirstClientHandler](src/main/java/hochenchong/chapter6/FirstClientHandler.java)和服务端[FirstServerHandler](src/main/java/hochenchong/chapter6/FirstServerHandler.java)的 Handler 都继承 ChannelInboundHandlerAdapter，重写部分代码
* 传输数据，通过没使用其它编解码器，使用默认的 ByteBuf 传输数据

### 第 7 章 数据载体 ByteBuf 的介绍
* [chapter7](src/main/java/hochenchong/chapter7)
* ByteBuf 是一个字节容器，由读指针（readerIndex）和写指针（writerIndex）划分为三个区域
  * 废弃字节：readerIndex 之前的
  * 可读字节：readerIndex 到 writerIndex
  * 可写字节：writerIndex 之后
* 扩容机制：capacity 用完后，如果还未达到 maxCapacity（最大可以占多少字节），则进行扩容，容量是 2 的幂次方
* read 和 write 方法会改变读写指针，get 和 set 不会改变读写指针

### 第 8 章节 客户端与服务端通信协议编解码
* [chapter8](src/main/java/hochenchong/chapter8)
* 通信协议设计
  * 魔数：4 字节
  * 版本号：1 字节
  * 序列化算法：1 字节
  * 指令：1 字节
  * 数据长度： 4 字节
  * 数据：N 字节
* 自定义编解码类：[PacketCodeC](src/main/java/hochenchong/chapter8/PacketCodeC.java)

### 第 9 章节 实现客户端登录
* [chapter9](src/main/java/hochenchong/chapter9)
* 复用 [chapter8](src/main/java/hochenchong/chapter8) 定义的编解码
* 客户端连接到服务器时，发送登录信息
* 服务器获取到登录信息时，校验数据，并返回结果
* 客户端输出服务器的结果

---

### 后记

开始时间：2024-08-19

结束时间：