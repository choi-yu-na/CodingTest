package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HashTable {

    static final int HASH_SIZE=1000;
    static final int HASH_LEN=100*4;
    static final int hash_value = 17;

    static int[][] data = new int[HASH_SIZE][HASH_LEN]; // input으로 받는 문자열이 들어온 수를 저장하는 곳
    static int[] length = new int[HASH_SIZE]; // key 값 마다 들어온 수를 저장하는 곳
    static String[][] s_data = new String[HASH_SIZE][HASH_LEN]; // input으로 받은 문자열을 저장하는 곳
    static String str;//각각의 문자열들
    static int N;

    public static void main(String[] args) throws IOException {
        //문자열 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            str = br.readLine();

            int key = makeHashKey(str);
            int cnt = checking(key);

            if (cnt!=-1) {
                sb.append(str).append(cnt).append("\n");
            }
            else {
                sb.append("OK").append("\n");
            }
            System.out.println(sb.toString());

        }
        //각 문자열 마다 HashKey 값 생성하기

        //HashKey 값 생성했는데.. 충돌이 일어났을 경우 처리해주기
        //충돌이 일어났으면
        //다음 인덱스..
        //안일어났으면 그냥 진행
    }

    public static int makeHashKey(String str) {
        int key = 0;
        for (int i = 0; i < str.length(); i++) {
            key = (key+hash_value) * str.charAt(i);
        }
        if(key<0) key=-key;
        return key%HASH_SIZE; //이렇게 하면 최대가 HASH_SIZE 가 되는거지
    }

    public static int checking (int key) {
        int len = length[key]; // 현재 string 의 hash key 값을 구해서.. hash key에.. 담긴 수..
        // 근데 이게 충돌때매.. 다른 string이여도 같은 hash key값이 나올수도 있당??
        if(len!=0) {//충돌이 났든 안났든.... 해당하는 key가 안들어왔으면
            for (int i = 0; i < len; i++) {

                if (str.equals(s_data[key][i])) {//[참고] ctrl+shifh+i 하면.. 변수 주석 볼수있음
                    //str:각각의 문자열들
                    //
                    data[key][i]++;
                    return data[key][i];
                }
            }
        }
        //근데 나 이 문자열 받은적 없는데 어떻게 우연하게 hash key값이 겹치게 나왔어.. 그러면?
        s_data[key][length[key]++] = str;
        return -1;
    }
}
