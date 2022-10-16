package progress;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1525 {
    //https://www.acmicpc.net/problem/1525
    //https://www.secmem.org/blog/2020/04/19/astar/
    //OPEN은 앞으로 탐색해야 할 노드들이 담긴 priority queue, CLOSED는 탐색이 완료된 노드들이 담긴 set입니다.
    //초기에는 OPEN에 시작점만이 포함된 상태로 시작합니다.
    //이후 OPEN에서 f값이 가장 작은 노드를 꺼낸 뒤, 해당 노드의 이웃들을 탐색합니다.
    //노드들이 이미 CLOSED나 OPEN에 포함되어 있고 그 g값이 (현재 노드의 g값 + 이동하는 데의 cost)보다 작다면 더 좋은 상태가 있는 것이므로 무시합니다.
    //그 외의 경우에는 해당 노드를 OPEN에 추가하여준 뒤, g값을 업데이트해줍니다.

    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static Map<Integer, Info> map = new HashMap<>();
    static PriorityQueue<Info> pq = new PriorityQueue<>((o1, o2) -> -Integer.compare(o1.f, o2.f));

    public static int hash(int[][] board) {
        int state = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                state = (state << 3) + board[i][j];
            }
        }
        return state;
    }

    public static int heuristic(int[][] board) {
        int ret = 0;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (board[i][j] != 0) {
                    int t = board[i][j] - 1;
                    int ii = t / 4;
                    int jj = t % 4;
                    int dist = Math.abs(ii - i) + Math.abs(jj - j);
                    ret += dist * dist;
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        Info start = new Info();
        int target = hash(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        for (int i = 0; i < 3; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                start.board[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
        start.g = 0;
        start.h = heuristic(start.board);
        start.f = start.g + start.h;
        start.h = hash(start.board);
        map.put(start.h, start);
        pq.add(start);

        int answer = -1;
        while (!pq.isEmpty() && answer == -1) {
            Info cur = pq.poll();
            if (map.get(cur.hash).f < cur.f) continue;
            if (cur.hash == target) {
                answer = cur.g;
                break;
            }

            int zeroX = -1, zeroY = -1;
            for (int i = 0; i < 3 && zeroY == -1; i++) {
                for (int j = 0; j < 3 && zeroX == -1; j++) {
                    if (cur.board[i][j] == 0) {
                        zeroY = i;
                        zeroX = j;
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                int newZeroY = dir[i][0] + zeroY, newZeroX = dir[i][1] + zeroX;
                if (newZeroY < 0 || newZeroY >= 3 || newZeroX < 0 || newZeroX >= 3) continue;

                Info next = new Info();
                //객체 아니라 primitive 참조라서 deep copy 되나...?
                next.board = cur.board;
                next.board[zeroY][zeroX] = next.board[newZeroY][newZeroX];
                next.board[newZeroY][newZeroX] = 0;

                next.h = heuristic(next.board);
                next.g = cur.g + 1;
                next.f = next.g + next.h;

                next.hash = hash(next.board);

                if (next.hash == target) {
                    answer = next.g;
                    break;
                }
                if (!map.containsKey(next.hash) || map.get(next.hash).f > next.f) {
                    map.put(next.hash, next);
                    pq.add(next);
                }
            }
        }

        System.out.println(answer);
    }

    static class Info {
        int f, g, h;
        int hash;
        int[][] board;
    }
}
