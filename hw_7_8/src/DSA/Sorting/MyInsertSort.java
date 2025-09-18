package DSA.Sorting;

import java.util.Comparator;

/**
 * GTUInsertSort is a concrete implementation of the GTUSorter class that uses
 * the insertion sort algorithm to sort arrays.
 */
public class MyInsertSort extends GTUSorter {

    /**
     * Insertion sort implementation. This method sorts a subarray of the given
     * array using the insertion sort algorithm.
     *
     * complexity: O(n^2)
     *
     * @param arr the array to be sorted
     * @param start the starting index of the subarray
     * @param end the ending index of the subarray
     * @param comparator the comparator to determine the order of the elements
     */
    @Override
    protected <T> void sort(T[] arr, int start, int end, Comparator<T> comparator) {
        //Insertion sort
        for (int i = start + 1; i < end; i++) {
            T selected_item = arr[i];
            int j = i - 1;
            while (j >= start && comparator.compare(arr[j], selected_item) > 0) {//lower to upper sort
                arr[j + 1] = arr[j];
                --j;
            }
            arr[j + 1] = selected_item;
        }

    }

}
