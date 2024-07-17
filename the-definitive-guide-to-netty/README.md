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
* 使用 ChatGPT 举例个 demo：[aiodemo](src/main/java/hochenchong/aiodemo)
* 相比于 NIO 代码，多了不少回调和异步操作

为何 netty 不使用 aio 实现？
* 成熟性和稳定性：NIO 自 JDK 1.4 引入以来，经过多年的发展和优化，已经变得非常成熟和稳定。相对而言，AIO（引入于 JDK 1.7）在实践中应用较少，社区支持和经验积累也不足。
* 性能和延迟：NIO 的非阻塞模型已经能够满足大部分高性能网络应用的需求。Netty 通过精心设计和优化，利用 NIO 实现了高效的 I/O 操作。在很多情况下，NIO 的性能和延迟表现优于 AIO，特别是在处理大量小型请求时。
* 复杂性：AIO 的编程模型相对复杂，需要处理更多的回调和异步操作，增加了代码的复杂性和维护成本。而 NIO 的非阻塞模型虽然也需要处理选择器和通道，但相对更简单易懂。
* 兼容性：Netty 需要支持各种版本的 Java 运行环境。NIO 从 JDK 1.4 开始就有支持，而 AIO 需要 JDK 1.7 及以上版本。使用 NIO 可以确保更广泛的兼容性和用户基础。
* 生态系统和社区支持：Netty 社区已经在 NIO 上积累了大量的经验和最佳实践，开发者也更熟悉 NIO 的使用。改变为 AIO 需要重新设计和实现很多功能，成本较高。

---

### 后记

开始时间：2024-07-17

结束时间：