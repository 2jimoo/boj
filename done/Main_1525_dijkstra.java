package done;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1525_dijkstra {
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static Map<Integer, Node> map = new HashMap<>();
    static PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));

    public static int hash(int[][] board) {
        int state = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                state = state * 9 + board[i][j];
            }
        }
        return state;
    }

    public void main(String[] args) throws IOException {
        Node start = new Node();
        int target = hash(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        for (int i = 0; i < 3; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                start.board[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
        start.cost = 0;
        start.hash = hash(start.board);
        map.put(start.hash, start);
        pq.add(start);

        int answer = -1;
        while (!pq.isEmpty() && answer == -1) {
            Node cur = pq.poll();
            if (map.containsKey(cur.hash) && map.get(cur.hash).cost < cur.cost) continue;
            if (cur.hash == target) {
                answer = cur.cost;
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

                Node next = new Node();
                for (int r = 0; r < 3; r++)
                    System.arraycopy(cur.board[r], 0, next.board[r], 0, 3);
                next.board[zeroY][zeroX] = next.board[newZeroY][newZeroX];
                next.board[newZeroY][newZeroX] = 0;

                next.cost = cur.cost + 1;
                next.hash = hash(next.board);

                if (next.hash == target) {
                    answer = next.cost;
                    break;
                }
                if (!map.containsKey(next.hash) || map.get(next.hash).cost > next.cost) {
                    map.put(next.hash, next);
                    pq.add(next);
                }
            }
        }

        System.out.println(answer);
    }

    static class Node {
        int cost, hash;
        int[][] board = new int[3][3];
    }
}
