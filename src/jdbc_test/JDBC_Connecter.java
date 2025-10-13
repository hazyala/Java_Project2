package jdbc_test;

import java.sql.*;

public class JDBC_Connecter {
    private static final String DRIVER_PATH = "oracle.jdbc.driver.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521/xe";
    private static final String USER_NAME = "c##hazyala";
    private static final String PASSWORD = "1234";

    private static Connection con;

    public static Connection getConnection() {

        try{
            //1. JDBC Driver Memory Loding
            Class.forName(DRIVER_PATH);
            System.out.println("JDBC Driver Loaded");
            //2. Connection
            con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver Not Found");
        } catch (SQLException e) {
            System.out.println("Connection Error");
        }
        return con;
    }

    public static void resultSetTest(){

        try{
             //3. SQL 문을 실행할 수 있는 Statement 객체 생성
            String sql="select id, name, publish_name, author from book, publish where book.publish_id=publish.publish_id";
            PreparedStatement pstmt= con.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery();
            while (rs.next()){
                System.out.print(rs.getString("name") + "||");
                System.out.print(rs.getString("publish_name"));
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Connection con = getConnection();
        resultSetTest();
    }
}
