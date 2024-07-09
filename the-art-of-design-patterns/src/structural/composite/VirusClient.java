package structural.composite;

/**
 * @author hochenchong
 * @date 2024/07/06
 */
public class VirusClient {
    public static void main(String[] args) {
        AbstractFile file1, file2, file3, file4, folder1, folder2, folder3;

        folder1 = new FolderFile("总的文件夹");
        folder2 = new FolderFile("图像文件夹");
        folder3 = new FolderFile("文件文件夹");

        file1 = new ImageFile("图片1.jpg");
        file2 = new ImageFile("图片2.gif");
        file3 = new TextFile("文本1.txt");
        file4 = new TextFile("文本2.doc");

        folder2.add(file1);
        folder2.add(file2);
        folder3.add(file3);
        folder3.add(file4);
        folder1.add(folder2);
        folder1.add(folder3);

        // 从根目录开始处理
        folder1.killVirus();
    }
}
