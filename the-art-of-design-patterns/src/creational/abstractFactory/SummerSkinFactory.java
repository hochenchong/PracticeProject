package creational.abstractFactory;

/**
 * @author hochenchong
 * @date 2024/7/5
 */
public class SummerSkinFactory implements SkinFactory {
    @Override
    public AbstractButton createButton() {
        return new SummerButton();
    }

    @Override
    public AbstractText createText() {
        return new SpringText();
    }
}
