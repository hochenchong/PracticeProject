## Java 并发编程之美

### 前言
该项目记录一下读《Java 并发编程之美》时，书中的代码（并不一定和书中一样）。

---

### 第 1 章 并发编程线程基础
#### 1.2 线程创建与运行
* [ThreadTest](./src/chapter01/ThreadTest.java)

#### 1.3 线程通知与等待
* [WaitNotifyInterrupt](./src/chapter01/WaitNotifyInterrupt.java)

#### 1.5 让线程睡眠的 sleep 方法
* [SleepTest](./src/chapter01/SleepTest.java)

#### 1.6 让出CPU执行权的yield方法
* [YieldTest](./src/chapter01/YieldTest.java)

#### 1.9 线程死锁
* [DeadLockTest](./src/chapter01/DeadLockTest.java)

#### 1.10 守护线程与用户线程
* [DaemonThreadTest](./src/chapter01/DaemonThreadTest.java)

#### 1.11 ThreadLocal
* [ThreadLocalTest](./src/chapter01/ThreadLocalTest.java)

---

### 第 2 章 并发编程的其他基础知识

#### 2.11 伪共享
* [TestForContent](./src/chapter02/TestForContent.java)

---

### 第 6 章 Java 并发包中锁原理剖析

#### 6.2 抽象同步队列 AQS 概述
* [NonReentrantLock](./src/chapter06/NonReentrantLock.java)

#### 6.3 独占锁 ReentrantLock 的原理
* [ReentrantLockList](./src/chapter06/ReentrantLockList.java)

#### 6.4 读写锁 ReentrantReadWriteLock 的原理
* [ReentrantReadWriteLockList](./src/chapter06/ReentrantReadWriteLockList.java)

---

### 第 10 章 Java 并发包中线程同步器原理剖析

#### 10.1 CountDownLatch 原理剖析
* [CountDownLatchDemo1](./src/chapter10/CountDownLatchDemo1.java)
* [CountDownLatchDemo2](./src/chapter10/CountDownLatchDemo2.java)

#### 10.2 回环屏障CyclicBarrier原理探究
* [CycleBarrierDemo1](./src/chapter10/CycleBarrierDemo1.java)
* [CycleBarrierDemo2](./src/chapter10/CycleBarrierDemo2.java)

#### 10.3 信号量 Semaphore 原理探究
* [SemaphoreDemo1](./src/chapter10/SemaphoreDemo1.java)

---

### 第 11 章 Java 并发编程实践篇

#### 11.4 SimpleDateFormat是线程不安全的
* [TestSimpleDateFormat](./src/chapter11/TestSimpleDateFormat.java)
* [TestSimpleDateFormat1](./src/chapter11/TestSimpleDateFormat2.java)

#### 11.5 使用Timer时需要注意的事情
* [TestScheduledThreadPoolExecutor](./src/chapter11/TestScheduledThreadPoolExecutor.java)

#### 11.9 线程池使用FutureTask时需要注意的事情
* [FutureTest](./src/chapter11/FutureTest.java)

#### 11.10 使用 ThreadLocal 不当可能会导致内存泄漏
* [ThreadPoolTest](./src/chapter11/ThreadPoolTest.java)

---

### 后记

开始时间：2024-05-31

结束时间：2024-06-04