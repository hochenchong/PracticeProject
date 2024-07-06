package pattern12.image;

import pattern12.Image;
import pattern12.Matrix;

/**
 * PNG 格式图片
 *
 * @author hochenchong
 * @date 2024/07/06
 */
public class PNGImage extends Image {
    @Override
    public void parseFile(String fileName) {
        System.out.println(fileName + "，格式为 PNG");
        Matrix m = new Matrix();
        imp.doPaint(m);
    }
}
