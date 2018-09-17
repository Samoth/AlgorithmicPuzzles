package pl.florsoft.puzzles.codility;

import java.util.Stack;

/**
 * Task: https://app.codility.com/programmers/lessons/8-leader/dominator/
 */
public class L08Dominator {

    public int solution(int[] A) {
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < A.length; i++) {
            if (st.isEmpty() || st.peek() == A[i]) {
                st.push(A[i]);
            } else {
                st.pop();
            }
            if (st.size() > A.length / 2) {
                return i;
            }
        }
        if (st.isEmpty()) {
            return -1;
        }
        int lastCorrectCandidateIndex = -1, leaderCandidate = st.pop(), candidateOccures = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == leaderCandidate) {
                candidateOccures++;
                lastCorrectCandidateIndex = i;
            }
        }
        return candidateOccures > A.length / 2 ? lastCorrectCandidateIndex : -1;
    }

    public int solution2(int[] A) {
        int candidate = 0, occurs = 0;
        for (int i = 0; i < A.length; i++) {
            if (occurs == 0) {
                candidate = A[i];
            }
            occurs += candidate == A[i] ? 1 : -1;
            if (occurs > A.length / 2) {
                return i;
            }
        }
        if (occurs == 0) {
            return -1;
        }
        occurs = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == candidate) {
                occurs++;
                if (occurs > A.length / 2) {
                    return i;
                }
            }
        }
        return -1;
    }

}
