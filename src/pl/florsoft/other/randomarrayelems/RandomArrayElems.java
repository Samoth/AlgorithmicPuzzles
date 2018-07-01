package pl.florsoft.other.randomarrayelems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Task: Write a method to randomly generate a set of M objects (eg. Integer) from an array of size N.
 * Each element must have equal probability of being chosen.
 */
public class RandomArrayElems {

    private static Integer[] generateRandomIntegersFromArray(int m, Integer[] array) {
        Random random = new Random();
        Integer[] resultArray = new Integer[m];
        Map<Integer, Integer> bufferMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int randomIdx = random.nextInt(array.length - i);
            resultArray[i] = getTrueValue(array, bufferMap, randomIdx);
            bufferMap.put(randomIdx, getTrueValue(array, bufferMap, array.length - i - 1));
        }
        return resultArray;
    }

    private static Integer getTrueValue(Integer[] array, Map<Integer, Integer> bufferMap, int idx) {
        return bufferMap.get(idx) != null ? bufferMap.get(idx) : array[idx];
    }

    // tests
    public static void main(String[] args) {
        Integer[] array = {3, 4, 1, 6, 4, 7, 234, 6, 2, 5, 11, 53, 2343242, Integer.MAX_VALUE, 34, -4, -1, 0, 0};
        int randomNumbers = 7;
        TestMachine testMachine = new TestMachine((RandomGenerator<Integer>) RandomArrayElems::generateRandomIntegersFromArray,
                randomNumbers, array.length);
        Integer[] results = generateRandomIntegersFromArray(randomNumbers, array);
        System.out.println(Arrays.toString(results));
    }
}
