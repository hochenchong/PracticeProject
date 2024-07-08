package behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 迭代器客户端
 *
 * @author hochenchong
 * @date 2024/07/08
 */
public class IteratorClient {
    public static void main(String[] args) {
        List products = new ArrayList<>();
        products.add("商品1");
        products.add("商品2");
        products.add("商品3");
        products.add("商品4");
        products.add("商品5");

        ProductList list = new ProductList(products);
        AbstractIterator iterator = list.createIterator();

        System.out.println("正向遍历：");
        while (!iterator.isLast()) {
            System.out.print(iterator.getNextItem() + ",");
            iterator.next();
        }

        System.out.println("---------");
        System.out.println("逆向遍历：");
        while (!iterator.isFirst()) {
            System.out.print(iterator.getPreviousItem() + ",");
            iterator.previous();
        }
    }
}
