package structural.decorator;

/**
 * @author hochenchong
 * @date 2024/07/06
 */
public class BlackBorderDecorator extends ComponentDecorator {
    public BlackBorderDecorator(Component component) {
        super(component);
    }

    @Override
    public void display() {
        this.setBlackBorder();
        super.display();
    }

    private void setBlackBorder() {
        System.out.println("为构件添加黑色边框！");
    }
}
