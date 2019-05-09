import java.sql.*;

public class DBA {

        static Connection con = null;
        static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
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
        /*
        // 3.해제
        try {
            if(con != null)
                con.close();
        } catch (SQLException e) {}

         */
    }

    public boolean add(User data) {
            String query = "insert into user value(?,?,?);";
            PreparedStatement pstmt = null;

            try {
                pstmt = con.prepareStatement(query);
                pstmt.setInt(1, data.getID());
                pstmt.setString(2, data.getName());
                pstmt.setInt(3, data.getPW());
                pstmt.executeUpdate();
            } catch(SQLException e) {
                e.printStackTrace();
                return false;
            }

            System.out.println("유저 저장완료...");
            return true;
    }

    public User search_One(int id) {
            String query = "Select * from user where user_id = ?";
            PreparedStatement pstmt = null;
            User data = new User();
            try {
                pstmt = con.prepareStatement(query);
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();

                while(rs.next()) {
                    data.setID(rs.getInt("user_id"));
                    data.setName(rs.getString("user_name"));
                    data.setPW(rs.getInt("user_pw"));
                }
            } catch( SQLException e){
                e.printStackTrace();
            }

            return data;
    }
}
