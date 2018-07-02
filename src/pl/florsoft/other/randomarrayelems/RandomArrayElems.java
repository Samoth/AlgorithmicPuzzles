package pl.florsoft.other.randomarrayelems;

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

    private static Integer[] generateRandomIntegersFromArrayByWW(int m, Integer[] array) {
        Random random = new Random();
        Integer[] resultArray = new Integer[m];
        int outputIdx = 0;
        for (int i = 0; i < array.length && outputIdx < m; i++) {
            if (random.nextInt(array.length - i) < m - outputIdx) {
                resultArray[outputIdx++] = array[i];
            }
        }
        return resultArray;
    }

    // tests
    public static void main(String[] args) {
        int arrayLength = 1000;
        int randomObjs = 200;
        int testCount = 1000000;
        RandomGenerator<Integer> randomGenerator = new RandomGenerator<Integer>() {

            @Override
            public Integer[] getRandomElemsFromArray(int m, Integer[] array) {
                return generateRandomIntegersFromArray(m, array);
            }

            @Override
            public Integer[] prepareUniqueArray(int arrayLength) {
                Integer[] array = new Integer[arrayLength];
                for (int i = 0; i < arrayLength; i++) {
                    array[i] = i;
                }
                return array;
            }
        };
        TestMachine testMachine = new TestMachine<Integer>(randomGenerator, randomObjs, arrayLength);
        TestResult result = testMachine.startTesting(testCount);
        System.out.println(String.format("N = %d, M = %d, testCount = %d", arrayLength, randomObjs, testCount));
        System.out.println(String.format("AVG occurrences = %d, MIN occurrences = %d, MAX occurences = %d",
                result.avgOccurrences, result.minOccurrences, result.maxOccurrences));
    }
}
