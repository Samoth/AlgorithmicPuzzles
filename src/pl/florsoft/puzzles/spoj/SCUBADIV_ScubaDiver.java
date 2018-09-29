package pl.florsoft.puzzles.spoj;

import pl.florsoft.puzzles.utils.InputStreamReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * task: http://www.spoj.com/problems/SCUBADIV/
 */
public class SCUBADIV_ScubaDiver {

    public static void main(String[] args) throws IOException {
        List<ScubaDiverTest> testCaseList = readTestCases();
        for (ScubaDiverTest test : testCaseList) {
            computeScubaDiverCylinders(test);
        }
    }

    private static void computeScubaDiverCylinders(ScubaDiverTest test) {
        System.out.println(computeMinWeight(test, test.availableCylinders.size()));
    }

    private static int computeMinWeight(ScubaDiverTest test, int n) {
        int[][][] dp = new int[2][test.oxygenNeeded + 1][test.nitrogenNeeded + 1];
        for (int cyl = 1; cyl <= n; cyl++) {
            Cylinder actualCyl = test.availableCylinders.get(cyl - 1);
            int prevRow = cyl % 2;
            int actualRow = (cyl + 1) % 2;
            for (int oxy = 1; oxy <= test.oxygenNeeded; oxy++) {
                for (int ni = 1; ni <= test.nitrogenNeeded; ni++) {
                    int prevCylVal = dp[prevRow][oxy][ni];
                    if (prevCylVal != 0) {
                        int idxSumOxy = oxy + actualCyl.oxygen > test.oxygenNeeded ? test.oxygenNeeded : oxy + actualCyl.oxygen;
                        int idxSumNit = ni + actualCyl.nitrogen > test.nitrogenNeeded ? test.nitrogenNeeded : ni + actualCyl.nitrogen;
                        int minCandidate = dp[prevRow][idxSumOxy][idxSumNit] != 0
                                ? Math.min(dp[prevRow][idxSumOxy][idxSumNit], prevCylVal + actualCyl.weight)
                                : prevCylVal + actualCyl.weight;
                        dp[actualRow][idxSumOxy][idxSumNit] = Math.min(minCandidate, dp[actualRow][idxSumOxy][idxSumNit] != 0
                                ? dp[actualRow][idxSumOxy][idxSumNit] : Integer.MAX_VALUE);
                        dp[actualRow][oxy][ni] = Math.min(prevCylVal, dp[actualRow][oxy][ni] != 0
                                ? dp[actualRow][oxy][ni] : Integer.MAX_VALUE);
                    }
                    if (oxy == actualCyl.oxygen && ni == actualCyl.nitrogen) {
                        dp[actualRow][oxy][ni] = Math.min(actualCyl.weight, prevCylVal != 0 ? prevCylVal : Integer.MAX_VALUE);
                    }
                }
            }
        }
        return dp[(n + 1) % 2][test.oxygenNeeded][test.nitrogenNeeded];
    }

    private static List<ScubaDiverTest> readTestCases() throws IOException {
        InputStreamReader reader = new InputStreamReader();
        int testCases = reader.nextInt();
        List<ScubaDiverTest> caseList = new ArrayList<>(testCases);
        for (int i = 0; i < testCases; i++) {
            ScubaDiverTest scubaDiverTest = new ScubaDiverTest();
            scubaDiverTest.oxygenNeeded = reader.nextInt();
            scubaDiverTest.nitrogenNeeded = reader.nextInt();
            int availableCylinders = reader.nextInt();
            scubaDiverTest.availableCylinders = new ArrayList<>(availableCylinders);
            for (int j = 0; j < availableCylinders; j++) {
                scubaDiverTest.availableCylinders.add(new Cylinder(reader.nextInt(), reader.nextInt(), reader.nextInt()));
            }
            caseList.add(scubaDiverTest);
        }
        return caseList;
    }


    static class ScubaDiverTest {
        int oxygenNeeded;
        int nitrogenNeeded;
        List<Cylinder> availableCylinders;
    }

    static class Cylinder {
        int oxygen;
        int nitrogen;
        int weight;

        Cylinder(int oxygen, int nitrogen, int weight) {
            this.oxygen = oxygen;
            this.nitrogen = nitrogen;
            this.weight = weight;
        }
    }

}
