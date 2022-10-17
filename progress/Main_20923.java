package progress;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_20923 {
    //https://www.acmicpc.net/problem/20923
    public static void main(String[] args) throws IOException {
        int n, m;
        Deque<Integer> doDump = new LinkedList<>(), suDump = new LinkedList<>();
        Deque<Integer> doGround = new LinkedList<>(), suGround = new LinkedList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        stringTokenizer = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());

        int a, b;
        while (n-- > 0) {
            stringTokenizer = new StringTokenizer(br.readLine());
            a = Integer.parseInt(stringTokenizer.nextToken());
            b = Integer.parseInt(stringTokenizer.nextToken());
            doDump.addFirst(a);
            suDump.addFirst(b);
        }

        while (!doDump.isEmpty() && !suDump.isEmpty()) {
            //도도부터 카드 맨 위(first)를 뽑는다. 뽑는 즉시 dump가 0이 되도 진다.
            int doCard = doDump.pollFirst();
            if(doDump.isEmpty()) break;
            doGround.addFirst(doCard);
            play(doDump,suDump,doGround,suGround);
            if(--m==0) break;

            //수연이 카드 맨 위(first)를 뽑는다. 도도가 종을 쳐도 도도부터 X
            int suCard = suDump.pollFirst();
            if(suDump.isEmpty()) break;
            suGround.addFirst(suCard);
            play(doDump,suDump,doGround,suGround);
            if(--m==0) break;
        }

        String answer;
        if (doDump.size() == suDump.size()) {
            answer = "dosu";
        } else {
            answer = doDump.size() > suDump.size() ? "do" : "su";
        }
        System.out.println(answer);
    }

    public static void play(Deque<Integer> doDump, Deque<Integer> suDump, Deque<Integer> doGround, Deque<Integer> suGround){
        // 상대 덱을 뒤집어 (top=last) 내 덱의 아래(last)에 넣는다.
        if ((!doGround.isEmpty() &&doGround.peekFirst() == 5)||(!suGround.isEmpty() && suGround.peekFirst() == 5)) {
            // 카드가 5이면 도도가 가져간다.
            while (!suGround.isEmpty()) doDump.addLast(suGround.pollLast());
            while (!doGround.isEmpty()) doDump.addLast(doGround.pollLast());
        } else if (!doGround.isEmpty() && !suGround.isEmpty() && doGround.peekFirst() + suGround.peekFirst() == 5) {
            // 둘의 그라운드가 비어있지않고 그라운드 맨위의 카드 합이 5이면 수연이 가져간다.
            while (!doGround.isEmpty()) suDump.addLast(doGround.pollLast());
            while (!suGround.isEmpty()) suDump.addLast(suGround.pollLast());
        }
    }
}
