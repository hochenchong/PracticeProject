## Java 并发编程之美

### 前言
该项目记录一下读《Java 并发编程之美》时，书中的代码。

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


### 后记
时间：2024-05-31