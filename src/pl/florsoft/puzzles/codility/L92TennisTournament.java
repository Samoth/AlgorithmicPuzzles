package pl.florsoft.puzzles.codility;

/**
 * Task: https://app.codility.com/programmers/lessons/92-tasks_from_indeed_prime_2016_college_coders_challenge/tennis_tournament/
 */
public class L92TennisTournament {

    public int solution(int P, int C) {
        return Math.min(C, P % 2 == 0 ? P / 2 : (P - 1) / 2);
    }

}
