package pl.florsoft.puzzles.codility;

/**
 * Task: https://app.codility.com/programmers/lessons/5-prefix_sums/genomic_range_query/
 */
public class L05GenomicRangeQuery {

    public int[] solution(String S, int[] P, int[] Q) {
        int[] result = new int[P.length];
        int[][] nucleotideCount = new int[4][S.length()];
        char[] chars = S.toCharArray();
        for (int i = 0; i < S.length(); i++) {
            int charIdx = getCharValue(S.charAt(i)) - 1;
            for (int j = 0; j < 4; j++) {
                if (charIdx == j) {
                    nucleotideCount[charIdx][i] = i == 0 ? 1 : nucleotideCount[charIdx][i - 1] + 1;
                } else {
                    nucleotideCount[j][i] = i == 0 ? 0 : nucleotideCount[j][i - 1];
                }
            }
        }
        for (int i = 0; i < P.length; i++) {
            for (int j = 0; j < 4; j++) {
                if (nucleotideCount[j][Q[i]] - (P[i] == 0 ? 0 : nucleotideCount[j][P[i] - 1]) > 0) {
                    result[i] = j + 1;
                    break;
                }
            }
        }
        return result;
    }

    private int getCharValue(char c) {
        if (c == 'A') {
            return 1;
        } else if (c == 'C') {
            return 2;
        } else if (c == 'G') {
            return 3;
        } else if (c == 'T') {
            return 4;
        }
        throw new IllegalArgumentException();
    }

}
