package pattern02.factoryMethod;

/**
 * 数据库日志记录器
 *
 * @author hochenchong
 * @date 2024/7/5
 */
public class DatabaseLogger implements Logger {
    @Override
    public void writeLog() {
        System.out.println("数据库日志记录");
    }
}
