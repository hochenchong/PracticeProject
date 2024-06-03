package chapter06;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 6.4 读写锁 ReentrantReadWriteLock 的原理
 *
 * @author hochenchong
 * @date 2024/6/3
 */
public class ReentrantReadWriteLockList {
    // 线程不安全的 list
    private final List<String> list = new ArrayList<>();
    // 独占锁
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();
    // 添加元素
    public void add(String e) {
        writeLock.lock();
        try {
            list.add(e);
        } finally {
            writeLock.unlock();
        }
    }
    // 删除元素
    public void remove(String e) {
        writeLock.lock();
        try {
            list.remove(e);
        } finally {
            writeLock.unlock();
        }
    }
    // 获取数据
    public String get(int index) {
        readLock.lock();
        try {
            return list.get(index);
        } finally {
            readLock.unlock();
        }
    }
}
