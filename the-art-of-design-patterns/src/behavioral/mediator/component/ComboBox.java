package behavioral.mediator.component;

/**
 * @author hochenchong
 * @date 2024/07/08
 */
public class ComboBox extends Component {
    @Override
    public void update() {
        System.out.println("组合框增加一项！");
    }

    public void select() {
        System.out.println("组合框选中一项！");
    }
}
