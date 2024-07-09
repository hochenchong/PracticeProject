package behavioral.memento;

/**
 * 象棋棋子备忘录管理类：负责人
 *
 * @author hochenchong
 * @date 2024/07/08
 */
public class MementoCaretaker {
    private ChessmanMemento memento;

    public ChessmanMemento getMemento() {
        return memento;
    }

    public void setMemento(ChessmanMemento memento) {
        this.memento = memento;
    }
}