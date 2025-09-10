package w0910;

public class ParametaReturnTest2 {
    public static void main(String[] args) {
        Calc1 calc1 = new Calc1();
        int result = calc1.plus(100, 200, 300);
        System.out.println("세개 정수의 합계: " + result);
    }
}
