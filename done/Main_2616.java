package done;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2616 {

    public void main(String[] args) throws IOException {
        //입출력 속도
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n][4];
        int[] sum = new int[n + 1];
        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            sum[i + 1] = sum[i] + a[i];
        }
        int k = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= 3; j++) {
                if (i - 1 >= 0) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                //1~k개에 대해 모두 탐색해야할 필요X
                //"소형 기관차가 최대로 끌 수 있는 객차의 수는 기관차가 끌고 가던 객차 수의 1/3보다 적다"는 조건 때문에
                //N=5, Arr[]={1,1,1,1,1}, K=2 와 같은 경우 발생X -> 무조건 최대 개수씩 끌 수 있음. 최대 개수 끄는 경우만 확인
                if (i - k + 1 >= 0) {
                    int psum = sum[i + 1] - sum[i - k + 1];
                    dp[i][j] = Math.max(dp[i][j], (i - k < 0 ? 0 : dp[i - k][j - 1]) + psum);
                }

            }
        }

        System.out.println(dp[n - 1][3]);
    }
}
