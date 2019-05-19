package edu.handong.database.team6;
import java.sql.*;

public class DBA {
        static int loginID = -9999;
        static Connection con = null;
        static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        static final String timeZone = "?serverTimezone=UTC&useSSL=false";

        //static String server = "localhost"; // MySQL 서버 주소
        //static String database = "test"; // MySQL DATABASE 이름
        static String user_name = "root"; //  MySQL 서버 아이디
        static String password = "jca+please"; // MySQL 서버 비밀번호

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
        String check_query = "select * from user where `user_id`=? AND `user_pw`=?;";

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
                loginID = id;
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


//Mail

    public boolean write(int recv, String title, String contents) {
        int id = 0;
            String check_query = "insert into TemporaryMailBox value(?,?,?,?,?);";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(check_query);
            ps.setString(1, null);
            ps.setInt(2, loginID);
            ps.setInt(3, recv);
            ps.setString(4, title);
            ps.setString(5, contents);
            ps.executeUpdate();

        } catch (SQLException e){
                return false;
        }

        return true;
    }

    public boolean send(int recv, String title, String contents) {
        int id=0;
        String insertQuery = "insert into sentmailbox value(?, ?, ?, ?, ?);";
        String selectSendQuery = "select * from temporarymailbox order by mailID desc limit 1;";
        String deleteQuery = "delete from temporarymailbox where mailID IN ( select mailID from sentmailbox);";
        //String selectDeleteQuery = "( select mailID from sentmailbox);";

        PreparedStatement selectSend = null;
        PreparedStatement insert = null;
        PreparedStatement selectDelete = null;
        PreparedStatement delete = null;
        ResultSet rs;
        System.out.println("please");

        try {
            selectSend = con.prepareStatement(selectSendQuery);
            rs = selectSend.executeQuery();
            if(rs.next()) {
                insert = con.prepareStatement(insertQuery);
                insert.setInt(1, rs.getInt(1));
                //System.out.println(rs.getInt(1));
                insert.setInt(2, rs.getInt(2));
                //System.out.println(rs.getInt(2));
                insert.setInt(3, rs.getInt(3));
                //System.out.println(rs.getInt(3));
                //System.out.println(rs.getString(4));
                insert.setString(4, rs.getString(4));
                //System.out.println(rs.getString(4));
                insert.setString(5, rs.getString(5));
                //System.out.println(rs.getString(5));
                insert.executeUpdate();
            }

                delete = con.prepareStatement(deleteQuery);
                delete.executeUpdate();

        } catch (SQLException e){
            System.out.println("Exception");
            return false;
        }

        return true;
    }

}