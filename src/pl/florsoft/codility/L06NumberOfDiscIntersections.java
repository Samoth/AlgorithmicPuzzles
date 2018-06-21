package pl.florsoft.codility;

import java.util.ArrayList;
import java.util.List;

/**
 * Task: https://app.codility.com/programmers/lessons/6-sorting/number_of_disc_intersections/
 */
public class L06NumberOfDiscIntersections {

    public int solution(int[] A) {
        List<Disc> discsSt = createList(A);
        List<Disc> discsEn = new ArrayList<>(discsSt);
        discsSt.sort((o1, o2) -> {
            int result = o1.startPos.compareTo(o2.startPos);
            if (result == 0) {
                return o1.endPos.compareTo(o2.endPos);
            }
            return result;
        });
        discsEn.sort((o1, o2) -> {
            int result = o1.endPos.compareTo(o2.endPos);
            if (result == 0) {
                return o1.startPos.compareTo(o2.startPos);
            }
            return result;
        });
        return countElements(discsSt, discsEn);
    }

    private static List<Disc> createList(int[] A) {
        List<Disc> list = new ArrayList<>(A.length);
        for (int i = 0; i < A.length; i++) {
            list.add(new Disc(Math.max(0, i - A[i]),
                    (int) Math.min((long) A.length - 1, ((long) i + (long) A[i]))));
        }
        return list;
    }

    private static int countElements(List<Disc> discsSt, List<Disc> discsEn) {
        int count = 0, currentItems = 0, endIdx = 0;
        for (Disc disc : discsSt) {
            while (disc.startPos > discsEn.get(endIdx).endPos) {
                endIdx++;
                currentItems--;
                count += currentItems;
            }
            currentItems++;
        }
        while (currentItems != 0) {
            currentItems--;
            count += currentItems;
        }
        return count;
    }

    static class Disc {
        Integer startPos;
        Integer endPos;

        Disc(Integer startPos, Integer endPos) {
            this.startPos = startPos;
            this.endPos = endPos;
        }
    }
    
}
