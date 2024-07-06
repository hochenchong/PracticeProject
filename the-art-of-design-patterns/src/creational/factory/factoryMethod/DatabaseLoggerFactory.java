package creational.factory.factoryMethod;

/**
 * 数据库日志记录器工厂
 *
 * @author hochenchong
 * @date 2024/7/5
 */
public class DatabaseLoggerFactory implements LoggerFactory {
    @Override
    public Logger createLogger() {
        // 简易版
        return new DatabaseLogger();
    }
}
