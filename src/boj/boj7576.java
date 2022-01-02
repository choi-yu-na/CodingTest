//https://www.acmicpc.net/problem/7576
//토마토
package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class boj7576 {
    static int n, m;
    static int[][] storage;
    static boolean[][] visited;
    static Queue<location> queue;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int day;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        m = Integer.parseInt(str.split(" ")[0]);
        n = Integer.parseInt(str.split(" ")[1]);
        storage = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            str = br.readLine();
            for (int j = 0; j < m; j++) {
                storage[i][j] = Integer.parseInt(str.split(" ")[j]);
            }
        }

        int loopCnt = 0;
        queue = new LinkedList<>();
        while(!isDone(storage)) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if(storage[i][j]==1 && visited[i][j]==false) {
                        visited[i][j]=true;
                        queue.offer(new location(i,j));
                        ripe(i,j);
                    }
                }
            }
            if(loopCnt>=(n*m)/2) {
                System.out.println("-1");
                return;
            }
        }
        System.out.println(day);

    }

    public static void ripe(int x, int y) {
        location l = queue.poll();
        for (int i = 0; i < 4; i++) {
            int newX = l.x+dx[i];
            int newY = l.y+dy[i];
            if(-1<newX && newX<n && -1<newY && newY<m) {
                if(storage[newX][newY]==0 && visited[newX][newY]==false) {
                    storage[newX][newY]=1;
                    visited[newX][newY]=true;
                }
            }
        }

    }

    public static boolean isDone(int[][] arr) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(arr[i][j]==0)
                    return false;
            }
        }
        return true;
    }

    public static class location {
        int x;
        int y;

        public location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
