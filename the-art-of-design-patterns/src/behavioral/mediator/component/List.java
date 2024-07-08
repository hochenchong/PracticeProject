package behavioral.mediator.component;

/**
 * 列表框类
 *
 * @author hochenchong
 * @date 2024/07/08
 */
public class List extends Component {
    @Override
    public void update() {
        System.out.println("列表框增加一项！");
    }

    public void select() {
        System.out.println("列表框选中一项！");
    }
}