package pl.florsoft.codility;

/**
 * Task: https://app.codility.com/programmers/lessons/5-prefix_sums/passing_cars/
 */
public class L05PassingCars2 {

    public int solution(int[] A) {
        int passingCars = 0, currentSum = 0;
        for (int i = A.length - 1; i >= 0; i--) {
            if (A[i] == 1) {
                currentSum++;
            } else {
                passingCars += currentSum;
                if (passingCars > 1000000000) {
                    return -1;
                }
            }
        }
        return passingCars;
    }
}
