package behavioral.command;

/**
 * 功能键类
 *
 * @author hochenchong
 * @date 2024/07/07
 */
public class FunctionButton {
    private String name;

    private Command command;

    public FunctionButton(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void onClick() {
        System.out.print("点击功能键：");
        command.execute();
    }
}
