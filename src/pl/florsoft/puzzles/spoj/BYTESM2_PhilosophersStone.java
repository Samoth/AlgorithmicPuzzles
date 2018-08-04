package pl.florsoft.puzzles.spoj;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * task: http://www.spoj.com/problems/BYTESM2/
 */
public class BYTESM2_PhilosophersStone {

    public static void main(String[] args) throws java.lang.Exception {
        List<TestCase> testCaseList = readTestCases();
        for (TestCase test : testCaseList) {
            computeMaxStones(test.matrix);
        }
    }

    private static void computeMaxStones(int[][] matrix) {
        if (matrix.length == 0) {
            System.out.println("0");
            return;
        }
        int maxStones = 0;
        int[][] dp = new int[2][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            int prevRow = i % 2;
            int actualRow = (i + 1) % 2;
            if (i == 0) { // first row
                dp[actualRow] = matrix[0];
                for (int value : matrix[0]) {
                    if (value > maxStones) {
                        maxStones = value;
                    }
                }
            } else {
                for (int j = 0; j < matrix[i].length; j++) {
                    dp[actualRow][j] = matrix[i][j] + dp[prevRow][j];
                    if (j > 0) {
                        dp[actualRow][j] = Math.max(dp[actualRow][j], matrix[i][j] + dp[prevRow][j - 1]);
                    }
                    if (j < matrix[i].length - 1) {
                        dp[actualRow][j] = Math.max(dp[actualRow][j], matrix[i][j] + dp[prevRow][j + 1]);
                    }
                    if (dp[actualRow][j] > maxStones) {
                        maxStones = dp[actualRow][j];
                    }
                }
            }
        }
        System.out.println(maxStones);
    }

    private static List<TestCase> readTestCases() throws IOException {
        Reader reader = new Reader();
        int testCases = reader.nextInt();
        List<TestCase> caseList = new ArrayList<>(testCases);
        for (int i = 0; i < testCases; i++) {
            int rows = reader.nextInt();
            int columns = reader.nextInt();
            int[][] matrix = new int[rows][columns];
            for (int j = 0; j < rows; j++) {
                for (int k = 0; k < columns; k++) {
                    matrix[j][k] = reader.nextInt();
                }
            }
            caseList.add(new TestCase(matrix));
        }
        return caseList;
    }

    static class TestCase {
        int[][] matrix;

        TestCase(int[][] matrix) {
            this.matrix = matrix;
        }
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
    }
}
