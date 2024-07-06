package pattern13;

/**
 * 抽象文件类
 *     对于普通文件来说，add，remove 和 getChild 都是不需要的,提供默认实现
 *
 * @author hochenchong
 * @date 2024/07/06
 */
public abstract class AbstractFile {
    protected String name;

    public AbstractFile(String name) {
        this.name = name;
    }

    /**
     * 添加文件
     *
     * @param file 文件
     */
    public void add(AbstractFile file) {
        System.out.println("对不起，不支持该方法");
    }

    /**
     * 移除文件
     *
     * @param file 文件
     */
    public void remove(AbstractFile file) {
        System.out.println("对不起，不支持该方法");
    }

    /**
     * 获取文件里的子文件
     *
     * @param i 序号
     * @return 子文件
     */
    public AbstractFile getChild(int i) {
        System.out.println("对不起，不支持该方法");
        return null;
    }

    /**
     * 查杀病毒
     */
    public abstract void killVirus();
}
