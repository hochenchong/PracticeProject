package pattern03;

import utils.XMLTagNameConstant;
import utils.XMLUtil;

/**
 * 抽象工厂模式 - 皮肤工厂客户端
 *
 * @author hochenchong
 * @date 2024/7/5
 */
public class SkinClient {
    public static void main(String[] args) {
        SkinFactory skinFactory = (SkinFactory) XMLUtil.getBean(XMLTagNameConstant.SKIN_FACTORY);
        assert skinFactory != null;
        AbstractButton button = skinFactory.createButton();
        AbstractText text = skinFactory.createText();
        button.display();
        text.display();
    }
}
