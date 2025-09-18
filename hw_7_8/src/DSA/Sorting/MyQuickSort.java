package DSA.Sorting;

import java.util.Comparator;
import java.util.Random;

/**
 * MyquickSort is a concrete implementation of the GTUSorter class that uses the
 * QuickSort algorithm to sort arrays. It can also use an alternative sorter for
 * small partitions. The partitioning is done using a random pivot to improve
 * performance.
 */
public class MyQuickSort extends GTUSorter {

    /**
     * The partition limit for using an alternative sorter.
     */
    private final int partitionLimit;

    /**
     * Alternative sorter to use for small partitions.
     */
    private final GTUSorter alternativeSorter;

    /**
     * Random number generator for pivot selection.
     */
    private final Random random = new Random();

    /**
     * Default constructor that initializes the partition limit to -1 and does
     * not use an alternative sorter.
     */
    public MyQuickSort() {
        this.partitionLimit = -1;
        this.alternativeSorter = null;
    }

    /**
     * Constructor that initializes the partition limit and sets an alternative
     * sorter.
     *
     * @param alternativeSorter The sorter to use for small partitions.
     * @param partitionLimit The size limit for using the alternative sorter.
     */
    public MyQuickSort(GTUSorter alternativeSorter, int partitionLimit) {
        this.partitionLimit = partitionLimit;
        this.alternativeSorter = alternativeSorter;
    }

    /**
     * Sorts the givven array using the QuickSort algorithm. If the partition
     * size is small and an alternative sorter is defined, it uses the
     * alternative sorter instead. This implementation uses a random pivot for
     * partitioning to improve performance
     *
     * Complexity: O(n log n) on average, O(n^2) in the worst case.
     *
     * @param arr The array to be sorted.
     * @param start The starting index of the subarray to be sorted.
     * @param end The ending index of the subarray to be sorted.
     * @param comparator The comparator to determine the order of the elements.
     * @param <T> The type of elements in the array.
     */
    @Override
    protected <T> void sort(T[] arr, int start, int end, Comparator<T> comparator) {
        int length = end - start;
        if (length <= 1) {
            return;
        }

        // Use alternative sorter if partition is small and sorter is defined
        if (partitionLimit > 0 && alternativeSorter != null && length <= partitionLimit) {
            alternativeSorter.sort(arr, start, end, comparator);
            return;
        }

        // QuickSort with random pivot
        int pivotIndex = randomPartition(arr, start, end - 1, comparator);
        sort(arr, start, pivotIndex, comparator);
        sort(arr, pivotIndex + 1, end, comparator);
    }

    /**
     * Partitions the array using a random pivot.
     *
     * @param arr The array to be partitioned.
     * @param start The starting index of the subarray to be partitioned.
     * @param end The ending index of the subarray to be partitioned.
     * @param comparator The comparator to determine the order of the elements.
     * @param <T> The type of elements in the array.
     * @return The index of the pivot after partitioning.
     */
    private <T> int randomPartition(T[] arr, int start, int end, Comparator<T> comparator) {
        int pivotIndex = start + random.nextInt(end - start + 1);
        swap(arr, pivotIndex, end);
        return partition(arr, start, end, comparator);
    }

    /**
     * Partitions the array around a pivot.
     *
     * @param arr The array to be partitioned.
     * @param start The starting index of the subarray to be partitioned.
     * @param end The ending index of the subarray to be partitioned.
     * @param comparator The comparator to determine the order of the elements.
     * @param <T> The type of elements in the array.
     * @return The index of the pivot after partitioning.
     */
    private <T> int partition(T[] arr, int start, int end, Comparator<T> comparator) {
        T pivot = arr[end];
        int i = start;
        for (int j = start; j < end; j++) {
            if (comparator.compare(arr[j], pivot) <= 0) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, end);
        return i;
    }

    /**
     * Swaps two elements in the array.
     *
     * @param arr The array in which the elements are to be swapped.
     * @param i The index of the first element.
     * @param j The index of the second element.
     * @param <T> The type of elements in the array.
     */
    private <T> void swap(T[] arr, int i, int j) {
        if (i != j) {
            T temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
