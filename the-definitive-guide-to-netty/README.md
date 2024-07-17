## Netty 权威指南

---

### 传统的 BIO 编程
[bio](src/main/java/hochenchong/bio)
* [同步阻塞 I/O 的 TimeServer](src/main/java/hochenchong/bio/TimeServer.java)
* [TimeClient 客户端](src/main/java/hochenchong/bio/TimeClient.java)
* [伪异步 I/O 的 TimeServer](src/main/java/hochenchong/bio/TimeServer2.java)
* 同步阻塞，来一个客户端则创建一个新的线程处理。服务器线程个数与客户端访问数量 1:1 正比关系
* 伪异步 I/O，通过线程池，限制了线程数量，避免线程耗尽。
* 底层通信还是同步阻塞模型

---

### 后记

开始时间：2024-07-17

结束时间：