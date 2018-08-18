package hochenchong.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import hochenchong.ribbon.MySelfRule;

@SpringBootApplication
@EnableEurekaClient           // 标记为 Eureka 的客户端
@RibbonClient(name="MICROSERVICECLOUD-DEPT",configuration=MySelfRule.class)  // 启动该微服务的时候去加载自定义 Ribbon 配置类，从而使配置生效
public class DeptConsumer80_App {

	public static void main(String[] args) {
		SpringApplication.run(DeptConsumer80_App.class, args);
	}
}
