package pl.florsoft.puzzles.codility;

/**
 * Task: https://app.codility.com/programmers/lessons/10-prime_and_composite_numbers/min_perimeter_rectangle/
 */
public class L10MinPerimeterRectangle {

    public int solution(int N) {
        int minPerimeter = Integer.MAX_VALUE;
        int i = 1;
        while (i * i <= N) {
            if (N % i == 0) {
                minPerimeter = Math.min(minPerimeter, 2 * (i + N / i));
            }
            i++;
        }
        return minPerimeter;
    }

}
