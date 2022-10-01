package done;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_11000 {
    public void main(String[] args) throws IOException {
        //빨리 끝나는 수업 반환
        PriorityQueue<int[]> pq= new PriorityQueue<>(Comparator.comparingInt((int[] x) -> x[1]));
        ArrayList<int[]> lessons = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n= Integer.parseInt(br.readLine());

        //시작시간 순 수업 정렬
        StringTokenizer stringTokenizer;
        int s, t;
        for(int i=0;i<n;i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            s= Integer.parseInt(stringTokenizer.nextToken());
            t= Integer.parseInt(stringTokenizer.nextToken());
            lessons.add(new int[]{s,t});
        }
        lessons.sort((int[] a, int[] b)-> a[0]!=b[0]? Integer.compare(a[0],b[0]): Integer.compare(a[1],b[1]));

        //진행 중 최대 큐 크기
        int max=0;
        for(int[] lesson: lessons){
            if(!pq.isEmpty()){
                if(pq.peek()[1]<=lesson[0]) pq.poll();
            }
            pq.add(lesson);
            max= Math.max(max, pq.size());
        }
        System.out.println(max);
    }
}
//최대 겹침X 끝나자마자 바로시작, 시작하자마자 끝 가능해서.
//4
//2 2
//2 2
//2 2
//2 2
//정답:1 출력:0