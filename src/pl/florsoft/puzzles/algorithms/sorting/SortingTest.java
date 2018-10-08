package pl.florsoft.puzzles.algorithms.sorting;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;

public class SortingTest {

    public static void main(String[] args) {
        int[] elementsTestCases = new int[]{
                10000, 25000, 50000, 75000, 100000, 150000, 200000, 250000, 300000
//                1000000, 5000000, 10000000, 15000000, 20000000, 25000000, 30000000, 35000000, 40000000, 45000000, 50000000
        };
        for (int elements : elementsTestCases) {
//            System.out.println("elements = " + elements);
//            int[] array = generateIntArray(elements);
            long[] array = generateLongArray(elements);
            sort("BubbleSort", array, BubbleSort::sort);
            sort("HeapSort", array, HeapSort::sort);
            sort("InsertionSort", array, InsertionSort::sort);
            sort("MergeSort", array, MergeSort::sort);
            sort("QuickSort", array, QuickSort::sort);
            sort("Java DualPivotQuickSort", array, Arrays::sort);
            sort("ParallelMergeSort", array, ParallelMergeSort::sort);
            sort("SelectionSort", array, SelectionSort::sort);
//            sort("ForkedMergeSort", array, ForkedMergeSort::sort);
            System.out.println();
        }
    }

    private static void sort(String algorithmName, int[] array, Consumer<int[]> consumer) {
        int[] arrayToSort = createArrayCopy(array);
        long start = System.nanoTime();
        consumer.accept(arrayToSort);
        long end = System.nanoTime();
        long duration = end - start;
        System.out.println(algorithmName + " - " + (duration / 1000000) + " ms");
//        System.out.print((duration / 1000000) + "\t");
        checkCorrectness(algorithmName, arrayToSort);
    }

    private static int[] createArrayCopy(int[] array) {
        int[] newArray = new int[array.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    private static void checkCorrectness(String algorithmName, int[] arrayToSort) {
        int currentVal = Integer.MIN_VALUE;
        for (int val : arrayToSort) {
            if (val < currentVal) {
                System.out.println(algorithmName + " --> !!!!!!!!! SORTING INCORRECT !!!!!!!!");
                System.out.println("Incorrect value = " + val + ". All Values: " + Arrays.toString(arrayToSort));
                return;
            }
            currentVal = val;
        }
        System.out.println(algorithmName + "--> SORTING CORRECT");
    }

    private static void sort(String algorithmName, long[] array, Consumer<long[]> consumer) {
        // JVM warm up
        long[] arrayToSort = createArrayCopy(array);
        consumer.accept(arrayToSort);
        arrayToSort = createArrayCopy(array);
        consumer.accept(arrayToSort);
        //
        arrayToSort = createArrayCopy(array);
        long start = System.nanoTime();
        consumer.accept(arrayToSort);
        long end = System.nanoTime();
        long duration = end - start;
//        System.out.println(algorithmName + " - " + (duration / 1000000) + " ms");
        System.out.print((duration / 1000000) + "\t");
        checkCorrectness(algorithmName, arrayToSort);
    }

    private static void checkCorrectness(String algorithmName, long[] arrayToSort) {
        long currentVal = Long.MIN_VALUE;
        for (long val : arrayToSort) {
            if (val < currentVal) {
                System.out.println(algorithmName + " --> !!!!!!!!! SORTING INCORRECT !!!!!!!!");
                System.out.println("Incorrect value = " + val + ". All Values: " + Arrays.toString(arrayToSort));
                return;
            }
            currentVal = val;
        }
//        System.out.println(algorithmName + "--> SORTING CORRECT");
    }

    private static long[] createArrayCopy(long[] array) {
        long[] newArray = new long[array.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    private static int[] generateIntArray(int elements) {
        int[] arr = new int[elements];
        Random random = new Random();
        for (int i = 0; i < elements; i++) {
            arr[i] = random.nextInt();
        }
        return arr;
    }

    private static long[] generateLongArray(int elements) {
        long[] arr = new long[elements];
        Random random = new Random();
        for (int i = 0; i < elements; i++) {
            arr[i] = random.nextLong();
        }
        return arr;
    }

}
