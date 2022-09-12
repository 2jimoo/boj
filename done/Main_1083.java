package done;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1083 {

    //[i]: [i+1:min(i+s,n-1)]중 가장 큰 수를 가져온다
    static int n;
    static int s;
    static int[] a;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        a = new int[n];
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        s = Integer.parseInt(br.readLine());
    }

    public void main(String[] args) throws IOException {
        input();
        for (int i = 0; i < n && s > 0; i++) {
            int minIdx = Math.min(n - 1, i + s), max = a[i], maxIdx = i;
            for (int j = i + 1; j <= minIdx; j++) {
                if (max < a[j]) {
                    maxIdx = j;
                    max = a[j];
                }
            }
            if (maxIdx != i) {
                for (int j = maxIdx; j >= i + 1; j--) {
                    int tmp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = tmp;
                }
                s -= (maxIdx - i);
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
