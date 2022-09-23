package done;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main_1058 {

    public void main(String[] args) throws IOException {
        int n;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n= Integer.parseInt(br.readLine());
        Set<Integer>[] friends=new HashSet[n];
        Set<Integer>[] totalFriends=new HashSet[n];
        for(int i=0;i<n;i++){
            friends[i]=new HashSet<>();
            totalFriends[i]= new HashSet<>();
        }

        for(int i=0;i<n;i++){
            char[] row= br.readLine().toCharArray();
            for(int j=0;j<n;j++)
            {
                if(row[j]=='Y') {
                    friends[i].add(j);
                    totalFriends[i].add(j);
                }
            }
        }

        for(int i=0;i<n;i++){
           for(int friend : friends[i]){
               totalFriends[friend].addAll(friends[i]);
           }
        }

        int max=0;
        for(int i=0;i<n;i++){
            max= Math.max(max, totalFriends[i].size()-1);
        }

        System.out.println(max);
    }
}
