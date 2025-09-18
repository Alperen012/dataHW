package DSA.Graphs.MatrixGraph;

import java.util.Collection;
import java.util.Iterator;

/**
 * AdjacencyVect is a collection that represents a vector-based adjacency list
 * for a graph. It allows for efficient storage and retrieval of vertices in a
 * graph using an array of boolean values.
 *
 * This class implements the Collection interface, providing methods to add,
 * remove, and check for the presence of vertices, as well as to iterate over
 * the vertices in the graph.
 */
public class AdjacencyVect implements Collection<Integer> {

    /**
     * The vector that stores the presence of vertices in the graph. Each index
     * represents a vertex, and the value at that index indicates whether the
     * vertex is present (true) or not (false).
     */
    private boolean[] vector;

    /**
     * The count of elements currently present in the adjacency vector. This is
     * used to keep track of the number of vertices added to the graph.
     */
    private int elementCount;

    /**
     * Constructs an AdjacencyVect with a specified size. The size determines
     * the number of vertices that can be represented in the graph.
     *
     * @param size the number of vertices in the graph
     */
    public AdjacencyVect(int size) {
        this.vector = new boolean[size];
        this.elementCount = 0;
    }

    /**
     * Checks if the given index is valid for the current vector. An index is
     * valid if it is within the bounds of the vector's length.
     *
     * @param index the index to check
     * @return true if the index is valid, false otherwise
     */
    private boolean isValidIndex(int index) {
        return index >= 0 && index < vector.length;
    }

    /**
     * Returns the size of the adjacency vector. This is the number of vertices
     * that can be represented in the graph.
     *
     * @return the size of the adjacency vector
     */
    @Override
    public boolean add(Integer e) {
        if (!isValidIndex(e)) {
            throw new IndexOutOfBoundsException("Index out of range: " + e);
        }

        if (!vector[e]) {
            vector[e] = true;
            elementCount++;
            return true;
        }
        return false;  // Element was already present
    }

    /**
     * Adds all elements from the specified collection to the adjacency vector.
     * If an element is already present, it will not be added again.
     *
     * @param c the collection of elements to add
     * @return true if any elements were added, false otherwise
     */
    @Override
    public boolean addAll(Collection<? extends Integer> c) { //! I asked to ai to implement this method(? extends Integer part actually)
        boolean any_modified = false;
        for (Integer e : c) {
            if (add(e)) {
                any_modified = true;
            }
        }
        return any_modified;
    }

    /**
     * Removes all elements from the adjacency vector, effectively clearing the
     * graph. This method sets all values in the vector to false and resets the
     * element count.
     * Complexity: O(n), where n is the size of the vector.
     */
    @Override
    public void clear() {
        for (int i = 0; i < vector.length; i++) {
            vector[i] = false;
        }
        elementCount = 0;
    }

    /**
     * Checks if the adjacency vector contains a specific element. This method
     * checks if the given index is valid and if the corresponding value in the
     * vector is true.
     *
     * Complexity: O(1), as it directly accesses the index in the vector.
     * 
     * @param c the element to check for presence
     * @return true if the element is present, false otherwise
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the adjacency vector is empty. This method returns true if no
     * vertices are present in the graph.
     * 
     * Complexity: O(n), where n is the size of the vector, as it checks each
     * element in the vector.
     *
     * @return true if the adjacency vector is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        for (boolean b : vector) {
            if (b) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns an iterator over the elements in the adjacency vector. The
     * iterator will only return indices of vertices that are present in the
     * graph.
     * 
     * Complexity: O(n), where n is the size of the vector, as it may need to
     * iterate through all elements to find the next one.
     *
     * @return an iterator over the indices of present vertices
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                while (currentIndex < vector.length && !vector[currentIndex]) {
                    currentIndex++;
                }
                return currentIndex < vector.length;
            }

            @Override
            public Integer next() {
                return currentIndex++;
            }
        };
    }

    /**
     * Removes a specific element from the adjacency vector. This method sets
     * the corresponding index in the vector to false and decrements the element
     * count if the element was present.
     * 
     * Complexity: O(1), as it directly accesses the index in the vector.
     *
     * @param o the element to remove
     * @return true if the element was removed, false otherwise
     */
    @Override
    public boolean remove(Object o) {
        if (o instanceof Integer) {
            int index = (Integer) o;
            if (isValidIndex(index) && vector[index]) {
                vector[index] = false;
                elementCount--;
                return true;
            }
        }
        return false;  // Element was not present
    }

    /**
     * Removes all elements in the specified collection from the adjacency
     * vector. This method iterates through the collection and removes each
     * element if it is present.
     * 
     * Complexity: O(m * n), where m is the size of the collection and n is
     * the size of the vector.
     *
     * @param c the collection of elements to remove
     * @return true if any elements were removed, false otherwise
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean any_modified = false;
        for (Object o : c) {
            if (remove(o)) {
                any_modified = true;
            }
        }
        return any_modified;
    }

    /**
     * Retains only the elements in the adjacency vector that are also contained
     * in the specified collection. This method removes all elements that are
     * not present in the collection.
     * 
     * Complexity: O(n * m), where n is the size of the vector and m is the
     * size of the collection.
     *
     * @param c the collection of elements to retain
     * @return true if any elements were removed, false otherwise
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean any_modified = false;
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] && !c.contains(i)) {
                vector[i] = false;
                elementCount--;
                any_modified = true;
            }
        }
        return any_modified;
    }

    /**
     * Returns the number of elements currently present in the adjacency vector.
     * This is the count of vertices that have been added to the graph.
     * 
     * Complexity: O(1), as it simply returns the elementCount variable.
     *
     * @return the number of elements in the adjacency vector
     */
    @Override
    public int size() {
        return elementCount;
    }

    /**
     * Converts the adjacency vector to an array of Integer objects. The array
     * will contain the indices of all vertices that are present in the graph.
     * 
     * Complexity: O(n), where n is the size of the vector, as it may need to
     * iterate through all elements to find the next one.
     *
     * @return an array containing the indices of present vertices
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[elementCount];
        int index = 0;
        for (int i = 0; i < vector.length; i++) {
            if (vector[i]) {
                array[index++] = i;
            }
        }
        return array;
    }

    /**
     * Converts the adjacency vector to an array of the specified type. The
     * array will contain the indices of all vertices that are present in the
     * graph.
     * 
     * Complexity: O(n), where n is the size of the vector, as it may need to
     * iterate through all elements to find the next one.
     *
     * @param a the array to store the elements
     * @return an array containing the indices of present vertices, possibly of
     * a different type
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < elementCount) {
            // If the provided array is not large enough, create a new one
            a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), elementCount);
        }
        int index = 0;
        for (int i = 0; i < vector.length; i++) {
            if (vector[i]) {
                a[index++] = (T) Integer.valueOf(i);
            }
        }
        return a;
    }

    /**
     * Checks if the adjacency vector contains a specific element. This method
     * checks if the given index is valid and if the corresponding value in the
     * vector is true.
     * 
     * Complexity: O(1), as it directly accesses the index in the vector.
     *
     * @param o the element to check for presence
     * @return true if the element is present, false otherwise
     */
    @Override
    public boolean contains(Object o) {
        if (o instanceof Integer) {
            int index = (Integer) o;
            return isValidIndex(index) && vector[index];
        }
        return false;
    }
}
