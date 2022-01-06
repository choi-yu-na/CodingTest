package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj4179 {
    static int R;
    static int C; //1<=R,C<=1000
    static int J;
    static char[][] maze;
    static boolean[][] visited;
    static Queue<Maze> queueJ;
    static Queue<Maze> queueF;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int escape=0; //-1:실패, 0:아직모름, 1:성공
    static int escapeTime=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        maze = new char[R][C];
        visited = new boolean[R][C];
        queueJ = new LinkedList<>();
        queueF = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                maze[i][j] = str.charAt(j);
                if (maze[i][j]=='J') {
                    queueJ.offer(new Maze(i,j,0));
                    visited[i][j]=true;
                }
                if (maze[i][j]=='F') queueF.offer(new Maze(i,j,0));
            }
        }

        while(escape==0) {
            bfsJ();
            bfsF();
        }

        if(escape==-1) {
            System.out.println("IMPOSSIBLE");
        }
        else if(escape==1) {
            System.out.println(escapeTime);
        }

    }

    public static void bfsJ() {



        bfsF();
        while(!queueJ.isEmpty()) {
            Maze nowJ = queueJ.poll();

            for (int i = 0; i < 4; i++) {
                int nextJx = nowJ.x+dx[i];
                int nextJy = nowJ.y+dy[i];

                if((nextJx<0 || nextJx>=R || nextJy<0 || nextJy>=C) && (maze[nowJ.x][nowJ.y]=='J')) {
                    escape=1;
                    escapeTime= nowJ.time+1;
                    return;
                }


                if((nextJx<0 || nextJx>=R || nextJy<0 || nextJy>=C) && !(maze[nowJ.x][nowJ.y]=='J')) continue;
                if(maze[nextJx][nextJy]=='#' || maze[nextJx][nextJy]=='F') continue;
                if(visited[nextJx][nextJy]) continue;
                if(maze[nextJx][nextJy]=='.') { //탈출이 진행되면..
                    visited[nextJx][nextJy]=true;
                    maze[nextJx][nextJy]='J';
                    maze[nowJ.x][nowJ.y]='.';
                    queueJ.offer(new Maze(nextJx,nextJy, nowJ.time+1));

                }

            }

        }
    }
    public static void bfsF() {
        Maze nowF = queueF.poll();
        for (int i = 0; i < 4; i++) {
            int nextFx = nowF.x+dx[i];
            int nextFy = nowF.y+dy[i];

            if((nextFx<0 || nextFx>=R || nextFy<0 || nextFy>=C)) continue;
            if(maze[nextFx][nextFy]=='.') {
                maze[nextFx][nextFy]='F';
                queueF.offer(new Maze(nextFx,nextFy, nowF.time+1));
            }
        }


    }

    public static class Maze {
        int x;
        int y;
        int time;

        public Maze(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
