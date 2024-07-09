package behavioral.mediator;

import behavioral.mediator.component.Button;
import behavioral.mediator.component.ComboBox;
import behavioral.mediator.component.List;

/**
 * 中介者模式客户端
 *
 * @author hochenchong
 * @date 2024/07/08
 */
public class MediatorClient {
    public static void main(String[] args) {
        ConcreteMediator mediator = new ConcreteMediator();

        Button button = new Button();
        List list = new List();
        ComboBox comboBox = new ComboBox();

        // 双向绑定
        button.setMediator(mediator);
        list.setMediator(mediator);
        comboBox.setMediator(mediator);
        mediator.setButton(button);
        mediator.setList(list);
        mediator.setComboBox(comboBox);

        // 调用变动
        button.changed();
        System.out.println("-------------");
        list.changed();
        System.out.println("-------------");
        comboBox.changed();
    }
}
