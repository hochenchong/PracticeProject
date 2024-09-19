package jvm.chapter02;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 借助 CGLib 使得方法区出现内存溢出异常
 * VM Args：-XX:PermSize=10M -XX:MaxPermSize=10M
 * JDK 7 运行
 *
 * @author hochenchong
 * @date 2024/09/19
 */
public class JavaMethodAreaOOM {
    static class OOMObject {}

    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o, args);
                }
            });
            enhancer.create();
        }
    }
}
