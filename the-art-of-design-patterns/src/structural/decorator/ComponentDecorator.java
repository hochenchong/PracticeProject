package structural.decorator;

/**
 * 构件装饰类
 *
 * @author hochenchong
 * @date 2024/07/06
 */
public class ComponentDecorator extends Component {
    private Component component;

    public ComponentDecorator(Component component) {
        this.component = component;
    }

    @Override
    public void display() {
        component.display();
    }
}
