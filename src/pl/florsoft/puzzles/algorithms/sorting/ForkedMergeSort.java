package pl.florsoft.puzzles.algorithms.sorting;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Author: Wild Wezyr
 */
public class ForkedMergeSort extends RecursiveAction {

    protected void mergeParts(long[] data, int offset, int len1, int len2) {
        int length = len1 + len2;
        int i = offset;
        int off1 = offset;
        int off2 = off1 + len1;
        long d0 = data[off1];
        long d1 = data[off2];
        while (true) {
            if (d0 < d1) {
                tmp[i++] = d0;
                off1++;
                len1--;
                if (len1 <= 0) {
                    break;
                }
                d0 = data[off1];
            } else {
                tmp[i++] = d1;
                off2++;
                len2--;
                if (len2 <= 0) {
                    break;
                }
                d1 = data[off2];
            }
        }

        if (len1 > 0) {
            tmp[i++] = d0;
            while (--len1 > 0) {
                tmp[i++] = data[++off1];
            }
        }
        if (len2 > 0) {
            tmp[i++] = d1;
            while (--len2 > 0) {
                tmp[i++] = data[++off2];
            }
        }

        for (i = 0; i < length; i++, offset++) {
            data[offset] = tmp[offset];
        }
    }

    public void sortPart(long[] data, int offset, int length) {
        if (length <= 1) {
            return;
        }
        if (length >= 4) {
            int len1 = length / 2;
            sortPart(data, offset, len1);
            int len2 = length - len1;
            sortPart(data, offset + len1, len2);
            mergeParts(data, offset, len1, len2);
        } else if (length == 2) {
            long d0 = data[offset];
            long d1 = data[offset + 1];
            if (d0 <= d1) {
                return;
            }
            data[offset] = d1;
            data[offset + 1] = d0;
        } else {
            long d0 = data[offset];
            long d1 = data[offset + 1];
            long d2 = data[offset + 2];
            if (d0 <= d1) {
                if (d1 <= d2) {
                    // d0 <= d1 <= d2
                    return;
                }
                // d0 <= d1 > d2
                if (d0 <= d2) {
                    // d0 <= d2 < d1
                    data[offset + 1] = d2;
                    data[offset + 2] = d1;
                } else {
                    // d2 < d0 <= d1
                    data[offset] = d2;
                    data[offset + 1] = d0;
                    data[offset + 2] = d1;
                }
            } else {
                if (d1 > d2) {
                    // d0 > d1 > d2
                    data[offset] = d2;
                    data[offset + 2] = d0;
                } else {
                    // d0 > d1 <= d2
                    if (d2 < d0) {
                        // d1 <= d2 < d0
                        data[offset] = d1;
                        data[offset + 1] = d2;
                        data[offset + 2] = d0;
                    } else {
                        // d1 < d0 <= d2
                        data[offset] = d1;
                        data[offset + 1] = d0;
                    }
                }
            }
        }
    }

    long[] data;
    long[] tmp;
    int offset;
    int length;
    int lvl;

    public ForkedMergeSort(long[] data, long[] tmp, int offset, int length, int lvl) {
        this.data = data;
        this.tmp = tmp;
        this.offset = offset;
        this.length = length;
        this.lvl = lvl;
    }

    static final int threshold = 20;

    @Override
    protected void compute() {
        boolean notForked = length <= threshold;
        if (notForked) {
            sortPart(data, offset, length);
        } else {
            int len1 = length / 2;
            int len2 = length - len1;
            invokeAll(new ForkedMergeSort(data, tmp, offset, len1, lvl + 1),
                    new ForkedMergeSort(data, tmp, offset + len1, len2, lvl + 1));
            mergeParts(data, offset, len1, len2);
        }
    }

    public static long[] sort(long[] data) {
        int length = data.length;
        long[] tmp = new long[length];
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new ForkedMergeSort(data, tmp, 0, length, 1));
        return data;
    }

}