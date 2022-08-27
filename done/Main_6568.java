package done;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_6568 {
    static int[] mem = new int[32];
    static int pc = 0, acc = 0, mask5 = ((1 << 5) - 1), mask8 = ((1 << 8) - 1);
    static StringBuilder stringBuilder;

    public static void round() {
        //초기화 하기, 테스트 케이스 여러 개인 경우
        boolean proceed = true;
        pc = 0;
        acc = 0;
        while (proceed) {
            int cmd = (mem[pc] >> 5);
            int operand = (mem[pc] & mask5);
            pc = ((pc + 1) & mask5);

            switch (cmd) {
                case 0: //000
                    mem[operand] = acc;
                    break;
                case 1: //001
                    acc = mem[operand];
                    break;
                case 2: //010
                    pc = (acc == 0) ? operand : pc;
                    break;
                case 3: //011
                    break;
                case 4: //100
                    acc = (acc + mask8) & mask8;
                    break;
                case 5: //101
                    acc = (acc + 1) & mask8;
                    break;
                case 6: //110
                    pc = operand;
                    break;
                case 7: //111
                    proceed = false;
                    break;
            }
        }

        //8자리 출력 맞추기
        stringBuilder = new StringBuilder();
        for (int i = 7; i >= 0; i--) {
            stringBuilder.append((acc >> i) & 1);
        }
        System.out.println(stringBuilder.toString());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String m;
        while (true) {
            for (int i = 0; i < 32; i++) {
                // EOF : 빋을 입력이 없음 의미
                // 누산기, 메모리, PC의 동작 이해
                // 명령어: 어디의 값과 어디의 값을 어떻게 해서 어디에 저장하라는 비트 패턴
                // 음수 만들기
                if ((m = br.readLine()) == null) {
                    return;
                }
                mem[i] = Integer.valueOf(m, 2);
            }
            round();
        }
    }
}
