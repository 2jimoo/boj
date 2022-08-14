package done;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1091 {
    static int n;
    static int[] s;
    static Map<Integer,Integer> numToPlayer;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n= Integer.parseInt(br.readLine());
        s=new int[n];
        numToPlayer= new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            numToPlayer.put(i,Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            s[i]=Integer.parseInt(st.nextToken());
        }
    }
    static public boolean check(int[] cur){
        for(int i=0;i<n;i++)
            //cur[i]를 갖게 되는 플레이어,cur[i]를 기져야하는 플레이어
            if(i%3!=numToPlayer.get(cur[i]))
                return false;
        return true;
    }
    public static void main(String[] args) throws IOException {
        input();
        Set<Integer> visit= new HashSet<>();
        int[] cur = new int[n];
        for(int i=0;i<n;i++) cur[i]=i;
        int cnt=0;

        while (!check(cur)){
            int state=0;
            for(int e: cur) state =state*50+e;
            if(!visit.add(state)){
                cnt=-1;
                break;
            }
            int[] next= new int[n];
            for(int i=0;i<n;i++)
                next[s[i]]=cur[i];
            cur=next;
            cnt++;
        }

        System.out.println(cnt);
    }
    //https://www.acmicpc.net/source/35269624
    //방법2: 카드를 값과 포인터를 가진 연결리스트로 연결, 원래 대로 돌아오거나 정답 상태가 되거나.
    public class Main {

        private static int N;
        private static int[] P,S,origin;
        private static Pair[] cards;
        private static boolean check;

        public static class Pair {
            int prev;
            int val;

            public Pair(int prev,int val) {
                this.prev = prev;
                this.val = val;
            }
        }

        public void main(String[] args) throws IOException {
            init();
            solve();
        }

        private static void solve() {
            int cnt = 0;

            while(check()&&cycle(cnt)) {
                cnt++;
                shuffle();
            }
            System.out.println(check?cnt:-1);
        }

        private static boolean cycle(int cnt) {
            // 사이클이 돌았다면
            check = false;

            if(cnt!=0) {
                for(int i=0;i<N;i++) {
                    if(origin[i]!=cards[i].val) {
                        check = true;
                        break;
                    }
                }
                return check;
            }

            return true;
        }

        private static void shuffle() {
            for(int i=0;i<N;i++) {
                int target = S[i];
                cards[i].val = cards[target].prev;
            }

            for(int i=0;i<N;i++) {
                cards[i].prev = cards[i].val;
            }

        }

        private static boolean check() {
            //아직 원하는 순서가 완성되지 않았으면
            for(int i=0;i<N;i++) {
                if(cards[i].val!=P[i]) return true;
            }
            check = true;
            return false;
        }

        private static void init() throws IOException {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N=Integer.parseInt(br.readLine());
            P = new int[N];
            S = new int[N];
            origin = new int[N];
            cards = new Pair[N];


            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++) {
                P[i] = Integer.parseInt(st.nextToken());
                cards[i] = new Pair(i%3,i%3);
                origin[i] = i%3;
            }

            st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++) {
                S[i] = Integer.parseInt(st.nextToken());
            }
        }

    }
}
