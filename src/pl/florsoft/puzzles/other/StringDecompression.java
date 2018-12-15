package pl.florsoft.puzzles.other;

/**
 * Task: https://techdevguide.withgoogle.com/paths/advanced/compress-decompression#code-challenge
 * Eg. 3[abc]4[ab]c  -->  abcabcabcababababc, 2[3[a]b]  -->  aaabaaab
 *
 */
public class StringDecompression {

    public static String decompressString(String compressedString) {
        return decompressRec(compressedString, new IndexHelper(0, compressedString.length() - 1));
    }

    private static String decompressRec(String compressedString, IndexHelper indexHelper) {
        StringBuilder sb = new StringBuilder();
        while (indexHelper.startIdx <= indexHelper.endIdx) {
            if (compressedString.charAt(indexHelper.startIdx) == ']') {
                indexHelper.startIdx++;
                break;
            }
            char currentChar = compressedString.charAt(indexHelper.startIdx);
            if (currentChar >= 'a' && currentChar <= 'z') {
                sb.append(currentChar);
                indexHelper.startIdx++;
            } else { // is number or [
                int numberToMultiply = 1;
                if (currentChar == '[') {
                    indexHelper.startIdx++;
                } else {
                    numberToMultiply = Character.getNumericValue(currentChar);
                    indexHelper.startIdx++;
                    while (indexHelper.startIdx < indexHelper.endIdx && compressedString.charAt(indexHelper.startIdx) != '[') {
                        numberToMultiply = numberToMultiply * 10 + Character.getNumericValue(compressedString.charAt(indexHelper.startIdx));
                        indexHelper.startIdx++;
                    }
                    indexHelper.startIdx++; // bracket
                }
                String innerVal = decompressRec(compressedString, indexHelper);
                for (int i = 0; i < numberToMultiply; i++) {
                    sb.append(innerVal);
                }
            }
        }
        return sb.toString();
    }

    static class IndexHelper {

        public int startIdx;
        public int endIdx;

        public IndexHelper(int startIdx, int endIdx) {
            this.startIdx = startIdx;
            this.endIdx = endIdx;
        }

    }

    public static void main(String[] args) {
        checkDecompression(decompressString("2[10[a]bc]"), "aaaaaaaaaabcaaaaaaaaaabc");
        checkDecompression(decompressString("2[3[a]b]"), "aaabaaab");
        checkDecompression(decompressString("3[abc]4[ab]c"), "abcabcabcababababc");
        checkDecompression(decompressString("a[]b"), "ab");
        checkDecompression(decompressString("0[abc]"), "");
    }

    private static void checkDecompression(String decompressionResult, String correctResult) {
        System.out.println(correctResult + " ?== " + decompressionResult + " --> "
                + (decompressionResult.equals(correctResult)));
    }

}
