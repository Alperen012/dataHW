package DSA.Sorting;

import java.util.Comparator;

/**
 * MySelectSort is a class that implements the selection sort algorithm.
 * It extends the GTUSorter class, which provides a framework for sorting algorithms.
 * This implementation sorts an array in ascending order using a comparator.
 */
public class MySelectSort extends GTUSorter {

    /**
     * Selection sort implementation. This method sorts a subarray of the given
     * array using the selection sort algorithm.
     *
     * complexity: O(n^2)
     *
     * @param arr the array to be sorted
     * @param start the starting index of the subarray
     * @param end the ending index of the subarray (exclusive)
     * @param comparator the comparator to determine the order of the elements
     */
    @Override
    protected <T> void sort(T[] arr, int start, int end, Comparator<T> comparator) {
        for (int i = start; i < end - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < end; j++) {
                if (comparator.compare(arr[j], arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    /**
     * Swaps two elements in the array.
     *
     * @param arr the array in which the elements are to be swapped
     * @param i the index of the first element
     * @param j the index of the second element
     */
    private <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
