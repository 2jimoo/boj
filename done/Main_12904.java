package done;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_12904 {

    public void main() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        while (T.length()>S.length()){
            char ch = T.charAt(T.length()-1);
            T= T.substring(0,T.length()-1);
            if(ch=='B'){
                T = new StringBuffer(T).reverse().toString();
            }
        }

        System.out.println(T.equals(S)?1:0);
    }
}
