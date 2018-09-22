package pl.florsoft.puzzles.codility;

/**
 * Task: https://app.codility.com/programmers/lessons/10-prime_and_composite_numbers/peaks/
 */
public class L10Peaks {

    public int solution(int[] A) {
        if (A.length < 3) {
            return 0;
        }
        int[] peaksCount = new int[A.length];
        for (int i = 1; i < A.length - 1; i++) {
            if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
                peaksCount[i] = peaksCount[i - 1] + 1;
            } else {
                peaksCount[i] = peaksCount[i - 1];
            }
        }
        peaksCount[A.length - 1] = peaksCount[A.length - 2];
        int numberOfBlock = 1;
        int maxBlockNumber = 0;
        while (numberOfBlock * numberOfBlock <= A.length && numberOfBlock * numberOfBlock > 0) {
            if (A.length % numberOfBlock == 0) {
                int blockSize = A.length / numberOfBlock;
                if (numberOfBlock > maxBlockNumber && isCorrectBlock(numberOfBlock, blockSize, peaksCount)) {
                    maxBlockNumber = numberOfBlock;
                }
                if (blockSize > maxBlockNumber && blockSize < A.length
                        && isCorrectBlock(blockSize, numberOfBlock, peaksCount)) {
                    maxBlockNumber = blockSize;
                }
            }
            numberOfBlock++;
        }
        return maxBlockNumber;
    }

    private boolean isCorrectBlock(int numberOfBlock, int blockSize, int[] peaksCount) {
        boolean areBlocksCorrect = true;
        for (int i = 0; i < numberOfBlock; i++) {
            boolean isCorrectForI = peaksCount[i * blockSize + blockSize - 1]
                    - (i == 0 ? 0 : peaksCount[i * blockSize - 1]) > 0;
            areBlocksCorrect &= isCorrectForI;
        }
        return areBlocksCorrect;
    }

}
