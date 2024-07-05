## 设计模式的艺术

### 前言


---

### 创建型设计模式

#### 单例模式（Singleton）
[pattern01](./src/pattern01)
* [饿汉式单例：EagerSingleton](./src/pattern01/EagerSingleton.java)
* [懒汉式单例：LazySingleton](./src/pattern01/LazySingleton.java)
* [IoDH 代理，静态内部类：Singleton](./src/pattern01/Singleton.java)

#### 简单工厂模式（Simple Factory Pattern）
[pattern02/simple](./src/pattern02/simple)
不属于 GoF 23 种设计模式，作为学习其他工厂模式的入门
通过 config.xml 配置来修改读取的要加载的图形类型，而不修改客户端的代码
* [ChartFactory](./src/pattern02/simple/ChartFactory.java)
* [客户端使用：Client](./src/pattern02/simple/Client.java)
缺点：加新的类型，需要修改静态工厂类

#### 工厂方法模式（Factory Method）
[pattern02/factoryMethod](./src/pattern02/factoryMethod)
又称为工厂模式，虚拟构造器模式，或多态工厂模式
提供一个抽象工厂接口，子类来实现工厂方法，创建具体的产品对象
以日志记录器为例
* [LoggerFactory](./src/pattern02/factoryMethod/LoggerFactory.java)
* [客户端使用：LoggerClient](./src/pattern02/factoryMethod/LoggerClient.java)
* 代码可以调整为，工厂不返回 Logger，而是提供方法直接调用即可，隐藏返回的 Logger，如继承 [AbstractLoggerFactory](./src/pattern02/factoryMethod/AbstractLoggerFactory.java)
解决了简单工厂模式，加新类型不需要修改抽象的工厂类
但是加一个类型，就需要加一个新的工厂类，容易造成类泛滥

#### 抽象工厂模式（Abstract Factory Pattern）
[pattern03](./src/pattern03)
创建相关或依赖对象的家族，而不是具体类
以界面皮肤库设计为例
* [SkinFactory](./src/pattern03/SkinFactory.java)
* [客户端使用：SkinClient](./src/pattern03/SkinClient.java)
抽象工厂模式中，增加新的产品族很方便，实现 SkinFactory 即可
但是增加新的产品等级结构很麻烦，需要修改 SkinFactory，导致所有子类都需要修改
所以需要前期就考虑全面，不然会改动很大

---

### 结构型设计模式


---

### 行为型设计模式

---

### 后记

开始时间：2024-07-04

结束时间：