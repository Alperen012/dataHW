package Tests;

import DSA.Graphs.MatrixGraph.MatrixGraph;
import DSA.Graphs.MatrixGraph.AdjacencyVect;
import java.util.Collection;

/**
 * A simple test class for MatrixGraph.
 * This class does not use any external testing libraries beyond core Java.
 * Test results are printed to the console.
 */
public class GraphTest {

    /**
     * Main method to run all MatrixGraph tests.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        System.out.println("--- Running MatrixGraph Tests (Simple) ---");
        int testsRun = 0;
        int testsPassed = 0;
        MatrixGraph graph;

        // Test Case 1: Constructor and size
        graph = new MatrixGraph();
        testsRun++;
        System.out.print("Test: MatrixGraph - Default constructor size is 0 ... ");
        if (graph.size() == 0) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }

        graph = new MatrixGraph(5);
        testsRun++;
        System.out.print("Test: MatrixGraph - Constructor with size sets correct size ... ");
        if (graph.size() == 5) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }

        // Test Case 2: Reset
        graph = new MatrixGraph(3);
        graph.setEdge(0, 1);
        graph.reset(2);
        
        testsRun++;
        System.out.print("Test: MatrixGraph - Reset changes graph size ... ");
        if (graph.size() == 2) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }
        
        testsRun++;
        System.out.print("Test: MatrixGraph - Reset clears previous edges (getEdge) ... ");
        if (!graph.getEdge(0, 1)) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }
        
        testsRun++;
        System.out.print("Test: MatrixGraph - Reset clears previous edges (neighbors of 0) ... ");
        if (graph.getNeighbors(0).isEmpty()) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }

        // Test Case 3: setEdge, getEdge, and undirected nature
        graph = new MatrixGraph(4);
        
        testsRun++;
        System.out.print("Test: MatrixGraph - setEdge for new edge returns true ... ");
        if (graph.setEdge(0, 1)) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }
        
        testsRun++;
        System.out.print("Test: MatrixGraph - getEdge for existing edge (v1, v2) ... ");
        if (graph.getEdge(0, 1)) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }
        
        testsRun++;
        System.out.print("Test: MatrixGraph - getEdge for existing edge (v2, v1) - undirected ... ");
        if (graph.getEdge(1, 0)) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }
        
        testsRun++;
        System.out.print("Test: MatrixGraph - setEdge for existing edge returns false ... ");
        if (!graph.setEdge(0, 1)) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }
        
        testsRun++;
        System.out.print("Test: MatrixGraph - setEdge for self-loop returns false ... ");
        if (!graph.setEdge(2, 2)) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }
        
        testsRun++;
        System.out.print("Test: MatrixGraph - getEdge for non-existent self-loop ... ");
        if (!graph.getEdge(2, 2)) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }

        testsRun++;
        System.out.print("Test: MatrixGraph - setEdge with v1 out of bounds (lower) ... ");
        if (!graph.setEdge(-1, 2)) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }
        
        testsRun++;
        System.out.print("Test: MatrixGraph - setEdge with v2 out of bounds (upper) ... ");
        if (!graph.setEdge(1, 4)) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }
        
        testsRun++;
        System.out.print("Test: MatrixGraph - getEdge with v1 out of bounds (lower) ... ");
        if (!graph.getEdge(-1, 2)) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }
        
        testsRun++;
        System.out.print("Test: MatrixGraph - getEdge with v2 out of bounds (upper) ... ");
        if (!graph.getEdge(1, 4)) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }

        // Test Case 4: getNeighbors
        graph = new MatrixGraph(4);
        graph.setEdge(0, 1);
        graph.setEdge(0, 2);
        graph.setEdge(2, 3);

        Collection<Integer> neighbors0 = graph.getNeighbors(0);
        testsRun++;
        System.out.print("Test: MatrixGraph - getNeighbors for vertex 0 ... ");
        if (checkCollectionContents(neighbors0, new Integer[]{1, 2})) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }

        Collection<Integer> neighbors1 = graph.getNeighbors(1);
        testsRun++;
        System.out.print("Test: MatrixGraph - getNeighbors for vertex 1 ... ");
        if (checkCollectionContents(neighbors1, new Integer[]{0})) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }

        Collection<Integer> neighbors3 = graph.getNeighbors(3);
        testsRun++;
        System.out.print("Test: MatrixGraph - getNeighbors for vertex 3 ... ");
        if (checkCollectionContents(neighbors3, new Integer[]{2})) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }

        System.out.println("\n--- MatrixGraph Tests Summary ---");
        System.out.println("Total Tests Run: " + testsRun);
        System.out.println("Tests Passed:    " + testsPassed);
        System.out.println("Tests Failed:    " + (testsRun - testsPassed));
        System.out.println("--------------------------------");
    }

    /**
     * Helper method to check if a collection contains all expected elements and no others.
     * Assumes elements in the collection are unique if expected unique.
     * @param actual Collection from the graph
     * @param expected Array of expected integers
     * @return true if they match, false otherwise
     */
    private static boolean checkCollectionContents(Collection<Integer> actual, Integer[] expected) {
        if (actual.size() != expected.length) {
            return false;
        }
        boolean[] foundInActual = new boolean[expected.length];
        for (Integer valActual : actual) {
            boolean currentValFoundInExpected = false;
            for (int i = 0; i < expected.length; i++) {
                if (!foundInActual[i] && valActual.equals(expected[i])) {
                    foundInActual[i] = true;
                    currentValFoundInExpected = true;
                    break;
                }
            }
            if (!currentValFoundInExpected) { // Element in actual not in expected
                return false;
            }
        }
        for (boolean b : foundInActual) { // Check if all expected were found
            if (!b) return false;
        }
        return true;
    }
}
