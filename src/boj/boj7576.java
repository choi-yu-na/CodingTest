//https://www.acmicpc.net/problem/7576
//토마토
package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj7576 {
    static int n, m;
    static int[][] storage;
    static int tomato;
    static boolean[][] visited;
    static Queue<location> queue;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        storage = new int[n][m];
        queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                storage[i][j] = Integer.parseInt(st.nextToken());
                if(storage[i][j]==1) queue.offer(new location(i,j,0));
                else if(storage[i][j]==0) tomato += 1;
            }
        }

        ripe();

        System.out.println(tomato>0?-1:max);
    }

    public static void ripe() {
        visited = new boolean[n][m];
        while(!queue.isEmpty()) {
            location now = queue.poll();
            if(max < now.day) max = now.day;
            for (int i = 0; i < 4; i++) {
                int newX = now.x + dx[i];
                int newY = now.y + dy[i];

                if (0 > newX || newX >= n || 0 > newY || newY >= m) continue;
                if (storage[newX][newY] == -1) continue;
                if (visited[newX][newY]) continue;

                queue.offer(new location(newX,newY, now.day+1));
                storage[newX][newY]=1;
                visited[newX][newY]=true;
                tomato -= 1;
            }
            print(storage);

        }
    }

    public static void print(int[][] arr) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("----------");
    }

    public static class location {
        int x;
        int y;
        int day;

        public location(int x, int y, int day) {
            this.x = x;
            this.y = y;
            this.day = day;
        }
    }
}
