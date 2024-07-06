package creational.abstractFactory;

abstract class AbstractText {
    public abstract void display();
}

// Spring 文本类
class SpringText extends AbstractText {
    @Override
    public void display() {
        System.out.println("显示浅绿色文本");
    }
}

// Summer 文本类
class SummerText extends AbstractText {
    @Override
    public void display() {
        System.out.println("显示浅蓝色文本");
    }
}