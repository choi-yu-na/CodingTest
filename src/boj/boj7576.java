package boj;

import java.io.*;
import java.util.*;
class tomato {
    int x; // 세로
    int y; // 가로
    tomato(int x, int y) {
        this.x = x; // 세로
        this.y = y; // 가로
    }
}
public class boj7576 {
    static int M; // 가로
    static int N; // 세로
    static int[] dx = { -1, 1, 0, 0 }; // 상하좌우위아래
    static int[] dy = { 0, 0, -1, 1 }; // 상하좌우위아래
    static int[][] board; // 토마토 판
    static Queue<tomato> que;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt(); // 가로
        N = sc.nextInt(); // 세로
        board = new int[N][M]; // 토마토판
        que = new LinkedList<tomato>();
// 토마토판 입력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] = sc.nextInt();
// 만약 토마토가 익은거라면
// 큐에 넣어
                if (board[i][j] == 1)
                    que.add(new tomato(i, j));
            }
        }
        System.out.println(BFS());
    }
    public static int BFS() {
        while (!que.isEmpty()) {
            tomato t = que.remove();
            int x = t.x; // 세로
            int y = t.y; // 가로
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i]; // 세로
                int ny = y + dy[i]; // 가로
// 범위 안에서
                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
// 토마토가 안익었으면
                    if (board[nx][ny] == 0) {
// 익은 토마토 추가
                        que.add(new tomato(nx, ny));
// 익은 날자를 얻기위해 그 전 값에서 1 증가
                        board[nx][ny] = board[x][y] + 1;
                    }
                }
            }
        }
// 최대 날짜
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
// 토마토가 안익은게 있으면
                if (board[i][j] == 0)
                    return -1;
// 날짜 최댓값 구하기
                result = Math.max(result, board[i][j]);
            }
        }
// 토마토가 모두 익은 상태인 경우
        if (result == 1)
            return 0;
// 걸린 일수는 최대 날짜에서 1을 빼줘야digka
        else
            return result - 1;
    }
}
