package Tests;

import DSA.Graphs.MatrixGraph.AdjacencyVect;
import java.util.Iterator;
/**
 * A simple test class for AdjacencyVect.
 * This class does not use any external testing libraries beyond core Java.
 * Test results are printed to the console.
 */
public class AdjacencyVectTest {

    /**
     * Main method to run all AdjacencyVect tests.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        System.out.println("--- Running AdjacencyVect Tests ---");
        int testsRun = 0;
        int testsPassed = 0;

        // Test Case 1: Constructor and basic properties
        AdjacencyVect vect1 = new AdjacencyVect(10);
        
        testsRun++;
        System.out.print("Test: AdjacencyVect - Constructor initial size is 0 ... ");
        if (vect1.size() == 0) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }
        
        testsRun++;
        System.out.print("Test: AdjacencyVect - Constructor isEmpty is true ... ");
        if (vect1.isEmpty()) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }

        // Test Case 2: Add, Contains, Size
        AdjacencyVect vect2 = new AdjacencyVect(5);
        
        testsRun++;
        System.out.print("Test: AdjacencyVect - Add new element returns true ... ");
        if (vect2.add(Integer.valueOf(1))) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }
        
        testsRun++;
        System.out.print("Test: AdjacencyVect - Contains added element ... ");
        if (vect2.contains(Integer.valueOf(1))) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }
        
        testsRun++;
        System.out.print("Test: AdjacencyVect - Size after one add ... ");
        if (vect2.size() == 1) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }
        
        testsRun++;
        System.out.print("Test: AdjacencyVect - Add existing element returns false ... ");
        if (!vect2.add(Integer.valueOf(1))) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }
        
        testsRun++;
        System.out.print("Test: AdjacencyVect - Size after adding existing ... ");
        if (vect2.size() == 1) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }
        
        testsRun++;
        System.out.print("Test: AdjacencyVect - Does not contain non-added element ... ");
        if (!vect2.contains(Integer.valueOf(3))) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }
        
        testsRun++;
        System.out.print("Test: AdjacencyVect - Contains non-Integer object ... ");
        if (!vect2.contains("test_string")) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }

        // Test Case 3: Remove
        vect2.add(Integer.valueOf(3)); // Now contains 1, 3
        
        testsRun++;
        System.out.print("Test: AdjacencyVect - Remove existing element returns true ... ");
        if (vect2.remove(Integer.valueOf(1))) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }
        
        testsRun++;
        System.out.print("Test: AdjacencyVect - Not contains removed element ... ");
        if (!vect2.contains(Integer.valueOf(1))) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }
        
        testsRun++;
        System.out.print("Test: AdjacencyVect - Size after remove ... ");
        if (vect2.size() == 1) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }
        
        testsRun++;
        System.out.print("Test: AdjacencyVect - Remove non-existing element returns false ... ");
        if (!vect2.remove(Integer.valueOf(0))) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }
        
        testsRun++;
        System.out.print("Test: AdjacencyVect - Remove non-Integer object ... ");
        if (!vect2.remove("test_string")) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }

        // Test Case 4: Iterator
        AdjacencyVect vectIter = new AdjacencyVect(7);
        vectIter.add(Integer.valueOf(0));
        vectIter.add(Integer.valueOf(3));
        vectIter.add(Integer.valueOf(6));
        Iterator<Integer> it = vectIter.iterator();

        int count = 0;
        boolean found0 = false,
                found3 = false,
                found6 = false;

        while (it.hasNext()) {
            Integer val = it.next();

            if (val.equals(0))
                found0 = true;

            if (val.equals(3))
                found3 = true;

            if (val.equals(6))
                found6 = true;

            count++;
        }

        testsRun++;
        System.out.print("Test: AdjacencyVect - Iterator yields correct number of elements ... ");
        if (count == 3) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }
        
        testsRun++;
        System.out.print("Test: AdjacencyVect - Iterator yields element 0 ... ");
        if (found0) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }
        
        testsRun++;
        System.out.print("Test: AdjacencyVect - Iterator yields element 3 ... ");
        if (found3) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }
        
        testsRun++;
        System.out.print("Test: AdjacencyVect - Iterator yields element 6 ... ");
        if (found6) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }

        // Re-get iterator for next() test
        final Iterator<Integer> finalIt = vectIter.iterator();

        while(finalIt.hasNext())
            finalIt.next(); // Exhaust it

        AdjacencyVect emptyVectIter = new AdjacencyVect(3);
        Iterator<Integer> emptyIt = emptyVectIter.iterator();
        
        testsRun++;
        System.out.print("Test: AdjacencyVect - Iterator on empty vector hasNext() is false ... ");
        if (!emptyIt.hasNext()) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }

        // Test Case 5: Clear
        vectIter.clear(); // vectIter had 0, 3, 6
        
        testsRun++;
        System.out.print("Test: AdjacencyVect - Clear makes vector empty ... ");
        if (vectIter.isEmpty()) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }
        
        testsRun++;
        System.out.print("Test: AdjacencyVect - Clear sets size to 0 ... ");
        if (vectIter.size() == 0) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }
        
        testsRun++;
        System.out.print("Test: AdjacencyVect - Clear removes elements (e.g., 0) ... ");
        if (!vectIter.contains(Integer.valueOf(0))) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }

        // Test Case 6: toArray()
        AdjacencyVect vectToArray = new AdjacencyVect(5);
        vectToArray.add(Integer.valueOf(1));
        vectToArray.add(Integer.valueOf(4));
        Object[] objArray = vectToArray.toArray();

        // Expected order for AdjacencyVect is by increasing vertex index
        boolean toArrayCorrect = (objArray.length == 2 &&
                Integer.valueOf(1).equals(objArray[0]) &&
                Integer.valueOf(4).equals(objArray[1]));

        testsRun++;
        System.out.print("Test: AdjacencyVect - toArray() ... ");
        if (toArrayCorrect) {
            System.out.println("PASSED");
            testsPassed++;
        } else {
            System.out.println("FAILED");
        }

        System.out.println("\n--- AdjacencyVect Tests Summary ---");
        System.out.println("Total Tests Run: " + testsRun);
        System.out.println("Tests Passed:    " + testsPassed);
        System.out.println("Tests Failed:    " + (testsRun - testsPassed));
        System.out.println("----------------------------------");
    }
}
