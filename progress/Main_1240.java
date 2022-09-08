package progress;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


//1. 첫째 줄 입력은 N, M 으로 이뤄져 있다
//2. 노드 번호가 1부터 N 까지 이뤄져 있다
public class Main_1240 {
    static ArrayList<Edge>[] adj;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static int V;
    static int M;
    static boolean[] check;
    static int[][] dist;

    public static void init() throws IOException {
        stringTokenizer = new StringTokenizer(br.readLine());
        V = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        dist = new int[V + 1][V + 1];

        adj = new ArrayList[V+1];
        for (int i = 1; i <= V; i++)
            adj[i] = new ArrayList<>();
        for(int i=0; i< V-1; i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(stringTokenizer.nextToken());
            int v = Integer.parseInt(stringTokenizer.nextToken());
            int w = Integer.parseInt(stringTokenizer.nextToken());
            adj[u].add(new Edge(v,w));
            adj[v].add(new Edge(u,w));
        }

        for (int i = 0; i < V + 1; i++) {
            for (int j = 0; j < V + 1; j++)
                dist[i][j] = Integer.MAX_VALUE;
        }
    }

    public static void dijkstra(int start) {
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Edge(start,0));
        dist[start][start] = 0;
        check= new boolean[V+1];
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            int destination = edge.destination;
            if(check[destination])
                continue;
            else
                check[edge.destination] = true;
            for (Edge next : adj[destination]) {
                if (dist[start][next.destination] >= dist[start][destination] + next.weight) {
                    dist[start][next.destination] = dist[start][destination] + next.weight;
                    priorityQueue.add(new Edge(next.destination,dist[start][next.destination]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        for (int i = 1; i <= V; i++)
            dijkstra(i);
        int a, b;
        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            a = Integer.parseInt(stringTokenizer.nextToken());
            b = Integer.parseInt(stringTokenizer.nextToken());
            System.out.println(dist[a][b]);
        }
    }

    static class Edge implements Comparable<Edge> {
        int destination;
        int weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            // TODO Auto-generated method stub
            return Integer.compare(this.weight, o.weight);
        }
    }
}
