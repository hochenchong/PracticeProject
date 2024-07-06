package creational.factory.factoryMethod;

/**
 * 文件日志记录器工厂
 * 
 * @author hochenchong
 * @date 2024/7/5
 */
public class FileLoggerFactory implements LoggerFactory {
    @Override
    public Logger createLogger() {
        // 简易版
        return new FileLogger();
    }
}
