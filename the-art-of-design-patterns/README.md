## 设计模式的艺术

### 前言
复习一下设计模式，顺带把书中例子敲一遍，代码不一定和书中一致，部分做了调整

章节对不上，根据标题即可

---

### 创建型设计模式

#### 单例模式（Singleton）
[singleton](src/creational/singleton)
* [饿汉式单例：EagerSingleton](src/creational/singleton/EagerSingleton.java)
* [懒汉式单例：LazySingleton](src/creational/singleton/LazySingleton.java)
* [IoDH 代理，静态内部类：Singleton](src/creational/singleton/Singleton.java)

#### 简单工厂模式（Simple Factory Pattern）
[factory/simple](src/creational/factory/simple)
* 不属于 GoF 23 种设计模式，作为学习其他工厂模式的入门
* 通过 config.xml 配置来修改读取的要加载的图形类型，而不修改客户端的代码
* [ChartFactory](src/creational/factory/simple/ChartFactory.java)
* [客户端使用：Client](src/creational/factory/simple/Client.java)
* 缺点：加新的类型，需要修改静态工厂类

#### 工厂方法模式（Factory Method）
[factory/factoryMethod](src/creational/factory/factoryMethod)
* 又称为工厂模式，虚拟构造器模式，或多态工厂模式
* 提供一个抽象工厂接口，子类来实现工厂方法，创建具体的产品对象
* 以日志记录器为例
* [LoggerFactory](src/creational/factory/factoryMethod/LoggerFactory.java)
* [客户端使用：LoggerClient](src/creational/factory/factoryMethod/LoggerClient.java)
* 代码可以调整为，工厂不返回 Logger，而是提供方法直接调用即可，隐藏返回的 Logger，如继承 [AbstractLoggerFactory](src/creational/factory/factoryMethod/AbstractLoggerFactory.java)
* 解决了简单工厂模式，加新类型不需要修改抽象的工厂类
* 但是加一个类型，就需要加一个新的工厂类，容易造成类泛滥

#### 抽象工厂模式（Abstract Factory Pattern）
[abstractFactory](src/creational/abstractFactory)
* 创建相关或依赖对象的家族，而不是具体类
* 以界面皮肤库设计为例
* [SkinFactory](src/creational/abstractFactory/SkinFactory.java)
* [客户端使用：SkinClient](src/creational/abstractFactory/SkinClient.java)
* 抽象工厂模式中，增加新的产品族很方便，实现 SkinFactory 即可
* 但是增加新的产品等级结构很麻烦，需要修改 SkinFactory，导致所有子类都需要修改
* 所以需要前期就考虑全面，不然会改动很大

#### 原型模式（Prototype Pattern）
[prototype](src/creational/prototype)
* 复制现有对象来创建对象，如果通过克隆，需要注意深克隆与浅克隆
* 或者通过序列化的方式，再反序列化为对象
* 优点在性能好，尤其在创建复杂对象时
* 以周报为例
* [WeeklyLog](src/creational/prototype/WeeklyLog.java)
* [客户端使用：WeeklyLogClient](src/creational/prototype/WeeklyLogClient.java)
* 默认的克隆是浅克隆
* 使用序列化的方式则为深克隆

#### 建造者模式（Builder Pattern）
[builder](src/creational/builder)
* 通过一个领导者，逐步构建一个复杂对象
* 也可以将领导者的构建方法，写在抽象 builder 对象中，提供统一步骤的构建方式
* [客户端使用：ActorClient](src/creational/builder/ActorClient.java)
* 还有种形式的，调用的大部分方法，返回值都是 Builder 对象，直到调用 build 方法才返回最终的对象。通过链式调用的方式，代码可读性更强，结构更清晰。例如
* [BuilderActor](src/creational/builder/BuilderActor.java)

---

### 结构型设计模式

#### 适配器模式（Adapter Pattern）
[adapter](src/structural/adapter)
1. **对象适配器模式**：通过组合来实现适配器功能。
* [客户端使用：AdapterClient](src/structural/adapter/AdapterClient.java)
* [适配器：OperationAdapter](src/structural/adapter/OperationAdapter.java)
2. **类适配器模式**：通过继承来实现适配器功能。
3. **缺省适配器模式**：不需要实现接口提供的所有方法，设计一个抽象类实现该接口，对方法提供默认实现（空方法）

#### 桥接模式（Bridge Pattern）
[bridge](src/structural/bridge)
* 将不同纬度的两个类建立连接
* 以不同图片格式解析，和不同操作系统展示图片为例
* [客户端使用：ImageClient](src/structural/bridge/ImageClient.java)
* [图片格式解析抽象类：Image](src/structural/bridge/Image.java)
* [操作系统显示接口：SystemImp](src/structural/bridge/SystemImp.java)
* 处理多维度问题

#### 组合模式（Composite Pattern）
[composite](src/structural/composite)
* 处理树形结构，强调将对象组合成更复杂的结构，所以叫组合模式
* 将叶子结点和非叶子结点都看成节点来处理，叶子节点没有子节点
* 组合模式使得用户对单个对象和组合对象的使用具有一致性。
* 
* 以杀毒软件扫描文件夹和文件为例，将文件夹和文件都看成抽象文件（有点 Linux 里的“万物皆为文件”意思）
* [客户端使用：VirusClient](src/structural/composite/VirusClient.java)
* [抽象文件类：AbstractFile](src/structural/composite/AbstractFile.java)
* **透明组合模式**：不够安全，叶子构件需要处理 add，remove 之类的方法，对叶子构件是无意义的
* **安全组合模式**：不够透明，即抽象组件不包含叶子构件没有的方法，例如 add，remove 之类的。Java AWT 中使用的组合模式就是安全组合模式

