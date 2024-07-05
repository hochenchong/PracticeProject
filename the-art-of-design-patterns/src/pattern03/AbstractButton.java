package pattern03;

abstract class AbstractButton {
    public abstract void display();
}

// Spring 按钮类
class SpringButton extends AbstractButton {
    @Override
    public void display() {
        System.out.println("显示浅绿色按钮");
    }
}

// Summer 按钮类
class SummerButton extends AbstractButton {
    @Override
    public void display() {
        System.out.println("显示浅蓝色按钮");
    }
}