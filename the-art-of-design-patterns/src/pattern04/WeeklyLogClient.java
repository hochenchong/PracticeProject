package pattern04;

/**
 * @author hochenchong
 * @date 2024/7/5
 */
public class WeeklyLogClient {

    public static void main(String[] args) throws CloneNotSupportedException {
        WeeklyLog log = new WeeklyLog();
        log.setName("张三");
        log.setData("第 1 周");
        log.setContent("666");
        Attachment attachment = new Attachment();
        attachment.setName("附件1");
        log.setAttachment(attachment);

        System.out.println("** 周报 **");
        System.out.println("周次：" + log.getData());
        System.out.println("姓名：" + log.getName());
        System.out.println("内容：" + log.getContent());
        System.out.println("-------------");

        // 使用克隆的方式
        WeeklyLog newLog = log.clone();
        newLog.setData("第 2 周");
        System.out.println("** 周报 **");
        System.out.println("周次：" + newLog.getData());
        System.out.println("姓名：" + newLog.getName());
        System.out.println("内容：" + newLog.getContent());
        System.out.println("-------------");

        System.out.println(log == newLog); // false
        System.out.println(log.getName() == newLog.getName()); // true
        System.out.println(log.getData() == newLog.getData()); // false
        System.out.println(log.getContent() == newLog.getContent()); // true
        System.out.println(log.getAttachment() == newLog.getAttachment()); // true，浅克隆，复制了引用地址
        System.out.println("-------------");

        // 使用深克隆，序列化方式，以下都是 false
        WeeklyLog deepLog = log.deepClone();
        System.out.println(log == deepLog);
        System.out.println(log.getName() == deepLog.getName());
        System.out.println(log.getData() == deepLog.getData());
        System.out.println(log.getContent() == deepLog.getContent());
        System.out.println(log.getAttachment() == deepLog.getAttachment());
    }
}
