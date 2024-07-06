package structural.decorator;

/**
 * 滚动条装饰类
 *
 * @author hochenchong
 * @date 2024/07/06
 */
public class ScrollBarDecorator extends ComponentDecorator {
    public ScrollBarDecorator(Component component) {
        super(component);
    }

    @Override
    public void display() {
        this.setScrollBar();
        super.display();
    }

    private void setScrollBar() {
        System.out.println("为构件添加滚动条！");
    }
}
