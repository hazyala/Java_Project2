package mvc_jdbc_test.controller;

import jdbc_test.JDBC_Connecter;

import mvc_jdbc_test.entity.Customer;
import mvc_jdbc_test.view.CustomerView;

import mvc_jdbc_test.entity.Order;
import mvc_jdbc_test.view.OrdersView;
import mvc_jdbc_test.view.inputCustomerInfoView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainController {
    public static void main(String[] args) {
        Connection con= JDBC_Connecter.getConnection();
       // customerListAndView(con);
       // orderListAndView(con);
        inputCustomerInfoProcess(con);
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
    // 교수닌은 InputAndView 로 되어있음. 나는 inputCustomerInfoProcess

    public static void inputCustomerInfoProcess (Connection con) {
        Scanner sc = new Scanner(System.in);
        inputCustomerInfoView inputView = new inputCustomerInfoView();
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
            System.out.println("계속 추가하시겠습니까? (C: 계속 / E: 종료) :");
            String input = sc.nextLine();
            if (input.equals("e")) {
                break;
            }
        }
        System.out.println("프로그램 종료");
    }

}
