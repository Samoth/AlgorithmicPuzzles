package pl.florsoft.puzzles.spoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * task: http://www.spoj.com/problems/PT07Y/
 */
public class PT07Y_IsItATree2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodes = Integer.parseInt(st.nextToken());
        int edges = Integer.parseInt(st.nextToken());
        if (edges != nodes - 1) {
            System.out.println("NO");
            return;
        }
        int[] parent = new int[nodes];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
        for (int i = 0; i < edges; i++) {
            st = new StringTokenizer(br.readLine());
            int x = find(parent, Integer.parseInt(st.nextToken()) - 1);
            int y = find(parent, Integer.parseInt(st.nextToken()) - 1);
            if (x == y) {
                System.out.println("NO");
                return;
            }
            union(parent, x, y);
        }
        System.out.println("YES");
    }

    private static int find(int[] parent, int i) {
        if (parent[i] == -1) {
            return i;
        }
        return find(parent, parent[i]);
    }

    private static void union(int[] parent, int x, int y) {
        int xSet = find(parent, x);
        int ySet = find(parent, y);
        parent[xSet] = ySet;
    }
}
