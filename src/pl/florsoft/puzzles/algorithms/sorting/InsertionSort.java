package pl.florsoft.puzzles.algorithms.sorting;

public class InsertionSort {

    public static void sort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        for (int i = 1; i < array.length; i++) {
            int currentElem = array[i];
            int j = i - 1;
            while (j >= 0 && currentElem < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = currentElem;
        }
    }

}
