package structural.adapter;

/**
 * 操作适配器：适配器
 *
 * @author hochenchong
 * @date 2024/7/5
 */
public class OperationAdapter implements ScoreOperation {
    private BubbleSort bubbleSort;

    private BinarySearch binarySearch;

    public OperationAdapter() {
        this.bubbleSort = new BubbleSort();
        this.binarySearch = new BinarySearch();
    }

    @Override
    public int[] sort(int[] array) {
        bubbleSort.bubbleSortOrderByAsc(array);
        return array;
    }

    @Override
    public int search(int[] array, int key) {
        return binarySearch.binarySearch(array, key);
    }
}
