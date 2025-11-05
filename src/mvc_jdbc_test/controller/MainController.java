package mvc_jdbc_test.controller;

import jdbc_test.JDBC_Connecter;

import mvc_jdbc_test.entity.Customer;
import mvc_jdbc_test.entity.Order;

import mvc_jdbc_test.view.CustomerView;
import mvc_jdbc_test.view.OrdersView;
import mvc_jdbc_test.view.InputCustomerView;
import mvc_jdbc_test.view.UpdateCustomerView;
import mvc_jdbc_test.view.DeleteCustomerView;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainController {
    public static void main(String[] args) {
        Connection con = JDBC_Connecter.getConnection();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n======= 고객 관리 프로그램 =======");
            System.out.println("0. 종료하기");
            System.out.println("1. 고객 목록 보기");
            System.out.println("2. 주문 목록 보기");
            System.out.println("3. 고객 정보 추가");
            System.out.println("4. 고객 정보 수정");
            System.out.println("5. 고객 정보 삭제");
            System.out.println("=========================================");
            System.out.print("원하는 작업의 번호를 입력해주세요 : ");

            int choice;
            try {
                String input = sc.nextLine();
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해주세요");
                continue;
            }

            switch (choice) {
                case 0:
                    System.out.println("\n프로그램을 종료합니다.");
                    try {
                        if (con != null) con.close();
                    } catch (SQLException e) {
                        System.out.println("오류 발생");
                    }
                    sc.close();
                    return;
                case 1:
                    System.out.println("\n [1. 고객 목록 보기]");
                    customerListAndView(con);
                    break;
                case 2:
                    System.out.println("\n[2. 주문 목록 보기]");
                    orderListAndView(con);
                    break;
                case 3:
                    System.out.println("\n[3. 고객 정보 추가 ]");
                    inputCustomerProcess(con);
                    break;
                case 4:
                    System.out.println("\n[4. 고객 정보 수정]");
                    updateCustomerProcess(con);
                    break;
                case 5:
                    System.out.println("\n[5. 고객 정보 삭제]");
                    deleteCustomerProcess(con);
                    break;
                default:
                    System.out.println("숫자만 입력해주세요");
            }
        }
    }

    public static void customerListAndView(Connection con){
        String sql = "select * from 고객";
        ArrayList<Customer> customerList = new ArrayList<Customer>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            Customer customer = null;

            while(rs.next()){
                customer = new Customer();
                customer.setCustomerid(rs.getString("고객아이디"));
                customer.setCustomername(rs.getString("고객이름"));
                customer.setAge(rs.getInt("나이"));
                customer.setLevel(rs.getString("등급"));
                customer.setJob(rs.getString("직업"));
                customer.setReward(rs.getInt("적립금"));
                customerList.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("Connection Error");
        }

        CustomerView customerView = new CustomerView();
        customerView.printHead();
        for (Customer customer : customerList) {
            customerView.printCustomer(customer);
        }
        customerView.printFooter();
    }

    public static void orderListAndView(Connection con) {
        ArrayList<Order> orderList = new ArrayList<Order>();
        String sql = "select 주문번호, 고객이름, 고객아이디, 배송지, 수량, 주문일자, 제품명  from 주문, 고객, 제품  where 주문.주문고객=고객.고객아이디 and 주문.주문제품=제품.제품번호";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Order order = null;
            while (rs.next()) {
                order = new Order();
                order.setOrderNum(rs.getString("주문번호"));
                order.setCustomername(rs.getString("고객이름"));
                order.setCustomerid(rs.getString("고객아이디"));
                order.setShippingAddress(rs.getString("배송지"));
                order.setQuantity(rs.getInt("수량"));
                order.setShippingDate(rs.getDate("주문일자"));
                order.setProductname(rs.getString("제품명"));
                orderList.add(order);
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        OrdersView.printHead();
        for (Order order : orderList) {
            OrdersView.printOrders(order);
        }

    }

    // 고객정보 입력 및 입력 내용 출력
    // 고객정보 DB의 고객 테이블에 고객 Entity 추가
    // 교수님은 InputAndView 로 되어있음. 나는 inputCustomerProcess

    public static void inputCustomerProcess(Connection con) {
        Scanner sc = new Scanner(System.in);
        InputCustomerView inputView = new InputCustomerView();
        while (true){
            Customer customer = inputView.inputCustomerInfo();
            CustomerView customerView = new CustomerView();
            customerView.printHead();
            customerView.printCustomerInfo(customer);
            customerView.printFooter();

            String sql = "INSERT INTO 고객 (고객아이디, 고객이름, 나이, 등급, 직업, 적립금) VALUES (?, ?, ?, ?, ?, ?)";
            try{
                PreparedStatement pstmt = con.prepareStatement(sql);

                pstmt.setString(1, customer.getCustomerid());
                pstmt.setString(2, customer.getCustomername());
                pstmt.setInt(3, customer.getAge());
                pstmt.setString(4, customer.getLevel());
                pstmt.setString(5, customer.getJob());
                pstmt.setInt(6, customer.getReward());
                pstmt.executeUpdate();
                pstmt.close();
            }
            catch (SQLException e){
                System.out.println("Connection Error");
            }
            System.out.println("계속 추가하시겠습니까? (Y: 계속 / N: 종료) :");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("n")) {
                break;
            }
        }
        System.out.println("고객 정보 추가 종료");
    }


    //===================================== 중간 과제 ============================================
    // 고객 정보 수정
    public static void updateCustomerProcess(Connection con) {
        CustomerView customerView = new CustomerView();
        UpdateCustomerView updateView = new UpdateCustomerView();

        Scanner sc = new Scanner(System.in);

        while (true) {
            // 1. 고객 ID 검색
            String customerId = updateView.getCustomerId();

            // 2. 고객 정보 검색
            Customer customer = findCustomerById(con, customerId);

            // 고객이 없는 경우
            if (customer == null) {
                System.out.println("[" + customerId + "] 에 해당하는 고객 정보를 찾을 수 없습니다.");

                System.out.println("\n계속 수정하시겠습니까? (Y: 계속 / N: 종료) :");
                String input = sc.nextLine();
                if (input.equalsIgnoreCase("n")) {
                    break;
                }
                continue;
            }

            // 3. 기존 고객 정보 확인
            System.out.println("\n[기존 고객 정보 확인]");
            customerView.printCustomerInfo(customer);

            // 4. 새로운 고객 정보 입력
            Customer newCustomerData = updateView.inputUpdateInfo(customer);

            // 5. DB 업데이트
            String sql = "UPDATE 고객 SET 고객이름 = ?, 나이 = ?, 등급 = ?, 직업 = ?, 적립금 = ? WHERE 고객아이디 = ?";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {

                pstmt.setString(1, newCustomerData.getCustomername());
                pstmt.setInt(2, newCustomerData.getAge());
                pstmt.setString(3, newCustomerData.getLevel());
                pstmt.setString(4, newCustomerData.getJob());
                pstmt.setInt(5, newCustomerData.getReward());
                pstmt.setString(6, customerId);

                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("\n[성공] 고객 정보가 성공적으로 수정되었습니다.");

                    System.out.println("--- [수정된 고객 정보] ---");
                    Customer updatedCustomer = findCustomerById(con, customerId);
                    if (updatedCustomer != null) {
                        customerView.printCustomerInfo(updatedCustomer);
                    }
                } else {
                    System.out.println("[실패] 고객 정보 수정에 실패했습니다.");
                }
            } catch (SQLException e) {
                System.out.println("Connection Error");
            }

            System.out.println("\n계속 수정하시겠습니까? (Y: 계속 / N: 종료) :");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("n")) {
                break;
            }
        }
        System.out.println("고객 정보 수정 종료");
    }

    // 고객 정보 삭제
    public static void deleteCustomerProcess(Connection con) {
        CustomerView customerView = new CustomerView();
        DeleteCustomerView deleteView = new DeleteCustomerView();
        Scanner sc = new Scanner(System.in);

        while (true) {
            // 1. 고객 ID 검색
            String customerId = deleteView.getCustomerId();

            // 2. 고객 정보 검색
            Customer customer = findCustomerById(con, customerId);

            // 3. 고객이 없는 경우
            if (customer == null) {
                System.out.println("[" + customerId + "] 에 해당하는 고객 정보를 찾을 수 없습니다.");

                System.out.println("\n계속 삭제하시겠습니까? (Y: 계속 / N: 종료) :");
                String input = sc.nextLine();
                if (input.equalsIgnoreCase("n")) {
                    break;
                }
                continue;
            }

            // 4. 삭제 대상 고객 정보 확인
            System.out.println("\n[삭제 대상 고객 정보]");
            customerView.printCustomerInfo(customer);

            // 5. 삭제 여부 확인 (Y/N)
            if (deleteView.confirmDeletion()) {

                // 7. DB 업데이트
                String sql = "DELETE FROM 고객 WHERE 고객아이디 = ?";

                try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                    pstmt.setString(1, customerId);
                    int rowsAffected = pstmt.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("\n[성공] 고객 정보가 성공적으로 삭제되었습니다.");
                    } else {
                        System.out.println("[실패] 고객 정보 삭제에 실패했습니다.");
                    }

                } catch (SQLException e) {
                    // 9. '주문 내역' 존재시
                    if (e.getErrorCode() == 2292) {
                        System.out.println("\n[삭제 실패] 해당 고객은 '주문' 내역이 존재하여 삭제할 수 없습니다.");

                        //10. 관리자 비밀번호 확인
                        String password = deleteView.askForAdminPassword();
                        if (password.equals("0000")) {
                            System.out.println("\n[관리자 확인] 비밀번호가 확인되었습니다.");
                            if (deleteView.confirmDeletion()) {
                                DeleteCustomerAll(con, customerId);

                            } else {
                                deleteView.printCancelMessage();
                            }
                        } else {
                            deleteView.printWrongPasswordMessage();
                        }

                    } else {
                        System.out.println("\n[실패] Connection Error " + e.getMessage());
                    }
                }

            } else {
                deleteView.printCancelMessage();
            }

            System.out.println("\n계속 삭제하시겠습니까? (Y: 계속 / N: 종료) :");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("n")) {
                break;
            }
        }

        System.out.println("고객 정보 삭제 종료");
    }

    private static void DeleteCustomerAll(Connection con, String customerId) {
        try {
            // 1. 자동 저장 끄기
            con.setAutoCommit(false);

            // 2. 주문 내역 먼저 삭제
            String sqlOrders = "DELETE FROM 주문 WHERE 주문고객 = ?";
            try (PreparedStatement pstmtOrders = con.prepareStatement(sqlOrders)) {
                pstmtOrders.setString(1, customerId);
                pstmtOrders.executeUpdate();
            }

            // 3. 고객 정보 삭제
            String sqlCustomer = "DELETE FROM 고객 WHERE 고객아이디 = ?";
            try (PreparedStatement pstmtCustomer = con.prepareStatement(sqlCustomer)) {
                pstmtCustomer.setString(1, customerId);
                int customerRows = pstmtCustomer.executeUpdate();

                if (customerRows > 0) {
                    con.commit();
                    System.out.println("\n[관리자 성공] 고객 정보와 모든 주문 내역이 성공적으로 삭제되었습니다.");
                } else {
                    throw new SQLException("고객 정보 삭제에 실패했습니다.");
                }
            }
        } catch (SQLException e) {
            System.out.println("[실패] Connection Error. 모든 작업이 초기화됩니다.");
            try {
                con.rollback(); 
            } catch (SQLException ex) {
            }
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {}
        }
    }

    private static Customer findCustomerById(Connection con, String customerId) {
        String sql = "SELECT * FROM 고객 WHERE 고객아이디 = ?";
        Customer customer = null;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    customer = new Customer();
                    customer.setCustomerid(rs.getString("고객아이디"));
                    customer.setCustomername(rs.getString("고객이름"));
                    customer.setAge(rs.getInt("나이"));
                    customer.setLevel(rs.getString("등급"));
                    customer.setJob(rs.getString("직업"));
                    customer.setReward(rs.getInt("적립금"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Connection Error : " + e.getMessage());
        }
        return customer;
    }
}
