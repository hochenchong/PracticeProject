package behavioral.mediator;


import behavioral.mediator.component.Component;

/**
 * 抽象中介者
 *
 * @author hochenchong
 * @date 2024/07/08
 */
public abstract class Mediator {
    public abstract void componentChanged(Component component);
}
