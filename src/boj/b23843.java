package boj;

import java.util.Scanner;

public class b23843 {
    static int N;
    static int M;
    static int[] num_of_products;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        num_of_products = new int[16];
        for (int i = 0; i < N; i++) {
            int tmp = sc.nextInt();
            int k = log2(tmp);
            num_of_products[k]++;
        }

        print_arr();

    }

    static int log2(int n) {
        if (n==1)
            return 0;

        else {
            int result=1;
            while(result!=n) {
                result = (int)Math.pow(result,2);
                result++;
            }
            return result;
        }
    }

    static void print_arr() {
        for (int i = 0; i < 16; i++) {
            System.out.print(num_of_products[i]+" ");
        }
    }
}
