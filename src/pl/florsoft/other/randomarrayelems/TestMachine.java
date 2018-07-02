package pl.florsoft.other.randomarrayelems;

import java.util.HashMap;
import java.util.Map;

public class TestMachine<T> {

    private static final int DEFAULT_TEST_COUNT = 1000000;
    private RandomGenerator<T> randomGenerator;
    private int randomElems;
    private int arraysLength;
    private final Map<T, Integer> occurrences;

    public TestMachine(RandomGenerator randomGenerator, int randomElems, int arraysLength) {
        this.randomGenerator = randomGenerator;
        this.randomElems = randomElems;
        this.arraysLength = arraysLength;
        this.occurrences = new HashMap<>(arraysLength);
        checkPreconditions();
    }

    private void checkPreconditions() {
        if (randomGenerator == null || randomElems <= 0 || arraysLength <= 0 || randomElems > arraysLength) {
            throw new IllegalArgumentException("Incorrect params!");
        }
    }

    public TestResult startTesting() {
        return startTesting(DEFAULT_TEST_COUNT);
    }

    public TestResult startTesting(int testCount) {
        T[] uniqueArray = randomGenerator.prepareUniqueArray(arraysLength);
        initOccurrencesMap(uniqueArray);
        for (int i = 0; i < testCount; i++) {
            T[] randomObjs = randomGenerator.getRandomElemsFromArray(randomElems, uniqueArray);
            storeOccurences(randomObjs);
        }
        return collectTestResult();
    }

    private TestResult collectTestResult() {
        long sumOccurrences = 0;
        int avgOccurrence = 0, minOccurrence = Integer.MAX_VALUE, maxOccurrence = 0;
        for (Integer occ: occurrences.values()) {
            if (occ > maxOccurrence) {
                maxOccurrence = occ;
            }
            if (occ < minOccurrence) {
                minOccurrence = occ;
            }
            sumOccurrences += occ;
        }
        avgOccurrence = (int) (sumOccurrences / occurrences.values().size());
        return new TestResult(avgOccurrence, minOccurrence, maxOccurrence);
    }

    private void initOccurrencesMap(T[] uniqueArray) {
        occurrences.clear();
        for (T val: uniqueArray) {
            occurrences.put(val, 0);
        }
    }

    private void storeOccurences(T[] randomObjs) {
        for (T elem: randomObjs) {
            Integer occurrencesInMap = occurrences.get(elem);
            occurrences.put(elem, ++occurrencesInMap);
        }
    }

}