## 《玩转 Spring 全家桶》 学习笔记
### 第一天
#### 前期准备
> Spring Boot 快速构建官方网站：[https://start.spring.io/](https://start.spring.io/)
> 
> Spring Boot 选择当前最新稳定版本：2.1.5
> 
> 依赖选择：Web、Actuator（用于监控和管理应用）
> 
> 点击 “Generate Project” 按钮，下载 demo，使用 IDE 工具导入项目（这里使用的是 IDEA，下面提到的快捷键，默认均为 IDEA 快捷键）

#### Hello Spring
修改 SpringApplication 类，代码如下
```
@SpringBootApplication
@RestController
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

	@RequestMapping("/hello")
	public String hello() {
		return "Hello Spring!";
	}
}
```
运行该类的 main 方法，启动项目。使用 ALT + F12 打开 IDEA 的 Terminal 对该项目进行访问
```
curl http://localhost:8080/hello
```
查看项目的健康状态
```
curl http://localhost:8080/actuator/health
```
正常返回如下
```
{"status":"UP"}
```

#### 打包项目
在 Terminal 中输入以下命令，注意该命令在此项目目录下输入
```
mvn clean package -Dmaven.test.skip
```
以上命令是使用 maven 插件进行打包，并跳过测试，之后就会在项目根目录下的 target 文件夹下生成一个 jar 包。

可以使用命令：```java -jar xxx.jar``` 命令来运行

#### pom.xml 不引入父工程如何引入 spring-boot-starter-parent
pom.xml 中 project 标签下加入
```xml
	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-parent</artifactId>
				<version>2.1.5.RELEASE</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>
```

build 标签修改为如下
```xml
	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<version>2.1.5.RELEASE</version>
				<executions>
					<execution>
						<goals>
							<goal>repackage</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
		</plugins>
	</build>
```