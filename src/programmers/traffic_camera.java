package programmers;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class traffic_camera {
    static int[][] routes;
    static boolean[] visited_routes;
    static Set<Integer> tmp_cross;
    static Integer[] cross;
    static int car;
    static int[][] comb_results;
    static final int MIN = -30000;
    static final int MAX = 30000;

    public static void main(String[] args) throws IOException {
        routes = new int[][]{{-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}};
        visited_routes = new boolean[routes.length];
        System.out.println(solution(routes));
    }

    public static int solution(int[][] routes) {
        tmp_cross = new HashSet<>();
        for (int[] i: routes) {
            for (int j: i) {
                tmp_cross.add(j);
            }
        }

        int cross_size = tmp_cross.size();
        cross = tmp_cross.toArray(new Integer[cross_size]);

        Arrays.sort(cross);
        //겹치는 구간들을 오름차순으로 정리함
        //System.out.println(Arrays.toString(cross));
        for(int i=1; i<=cross_size; i++) {
            if(is_ok(i))
                return i;
        }
        return -1;
    }

    public static boolean is_ok(int n) { // 1개 2개,, 이런거
        int[] pick = new int[]{-5,-15};
        //int[] pick = comb(n);

        for(int i=0; i<routes.length; i++) {
            if(!visited_routes[i]) {
                for(int p : pick) {
                    if(routes[i][0] <= p & routes[i][1] >= p) { // p가 범위에 들어간다면?
                        visited_routes[i] = true;
                        break;
                    }
                }
            }
        }

        for (boolean b:visited_routes){
            if(!b)
                return false;
        }
        return true;
        //int[] pick = comb(i);
    }

    public static void comb(int[] arr, boolean[] visited, int start, int n, int r) {
//        int results[][] = {};
        if(r == 0) {
            for(boolean b : visited) {
                if(b) {
                    comb_results[1][1];
                }
            }
            return;
        }

        for(int i=start; i<n; i++) {
            visited[i] = true;
            comb(arr, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }
}
