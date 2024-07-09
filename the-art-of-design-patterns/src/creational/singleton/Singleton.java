package creational.singleton;

/**
 * 单例 - 使用静态内部类
 *     调用到 HolderClass，才会去初始化 Singleton 对象
 *     延迟加载，又保证线程安全，不影响系统性能
 *     IoDH 技术，与编程语言本身特性相关，很多面向对象语言不支持 IoDH
 *
 * @author hochenchong
 * @date 2024/7/5
 */
public class Singleton {
    private static class HolderClass {
        public static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return HolderClass.INSTANCE;
    }
}