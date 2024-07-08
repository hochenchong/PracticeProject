package behavioral.mediator.component;

import behavioral.mediator.Mediator;

/**
 * 抽象组件类
 *
 * @author hochenchong
 * @date 2024/07/08
 */
public abstract class Component {
    protected Mediator mediator;

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public void changed() {
        mediator.componentChanged(this);
    }

    public abstract void update();
}