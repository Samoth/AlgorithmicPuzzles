package pl.florsoft.codility;

/**
 * Task: https://app.codility.com/programmers/lessons/5-prefix_sums/passing_cars/
 */
public class L05PassingCars {

    public int solution(int[] A) {
        int[] carsDrivingWest = new int[A.length + 1];
        int passingCars = 0;
        for (int i = A.length - 1; i >= 0; i--) {
            carsDrivingWest[i] = carsDrivingWest[i + 1] + A[i];
        }
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                passingCars += carsDrivingWest[i];
                if (passingCars > 1000000000) {
                    return -1;
                }
            }
        }
        return passingCars;
    }
}
