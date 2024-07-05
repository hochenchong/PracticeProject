package pattern04;

import java.io.*;

/**
 * 工作周报
 *
 * @author hochenchong
 * @date 2024/7/5
 */
public class WeeklyLog implements Cloneable, Serializable {
    private String name;
    private String data;
    private String content;
    private Attachment attachment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public WeeklyLog clone() throws CloneNotSupportedException {
        return (WeeklyLog) super.clone();
    }

    public WeeklyLog deepClone() {
        try {
            // 将对象写入流中
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            // 从流中取出对象
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (WeeklyLog) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
