package done;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9576 {
    static int T,n,m;
    static int[][] student;
    static int[] book;  //book[i]에 매칭된 학생.

    static boolean[] visit; //매칭마다 배분 여부
    //student[i]를 책에 매칭 성공여부
    static boolean dfs(int i){
        if(visit[i]) return false;
        visit[i]=true;

        for (int j=student[i][0]; j<=student[i][1];j++){
            if(book[j]==-1 || (book[j]!=i && dfs(book[j]))){
                book[j]=i;
                return true;
            }
        }
        return false;
    }

    public void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        T= Integer.parseInt(br.readLine());
        while (T-->0){
            stringTokenizer =new StringTokenizer(br.readLine());
            n= Integer.parseInt(stringTokenizer.nextToken());
            m= Integer.parseInt(stringTokenizer.nextToken());

            book= new int[n];
            for (int i=0;i<n;i++)
                book[i]=-1;
            student= new int[m][2];
            for(int i=0;i<m;i++){
                stringTokenizer =new StringTokenizer(br.readLine());
                student[i][0]= Integer.parseInt(stringTokenizer.nextToken())-1;
                student[i][1]= Integer.parseInt(stringTokenizer.nextToken())-1;
            }

            int cnt=0;
            for(int i=0;i<m;i++)
            {
                visit= new boolean[m];
                dfs(i);
            }
            for(int i=0;i<n;i++)
                cnt+=book[i]!=-1?1:0;
            System.out.println(cnt);
        }
    }
    /*
    pq 사용하는 경우: o[1], o[0]순 정령 - 배분할 는 가장 작은 번호부터 분배
    1
    6 6
    1 5
    1 2
    2 3
    3 4
    4 5
    5 6
    5
    * */
}
