package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bog_12745 {
    static int N; // 서버실 칸 수 NxN
    static int[][] computerNum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        computerNum = new int[N][N];
        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < N; j++) {
                computerNum[i][j] = Integer.parseInt(tmp.split(" ")[j]);
            }
        }

        float totalComputerNum = totalComputerNum(computerNum);
        int half = Math.round(totalComputerNum/2);
        int now = 0;
        int minute = 0;

        while(half > now) {
            now += cooledComputerNum();
            minute++;
        }


        System.out.print(minute);
//        test_print(int[][] computerNum);
    }

    public static void test_print(int[][] computerNum) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(computerNum[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static float totalComputerNum(int[][] computerNum) {
        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                total += computerNum[i][j];
            }
        }
        return total;
    }

    public static int cooledComputerNum() {
        int num = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(computerNum[i][j]!=0) {
                    computerNum[i][j]--;
                    num++;
                }
            }
        }
        return num;
    }
}
