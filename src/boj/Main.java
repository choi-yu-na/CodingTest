package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[][] graph;
    static boolean[] visited;

    /* 아 이거는.... 그냥 이차원 배열로... 양방향 그래프인걸.. 나타내주는게 좋을듯*/

    //static int line_1;
    //static int line_2;
    /*야네도 이런식으로 변수명 하지말고.. 변수명을 좀 구체적으로 짓는 습관을..*/
    static int n; // num of computer
    static int p; // num of pair

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine()); //컴퓨터의 수
        p = Integer.parseInt(br.readLine()); //네트워크 상에서 직접 연결되어 있는 컴퓨터 쌍의 수

        graph = new int[n+1][n+1];
        visited = new boolean[n+1]; // 왜 +1을 해주냐면... 그래프의... 인덱스가 1부터 시작하니까
        // 한칸 더 해준거지..

        for(int i=0; i<p; i++) {
            String str = br.readLine();
            int v1 = Integer.parseInt(str.split(" ")[0]);
            int v2 = Integer.parseInt(str.split(" ")[1]);

            graph[v1][v2] = 1;
            graph[v2][v1] = 1;
//            graph.add(new Edge(v1, v2));
            //graph.add(new Edge(v1,v2));
        }

        System.out.println(bfs(1));
//        Arrays.sort(graph);
        /*이거 굳이 안해도...... 되는 코드인가본데??*/
    }
    public static int bfs(int start) {
        /*
        * //bfs의 param 뭐가 필요한가....? 필요없어 왜냐면... bfs는 while문 안에서
        //만 돌기때문에 .. bfs를 재귀로 안불러오거든 그래서 param이 필요가 없징
        //Q. bfs에 visited 필요함?? 아니지않아? .. -> 필요한거같은데..
        Queue<Edge> queue = new LinkedList<>(); // 이걸 bfs 안에서 해버린다면.. bfs 반복호출 할때마다... 초기화가 될텐데
        * */



        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.offer(start); //첫번째꺼 넣고
        int cnt = 0; //감염된 컴퓨터의 수
        while(!queue.isEmpty()) {
            int v1 = queue.poll(); // 현재꺼..

            for(int i=1; i< n; i++) { //graph의 길이만큼 돌려야하는 이유
                if(visited[i]==false && graph[v1][i]==1) {
                    queue.offer(i);
                    visited[i]=true;
                    cnt++;
                }
            }
        }

        return cnt;
    }

//    public static class Edge implements Comparable<Edge>{
//        int v1;
//        int v2;
//
//        public Edge(int v1, int v2) {
//            this.v1 = v1;
//            this.v2 = v2;
//        }
//
//        @Override
//        public int compareTo(Edge o) {
//            if(this.v1 == o.v1)
//                return this.v2-o.v2;
//            return this.v1-o.v1;
//        }
//    }
}