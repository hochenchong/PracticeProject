## 设计模式的艺术

### 前言


---

### 创建型设计模式

#### [单例模式](./src/pattern01)
* [饿汉式单例：EagerSingleton](./src/pattern01/EagerSingleton.java)
* [懒汉式单例：LazySingleton](./src/pattern01/LazySingleton.java)
* [IoDH 代理，静态内部类：Singleton](./src/pattern01/Singleton.java)

#### 简单工厂模式
不属于 GoF 23 种设计模式，作为学习其他工厂模式的入门
通过 config.xml 配置来修改读取的要加载的图形类型，而不修改客户端的代码
* [ChartFactory](./src/pattern02/simple/ChartFactory.java)
* [客户端使用：Client](./src/pattern02/simple/Client.java)
缺点：加新的类型，需要修改静态工厂类


---

### 结构型设计模式


---

### 行为型设计模式

---

### 后记

开始时间：2024-07-04

结束时间：