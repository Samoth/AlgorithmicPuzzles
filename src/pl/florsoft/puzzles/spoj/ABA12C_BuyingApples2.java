package pl.florsoft.puzzles.spoj;

import pl.florsoft.puzzles.utils.InputStreamReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * task: http://www.spoj.com/problems/ABA12C/
 * version 2: without consider N
 */
public class ABA12C_BuyingApples2 {

    public static void main(String[] args) throws java.lang.Exception {
        List<AppleTestCase> testCases = readTestCases();
        for (AppleTestCase testCase : testCases) {
            System.out.println(computeMinApplePrice(testCase));
        }
    }

    private static int computeMinApplePrice(AppleTestCase testCase) {
        int[] dp = new int[testCase.k + 1];
        for (int sum = 1; sum <= testCase.k; sum++) {
            for (int el = 0; el < sum; el++) {
                if (testCase.prices[el] == -1) {
                    continue;
                }
                int currentSum = 0;
                if (sum == el + 1) {
                    currentSum = testCase.prices[el];
                } else if (dp[sum - (el + 1)] != 0) {
                    currentSum = dp[sum - (el + 1)] + testCase.prices[el];
                }
                if (currentSum != 0) {
                    dp[sum] = dp[sum] != 0 ? Math.min(dp[sum], currentSum) : currentSum;
                }
            }
        }
        return dp[testCase.k] != 0 ? dp[testCase.k] : -1;
    }

    private static List<AppleTestCase> readTestCases() throws IOException {
        InputStreamReader reader = new InputStreamReader();
        int testCases = reader.nextInt();
        List<AppleTestCase> caseList = new ArrayList<>(testCases);
        for (int i = 0; i < testCases; i++) {
            int n = reader.nextInt();
            int k = reader.nextInt();
            int[] prices = new int[k];
            for (int j = 0; j < k; j++) {
                prices[j] = reader.nextInt();
            }
            caseList.add(new AppleTestCase(n, k, prices));
        }
        return caseList;
    }

    static class AppleTestCase {
        int n;
        int k;
        int[] prices;

        public AppleTestCase(int n, int k, int[] prices) {
            this.n = n;
            this.k = k;
            this.prices = prices;
        }
    }

}
