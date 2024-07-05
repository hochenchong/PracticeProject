package pattern03;

/**
 * @author hochenchong
 * @date 2024/7/5
 */
public class SpringSkinFactory implements SkinFactory {
    @Override
    public AbstractButton createButton() {
        return new SpringButton();
    }

    @Override
    public AbstractText createText() {
        return new SpringText();
    }
}
