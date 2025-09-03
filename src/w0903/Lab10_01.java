package w0903;

import javax.imageio.stream.ImageInputStream;
import java.util.Scanner;

public class Lab10_01 {
    static int calc(int v1, int v2, char op) {
        int result;
        switch (op) {
            case '+':
                result = v1 + v2;
                break;
            case '-':
                result = v1 - v2;
                break;
            case '*':
                result = v1 * v2;
                break;
            case '/':
                result = v1 / v2;
                break;
            default:
                result = 0;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int res;
        char oper;
        int num1, num2;

        System.out.printf("계산 입력(+,-,*,/) : ");
        oper = s.next().charAt(0);

        System.out.printf("첫 번째 숫자 입력 : ");
        num1 = s.nextInt();
        System.out.printf("두 번째 숫자 입력 : ");
        num2 = s.nextInt();

        res = calc(num1, num2, oper);
        System.out.printf("%d %c %d = %d\n", num1, oper, num2, res);

        s.close();
    }
}
