package pl.florsoft.codility;

import java.util.*;

/**
 * Task: https://app.codility.com/programmers/lessons/6-sorting/number_of_disc_intersections/
 */
public class L06NumberOfDiscIntersections {

    public int solution(int[] A) {
        List<Disc> discs = createList(A);
        Collections.sort(discs);
        return countElements(discs);
    }

    private static List<Disc> createList(int[] A) {
        List<Disc> list = new ArrayList<>(A.length);
        for (int i = 0; i < A.length; i++) {
            list.add(new Disc(Math.max(0, i - A[i]),
                    (int) Math.min((long) A.length, ((long) i + (long) A[i]))));
        }
        return list;
    }

    private static int countElements(List<Disc> discs) {
        int count = 0;
        PriorityQueue<Disc> queue = new PriorityQueue<>(Comparator.comparing(o -> o.endPos));
        for (Disc disc : discs) {
            while (queue.size() > 0 && queue.peek().endPos < disc.startPos) {
                queue.poll();
                count += queue.size();
            }
            queue.add(disc);
        }
        while (queue.size() > 0) {
            queue.poll();
            count += queue.size();
        }
        return count;
    }

    static class Disc implements Comparable<Disc> {
        Integer startPos;
        Integer endPos;

        public Disc(Integer startPos, Integer endPos) {
            this.startPos = startPos;
            this.endPos = endPos;
        }

        @Override
        public int compareTo(Disc o) {
            int result = this.startPos.compareTo(o.startPos);
            if (result == 0) {
                return this.endPos.compareTo(o.endPos);
            }
            return result;
        }
    }
}
