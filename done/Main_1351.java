package done;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_1351 {
    static Map<Long, Long> dp =new HashMap<>();
    static long n, p, q;
    static long go(long i){
        if(i==0) return 1;
        if(dp.containsKey(i)) return dp.get(i);
        dp.put(i, go(i/p)+go(i/q));
        return dp.get(i);
    }
    public static void main() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        n= Long.parseLong(stringTokenizer.nextToken());
        p= Long.parseLong(stringTokenizer.nextToken());
        q = Long.parseLong(stringTokenizer.nextToken());
        System.out.println(go(n));
    }
}
