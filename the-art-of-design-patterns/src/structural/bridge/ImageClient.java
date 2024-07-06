package structural.bridge;

import utils.XMLTagNameConstant;
import utils.XMLUtil;

/**
 * 桥接模式 客户端代码
 *
 * @author hochenchong
 * @date 2024/07/06
 */
public class ImageClient {
    public static void main(String[] args) {
        Image image = (Image) XMLUtil.getBean(XMLTagNameConstant.IMG_BRIDGE);
        SystemImp imageImp = (SystemImp) XMLUtil.getBean(XMLTagNameConstant.SYSTEM_BRIDGE);
        assert image != null;
        image.setImageImp(imageImp);
        image.parseFile("测试图片");
    }
}
