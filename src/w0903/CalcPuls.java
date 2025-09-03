package w0903;

public class CalcPuls {
    static int plus(int v1, int v2) {
        int result;
        result = v1 + v2;
        return result;
    }

    public static void main(String[] args) {
        int hap;
        hap = plus(100, 200);
        System.out.println("100과 200의 plus() 메서드 결과 : " + hap);
    }
}
