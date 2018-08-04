package pl.florsoft.puzzles.codility;

/**
 * Task: https://app.codility.com/programmers/lessons/3-time_complexity/frog_jmp/
 */
public class L03FrogJmp {

    public int solution(int X, int Y, int D) {
        if ((Y - X) % D == 0) {
            return (Y - X) / D;
        }
        return (Y - X) / D + 1;
    }
}
