package pl.florsoft.other.randomarrayelems;

public class TestMachine {

    private RandomGenerator randomGenerator;
    private int randomElems;
    private int arraysLength;

    public TestMachine(RandomGenerator randomGenerator, int randomElems, int arraysLength) {
        this.randomGenerator = randomGenerator;
        this.randomElems = randomElems;
        this.arraysLength = arraysLength;
        checkPreconditions();
    }

    private void checkPreconditions() {
        if (randomGenerator == null || randomElems <= 0 || arraysLength <= 0 || randomElems > arraysLength) {
            throw new IllegalArgumentException();
        }
    }

    public void startTesting() {

    }
}
