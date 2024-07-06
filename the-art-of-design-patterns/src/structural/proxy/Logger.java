package structural.proxy;

/**
 * 日志记录类
 *
 * @author hochenchong
 * @date 2024/07/06
 */
public class Logger {
    // 模拟记录日志
    public void log(String userId) {
        System.out.println("更新数据库，用户'" + userId + "'查询次数加 1！");
    }
}
