package structural.bridge;

/**
 * 抽象图像类
 *
 * @author hochenchong
 * @date 2024/07/06
 */
public abstract class Image {
    protected SystemImp imp;

    public void setImageImp(SystemImp imp) {
        this.imp = imp;
    }
    // 解析图像文件
    public abstract void parseFile(String fileName);
}
