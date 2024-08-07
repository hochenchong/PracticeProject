package creational.abstractFactory;

/**
 * @author hochenchong
 * @date 2024/7/5
 */
public interface SkinFactory {

    AbstractButton createButton();
    AbstractText createText();
}
