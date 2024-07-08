package behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hochenchong
 * @date 2024/07/08
 */
public abstract class AbstractObjectList {
    protected List<Object> objects = new ArrayList<>();

    public AbstractObjectList(List<Object> objects) {
        this.objects = objects;
    }

    public void addObject(Object object) {
        objects.add(object);
    }

    public void removeObject(Object object) {
        objects.remove(object);
    }

    public List getObjects() {
        return this.objects;
    }

    // 创建迭代器对象的抽象工厂方法
    public abstract AbstractIterator createIterator();
}
