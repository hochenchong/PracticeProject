package hochenchong.ribbon;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;


/**
 * @Description: 根据 com.netflix.loadbalancer.RoundRobinRule 而修改的自定义轮询算法
 * 					轮询的同时，每个服务被调用 5次
 * @author: HochenChong
 * @date: 2018-08-18
 * @version v0.1
 */

public class RoundRobinRuleByCustom extends AbstractLoadBalancerRule {

    private AtomicInteger nextServerCyclicCounter;
    private int total = 0;    //总共被调用的次数，目前要求每台被调用5次
    private int currentIndex = 0;//当前提供服务的机器号


    private static Logger log = LoggerFactory.getLogger(RoundRobinRuleByCustom.class);

    public RoundRobinRuleByCustom() {
        nextServerCyclicCounter = new AtomicInteger(0);
    }

    public RoundRobinRuleByCustom(ILoadBalancer lb) {
        this();
        setLoadBalancer(lb);
    }

    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            log.warn("no load balancer");
            return null;
        }

        Server server = null;
        int count = 0;
        while (server == null && count++ < 10) {
            List<Server> reachableServers = lb.getReachableServers();
            List<Server> allServers = lb.getAllServers(); 
            int upCount = reachableServers.size();
            int serverCount = allServers.size();

            if ((upCount == 0) || (serverCount == 0)) {
                log.warn("No up servers available from load balancer: " + lb);
                return null;
            }

            // 判断该服务器是否被调用 5 次了，如果 5 次了则轮询到下一台服务
            if (total < 5) {
            	total ++;
            	server = allServers.get(currentIndex);
            } else {
            	int nextServerIndex = incrementAndGetModulo(serverCount);
            	server = allServers.get(nextServerIndex);
            	
            	total = 1;
            	currentIndex = nextServerIndex;
            }

            if (server == null) {
                /* Transient. */
                Thread.yield();
                continue;
            }

            if (server.isAlive() && (server.isReadyToServe())) {
                return (server);
            }

            // Next.
            server = null;
        }

        if (count >= 10) {
            log.warn("No available alive servers after 10 tries from load balancer: "
                    + lb);
        }
        return server;
    }

    /**
     * Inspired by the implementation of {@link AtomicInteger#incrementAndGet()}.
     *
     * @param modulo The modulo to bound the value of the counter.
     * @return The next value.
     */
    private int incrementAndGetModulo(int modulo) {
        for (;;) {
            int current = nextServerCyclicCounter.get();
            int next = (current + 1) % modulo;
            if (nextServerCyclicCounter.compareAndSet(current, next))
                return next;
        }
    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}