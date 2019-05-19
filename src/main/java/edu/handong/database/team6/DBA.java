package edu.handong.database.team6;
import java.sql.*;
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

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

    public boolean send() {
        //int id=0;
        String insertQuery = "insert into sentmailbox value(?, ?, ?, ?, ?, ?);";
        String selectSendQuery = "select * from temporarymailbox order by mailID desc limit 1;";
        String deleteQuery = "delete from temporarymailbox where mailID IN ( select mailID from sentmailbox);";
        //String selectDeleteQuery = "( select mailID from sentmailbox);";

        PreparedStatement selectSend = null;
        PreparedStatement insert = null;
        PreparedStatement selectDelete = null;
        PreparedStatement delete = null;
        ResultSet rs;
        //System.out.println("please");

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
                insert.setInt(6, 0);
                //System.out.println(rs.getString(5));
                insert.executeUpdate();
                delete = con.prepareStatement(deleteQuery);
                delete.executeUpdate();
            }

        } catch (SQLException e){
            System.out.println("Exception");
            return false;
        }

        return true;
    }

    public boolean getAllMails(){
        String check_query = "select mailID, sender, title,star from Allmailbox union " +
                             "select mailID, sender, title, star from mailbox union" +
                             "select mailID, sender, title star from tomemailbox;";

        PreparedStatement ps = null;
        ResultSet rs;
        boolean gotIt = false;

        try {
            ps = con.prepareStatement(check_query);
            rs = ps.executeQuery();

            while (rs.next()) {
                gotIt= true;
                if(rs.getInt(4) == 1) {

                    System.out.println("MailID :" + ansi().fg(CYAN).a(rs.getInt(1)).reset()
                            + "   Sender : " + ansi().fg(CYAN).a(rs.getInt(2)).reset()
                            + "   Title : " + ansi().fg(CYAN).a(rs.getString(3)).reset());
                }

                else{
                    System.out.println("MailID :" + rs.getInt(1)
                            + "   Sender : " +rs.getInt(2)
                            + "   Title : " + rs.getString(3));
                }
            }
        } catch (SQLException e){

        }

        return gotIt;
    }

    public boolean getMails(){
        String check_query = "select mailID, sender, title, star from mailbox;";

        PreparedStatement ps = null;
        ResultSet rs;
        boolean gotIt = false;

        try {
            ps = con.prepareStatement(check_query);
            rs = ps.executeQuery();

            while (rs.next()) {
                gotIt= true;
                if(rs.getInt(4) == 1) {

                    System.out.println("MailID :" + ansi().fg(CYAN).a(rs.getInt(1)).reset()
                            + "   Sender : " + ansi().fg(CYAN).a(rs.getInt(2)).reset()
                            + "   Title : " + ansi().fg(CYAN).a(rs.getString(3)).reset());
                }

                else{
                    System.out.println("MailID :" + rs.getInt(1)
                            + "   Sender : " +rs.getInt(2)
                            + "   Title : " + rs.getString(3));
                }
            }
        } catch (SQLException e){

        }

        return gotIt;
    }

    public boolean getSentMails(){
        String check_query = "select mailID, sender, title, star from sentmailbox;";

        PreparedStatement ps = null;
        ResultSet rs;
        boolean gotIt = false;

        try {
            ps = con.prepareStatement(check_query);
            rs = ps.executeQuery();

            while (rs.next()) {
                gotIt= true;
                if(rs.getInt(4) == 1) {

                    System.out.println("MailID :" + ansi().fg(CYAN).a(rs.getInt(1)).reset()
                            + "   Sender : " + ansi().fg(CYAN).a(rs.getInt(2)).reset()
                            + "   Title : " + ansi().fg(CYAN).a(rs.getString(3)).reset());
                }

                else{
                    System.out.println("MailID :" + rs.getInt(1)
                            + "   Sender : " +rs.getInt(2)
                            + "   Title : " + rs.getString(3));
                }
            }
        } catch (SQLException e){

        }

        return gotIt;
    }

    public boolean getSpamMails(){
        String check_query = "select mailID, sender, title from spammailbox;";

        PreparedStatement ps = null;
        ResultSet rs;
        boolean gotIt = false;

        try {
            ps = con.prepareStatement(check_query);
            rs = ps.executeQuery();

            while (rs.next()) {
                gotIt= true;
                    System.out.println("MailID :" + rs.getInt(1)
                            + "   Sender : " +rs.getInt(2)
                            + "   Title : " + rs.getString(3));

            }
        } catch (SQLException e){

        }

        return gotIt;
    }

    public boolean getTemporaryMails(){
        String check_query = "select mailID, sender, title from temporarymailbox;";

        PreparedStatement ps = null;
        ResultSet rs;
        boolean gotIt = false;

        try {
            ps = con.prepareStatement(check_query);
            rs = ps.executeQuery();

            while (rs.next()) {
                gotIt= true;
                    System.out.println("MailID :" + rs.getInt(1)
                            + "   Sender : " +rs.getInt(2)
                            + "   Title : " + rs.getString(3));

            }
        } catch (SQLException e){

        }

        return gotIt;
    }

    public boolean getToMeMails(){
        String check_query = "select mailID, sender, title, star from tomemailbox;";

        PreparedStatement ps = null;
        ResultSet rs;
        boolean gotIt = false;

        try {
            ps = con.prepareStatement(check_query);
            rs = ps.executeQuery();

            while (rs.next()) {
                gotIt= true;
                if(rs.getInt(4) == 1) {

                    System.out.println("MailID :" + ansi().fg(CYAN).a(rs.getInt(1)).reset()
                            + "   Sender : " + ansi().fg(CYAN).a(rs.getInt(2)).reset()
                            + "   Title : " + ansi().fg(CYAN).a(rs.getString(3)).reset());
                }

                else{
                    System.out.println("MailID :" + rs.getInt(1)
                            + "   Sender : " +rs.getInt(2)
                            + "   Title : " + rs.getString(3));
                }
            }
        } catch (SQLException e){

        }

        return gotIt;
    }

    public boolean getTrashCan(){
        String check_query = "select mailID, sender, title from trashcan;";

        PreparedStatement ps = null;
        ResultSet rs;
        boolean gotIt = false;

        try {
            ps = con.prepareStatement(check_query);
            rs = ps.executeQuery();

            while (rs.next()) {
                gotIt= true;
                System.out.println("MailID :"+ rs.getInt(1)
                        + "   Sender : " + rs.getInt(2)
                        +"   Title : " + rs.getString(3));
            }
        } catch (SQLException e){

        }

        return gotIt;
    }

    public boolean getAllMailcontent(int AllmailInput){
        String check_query = "select sender, title, contents from Allmailbox where mailID = ?;";

        PreparedStatement ps = null;
        ResultSet rs;
        boolean gotIt = false;

        try {
            ps = con.prepareStatement(check_query);
            ps.setInt(1, AllmailInput);
            rs = ps.executeQuery();

            while (rs.next()) {
                gotIt= true;
                System.out.println("------------------------");
                System.out.println("Sender :"+ rs.getInt(1)
                        + "\nTitle : " + rs.getString(2)
                        +"\nContents : " + rs.getString(3));
                System.out.println("------------------------");
            }
        } catch (SQLException e){

        }

        return gotIt;
    }

    public boolean getMailcontent(int mailInput){
        String check_query = "select sender, title, contents from mailbox where mailID = ?;";

        PreparedStatement ps = null;
        ResultSet rs;
        boolean gotIt = false;

        try {
            ps = con.prepareStatement(check_query);
            ps.setInt(1, mailInput);
            rs = ps.executeQuery();

            while (rs.next()) {
                gotIt= true;
                System.out.println("------------------------");
                System.out.println("Sender :"+ rs.getInt(1)
                        + "\nTitle : " + rs.getString(2)
                        +"\nContents : " + rs.getString(3));
                System.out.println("------------------------");
            }
        } catch (SQLException e){

        }

        return gotIt;
    }

    public boolean getSentMailcontent(int mailInput){
        String check_query = "select sender, title, contents from Sentmailbox where mailID = ?;";

        PreparedStatement ps = null;
        ResultSet rs;
        boolean gotIt = false;

        try {
            ps = con.prepareStatement(check_query);
            ps.setInt(1, mailInput);
            rs = ps.executeQuery();

            while (rs.next()) {
                gotIt= true;
                System.out.println("------------------------");
                System.out.println("Sender :"+ rs.getInt(1)
                        + "\nTitle : " + rs.getString(2)
                        +"\nContents : " + rs.getString(3));
            }System.out.println("------------------------");
        } catch (SQLException e){

        }

        return gotIt;
    }

    public boolean getTempMailcontent(int mailInput){
        String check_query = "select sender, title, contents from TemporaryMailbox where mailID = ?;";

        PreparedStatement ps = null;
        ResultSet rs;
        boolean gotIt = false;

        try {
            ps = con.prepareStatement(check_query);
            ps.setInt(1, mailInput);
            rs = ps.executeQuery();

            while (rs.next()) {
                gotIt= true;
                System.out.println("------------------------");
                System.out.println("Sender :"+ rs.getInt(1)
                        + "\nTitle : " + rs.getString(2)
                        +"\nContents : " + rs.getString(3));
                System.out.println("------------------------");
            }
        } catch (SQLException e){

        }

        return gotIt;
    }

    public boolean getToMeMailcontent(int mailInput){
        String check_query = "select sender, title, contents from TomeMailbox where mailID = ?;";

        PreparedStatement ps = null;
        ResultSet rs;
        boolean gotIt = false;

        try {
            ps = con.prepareStatement(check_query);
            ps.setInt(1, mailInput);
            rs = ps.executeQuery();

            while (rs.next()) {
                gotIt= true;
                System.out.println("------------------------");
                System.out.println("Sender :"+ rs.getInt(1)
                        + "\nTitle : " + rs.getString(2)
                        +"\nContents : " + rs.getString(3));
                System.out.println("------------------------");
            }
        } catch (SQLException e){

        }

        return gotIt;
    }

    public boolean getTrashcontent(int mailInput){
        String check_query = "select sender, title, contents from TrashCan where mailID = ?;";

        PreparedStatement ps = null;
        ResultSet rs;
        boolean gotIt = false;

        try {
            ps = con.prepareStatement(check_query);
            ps.setInt(1, mailInput);
            rs = ps.executeQuery();

            while (rs.next()) {
                gotIt= true;
                System.out.println("------------------------");
                System.out.println("Sender :"+ rs.getInt(1)
                        + "\nTitle : " + rs.getString(2)
                        +"\nContents : " + rs.getString(3));
                System.out.println("------------------------");
            }
        } catch (SQLException e){

        }

        return gotIt;
    }

    public boolean getSpamcontent(int mailInput){
        String check_query = "select sender, title, contents from spammailBox where mailID = ?;";

        PreparedStatement ps = null;
        ResultSet rs;
        boolean gotIt = false;

        try {
            ps = con.prepareStatement(check_query);
            ps.setInt(1, mailInput);
            rs = ps.executeQuery();

            while (rs.next()) {
                gotIt= true;
                System.out.println("------------------------");
                System.out.println("Sender :"+ rs.getInt(1)
                        + "\nTitle : " + rs.getString(2)
                        +"\nContents : " + rs.getString(3));
                System.out.println("------------------------");
            }
        } catch (SQLException e){

        }

        return gotIt;
    }

    public boolean replyInAllMailQ(int id, String title, String contents) {
        String check_query = "insert into TemporaryMailBox value(?,?,?,?,?);";
        String select_query = "select sender, receiver from Allmailbox where mailid = ?";

        ResultSet rs;
        PreparedStatement ps = null;
        PreparedStatement select = null;
        try {
            select = con.prepareStatement(select_query);
            select.setInt(1, id);
            rs = select.executeQuery();
            if(rs.next()) {
                ps = con.prepareStatement(check_query);
                ps.setString(1, null);
                ps.setInt(2, rs.getInt(2));
                ps.setInt(3, rs.getInt(1));
                ps.setString(4, title);
                ps.setString(5, contents);
                ps.executeUpdate();
            }
        } catch (SQLException e){
            return false;
        }

        return true;
    }

    public boolean replyInMailQ(int id, String title, String contents) {
        String check_query = "insert into TemporaryMailBox value(?,?,?,?,?);";
        String select_query = "select sender, receiver from mailbox where mailid = ?";

        ResultSet rs;
        PreparedStatement ps = null;
        PreparedStatement select = null;
        try {
            select = con.prepareStatement(select_query);
            select.setInt(1, id);
            rs = select.executeQuery();
            //int newReceiver = rs.getInt(1);
            //int newSender = rs.getInt(2);
            if(rs.next()) {
                ps = con.prepareStatement(check_query);
                ps.setString(1, null);
                ps.setInt(2, rs.getInt(2));
                ps.setInt(3, rs.getInt(1));
                ps.setString(4, title);
                ps.setString(5, contents);
                ps.executeUpdate();
            }
        } catch (SQLException e){
            return false;
        }

        return true;
    }

    public boolean passInAllMailQ(int id, int receiver) {
        String check_query = "insert into TemporaryMailBox value(?,?,?,?,?);";
        String select_query = "select receiver, title, contents from allmailbox where mailid = ?";

        ResultSet rs;
        PreparedStatement ps = null;
        PreparedStatement select = null;
        try {
            select = con.prepareStatement(select_query);
            select.setInt(1, id);
            rs = select.executeQuery();
            //int newReceiver = rs.getInt(1);
            //int newSender = rs.getInt(2);
            if(rs.next()) {
                ps = con.prepareStatement(check_query);
                ps.setString(1, null);
                ps.setInt(2, rs.getInt(1));
                ps.setInt(3, receiver);
                ps.setString(4, rs.getString(2));
                ps.setString(5, rs.getString(3));
                ps.executeUpdate();
            }
        } catch (SQLException e){
            return false;
        }

        return true;
    }

    public boolean passInMailQ(int id, int receiver) {
        String check_query = "insert into TemporaryMailBox value(?,?,?,?,?);";
        String select_query = "select receiver, title, contents from mailbox where mailid = ?";

        ResultSet rs;
        PreparedStatement ps = null;
        PreparedStatement select = null;
        try {
            select = con.prepareStatement(select_query);
            select.setInt(1, id);
            rs = select.executeQuery();
            //int newReceiver = rs.getInt(1);
            //int newSender = rs.getInt(2);
            if(rs.next()) {
                ps = con.prepareStatement(check_query);
                ps.setString(1, null);
                ps.setInt(2, rs.getInt(1));
                ps.setInt(3, receiver);
                ps.setString(4, rs.getString(2));
                ps.setString(5, rs.getString(3));
                ps.executeUpdate();
            }
        } catch (SQLException e){
            return false;
        }

        return true;
    }

    public boolean passInSentMailQ(int id, int receiver) {
        String check_query = "insert into TemporaryMailBox value(?,?,?,?,?);";
        String select_query = "select receiver, title, contents from sentmailbox where mailid = ?";

        ResultSet rs;
        PreparedStatement ps = null;
        PreparedStatement select = null;
        try {
            select = con.prepareStatement(select_query);
            select.setInt(1, id);
            rs = select.executeQuery();
            //int newReceiver = rs.getInt(1);
            //int newSender = rs.getInt(2);
            if(rs.next()) {
                ps = con.prepareStatement(check_query);
                ps.setString(1, null);
                ps.setInt(2, rs.getInt(1));
                ps.setInt(3, receiver);
                ps.setString(4, rs.getString(2));
                ps.setString(5, rs.getString(3));
                ps.executeUpdate();
            }
        } catch (SQLException e){
            return false;
        }

        return true;
    }

    public boolean passInToMeMailQ(int id, int receiver) {
        String check_query = "insert into TemporaryMailBox value(?,?,?,?,?,?);";
        String select_query = "select sender, title, contents from tomemailbox where mailid = ?";

        ResultSet rs;
        PreparedStatement ps = null;
        PreparedStatement select = null;
        try {
            select = con.prepareStatement(select_query);
            select.setInt(1, id);
            rs = select.executeQuery();
            //int newReceiver = rs.getInt(1);
            //int newSender = rs.getInt(2);
            if(rs.next()) {
                ps = con.prepareStatement(check_query);
                ps.setString(1, null);
                ps.setInt(2, rs.getInt(1));
                ps.setInt(3, receiver);
                ps.setString(4, rs.getString(2));
                ps.setString(5, rs.getString(3));
                ps.setInt(6, 0);
                ps.executeUpdate();
            }
        } catch (SQLException e){
            return false;
        }

        return true;
    }

    public boolean markStarInAllMailQ(int id){
        String check_query = "update allmailbox set star = 1 where mailid = ?;";

        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(check_query);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Marked " +
                    ansi().fg(BLUE).a("SUCCESSFULLY").reset());
        }catch(SQLException e){
            return false;
        }
            return true;
        }

    public boolean markStarInMailQ(int id){
        String check_query = "update mailbox set star = 1 where mailid = ?;";

        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(check_query);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Marked " +
                    ansi().fg(BLUE).a("SUCCESSFULLY").reset());
        }catch(SQLException e){
            return false;
        }
        return true;
    }

    public boolean markStarInSentMailQ(int id){
        String check_query = "update Sentmailbox set star = 1 where mailid = ?;";

        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(check_query);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Marked " +
                    ansi().fg(BLUE).a("SUCCESSFULLY").reset());
        }catch(SQLException e){
            return false;
        }
        return true;
    }

    public boolean markStarInToMeMailQ(int id){
        String check_query = "update Tomemailbox set star = 1 where mailid = ?;";

        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(check_query);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Marked " +
                    ansi().fg(BLUE).a("SUCCESSFULLY").reset());
        }catch(SQLException e){
            return false;
        }
        return true;
    }

    public boolean deleteFromAllMailQ(int id) {
            //trashcan mailID, sender, title, contents, receiver, star
        String insertQuery = "insert into Trashcan value(?, ?, ?, ?, ?, ?);";
        //allmailbox mailID sender title, contents receiver, star
        String selectSendQuery = "select * from allmailbox where mailid = ? and star <> 1;";
        String deleteQuery = "delete from allmailbox where mailID IN ( select mailID from trashcan);";
        //String selectDeleteQuery = "( select mailID from sentmailbox);";

        PreparedStatement selectSend = null;
        PreparedStatement insert = null;
        //PreparedStatement selectDelete = null;
        PreparedStatement delete = null;
        ResultSet rs;
        //System.out.println("please");

        try {
            selectSend = con.prepareStatement(selectSendQuery);
            selectSend.setInt(1, id);
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
                insert.setString(6, rs.getString(6));
                //System.out.println(rs.getString(5));
                insert.executeUpdate();
                delete = con.prepareStatement(deleteQuery);
                delete.executeUpdate();

                System.out.printf("The mail is moved ");
                System.out.println(ansi().fg(BLUE).a("SUCCESSFULLY").reset()+" to Trash Can");
            }
            else{
                System.out.printf("There is ");
                System.out.println(ansi().fg(RED).a("NO").reset() +" MailID which isn't marked\n");
            }




        } catch (SQLException e){
            System.out.println("Exception");
            return false;
        }

        return true;
    }

    public boolean deleteFromMailQ(int id) {
        //trashcan mailID, sender, title, contents, receiver, star
        String insertQuery = "insert into Trashcan value(?, ?, ?, ?, ?, ?);";
        //allmailbox mailID sender title, contents receiver, star
        String selectSendQuery = "select * from mailbox where mailid = ? and star <> 1;";
        String deleteQuery = "delete from mailbox where mailID IN ( select mailID from trashcan);";
        //String selectDeleteQuery = "( select mailID from sentmailbox);";

        PreparedStatement selectSend = null;
        PreparedStatement insert = null;
        //PreparedStatement selectDelete = null;
        PreparedStatement delete = null;
        ResultSet rs;
        //System.out.println("please");

        try {
            selectSend = con.prepareStatement(selectSendQuery);
            selectSend.setInt(1, id);
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
                insert.setString(6, rs.getString(6));
                //System.out.println(rs.getString(5));
                insert.executeUpdate();
                delete = con.prepareStatement(deleteQuery);
                delete.executeUpdate();

                System.out.printf("The mail is moved ");
                System.out.println(ansi().fg(BLUE).a("SUCCESSFULLY").reset()+" to Trash Can");
            }
            else{
                System.out.printf("There is ");
                System.out.println(ansi().fg(RED).a("NO").reset() +" MailID which isn't marked\n");
            }

        } catch (SQLException e){
            System.out.println("Exception");
            return false;
        }

        return true;
    }

    public boolean deleteFromSentMailQ(int id) {
        //trashcan mailID, sender, title, contents, receiver, star
        String insertQuery = "insert into Trashcan value(?, ?, ?, ?, ?, ?);";
        //allmailbox mailID sender title, contents receiver, star
        String selectSendQuery = "select * from Sentmailbox where mailid = ? and star <> 1;";
        String deleteQuery = "delete from Sentmailbox where mailID IN ( select mailID from trashcan);";
        //String selectDeleteQuery = "( select mailID from sentmailbox);";

        PreparedStatement selectSend = null;
        PreparedStatement insert = null;
        //PreparedStatement selectDelete = null;
        PreparedStatement delete = null;
        ResultSet rs;
        //System.out.println("please");

        try {
            selectSend = con.prepareStatement(selectSendQuery);
            selectSend.setInt(1, id);
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
                insert.setString(6, rs.getString(6));
                //System.out.println(rs.getString(5));
                insert.executeUpdate();
                delete = con.prepareStatement(deleteQuery);
                delete.executeUpdate();

                System.out.printf("The mail is moved ");
                System.out.println(ansi().fg(BLUE).a("SUCCESSFULLY").reset()+" to Trash Can");
            }
            else{
                System.out.printf("There is ");
                System.out.println(ansi().fg(RED).a("NO").reset() +" MailID which isn't marked\n");
            }

        } catch (SQLException e){
            System.out.println("Exception");
            return false;
        }

        return true;
    }

    public boolean deleteFromTempMailQ(int id) {
        //trashcan mailID, sender, title, contents, receiver, star
        String insertQuery = "insert into Trashcan value(?, ?, ?, ?, ?, ?);";
        //allmailbox mailID sender title, contents receiver, star
        String selectSendQuery = "select * from Temporarymailbox where mailid = ? and star <> 1;";
        String deleteQuery = "delete from Temporarymailbox where mailID IN ( select mailID from trashcan);";
        //String selectDeleteQuery = "( select mailID from sentmailbox);";

        PreparedStatement selectSend = null;
        PreparedStatement insert = null;
        //PreparedStatement selectDelete = null;
        PreparedStatement delete = null;
        ResultSet rs;
        //System.out.println("please");

        try {
            selectSend = con.prepareStatement(selectSendQuery);
            selectSend.setInt(1, id);
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
                insert.setString(6, rs.getString(6));
                //System.out.println(rs.getString(5));
                insert.executeUpdate();
                delete = con.prepareStatement(deleteQuery);
                delete.executeUpdate();

                System.out.printf("The mail is moved ");
                System.out.println(ansi().fg(BLUE).a("SUCCESSFULLY").reset()+" to Trash Can");
            }
            else{
                System.out.printf("There is ");
                System.out.println(ansi().fg(RED).a("NO").reset() +" MailID which isn't marked\n");
            }

        } catch (SQLException e){
            System.out.println("Exception");
            return false;
        }

        return true;
    }

    public boolean deleteFromToMeMailQ(int id) {
        //trashcan mailID, sender, title, contents, receiver, star
        String insertQuery = "insert into Trashcan value(?, ?, ?, ?, ?, ?);";
        //allmailbox mailID sender title, contents receiver, star
        String selectSendQuery = "select * from Tomemailbox where mailid = ? and star <> 1;";
        String deleteQuery = "delete from Tomemailbox where mailID IN ( select mailID from trashcan);";
        //String selectDeleteQuery = "( select mailID from sentmailbox);";

        PreparedStatement selectSend = null;
        PreparedStatement insert = null;
        //PreparedStatement selectDelete = null;
        PreparedStatement delete = null;
        ResultSet rs;
        //System.out.println("please");

        try {
            selectSend = con.prepareStatement(selectSendQuery);
            selectSend.setInt(1, id);
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
                insert.setInt(5, rs.getInt(2));
                insert.setInt(6, rs.getInt(5));
                //System.out.println(rs.getString(5));
                insert.executeUpdate();
                delete = con.prepareStatement(deleteQuery);
                delete.executeUpdate();

                System.out.printf("The mail is moved ");
                System.out.println(ansi().fg(BLUE).a("SUCCESSFULLY").reset()+" to Trash Can");
            }
            else{
                System.out.printf("There is ");
                System.out.println(ansi().fg(RED).a("NO").reset() +" MailID which isn't marked\n");
            }

        } catch (SQLException e){
            System.out.println("Exception");
            return false;
        }

        return true;
    }

    public boolean deleteFromTrashCanQ(int id) {
        String deleteQuery = "delete from Trashcan where mailID = ?;";
        //String selectDeleteQuery = "( select mailID from sentmailbox);";

        PreparedStatement selectSend = null;
        PreparedStatement insert = null;
        //PreparedStatement selectDelete = null;
        PreparedStatement delete = null;
        ResultSet rs;
        //System.out.println("please");

        try {
           delete = con.prepareStatement(deleteQuery);
            delete.setInt(1, id);
            if(delete.executeUpdate() != 0) {
                System.out.printf("The mail is removed ");
                System.out.println(ansi().fg(BLUE).a("COMPLETELY").reset());

            }else{
                System.out.printf("There is ");
                System.out.println(ansi().fg(RED).a("NO").reset() +" MailID which you input\n");
            }

        } catch (SQLException e){
            System.out.println("Exception");
            return false;
        }

        return true;
    }

    public boolean deleteFromSpamQ(int id) {
        String deleteQuery = "delete from spammailbox where mailID = ?;";
        //String selectDeleteQuery = "( select mailID from sentmailbox);";

        PreparedStatement selectSend = null;
        PreparedStatement insert = null;
        //PreparedStatement selectDelete = null;
        PreparedStatement delete = null;
        ResultSet rs;
        //System.out.println("please");

        try {
            delete = con.prepareStatement(deleteQuery);
            delete.setInt(1, id);
            delete.executeUpdate();
            return true;

        } catch (SQLException e){
            System.out.println("Exception");
            return false;
        }

    }

    public boolean toSpamFromAllQ(int id) {
        //trashcan mailID, sender, title, contents, receiver, star
        String insertQuery = "insert into spammailbox value(?, ?, ?, ?, ?);";
        //allmailbox mailID sender title, contents receiver, star
        String selectSendQuery = "select mailID, sender, title, contents, receiver from Allmailbox where mailid = ? and star <> 1;";
        String deleteQuery = "delete from Allmailbox where mailID IN ( select mailID from spammailbox);";
        //String selectDeleteQuery = "( select mailID from sentmailbox);";

        PreparedStatement selectSend = null;
        PreparedStatement insert = null;
        //PreparedStatement selectDelete = null;
        PreparedStatement delete = null;
        ResultSet rs;
        //System.out.println("please");

        try {
            selectSend = con.prepareStatement(selectSendQuery);
            selectSend.setInt(1, id);
            rs = selectSend.executeQuery();
            if(rs.next()) {
                insert = con.prepareStatement(insertQuery);
                insert.setInt(1, rs.getInt(1));
                //System.out.println(rs.getInt(1));
                insert.setInt(2, rs.getInt(2));
                //System.out.println(rs.getInt(2));
                insert.setString(3, rs.getString(3));
                //System.out.println(rs.getInt(3));
                //System.out.println(rs.getString(4));
                insert.setString(4, rs.getString(4));
                //System.out.println(rs.getString(4));
                insert.setInt(5, rs.getInt(5));
                //insert.setString(6, rs.getString(6));
                //System.out.println(rs.getString(5));
                insert.executeUpdate();
                delete = con.prepareStatement(deleteQuery);
                delete.executeUpdate();

                System.out.printf("The mail is moved ");
                System.out.println(ansi().fg(BLUE).a("SUCCESSFULLY").reset()+" to Spam Mail Box");
            }
            else{
                System.out.printf("There is ");
                System.out.println(ansi().fg(RED).a("NO").reset() +" MailID which isn't marked\n");
            }

        } catch (SQLException e){
            System.out.println("Exception");
            return false;
        }

        return true;
    }

    public boolean toSpamFromMailQ(int id) {
        //trashcan mailID, sender, title, contents, receiver, star
        String insertQuery = "insert into spammailbox value(?, ?, ?, ?, ?);";
        //allmailbox mailID sender title, contents receiver, star
        String selectSendQuery = "select mailID, sender, title, contents, receiver from mailbox where mailid = ? and star <> 1;";
        String deleteQuery = "delete from mailbox where mailID IN ( select mailID from spammailbox);";
        //String selectDeleteQuery = "( select mailID from sentmailbox);";

        PreparedStatement selectSend = null;
        PreparedStatement insert = null;
        //PreparedStatement selectDelete = null;
        PreparedStatement delete = null;
        ResultSet rs;
        //System.out.println("please");

        try {
            selectSend = con.prepareStatement(selectSendQuery);
            selectSend.setInt(1, id);
            rs = selectSend.executeQuery();
            if(rs.next()) {
                insert = con.prepareStatement(insertQuery);
                insert.setInt(1, rs.getInt(1));
                //System.out.println(rs.getInt(1));
                insert.setInt(2, rs.getInt(2));
                //System.out.println(rs.getInt(2));
                insert.setString(3, rs.getString(3));
                //System.out.println(rs.getInt(3));
                //System.out.println(rs.getString(4));
                insert.setString(4, rs.getString(4));
                //System.out.println(rs.getString(4));
                insert.setInt(5, rs.getInt(5));
                //insert.setString(6, rs.getString(6));
                //System.out.println(rs.getString(5));
                insert.executeUpdate();
                delete = con.prepareStatement(deleteQuery);
                delete.executeUpdate();

                System.out.printf("The mail is moved ");
                System.out.println(ansi().fg(BLUE).a("SUCCESSFULLY").reset()+" to Spam Mail Box");
            }
            else{
                System.out.printf("There is ");
                System.out.println(ansi().fg(RED).a("NO").reset() +" MailID which isn't marked\n");
            }

        } catch (SQLException e){
            System.out.println("Exception");
            return false;
        }

        return true;
    }

    public boolean toSpamFromToMeMailQ(int id) {
        //trashcan mailID, sender, title, contents, receiver, star
        String insertQuery = "insert into spammailbox value(?, ?, ?, ?, ?);";
        //allmailbox mailID sender title, contents receiver, star
        String selectSendQuery = "select mailID, sender, title, contents from tomemailbox where mailid = ? and star <> 1;";
        String deleteQuery = "delete from tomemailbox where mailID IN ( select mailID from spammailbox);";
        //String selectDeleteQuery = "( select mailID from sentmailbox);";

        PreparedStatement selectSend = null;
        PreparedStatement insert = null;
        //PreparedStatement selectDelete = null;
        PreparedStatement delete = null;
        ResultSet rs;
        //System.out.println("please");

        try {
            selectSend = con.prepareStatement(selectSendQuery);
            selectSend.setInt(1, id);
            rs = selectSend.executeQuery();
            if(rs.next()) {
                insert = con.prepareStatement(insertQuery);
                insert.setInt(1, rs.getInt(1));
                //System.out.println(rs.getInt(1));
                insert.setInt(2, rs.getInt(2));
                //System.out.println(rs.getInt(2));
                insert.setString(3, rs.getString(3));
                //System.out.println(rs.getInt(3));
                //System.out.println(rs.getString(4));
                insert.setString(4, rs.getString(4));
                //System.out.println(rs.getString(4));
                insert.setInt(5, rs.getInt(2));
                //insert.setString(6, rs.getString(6));
                //System.out.println(rs.getString(5));
                insert.executeUpdate();
                delete = con.prepareStatement(deleteQuery);
                delete.executeUpdate();

                System.out.printf("The mail is moved ");
                System.out.println(ansi().fg(BLUE).a("SUCCESSFULLY").reset()+" to Spam Mail Box");
            }
            else{
                System.out.printf("There is ");
                System.out.println(ansi().fg(RED).a("NO").reset() +" MailID which isn't marked\n");
            }

        } catch (SQLException e){
            System.out.println("Exception");
            return false;
        }

        return true;
    }

    public boolean modifyTempMailQ(int id, String contents) {
        String check_query = "update temporarymailbox set contents=? where mailid=?;";

        ResultSet rs;
        PreparedStatement ps = null;
        PreparedStatement select = null;
        try {
            select = con.prepareStatement(check_query);
            select.setString(1,contents);
            select.setInt(2, id);

            if(select.executeUpdate()!=0){
                System.out.printf("Modified ");
                System.out.println(ansi().fg(BLUE).a("SUCCESSFULLY").reset());
            }
            else{
                System.out.println(ansi().fg(RED).a("FAILED").reset());
            }


        } catch (SQLException e){
            return false;
        }

        return true;
    }

    public boolean sendTemp(int id) {
        //int id=0;
        String insertQuery = "insert into sentmailbox value(?, ?, ?, ?, ?, ?);";
        String selectSendQuery = "select * from temporarymailbox where mailid = ?;";
        String deleteQuery = "delete from temporarymailbox where mailID IN ( select mailID from sentmailbox);";
        //String selectDeleteQuery = "( select mailID from sentmailbox);";

        PreparedStatement selectSend = null;
        PreparedStatement insert = null;
        PreparedStatement selectDelete = null;
        PreparedStatement delete = null;
        ResultSet rs;
        //System.out.println("please");

        try {
            selectSend = con.prepareStatement(selectSendQuery);
            selectSend.setInt(1, id);
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
                insert.setString(6, null);
                insert.executeUpdate();
                delete = con.prepareStatement(deleteQuery);
                delete.executeUpdate();
            }

        } catch (SQLException e){
            System.out.println("Exception");
            return false;
        }

        return true;
    }

    public boolean cleanUp() {
        //int id=0;
        String deleteQuery = "delete from trashcan where mailID > 0";
        //String selectDeleteQuery = "( select mailID from sentmailbox);";

        PreparedStatement delete = null;
        //System.out.println("please");

        try {
                delete = con.prepareStatement(deleteQuery);
                delete.executeUpdate();

        } catch (SQLException e){
            System.out.println("Exception");
            return false;
        }

        return true;
    }
}

