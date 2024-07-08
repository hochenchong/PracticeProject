package behavioral.memento;

/**
 * 备忘录模式客户端
 *
 * @author hochenchong
 * @date 2024/07/08
 */
public class MementoClient {
    public static void main(String[] args) {
        MementoCaretaker mementoCaretaker = new MementoCaretaker();

        Chessman chessman = new Chessman("车", 1, 1);
        display(chessman);
        mementoCaretaker.setMemento(chessman.save());
        chessman.setY(4);
        display(chessman);
        System.out.println("---悔棋--");
        chessman.restore(mementoCaretaker.getMemento());
        display(chessman);
    }

    private static void display(Chessman chessman) {
        System.out.println("棋子 '" + chessman.getLabel() +  "' 当前位置为：第 "
                + chessman.getX() + " 行，第 " + chessman.getY() + " 列。");
    }
}
