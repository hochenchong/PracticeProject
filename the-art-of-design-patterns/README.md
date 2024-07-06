## 设计模式的艺术

### 前言
复习一下设计模式，顺带把书中例子敲一遍
章节对不上，根据标题即可

---

### 创建型设计模式

#### 单例模式（Singleton）
[pattern01](./src/pattern01)
* [饿汉式单例：EagerSingleton](./src/pattern01/EagerSingleton.java)
* [懒汉式单例：LazySingleton](./src/pattern01/LazySingleton.java)
* [IoDH 代理，静态内部类：Singleton](./src/pattern01/Singleton.java)

#### 简单工厂模式（Simple Factory Pattern）
[pattern02/simple](./src/pattern02/simple)
* 不属于 GoF 23 种设计模式，作为学习其他工厂模式的入门
* 通过 config.xml 配置来修改读取的要加载的图形类型，而不修改客户端的代码
* [ChartFactory](./src/pattern02/simple/ChartFactory.java)
* [客户端使用：Client](./src/pattern02/simple/Client.java)
* 缺点：加新的类型，需要修改静态工厂类

#### 工厂方法模式（Factory Method）
[pattern02/factoryMethod](./src/pattern02/factoryMethod)
* 又称为工厂模式，虚拟构造器模式，或多态工厂模式
* 提供一个抽象工厂接口，子类来实现工厂方法，创建具体的产品对象
* 以日志记录器为例
* [LoggerFactory](./src/pattern02/factoryMethod/LoggerFactory.java)
* [客户端使用：LoggerClient](./src/pattern02/factoryMethod/LoggerClient.java)
* 代码可以调整为，工厂不返回 Logger，而是提供方法直接调用即可，隐藏返回的 Logger，如继承 [AbstractLoggerFactory](./src/pattern02/factoryMethod/AbstractLoggerFactory.java)
* 解决了简单工厂模式，加新类型不需要修改抽象的工厂类
* 但是加一个类型，就需要加一个新的工厂类，容易造成类泛滥

#### 抽象工厂模式（Abstract Factory Pattern）
[pattern03](./src/pattern03)
* 创建相关或依赖对象的家族，而不是具体类
* 以界面皮肤库设计为例
* [SkinFactory](./src/pattern03/SkinFactory.java)
* [客户端使用：SkinClient](./src/pattern03/SkinClient.java)
* 抽象工厂模式中，增加新的产品族很方便，实现 SkinFactory 即可
* 但是增加新的产品等级结构很麻烦，需要修改 SkinFactory，导致所有子类都需要修改
* 所以需要前期就考虑全面，不然会改动很大

#### 原型模式（Prototype Pattern）
[pattern04](./src/pattern04)
* 复制现有对象来创建对象，如果通过克隆，需要注意深克隆与浅克隆
* 或者通过序列化的方式，再反序列化为对象
* 优点在性能好，尤其在创建复杂对象时
* 以周报为例
* [WeeklyLog](./src/pattern04/WeeklyLog.java)
* [客户端使用：WeeklyLogClient](./src/pattern04/WeeklyLogClient.java)
* 默认的克隆是浅克隆
* 使用序列化的方式则为深克隆

#### 建造者模式（Builder Pattern）
[pattern05](./src/pattern05)
* 通过一个领导者，逐步构建一个复杂对象
* 也可以将领导者的构建方法，写在抽象 builder 对象中，提供统一步骤的构建方式
* [客户端使用：ActorClient](./src/pattern05/ActorClient.java)
* 还有种形式的，调用的大部分方法，返回值都是 Builder 对象，直到调用 build 方法才返回最终的对象。通过链式调用的方式，代码可读性更强，结构更清晰。例如
* [BuilderActor](./src/pattern05/BuilderActor.java)

---

### 结构型设计模式

#### 适配器模式（Adapter Pattern）
[pattern11](./src/pattern11)
1. **对象适配器模式**：通过组合来实现适配器功能。
* [客户端使用：AdapterClient](./src/pattern11/AdapterClient.java)
* [适配器：OperationAdapter](./src/pattern11/OperationAdapter.java)
2. **类适配器模式**：通过继承来实现适配器功能。
3. **缺省适配器模式**：不需要实现接口提供的所有方法，设计一个抽象类实现该接口，对方法提供默认实现（空方法）

#### 桥接模式（Bridge Pattern）
[pattern12](./src/pattern12)
* 将不同纬度的两个类建立连接
* 以不同图片格式解析，和不同操作系统展示图片为例
* [客户端使用：ImageClient](./src/pattern12/ImageClient.java)
* [图片格式解析抽象类：Image](./src/pattern12/Image.java)
* [操作系统显示接口：SystemImp](./src/pattern12/SystemImp.java)
* 处理多维度问题

#### 组合模式（Composite Pattern）
[pattern13](./src/pattern13)
* 处理树形结构，强调将对象组合成更复杂的结构，所以叫组合模式
* 将叶子结点和非叶子结点都看成节点来处理，叶子节点没有子节点
* 组合模式使得用户对单个对象和组合对象的使用具有一致性。
* 
* 以杀毒软件扫描文件夹和文件为例，将文件夹和文件都看成抽象文件（有点 Linux 里的“万物皆为文件”意思）
* [客户端使用：VirusClient](./src/pattern13/VirusClient.java)
* [抽象文件类：AbstractFile](./src/pattern13/AbstractFile.java)
* **透明组合模式**：不够安全，叶子构件需要处理 add，remove 之类的方法，对叶子构件是无意义的
* **安全组合模式**：不够透明，即抽象组件不包含叶子构件没有的方法，例如 add，remove 之类的。Java AWT 中使用的组合模式就是安全组合模式

#### 装饰器模式（Decorator Pattern）
[pattern14](./src/pattern14)
* 以图形界面构件库为例
* [客户端使用：DecoratorClient](./src/pattern14/DecoratorClient.java)
* 通过装饰类，扩展原本组件的功能
* 例如 Java IO 流

#### 外观模式（Facade Pattern）
[pattern15](./src/pattern15)
* 对客户端调用来说，只要对外观类处理即可，里面怎么调用子系统无需关注
* 对于子系统，也只是将外观类当作一个客户端
* 以文件加密模块为例
* [客户端使用：FacadeClient](./src/pattern15/FacadeClient.java)
* [外观类：EncryptFacade](./src/pattern15/EncryptFacade.java)
* 降低客户端与子系统对耦合

#### 享元模式（Flyweight Pattern）
[pattern16](./src/pattern16)
* 实现对象的复用
* 享：共享，元：元素/资源
* 以围棋黑白子为例
* [客户端使用：FlyweightClient](./src/pattern16/FlyweightClient.java)
* 内部共享，如棋子颜色
* 外部则通过传入的方式，如 [Coordinates](./src/pattern16/Coordinates.java)
* 一般和工厂模式或者组合模式配合使用
* Integer 类就使用了享元模式，默认先创建和缓存 -128～127 之间的 Integer 对象，需要的是直接返回，而不是创建新的对象
* String 类也使用了，以此来减少内存中对象的数量

#### 代理模式（Proxy Pattern）

---

### 行为型设计模式

---

### 后记

开始时间：2024-07-04

结束时间：