package retry;

import java.util.Scanner;

public class Solution_2482 {

    static int mod =1000000003;
    public void solution1(int n, int k){
        //n 개중 k를 칠하는 방법의 수
        int[][] dp = new int[n+1][k+1];
        for (int i=0;i<=n;i++){
            dp[i][1]=i; //i개중 1개를 칠하는 방법의 수
            dp[i][0]=1; //i개중 0개를 칠하는 방법의 수
        }

        //0,1개 채우는 방법은 이미 채웠으므로 다시 누적하면 안 됨
        for(int i=2;i<=n;i++){
            for (int j=2;j<=k;j++){
                //i를 칠하는 경우 : i-2까지 j-1개 색칠
                if(i-2>=0)
                    dp[i][j]=(dp[i][j]+dp[i-2][j-1])%mod;
                //i를 칠하지 않는 경우
                if(i-1>=0)
                    dp[i][j]=(dp[i][j]+dp[i-1][j])%mod;
            }
        }
        //dp[n][k] != dp[n-2][k-1]+dp[n-1][k]
        //dp[n][k]  = dp[n-3][k-1]+dp[n-1][k]
        //1) n번을 칠하는 경우: dp[n-3][k-1], 1과 n을 제외한 2~n-2에서 k-1개
        //2) n번을 칠하지않는 경우: dp[n-1][k], 1~n-1에서 k개
        System.out.println((dp[n-3][k-1]+dp[n-1][k])%mod);
    }

    public void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n= sc.nextInt();
        int k= sc.nextInt();
        solution1(n,k);
    }
}
