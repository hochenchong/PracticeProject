package structural.bridge.image;

import structural.bridge.Image;
import structural.bridge.Matrix;

/**
 * JPG 格式图片
 *
 * @author hochenchong
 * @date 2024/07/06
 */
public class JPGImage extends Image {
    @Override
    public void parseFile(String fileName) {
        System.out.println(fileName + "，格式为 JPG");
        Matrix m = new Matrix();
        imp.doPaint(m);
    }
}
