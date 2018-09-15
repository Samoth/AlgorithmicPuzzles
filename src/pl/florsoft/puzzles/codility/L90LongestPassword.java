package pl.florsoft.puzzles.codility;

/**
 * Task: https://app.codility.com/programmers/lessons/90-tasks_from_indeed_prime_2015_challenge/longest_password/
 */
public class L90LongestPassword {

    public int solution(String S) {
        String[] words = S.split(" ");
        int maxLength = -1;
        for (String word : words) {
            int wordLength = isValidWord(word);
            if (wordLength > maxLength) {
                maxLength = wordLength;
            }
        }
        return maxLength;
    }

    private int isValidWord(String str) {
        char[] letters = str.toCharArray();
        int letterCount = 0;
        int numberCount = 0;
        for (char letter : letters) {
            if (!isAlphanumericLetter(letter)) {
                return -1;
            } else if (isNumber(letter)) {
                numberCount++;
            } else {
                letterCount++;
            }
        }
        return letterCount % 2 == 0 && numberCount % 2 == 1 ? str.length() : -1;
    }

    private boolean isAlphanumericLetter(char c) {
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || isNumber(c);
    }

    private boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

}
