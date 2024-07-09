package creational.singleton;

/**
 * 饿汉式单例模式
 *     类加载时，静态变量 instance 就会初始化，创建唯一的实例
 *     可能导致加载时间过长，或者该对象从不使用却创建了
 *
 * @author hochenchong
 * @date 2024/7/5
 */
public class EagerSingleton {
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
}
