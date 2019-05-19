package edu.handong.database.team6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JisikIn {

    BufferedReader rs = new BufferedReader(new InputStreamReader(System.in));
    PreparedStatement pstmt = null;
    int menuopt = 1;
    int order = 0;

    JisikInList jisik = new JisikInList();

    public void selectMenu () throws IOException, SQLException{
        String mode = null;
        if (order == 0)
            mode = "asending";
        else if(order == 1)
                mode = "descending";

        while(menuopt != 0) {
            System.out.println("\n\tNAVER JISIKIN");
            System.out.println("------------------------");
            System.out.println("Default setting is " + mode + " order. You can change this setting by choosing \"10. Setting\"");
            System.out.println("1. ASK");
            System.out.println("2. ANSWER");
            System.out.println("3. LIKE");
            System.out.println("------------------------");
            System.out.println("4. My Question List");
            System.out.println("5. All Question List");
            System.out.println("6. List by Category");
            System.out.println("7. Popular Questions");
            System.out.println("8. Question List by user");
            System.out.println("------------------------");
            System.out.println("9. See the reply");
            System.out.println("10. Setting");
            System.out.println("0. Back to main page");
            System.out.println("------------------------");

            menuopt = Integer.parseInt(rs.readLine());

            switch(menuopt){
                case 1:
                    writeQuestion();
                    break;
                case 2:
                    answerQuestion(order);
                    break;
                case 3:
                    likeQuestion();
                    break;
                case 4:
                    showMyQuestion(order);
                    break;
                case 5:
                    showQuestionList(order);
                    break;
                case 6:
                    showQuestionListCategory(order);
                    break;
                case 7:
                    showQuestionListLikes();
                    break;
                case 8:
                    showQuestionListUser(order);
                    break;
                case 9:
                    showReply(order);
                    break;
                case 10:
                    setting();
                    break;
            }
        }
    }

    public void setting() throws IOException {
        System.out.println("In which order do you want to see the query? Ascending or Descending?");
        String ord = rs.readLine();

        if (ord.equalsIgnoreCase("ASCENDING"))
            order = 0;
        else if (ord.equalsIgnoreCase("DESCENDING"))
            order = 1;
        else
            System.out.println("Wrong setting, pleas try again. Default setting is ascending order.");
    }

    public void writeQuestion() throws IOException, SQLException {
        String query = "insert into question (user_id, category, context, likes) values (?, ?, ?, 0);";

        System.out.println("Category: Education, Game, Life, Entertainment \nWhat kind of category do you want to ask?");

        jisik.setCategory(rs.readLine().toUpperCase());

        System.out.println("Please ask the question:");
        jisik.setContext(rs.readLine());

        jisik.setUserId(Main.user_id);

        try {
            pstmt = DBA.con.prepareStatement(query);
            pstmt.setInt(1, jisik.getUserId());
            pstmt.setString(2, jisik.getCategory());
            pstmt.setString(3, jisik.getContext());
            pstmt.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Fail to upload question!");
            return;
        }

        System.out.println("Success uploading question!");


    }

    public void showQuestionList(int order) throws IOException, SQLException {
        String query = null;

        if (order == 0)
            query = "select * from question order by post_id asc;";
        else if (order == 1)
            query = "select * from question order by post_id desc;";

        ResultSet rt = null;

        try {
            pstmt = DBA.con.prepareStatement(query);
            rt = pstmt.executeQuery();

        } catch (SQLException e) {
            System.out.println("Fail to show question list.");
        }

        while(rt.next()) {
            int user_id = rt.getInt("user_id");
            int post_id = rt.getInt("post_id");
            String category = rt.getString("category");
            String question = rt.getString("context");
            int like = rt.getInt("likes");

            System.out.format("Post ID: %d, Category: %s,  Writer: %d\nQuestion:%s\nLikes: %d\n", post_id, category, user_id, question, like);
            System.out.println("\n");
        }

    }

    public void showMyQuestion(int order) throws IOException, SQLException {
        String query = null;

        if (order == 0)
            query = "select user_id, post_id, category, context, likes from question where `user_id`=? order by post_id asc;";
        else if (order == 1)
            query = "select user_id, post_i,d category, context, likes from question where `user_id`=? order by post_id desc;";

        ResultSet rt = null;

        try {
            pstmt = DBA.con.prepareStatement(query);
            pstmt.setInt(1, Main.user_id);
            rt = pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Fail to show question list.");
        }

        while(rt.next()) {
            int post_id = rt.getInt("post_id");
            String category = rt.getString("category");
            String question = rt.getString("context");
            int like = rt.getInt("likes");
            System.out.format("Post ID: %d, Category: %s,  \nQuestion:%s\nLikes: %d\n\n", post_id, category, question, like);

        }

    }


    public void answerQuestion(int order) throws IOException, SQLException {
        System.out.println("━━━━━━━━━━━━━━━JisikIn Question━━━━━━━━━━━━━━━\n\n");
        showQuestionList(order);

        String query = "insert into reply (user_id, post_id, reply, likereply) values (?, ?, ?, 0);";

        System.out.println("Select the post_id you want to reply");

        int post = Integer.parseInt(rs.readLine());
        System.out.println("\n");
        showQuestion(post);

        System.out.println("Write down the reply tou want to answer:");

        String reply = rs.readLine();

        try {
            pstmt = DBA.con.prepareStatement(query);
            pstmt.setInt(1, Main.user_id);
            pstmt.setInt(2, post);
            pstmt.setString(3, reply);
            pstmt.executeUpdate();

        } catch(SQLException e) {
            System.out.println("Fail to answer question!");
            return;
        }

        System.out.println("Success answering question!");

    }

    public void showQuestion(int id) throws SQLException{
        String query = "select context from question where `post_id`=?;";
        ResultSet rt = null;
        String context = null;
        try {
            pstmt = DBA.con.prepareStatement(query);
            pstmt.setInt(1, id);
            rt = pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Fail to load the question.");
        }
        while(rt.next()) {
            context = rt.getString("context");
        }
        System.out.println("The Question is \n\t" + context);

    }

    public void showReply(int order) throws SQLException, IOException {
        String query = null;

        if (order == 0)
            query = "select user_id, reply, likereply from reply where `post_id`=? order by likereply asc;";
        else if (order == 1)
            query = "select user_id, reply, likereply from reply where `post_id`=? order by likereply desc;";

        ResultSet rt = null;
        String reply = null;
        int uid = 0;
        int like = 0;
        int rid = 0;

        showQuestionList(order);

        System.out.print("Choose the post_id to see the replies:");
        rid = Integer.parseInt(rs.readLine());

        try {
            pstmt = DBA.con.prepareStatement(query);
            pstmt.setInt(1, rid);
            rt = pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Fail to load the reply.");
        }

        while(rt.next()) {
            uid = rt.getInt("user_id");
            reply = rt.getString("reply");
            like = rt.getInt("likereply");

            System.out.println("\nReply: " + reply + "\n\n\twritten by " + uid + ", likes " + like);

        }

        if (uid == 0)
           System.out.println("There is no reply...");


    }

    public void showQuestionListCategory(int order) throws IOException, SQLException {
        String query = null;

        if (order == 0)
            query = "select user_id, post_id, context, likes from question where `category`=? order by post_id asc;";
        else if (order == 1)
            query = "select user_id, post_id, context, likes from question where `category`=? order by post_id desc;";

        ResultSet rt = null;

        System.out.println("Choose the category type you want to see:");
        String category = rs.readLine().toUpperCase();
        try {
            pstmt = DBA.con.prepareStatement(query);
            pstmt.setString(1, category);
            rt = pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Fail to show question list.");
        }

        System.out.println("Category: " + category);

        while(rt.next()) {
            int user_id = rt.getInt("user_id");
            int post_id = rt.getInt("post_id");
            String question = rt.getString("context");
            int like = rt.getInt("likes");

            System.out.format("Post ID: %d, Writer: %d\nQuestion:%s\nLikes: %d\n\n", post_id, user_id, question, like);

        }

    }

    public void likeQuestion() throws IOException, SQLException {
        String query1 = "select likes from question where `post_id`=?;";
        String query2 = "update question set likes=? where `post_id`=?;";

        ResultSet rt;
        System.out.println("Select the post_id you likes");

        int post = Integer.parseInt(rs.readLine());
        System.out.println("\n");

        try {
            pstmt = DBA.con.prepareStatement(query1);
            pstmt.setInt(1, post);
            rt = pstmt.executeQuery();

            while (rt.next()){
                int count = rt.getInt("likes");
                count++;
                pstmt = DBA.con.prepareStatement(query2);
                pstmt.setInt(1, count);
                pstmt.setInt(2, post);
                pstmt.executeUpdate();
            }

        } catch(SQLException e) {
            System.out.println("Fail to like the question!");
            return;
        }

        System.out.println("Success liking the question!");

    }

    public void showQuestionListLikes() throws SQLException{
        String query = "select user_id, post_id, category, context, likes from question order by likes desc;";

        ResultSet rt = null;

        try {
            pstmt = DBA.con.prepareStatement(query);
            rt = pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Fail to show question list.");
        }

        System.out.println("Popular Questions: ");

        while(rt.next()) {
            int user_id = rt.getInt("user_id");
            int post_id = rt.getInt("post_id");
            String category = rt.getString("category");
            String question = rt.getString("context");
            int like = rt.getInt("likes");

            System.out.format("Post ID: %d, Category: %s,  Writer: %d\nQuestion:%s\nLikes: %d\n\n", post_id, category, user_id, question, like);
        }
    }

    public void showQuestionListUser(int order) throws SQLException, IOException {
        String query = null;

        if (order == 0)
            query = "select post_id, category, context, likes from question where `user_id`=? order by post_id asc;";
        else if (order == 1)
            query = "select post_id, category, context, likes from question where `user_id`=? order by post_id desc;";

        ResultSet rt = null;

        System.out.print("Please select the user id: ");
        int uid = Integer.parseInt(rs.readLine());

        try {
            pstmt = DBA.con.prepareStatement(query);
            pstmt.setInt(1, uid);
            rt = pstmt.executeQuery();

        } catch (SQLException e) {
            System.out.println("Fail to show " + uid + "'s question list");
        }

        System.out.println("User " + uid + "'s question list");

        while(rt.next()) {
            int post_id = rt.getInt("post_id");
            String category = rt.getString("category");
            String question = rt.getString("context");
            int like = rt.getInt("likes");

            System.out.format("Post ID: %d, Category: %s \nQuestion:%s\nLikes: %d\n", post_id, category, question, like);

        }

    }
}

