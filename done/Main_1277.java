package done;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1277 {
    static ArrayList<Edge>[] adj;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static int n, w;
    static double m;
    static boolean[] check;
    static double[] dist;

    public static void init() throws IOException {
        stringTokenizer = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        w = Integer.parseInt(stringTokenizer.nextToken());

        stringTokenizer = new StringTokenizer(br.readLine());
        m = Double.parseDouble(stringTokenizer.nextToken());

        dist = new double[n + 1];
        adj = new ArrayList[n + 1];
        int[][] coord = new int[n + 1][2];
        boolean[][] connected = new boolean[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE;
            adj[i] = new ArrayList<>();
            stringTokenizer = new StringTokenizer(br.readLine());
            coord[i][0] = Integer.parseInt(stringTokenizer.nextToken());
            coord[i][1] = Integer.parseInt(stringTokenizer.nextToken());
        }

        for (int i = 0; i < w; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            connected[a][b] = true;
            connected[b][a] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                double dist = connected[i][j] ? 0L :
                        Math.sqrt((long) (coord[i][0] - coord[j][0]) * (coord[i][0] - coord[j][0]) + (long) (coord[i][1] - coord[j][1]) * (coord[i][1] - coord[j][1]));
                adj[i].add(new Edge(j, dist));
                adj[j].add(new Edge(i, dist));
            }
        }
    }

    public static void dijkstra() {
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Edge(1, 0));
        dist[1] = 0;
        check = new boolean[n + 1];
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            int destination = edge.destination;
            if (check[destination])
                continue;

            check[edge.destination] = true;
            for (Edge next : adj[destination]) {
                if (next.weight < m && dist[next.destination] > dist[destination] + next.weight) {
                    dist[next.destination] = dist[destination] + next.weight;
                    priorityQueue.add(new Edge(next.destination, dist[next.destination]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        dijkstra();
        System.out.println((int) (dist[n] * 1000));
    }

    static class Edge implements Comparable<Edge> {
        int destination;
        double weight;

        public Edge(int destination, double weight) {
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.weight, o.weight);
        }
    }
}
