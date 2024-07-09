package behavioral.iterator;

import java.util.List;

/**
 * @author hochenchong
 * @date 2024/07/08
 */
public class ProductList extends AbstractObjectList {

    public ProductList(List<Object> products) {
        super(products);
    }

    @Override
    public AbstractIterator createIterator() {
        return new ProductIterator(this);
    }
}
