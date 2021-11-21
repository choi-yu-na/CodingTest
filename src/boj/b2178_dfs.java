package boj;

import java.util.*;
import java.io.*;

public class b2178_dfs {
    static int N, M; //2 ≤ N, M ≤ 100
    static int[][] maze;
    static ArrayList<Maze> mazeArrayList;
    static boolean[][] visited;

    static int dx[] = {1,-1,0,0};
    static int dy[] = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String NM = br.readLine();
        N = Integer.parseInt(NM.split(" ")[0]);
        M = Integer.parseInt(NM.split(" ")[1]);
        maze = new int[N+1][M+1];
        visited = new boolean[N+1][M+1];

        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            for (int j = 1; j <= M; j++) {
                maze[i][j] = Integer.parseInt(str.split("")[j-1]);
            }
        }
        print_maze();
        dfs(1,1);
        System.out.println(maze[N][M]);
    }

    public static void dfs(int n, int m) {
        Maze now = new Maze(n,m);

        visited[n][m] = true;
        for (int i = 0; i < 4; i++) {
            int next_x = now.x+dx[i];
            int next_y = now.y+dy[i];
            if(1<=next_x && next_x<=N && 1<=next_y && next_y<=M) {
                if(maze[next_x][next_y]==1 && !visited[next_x][next_y]) {
                    visited[next_x][next_y]=true;
                    maze[next_x][next_y] = maze[now.x][now.y] + 1;
                    dfs(next_x,next_y);
                    print_maze();

                }
            }
        }
    }

    public static void print_maze() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                System.out.printf("%02d",maze[i][j]);
                System.out.print(" ");

            }
            System.out.println("");
        }
        System.out.println("--------------");
    }

    static class Maze {
        int x;
        int y;
        //        int block;
        public Maze(int x, int y) {
            this.x = x;
            this.y = y;
//            this.block = block;
        }
    }
}


