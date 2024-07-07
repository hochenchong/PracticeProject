package behavioral.command;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hochenchong
 * @date 2024/07/07
 */
public class FBSettingWindow {
    // 窗口标题
    private String title;
    // 定义一个 List 存储所有功能键
    private List<FunctionButton> functionButtons = new ArrayList<>();

    public FBSettingWindow(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addFunctionButton(FunctionButton functionButton) {
        functionButtons.add(functionButton);
    }

    public void removeFunctionButton(FunctionButton functionButton) {
        functionButtons.remove(functionButton);
    }

    public void display() {
        System.out.println("显示窗口：" + title);
        System.out.println("显示功能键：");
        for (FunctionButton functionButton : functionButtons) {
            System.out.println(functionButton.getName());
        }
        System.out.println("------------");
    }
}
