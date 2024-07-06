package pattern13;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件夹文件
 *
 * @author hochenchong
 * @date 2024/07/06
 */
public class FolderFile extends AbstractFile {
    private List<AbstractFile> list = new ArrayList<>();

    public FolderFile(String name) {
        super(name);
    }

    @Override
    public void add(AbstractFile file) {
        list.add(file);
    }

    @Override
    public void remove(AbstractFile file) {
        list.remove(file);
    }

    @Override
    public AbstractFile getChild(int i) {
        return list.get(i);
    }

    @Override
    public void killVirus() {
        System.out.println("对文件夹 '" + name + "' 进行杀毒");

        for (AbstractFile file : list) {
            file.killVirus();
        }
    }
}
