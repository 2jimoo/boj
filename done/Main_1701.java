package done;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1701 {

    public static int fail(char[] p) {
        int ans = 0;
        int[] pi = new int[p.length];
        //모든 위치에서 나 자신을 제외한 접두사=접미사인 곳을 찾는다. qbqtzqqt ->2인데 1나옴
        //max(pi[i]).
        for (int i = 0; i < p.length; i++) {
            //j: 시작점, k: 시작점으로부터의 길이
            for (int j = i + 1, k = 0; j < p.length; j++) {
                //불일치 시 일치할 때 까지 마지막 탐색 후보로 이동
                while (k > 0 && p[i + k] != p[j]) k = pi[k - 1];
                if (p[i + k] == p[j]) {
                    //다음 탐색 후보
                    pi[j - i] = ++k;
                    ans = Math.max(ans, k);
                }
            }
        }
        return ans;
    }

    public void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String p = br.readLine();
//        int max=0;
//        for(int i=0;i<p.length();i++){
//            max= Math.max(max,fail(p.substring(i).toCharArray()));
//        }
        System.out.println(fail(p.toCharArray()));
    }

//     for(int i = 0; t[i] != '\0'; i++){
//        int k = 0;
//        for(int j = i + 1; t[j] != '\0'; j++){
//            while(k > 0 && t[j] != t[i + k]) k = pi[k - 1];
//            if(t[j] == t[i + k]){
//                k++;
//                if(ans < k)ans = k;
//            }능 재활용가
//        }
//    }
}
