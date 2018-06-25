package pl.florsoft.codility;

public class L03PermMissingElem {

    public int solution(int[] A) {
        long sum = (long) (A.length + 1) * (A.length + 2) / 2;
        for (int i = 0; i < A.length; i++) {
            sum -= A[i];
        }
        return (int) sum;
    }

}
