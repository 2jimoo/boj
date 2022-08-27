package done;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2240 {
    static int T, W;
    static int[] tree;
    static int[][][] dp;
    static int[][] bp;

    public static void main() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        tree = new int[T + 1];
        bp = new int[T + 1][W + 1];

        for (int i = 1; i <= T; i++) {
            tree[i] = Integer.parseInt(br.readLine());
        }

        if (tree[1] == 1) {
            bp[1][0] = 1;
        } else {
            bp[1][1] = 1;
        }

        for (int i = 2; i <= T; i++) {
            for (int j = 0; j <= W; j++) {
                if (j == 0) {
                    bp[i][0] = tree[i] == 1 ? bp[i - 1][0] + 1 : bp[i - 1][0];
                } else {
                    if (j % 2 == 0) {
                        //짝수번 이동했으면 1번
                        bp[i][j] = tree[i] == 1 ? Math.max(bp[i - 1][j - 1], bp[i - 1][j]) + 1 : bp[i - 1][j];
                    } else {
                        //홀수번 이동 2번
                        bp[i][j] = tree[i] == 2 ? Math.max(bp[i - 1][j - 1], bp[i - 1][j]) + 1 : bp[i - 1][j];
                    }
                }
            }
        }
        int answer = 0;
        for (int i = 0; i <= W; i++) {
            answer = Math.max(answer, bp[T][i]);
        }
        System.out.println(answer);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        tree = new int[T];
        dp = new int[T][2][W + 1];

        for (int i = 0; i < T; i++) {
            tree[i] = Integer.parseInt(br.readLine()) - 1;
            for (int j = 0; j <= W; j++) {
                dp[i][0][j] = -1;
                dp[i][1][j] = -1;
            }
        }
    }

    static void solve() throws IOException {
        init();
        int go1 = go(0, 0, 0);
        int go2 = go(0, 1, 1);
        System.out.println(Math.max(go1, go2));
    }

    static int go(int t, int pos, int w) {
        if (t == T) return 0;
        if (dp[t][pos][w] != -1)
            return dp[t][pos][w];

        dp[t][pos][w] = (tree[t] == pos ? 1 : 0);
        int max = go(t + 1, pos, w);
        if (w + 1 <= W) {
            max = Math.max(go(t + 1, 1 - pos, w + 1), max);
        }
        dp[t][pos][w] += max;

        return dp[t][pos][w];
    }

}
