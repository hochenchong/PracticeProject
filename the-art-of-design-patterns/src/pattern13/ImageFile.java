package pattern13;

/**
 * @author hochenchong
 * @date 2024/07/06
 */
public class ImageFile extends AbstractFile {
    public ImageFile(String name) {
        super(name);
    }

    @Override
    public void killVirus() {
        System.out.println("对图像文件 '" + name + "' 进行杀毒");
    }
}
