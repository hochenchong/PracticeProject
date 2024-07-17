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


### NIO 编程
[nio](src/main/java/hochenchong/nio)
* [非阻塞 I/O 的 TimeServer](src/main/java/hochenchong/nio/TimeServer.java)
* [TimeClient 客户端](src/main/java/hochenchong/nio/TimeClient.java)
* 相比于 BIO 代码，NIO 代码会复杂不少。而且当前还未考虑 “半包读” 和 “半包写” 之类的。
* 优点：使用多路复用，非阻塞，可以继续做其它事情
* JDK 的 Selector 在 Linux 等主流操作系统上通过 epoll 实现，没有连接句柄数的限制，意味着一个 Selector 线程可以同时处理成千上万个客户端连接，而且性能不会随之线性下降

### AIO 编程
[aio](src/main/java/hochenchong/aio)
* [AIO 的 TimeServer](src/main/java/hochenchong/aio/TimeServer.java)
* [TimeClient 客户端](src/main/java/hochenchong/aio/TimeClient.java)
* JDK 底层通过线程池 ThreadPoolExecutor 来执行回调通知


---

### 后记

开始时间：2024-07-17

结束时间：