package mvc_jdbc_test.view;

import mvc_jdbc_test.entity.Customer;

public class CustomerView {
    public String title = "고객 정보";
    public void printCustomer(Customer customer){
        System.out.printf("* 고객명 : %s\n", customer.getCustomername());
        System.out.printf("* 나이 : %s\n", customer.getAge());
        System.out.printf("* 등급 : %s\n", customer.getLevel());
        System.out.printf("* 직업 : %s\n", customer.getJob());
        System.out.printf("* 적립금 : %s\n", customer.getReward());
    }

    public void printHead(){
        System.out.println("=========================================");
        System.out.println("==============" + title + "===============");
        System.out.println("=========================================");
    }

    public void printFooter(){
        System.out.println("=========================================");
        System.out.println("============== Print Done ===============");
        System.out.println("=========================================\n");
    }

    public void printCustomerInfo(Customer customer) {
        System.out.println("=====================================");
        System.out.println(" 입력된 고객 정보 확인 ");
        System.out.println("=====================================");
        System.out.println("고객 ID    : " + customer.getCustomerid());
        System.out.println("고객 이름  : " + customer.getCustomername());
        System.out.println("고객 나이  : " + customer.getAge() + "세");
        System.out.println("고객 등급  : " + customer.getLevel());
        System.out.println("고객 직업  : " + customer.getJob());
        System.out.println("고객 적립금: " + customer.getReward() + "점");
        System.out.println("=====================================");
    }
}

