package progress;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_16928 {
    public static void main(String[] args) throws IOException {
        int[] adj= new int[101];
        Queue<int[]> q= new LinkedList<>();
        boolean[] visit=new boolean[101];
        int n,m;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer= new StringTokenizer(br.readLine());
        n= Integer.parseInt(stringTokenizer.nextToken());
        m= Integer.parseInt(stringTokenizer.nextToken());
        for(int i=0;i<n+m;i++)
        {
            stringTokenizer= new StringTokenizer(br.readLine());
            int from= Integer.parseInt(stringTokenizer.nextToken());
            int to= Integer.parseInt(stringTokenizer.nextToken());
            adj[from]=to;
        }

        //사다리, 뱀은 주사위 굴리는 것 아님, 사다리, 뱀은 도착 즉시 이동
        q.add(new int[]{1,0});
        visit[1]=true;
        while (!q.isEmpty()){
            int[] cur= q.poll();

            for (int i = 1; i <= 6;i++){
                int nx = cur[0] + i;
                if(nx == 100){
                    System.out.println(cur[1]+1);
                    return;
                }
                else if(nx < 100){
                    while(adj[nx] != 0){
                        nx = adj[nx];
                    }
                    if(!visit[nx]){
                        q.add(new int[]{nx, cur[1] + 1});
                        visit[nx] = true;
                    }
                }
            }
        }
    }
}
