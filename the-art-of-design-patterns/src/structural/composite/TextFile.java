package structural.composite;

/**
 * @author hochenchong
 * @date 2024/07/06
 */
public class TextFile extends AbstractFile {
    public TextFile(String name) {
        super(name);
    }

    @Override
    public void killVirus() {
        System.out.println("对文本文件 '" + name + "' 进行杀毒");
    }
}
