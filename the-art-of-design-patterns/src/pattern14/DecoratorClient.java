package pattern14;

/**
 * 装饰类客户端
 *
 * @author hochenchong
 * @date 2024/07/06
 */
public class DecoratorClient {
    public static void main(String[] args) {
        Component component = new Window();
        component = new ScrollBarDecorator(component);
        component = new BlackBorderDecorator(component);
        component.display();
    }
}
