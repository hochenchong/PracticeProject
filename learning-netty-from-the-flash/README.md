## 《跟闪电侠学Netty：Netty 即时聊天实战与底层原理》

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


---

### 后记

开始时间：2024-08-19

结束时间：