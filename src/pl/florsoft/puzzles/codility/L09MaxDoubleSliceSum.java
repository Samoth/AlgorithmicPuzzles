package pl.florsoft.puzzles.codility;

/**
 * Task: https://app.codility.com/programmers/lessons/9-maximum_slice_problem/max_double_slice_sum/
 */
public class L09MaxDoubleSliceSum {

    public int solution(int[] A) {
        int currentSum = 0, maxDoubleSlice = 0, minVal = A[1], prevSum = 0;
        if (A.length == 3) {
            return maxDoubleSlice;
        }
        for (int i = 1; i < A.length - 1; i++) {
            int val = A[i];
            if (currentSum - minVal + val >= 0) {
                currentSum += val;
            } else {
                System.out.println("WESZLO");
                currentSum = currentSum + val - prevSum;
                if (currentSum - val > 0) {
                    System.out.println("A");
                    prevSum = currentSum;
                } else {
                    System.out.println("B");
                    prevSum = minVal;
                }
                minVal = val;
            }
            if (val < minVal) {
                minVal = val;
            }
            maxDoubleSlice = Math.max(maxDoubleSlice, currentSum - minVal);

            System.out.println("val = " + val + ", minVal = " + minVal + ", prevSum = " + prevSum + ", currentSum = " + currentSum + ", max = " + maxDoubleSlice);

        }
        return maxDoubleSlice;
    }

    public static void main(String[] args) {
        int[] test1 = {2, 3, -1231, 1, 3, -10000, 3, 1, 2, -111, 4, 3}; // 10
        int[] test2 = {-2, -3, -4, 1, -5, -6, -7}; // 1
        int[] test3 = {5, 17, 0, 3}; // 17
        int[] test4 = {5, 1, -7, 1, -8, 3, -2, 8, -8, 20, 8}; // 17
        int[] test5 = {-4, -5, -1, -5, -7, -19, -11}; // 0

    }


    public int solution2(int[] A) {
        int currentSum = 0, maxDoubleSlice = 0, minVal = A[1], prevSum = 0;
        if (A.length == 3) {
            return maxDoubleSlice;
        }
        for (int i = 1; i < A.length - 1; i++) {
            int val = A[i];
            if (val < 0) {
                if (currentSum - prevSum > prevSum) {
                    // System.out.println("WESZLO");
                    currentSum = currentSum + val - prevSum;
                    if (currentSum - val > 0) {
                        System.out.println("A");
                        prevSum = currentSum;
                    } else {
                        System.out.println("B");
                        prevSum = Math.max(val, A[i-1]);
                    }
                    minVal = val;
                } else {
                    // if (val < 0) {
                    //     int oldSum = currentSum;
                    currentSum = currentSum + val - prevSum;
                    //     prevSum = oldSum;
                    // } else {
                    //     currentSum = currentSum + val - prevSum;
                    prevSum = currentSum;

                    // }
                    System.out.println("XXX");
                }
            } else {
                currentSum += val;
            }
            if (val < minVal) {
                minVal = val;
            }
            maxDoubleSlice = Math.max(maxDoubleSlice, currentSum - minVal);

            System.out.println("val = " + val + ", minVal = " + minVal + ", prevSum = " + prevSum + ", currentSum = " + currentSum + ", max = " + maxDoubleSlice);

        }
        return maxDoubleSlice;
    }

}
