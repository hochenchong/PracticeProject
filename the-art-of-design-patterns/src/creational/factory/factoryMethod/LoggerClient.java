package creational.factory.factoryMethod;

import utils.XMLTagNameConstant;
import utils.XMLUtil;

/**
 * 客户端使用
 *
 * @author hochenchong
 * @date 2024/7/5
 */
public class LoggerClient {
    public static void main(String[] args) {
        LoggerFactory factory = (LoggerFactory) XMLUtil.getBean(XMLTagNameConstant.LOGGER_FACTORY);
        assert factory != null;
        Logger logger = factory.createLogger();
        logger.writeLog();
    }
}
