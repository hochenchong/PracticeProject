package hochenchong.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Description: EurekaServer 服务器端启动类，接受其它微服务注册进来
 * @author: HochenChong
 * @date: 2018-08-23
 * @version v0.1
 */

@SpringBootApplication
@EnableEurekaServer
public class Config_Git_EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Config_Git_EurekaServerApplication.class, args);
	}

}
