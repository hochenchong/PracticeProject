package hochenchong.ribbon;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

@Configuration
public class MySelfRule {
	@Bean
	public IRule getRule() {
		
		// return new RoundRobinRule(); // Ribbon 默认的负载均衡算法为轮询
		// return new RandomRule(); // 随机算法
		return new RoundRobinRuleByCustom(); // 使用自定义的轮询算法，每台服务器被调用 5次
	}
}
