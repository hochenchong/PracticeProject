package behavioral.mediator;

import behavioral.mediator.component.Button;
import behavioral.mediator.component.ComboBox;
import behavioral.mediator.component.Component;
import behavioral.mediator.component.List;

/**
 * 中介者
 *
 * @author hochenchong
 * @date 2024/07/08
 */
public class ConcreteMediator extends Mediator {
    private Button button;
    private List list;
    private ComboBox comboBox;

    @Override
    public void componentChanged(Component component) {
        // 单击按钮
        if (component == button) {
            System.out.println("--单击增加按钮--");
            list.update();
            comboBox.update();
        } else if (component == list) {
            System.out.println("--从列表框选中客户--");
            comboBox.select();
        } else if (component == comboBox) {
            System.out.println("--从组合框选中客户--");
            list.select();
        }
    }

    public void setButton(Button addButton) {
        this.button = addButton;
    }

    public void setList(List list) {
        this.list = list;
    }

    public void setComboBox(ComboBox comboBox) {
        this.comboBox = comboBox;
    }
}
