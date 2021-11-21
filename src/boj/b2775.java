package boj;

import java.util.*;
import java.io.*;
public class b2775 {
    static int T;
    static int k, n;
    static ArrayList<Integer> results;
    static int[][] villager;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        results = new ArrayList<>();
        villager = new int[14+1][14+1];

        for (int i = 1; i <= 14; i++) {
            villager[0][i] = i;
        }

        for (int i = 0; i < T; i++) {
            k = Integer.parseInt(br.readLine());
            n = Integer.parseInt(br.readLine());

            f(k,n);

            results.add(villager[k][n]);
        }

        for (int i = 0; i < T; i++) {
            System.out.println(results.get(i));
        }
    }

    public static int f(int a, int b) {
        for (int i = 1; i <= b; i++) {
            if(villager[a-1][i] != 0) { // 이미 구했다면
                villager[a][b] += villager[a-1][i];
            }
            else { //안구했음
                villager[a][b] += f(a-1,i);
            }
        }
        return villager[a][b];
    }
}