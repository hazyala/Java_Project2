package w0820;

public class ArrayTest2 {
    public static void main(String[] args) {
        int[][] arr1 = new int[2][3];
        int cnt = 1;
//초기화
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[i].length; j++) {
                arr1[i][j] = cnt++;
            }
        }
// 출력
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[i].length; j++) {
                System.out.println(arr1[i][j] + "\t");
            }
            System.out.println();
        }
// 다른 방법
        int[][] arr2 = {{1, 3, 5}, {7, 8, 9 }};
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < arr2[i].length; j++) {
                System.out.println(arr2[i][j] + "\t");
            }
            System.out.println();
        }

    }
}
