package retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1091 {
    private static int N;
    private static int[] P, S, origin;
    private static Pair[] cards;
    private static boolean check;

    public static void solves(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        int cnt = 0;

        while (check() && cycle(cnt)) {
            cnt++;
            shuffle();
        }
        System.out.println(check ? cnt : -1);
    }

    private static boolean cycle(int cnt) {
        // 사이클이 돌았다면
        check = false;

        if (cnt != 0) {
            for (int i = 0; i < N; i++) {
                if (origin[i] != cards[i].val) {
                    check = true;
                    break;
                }
            }
            return check;
        }

        return true;
    }

    private static void shuffle() {
        for (int i = 0; i < N; i++) {
            int target = S[i];
            cards[i].val = cards[target].prev;
        }

        for (int i = 0; i < N; i++) {
            cards[i].prev = cards[i].val;
        }

    }

    private static boolean check() {
        //아직 원하는 순서가 완성되지 않았으면
        for (int i = 0; i < N; i++) {
            if (cards[i].val != P[i]) return true;
        }
        check = true;
        return false;
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        P = new int[N];
        S = new int[N];
        origin = new int[N];
        cards = new Pair[N];


        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            P[i] = Integer.parseInt(st.nextToken());
            cards[i] = new Pair(i % 3, i % 3);
            origin[i] = i % 3;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static class Pair {
        int prev;
        int val;

        public Pair(int prev, int val) {
            this.prev = prev;
            this.val = val;
        }
    }
}
