package done;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1013 {
    public void main(String[] args) throws IOException {
        int t;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String pattern = "(100+1+|01)+";

        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            if (br.readLine().matches(pattern))
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}
