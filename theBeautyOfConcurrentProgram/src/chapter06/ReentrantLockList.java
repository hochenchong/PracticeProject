package chapter06;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 6.3 独占锁 ReentrantLock 的原理
 *
 * @author hochenchong
 * @date 2024/6/3
 */
public class ReentrantLockList {
    // 线程不安全的 list
    private final List<String> list = new ArrayList<>();
    // 独占锁
    private final ReentrantLock lock = new ReentrantLock();
    // 添加元素
    public void add(String e) {
        lock.lock();
        try {
            list.add(e);
        } finally {
            lock.unlock();
        }
    }
    // 删除元素
    public void remove(String e) {
        lock.lock();
        try {
            list.remove(e);
        } finally {
            lock.unlock();
        }
    }
    // 获取数据
    public String get(int index) {
        lock.lock();
        try {
            return list.get(index);
        } finally {
            lock.unlock();
        }
    }
}
