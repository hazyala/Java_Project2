package mvc_jdbc_test.view;

import mvc_jdbc_test.entity.Customer;
import java.util.Scanner;

public class DeleteCustomerView {

    private Scanner sc = new Scanner(System.in);

    public String getCustomerId() {
        System.out.println("\n--- [고객 정보 삭제] ---");
        System.out.print("삭제할 고객의 아이디를 입력해주세요: ");
        return sc.nextLine();
    }

    public boolean confirmDeletion() {
        System.out.print("정말로 위 고객의 정보를 삭제하시겠습니까? (Y/N) : ");
        String confirm = sc.nextLine();
        return confirm.equalsIgnoreCase("Y");
    }

    public String askForAdminPassword() {
        System.out.println("\n주문내역까지 삭제를 원하시면 비밀번호를 입력해주세요.(비밀번호 : 0000)");
        System.out.print("비밀번호 입력: ");
        return sc.nextLine();
    }

    public void printWrongPasswordMessage() {
        System.out.println("비밀번호가 일치하지 않습니다. 삭제가 취소되었습니다.");
    }

    public void printCancelMessage() {
        System.out.println("\n[취소] 삭제 작업을 취소했습니다.");
    }
}