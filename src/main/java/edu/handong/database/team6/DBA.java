package edu.handong.database.team6;
import java.sql.*;

public class DBA {

        static Connection con = null;
        static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        static final String timeZone = "?serverTimezone=UTC&useSSL=false";

        //static String server = "localhost"; // MySQL 서버 주소
        //static String database = "test"; // MySQL DATABASE 이름
        static String user_name = "root"; //  MySQL 서버 아이디
        static String password = "useruser"; // MySQL 서버 비밀번호

        static String url = "jdbc:mysql://localhost/test";

        public DBA() {
            url += timeZone;
        }
        public boolean connect() {

        // 1.드라이버 로딩
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println(" !! <JDBC 오류> Driver load 오류: " + e.getMessage());
            e.printStackTrace();
            return false;
        }

        // 2.연결
        try {
            con = DriverManager.getConnection(url, user_name, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch(SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;

    }

    public void disconnect() {
        try {
            if(con != null)
                con.close();

        } catch (SQLException e) {}
    }

    public boolean isExist(int id, String name, String email) {
        String check_query = "select * from user where `user_id`=? OR `user_name`=? OR `user_email`=?;";

        PreparedStatement ps = null;
        ResultSet rs;
        boolean checkUser = false;

        try {
            ps = con.prepareStatement(check_query);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                checkUser = true;
            }
        } catch (SQLException e){

        }

        return checkUser;
    }

    public boolean logIn(int id, int pw) {
        String check_query = "select * from user where `id`=? OR `name`=?;";

        PreparedStatement ps = null;
        ResultSet rs;
        boolean checkUser = false;

        try {
            ps = con.prepareStatement(check_query);
            ps.setInt(1, id);
            ps.setInt(2, pw);
            rs = ps.executeQuery();

            if (rs.next()) {
                checkUser = true;
            }
        } catch (SQLException e){

        }

        return checkUser;
    }


    public boolean add(User data) {
            String query = "insert into user value(?,?,?,?,?);";

            PreparedStatement pstmt = null;

        try {
                pstmt = con.prepareStatement(query);
                pstmt.setInt(1, data.getID());
                pstmt.setString(2, data.getName());
                pstmt.setInt(3, data.getPW());
                pstmt.setInt(4, data.getPhone());
                pstmt.setString(5, data.getEmail());
                pstmt.executeUpdate();

            } catch(SQLException e) {
                e.printStackTrace();
                return false;
            }

            return true;
    }

    public boolean change_pw(int id, int pw) {
            String query = "update user set `user_pw`=? where `user_id`=?;";
            PreparedStatement pstmt = null;
            User data = new User();

            try {
                pstmt = con.prepareStatement(query);
                pstmt.setInt(1, pw);
                pstmt.setInt(2, id);
                int rs = pstmt.executeUpdate();

                if (rs != 0) {
                    return true;
                }
            } catch( SQLException e){
                e.printStackTrace();
            }

            return false;
    }

    public boolean check_user(int id, int phone) {
        String check_query = "select * from user where `user_id`=? AND `user_phone`=?;";

        PreparedStatement ps = null;
        ResultSet rs;
        boolean confirm = false;

        try {
            ps = con.prepareStatement(check_query);
            ps.setInt(1, id);
            ps.setInt(2, phone);
            rs = ps.executeQuery();

            if (rs.next()) {
                confirm = true;
            }
        } catch (SQLException e){

        }

        return confirm;

    }
}
