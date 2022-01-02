package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class b1926 {
    static int n, m;
    static int[][] paper;
    static boolean[][] visited;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int numOfPaint;
    static int max;
    static int nowArea;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        n = Integer.parseInt(str.split(" ")[0]);
        m = Integer.parseInt(str.split(" ")[1]);
        paper = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                str = br.readLine();
                paper[n][m] = Integer.parseInt(str.split(" ")[m]);
            }
        }

        numOfPaint=0;
        max=0;
        Queue<location> queue = new LinkedList<location>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(paper[i][j]==1 && visited[i][j]==false) {
                    queue.offer(new location(i,j,paper[i][j]));
                    numOfPaint++;
                    nowArea=0;
                    while(!queue.isEmpty()) {
                        location l = queue.poll();
                        for(int k=0; k<4; k++) {
                            int newX = l.x+dx[k];
                            int newY = l.y+dy[k];

                            if(-1 < newX && newX < n && -1 < newY && newY < m) {
                                if(paper[newX][newY]==1) {
                                    queue.offer(new location(newX,newY,paper[newX][newY]));
                                    nowArea++;
                                }
                            }
                        }
                    }
                    max = Math.max(max,nowArea);
                }
            }
        }
        System.out.println(numOfPaint);
        System.out.println(max);
    }


    public static class location {
        int x;
        int y;
        int value;

        public location(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
}