#### 装饰器模式（Decorator Pattern）
[decorator](src/structural/decorator)
* 以图形界面构件库为例
* [客户端使用：DecoratorClient](src/structural/decorator/DecoratorClient.java)
* 通过装饰类，扩展原本组件的功能
* 例如 Java IO 流

#### 外观模式（Facade Pattern）
[facade](src/structural/facade)
* 对客户端调用来说，只要对外观类处理即可，里面怎么调用子系统无需关注
* 对于子系统，也只是将外观类当作一个客户端
* 以文件加密模块为例
* [客户端使用：FacadeClient](src/structural/facade/FacadeClient.java)
* [外观类：EncryptFacade](src/structural/facade/EncryptFacade.java)
* 降低客户端与子系统对耦合

#### 享元模式（Flyweight Pattern）
[flyweight](src/structural/flyweight)
* 实现对象的复用
* 享：共享，元：元素/资源
* 以围棋黑白子为例
* [客户端使用：FlyweightClient](src/structural/flyweight/FlyweightClient.java)
* 内部共享，如棋子颜色
* 外部则通过传入的方式，如 [Coordinates](src/structural/flyweight/Coordinates.java)
* 一般和工厂模式或者组合模式配合使用
* Integer 类就使用了享元模式，默认先创建和缓存 -128～127 之间的 Integer 对象，需要的是直接返回，而不是创建新的对象
* String 类也使用了，以此来减少内存中对象的数量

#### 代理模式（Proxy Pattern）
[proxy](src/structural/proxy)
* 代理对象，由代理对象控制对原对象的引用。
* 以查询系统为例
* [客户端使用：ProxyClient](src/structural/proxy/ProxyClient.java)
* [代理对象：SearcherProxy](src/structural/proxy/SearcherProxy.java)
* 静态代理，提前写好
* 动态代理，运行时动态创建代理类。例如 aop，事务管理等。动态代理，JDK 的 Proxy 或者 CGLib 等工具
* 与装饰模式区别，职责不同：代理是扩展的职责与原有职责不属于同一个问题域，而装饰的同类职责


---

### 行为型设计模式

#### 职责链模式（Chain of Responsibility Pattern）
[chainOfResponsibility](src/behavioral/chainOfResponsibility)
* 请求的链式处理
* 以采购单分级审批为例
* [客户端使用：ChainClient](src/behavioral/chainOfResponsibility/ChainClient.java)
* 例子：web 应用中的过滤器（Filter）链

#### 命令模式（Command Pattern）
[command](src/behavioral/command)
* 请求发送者与接收者解耦
* [客户端使用：CommandClient](src/behavioral/command/CommandClient.java)
* 将请求者的操作，与传入的命令交流。由命令来与接收者打交道。而请求者不需要知道具体的接收者是谁
* 例如 JDK 中，Runnable、Callable

####  解释器模式（Interpreter Pattern）
[interpreter](src/behavioral/interpreter)
* 自定义语言的实现
* 用的较少
* 以机器人控制程序结构为例
* [客户端使用：InterpreterClient](src/behavioral/interpreter/InterpreterClient.java)
* [抽象表达式：AbstractNode](src/behavioral/interpreter/AbstractNode.java)

#### 迭代器模式（Iterator Pattern）
[iterator](src/behavioral/iterator)
* 遍历聚合对象中的元素
* 将遍历数据，与数据存储分离。通过迭代器，无需暴露聚合类的内部属性
* 管理系统数据遍历为例
* [客户端使用：IteratorClient](src/behavioral/iterator/IteratorClient.java)
* [抽象迭代器：AbstractIterator](src/behavioral/iterator/AbstractIterator.java)
* JDK 使用内部类实现迭代器

#### 中介者模式（Mediator Pattern）
[mediator](src/behavioral/mediator)
* 协调多个对象之间的交互
* 网状结构，提出一个中介者来交互，变成星状图
* [客户端使用：MediatorClient](src/behavioral/mediator/MediatorClient.java)
* 缺点：中介者大量交互逻辑


#### 备忘录模式（Memento Pattern）
[memento](src/behavioral/memento)
* 撤销功能的实现
* 以中国象棋棋子撤销功能为例
* [客户端使用：MementoClient](src/behavioral/memento/MementoClient.java)
* 原发器和备忘录类放一个包中，满足默认的包内可见行，或者将备忘录类作为原发器类的内部类，只有原发器类才能访问备忘录中的数据
* 使用频率不太高
* 缺点：资源消耗过大

#### 观察者模式（Observer Pattern）
[observer](src/behavioral/observer)
* 对象间的联动
* 别名：发布-订阅模式
* 以多人联机对战游戏为例
* [客户端使用：ObserverClient](src/behavioral/observer/ObserverClient.java)
* 使用频率最高的设计模式之一

#### 状态模式（State Pattern）
[state](src/behavioral/state)
* [客户端使用：StateClient](src/behavioral/state/StateClient.java)

#### 策略模式（Strategy Pattern）
[strategy](src/behavioral/strategy)
* [客户端使用：StrategyClient](src/behavioral/strategy/StrategyClient.java)

#### 模板方法模式（Template Method Pattern）
[template](src/behavioral/template)
* [客户端使用：TemplateClient](src/behavioral/template/TemplateClient.java)

#### 访问者模式（Visitor Pattern）
[visitor](src/behavioral/visitor)
* [客户端使用：VisitorClient](src/behavioral/visitor/VisitorClient.java)

---

### 后记

开始时间：2024-07-04

结束时间：