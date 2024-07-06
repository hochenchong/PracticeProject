package creational.factory.factoryMethod;

/**
 * 抽象工厂类，直接提供统一的使用方法
 *
 * @author hochenchong
 * @date 2024/7/5
 */
abstract class AbstractLoggerFactory {
    public void writeLog() {
        Logger logger = this.createLogger();
        logger.writeLog();
    }

    public abstract Logger createLogger();
}
