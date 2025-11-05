package mvc_jdbc_test.view;

import mvc_jdbc_test.entity.Customer;
import java.util.Scanner;

public class UpdateCustomerView {

    private Scanner sc = new Scanner(System.in);

    public String getCustomerId() {
        System.out.println("\n--- [고객 정보 수정] ---");
        System.out.print("수정하실 고객의 아이디를 입력해주세요: ");
        return sc.nextLine();
    }

    public Customer inputUpdateInfo(Customer oldCustomer) {
        Customer newCustomer = new Customer();

        System.out.println("--- [새로운 정보 입력] ---");
        System.out.print("새 고객 이름 (기존: " + oldCustomer.getCustomername() + "): ");
        newCustomer.setCustomername(sc.nextLine()); // 이름

        int newAge;
        while (true) {
            System.out.print("새 나이 (기존: " + oldCustomer.getAge() + "): ");
            String ageInput = sc.nextLine();
            try {
                newAge = Integer.parseInt(ageInput);
                break;
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해주세요");
            }
        }
        newCustomer.setAge(newAge);

        System.out.print("새 등급 (기존: " + oldCustomer.getLevel() + "): ");
        newCustomer.setLevel(sc.nextLine());

        System.out.print("새 직업 (기존: " + oldCustomer.getJob() + "): ");
        newCustomer.setJob(sc.nextLine());

        int newReward;
        while (true) {
            System.out.print("새 적립금 (기존: " + oldCustomer.getReward() + "): ");
            String rewardInput = sc.nextLine();
            try {
                newReward = Integer.parseInt(rewardInput);
                break;
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해주세요");
            }
        }
        newCustomer.setReward(newReward);

        return newCustomer;
    }
}