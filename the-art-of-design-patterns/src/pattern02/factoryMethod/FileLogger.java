package pattern02.factoryMethod;

/**
 * 文件日志记录器
 *
 * @author hochenchong
 * @date 2024/7/5
 */
public class FileLogger implements Logger {
    @Override
    public void writeLog() {
        System.out.println("文件日志记录");
    }
}
