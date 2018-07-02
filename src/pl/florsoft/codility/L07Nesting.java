package pl.florsoft.codility;

/**
 * Task: https://app.codility.com/programmers/lessons/7-stacks_and_queues/nesting/
 */
public class L07Nesting {

    public int solution(String S) {
        int openBracketCnt = 0;
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '(') {
                openBracketCnt++;
            } else {
                openBracketCnt--;
                if (openBracketCnt < 0) {
                    return 0;
                }
            }
        }
        return openBracketCnt == 0 ? 1 : 0;
    }

}
