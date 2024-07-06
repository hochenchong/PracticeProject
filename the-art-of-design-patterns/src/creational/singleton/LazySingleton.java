package creational.singleton;

/**
 * 懒汉式单例
 *     获取时，再判断是否为空，为空则初始化，避免浪费
 *     存在问题，每次都要非空判断
 *     volatile 屏蔽 JVM 的代码优化
 *
 * @author hochenchong
 * @date 2024/7/5
 */
public class LazySingleton {
    // 确保多个线程都能观测到变化
    private volatile static LazySingleton instance;

    public static LazySingleton getInstance() {
        // 双重检查锁定
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}
