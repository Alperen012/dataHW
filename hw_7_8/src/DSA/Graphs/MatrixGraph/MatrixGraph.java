package DSA.Graphs.MatrixGraph;

import DSA.Graphs.GTUGraph;
import java.util.Collection;

/**
 * MatrixGraph class implements the GTUGraph interface. It uses an adjacency
 * matrix structure to store edge information.
 */
public class MatrixGraph implements GTUGraph {

    /**
     * The adjacency matrix represented as an array of AdjacencyVect objects.
     * Each AdjacencyVect contains the neighbors of a vertex.
     */
    private AdjacencyVect[] matrix;

    /**
     * The number of vertices in the graph.
     */
    private int numVertices;

    /**
     * Default constructor that initializes the graph with zero vertices. This
     * is useful for creating an empty graph that can be resized later.
     */
    public MatrixGraph() {
        this(0);
    }

    /**
     * Constructs a MatrixGraph with the specified number of vertices.
     *
     * @param size The number of vertices in the graph
     * @throws IllegalArgumentException if size is negative
     *
     * Time Complexity: O(n) where n is the size
     */
    public MatrixGraph(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Size cannot be negative");
        }
        reset(size);
    }

    /**
     * sets an edge between two vertices in the graph. * This method adds an
     * edge between vertex v1 and vertex v2 in the adjacency matrix. If the edge
     * already exists, it will not be added again.
     *
     * Complexity: O(1) for adding an edge, as it directly accesses the
     * adjacency matrix.
     *
     * @param v1 The first vertex
     * @param v2 The second vertex
     * @return true if the edge was successfully added, false if the vertices
     * are out of bounds or if the edge already exists.
     *
     */
    @Override
    public Boolean setEdge(int v1, int v2) {
        if (v1 < 0 || v1 >= numVertices || v2 < 0 || v2 >= numVertices || v1 == v2) {
            return false;
        }

        // For undirected graph, set both directions
        boolean changed1 = matrix[v1].add(v2);
        boolean changed2 = matrix[v2].add(v1);

        // Return true if either edge was changed
        return changed1 || changed2;
    }

    /**
     * gets an edge between two vertices in the graph. This method checks
     * whether an edge exists between vertex v1 and vertex v2 in the adjacency
     * matrix. If the edge exists, it returns true; otherwise, it returns false.
     *
     * Complexity: O(1) for checking the existence of an edge.
     *
     * @param v1 The first vertex
     * @param v2 The second vertex
     * @return true if the edge exists, false if the vertices are out of bounds
     * or if the edge does not exist.
     */
    @Override
    public Boolean getEdge(int v1, int v2) {
        if (v1 < 0 || v1 >= numVertices || v2 < 0 || v2 >= numVertices) {
            return false;
        }

        return matrix[v1].contains(v2);
    }

    /**
     * Retrieves the neighbors of a given vertex. This method returns a
     * collection of integers representing the neighbors of the specified vertex
     * in the graph. If the vertex index is out of bounds, it throws an
     * IndexOutOfBoundsException.
     *
     * Complexity: O(k) where k is the number of neighbors of vertex v.
     *
     * @param v The vertex index for which to retrieve neighbors
     * @return A collection of integers representing the neighbors of vertex v
     * @throws IndexOutOfBoundsException if the vertex index is out of bounds
     */
    @Override
    public Collection<Integer> getNeighbors(int v) {
        if (v < 0 || v >= numVertices) {
            throw new IndexOutOfBoundsException("Vertex index out of bounds: " + v);
        }

        return matrix[v];
    }

    /**
     * get size of the graph. This method returns the number of vertices in the
     * graph. It is useful for determining the size of the graph when iterating
     * over vertices or edges.
     *
     * Complexity: O(1) as it simply returns the size of the graph.
     *
     * @return The number of vertices in the graph
     */
    @Override
    public int size() {
        return numVertices;
    }

    /**
     * Resets the graph to a new size. This method initializes the graph with a
     * new number of vertices. It clears the existing adjacency matrix and
     * creates a new one with the specified size. If the size is negative, it
     * throws an IllegalArgumentException.
     *
     * Complexity: O(n) where n is the new size, as it creates a new adjacency
     * matrix.
     *
     * @param size The new number of vertices for the graph
     * @throws IllegalArgumentException if size is negative
     */
    @Override
    public void reset(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Size cannot be negative");
        }

        this.numVertices = size;
        this.matrix = new AdjacencyVect[size];

        for (int i = 0; i < size; i++) {
            matrix[i] = new AdjacencyVect(size);
        }
    }
}
