package edu.handong.database.team6;
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

public class Mail {

    private static int mailBoxCount;// = 0;
    private static int input;// = -99;
    private static String contents;
    private static int receiver;
    //private static int sender;
    private static String title;
    private static String sendStatus = null;
    private static String mailBoxName = null;

    public Mail(){
        mailBoxCount = 0;
        input = -99;
        //mailBoxName = new String[30];
    }

    public static void printMailMenu(){
        mailBoxName = null;
        System.out.println("Weclome to Mail service!");
        System.out.println("------------------------");
        System.out.println("0. Write mail");
        System.out.println("------------------------");
        System.out.println("1. All mails");
        System.out.println("2. Mail Box");
        System.out.println("3. Sent Mail Box");
        System.out.println("4. Temporary Mail Box");
        System.out.println("5. To-me Mail Box");
        System.out.println("6. Trash Can");
        System.out.println("7. Spam Mail Box");
        System.out.println("8. Go to Home");
        System.out.println("9. Go to Mail Home");
        System.out.println("------------------------");
    }

    public static void printSendMenu() {
        mailBoxName = null;
        System.out.println("Do you want to send now?(Y/N)");
    }

    public static int getMailInput(){
        return input;
    }

    public static void setMailInput(int inputFromCLI){
        input = inputFromCLI;
    }

    public static void setReceiver(int sendTo){
        receiver = sendTo;
    }

    public static void setTitle(String mailTitle){
        title = mailTitle;
    }

    public static void setContents(String mailContents){
        contents = mailContents;
    }

    public static int getReceiver(){
        return receiver;
    }

    public static String getTitle(){
        return title;
    }

    public static String getContents(){
        return contents;
    }

    public static void writeMail(DBA db, BufferedReader rs) throws IOException {
        try {
            System.out.println("Receiver (INPUT ID:INT)");
            setReceiver(Integer.parseInt(rs.readLine()));

            System.out.println("Title (INPUT TITLE OF YOUR MAIL<30)");
            setTitle(rs.readLine());

            System.out.println("Contents (INPUT CONTENTS OF YOUR MAIL<300 without Enter)");
            setContents(rs.readLine());

            if (db.write(getReceiver(), getTitle(), getContents())) {
                System.out.println(" \nSaved....\n ");
                printSendMenu();
                rs = new BufferedReader(new InputStreamReader(System.in));
                sendStatus = rs.readLine();
                if(sendStatus.equalsIgnoreCase("Y")){
                    //move the mail from temp mail to sent mail
                    if(db.send())
                        System.out.println("Sent " +
                                ansi().fg(BLUE).a("SUCCESSFULLY").reset());
                }
                else{
                    System.out.println("This mail is saved in Temporary Mail Box");
                }
            } /*else {
                if (db.add(user))
                    System.out.println(" Sign up success ! Welcome to NAVER :) ");
                else
                    System.out.println(" Sign up fail... Please try again");
                    }*/
            setContents(null);

        } catch(Exception e) {

        }
    }

    public static void printAllMails(DBA db, BufferedReader rs) throws IOException{
        System.out.println("------------------------");
        System.out.println(""+ db.loginID + "'s All Mail Box");
        db.getAllMails();
    }

    public static void printMails(DBA db, BufferedReader rs) throws IOException{
        System.out.println("------------------------");
        System.out.println(""+ db.loginID + "'s Mail Box");
        db.getMails();
    }

    public static void printSentMails(DBA db, BufferedReader rs) throws IOException{
        System.out.println("------------------------");
        System.out.println(""+ db.loginID + "'s Sent Mail Box");
        db.getSentMails();
    }

    public static void printSpamMails(DBA db, BufferedReader rs) throws IOException{
        System.out.println("------------------------");
        System.out.println(""+ db.loginID + "'s Spam Mail Box");
        db.getSpamMails();
    }

    public static void printTemporaryMails(DBA db, BufferedReader rs) throws IOException{
        System.out.println("------------------------");
        System.out.println(""+ db.loginID + "'s Temporary Mail Box");
        db.getTemporaryMails();
    }

    public static void printToMeMails(DBA db, BufferedReader rs) throws IOException{
        System.out.println("------------------------");
        System.out.println(""+ db.loginID + "'s To-me Mail Box");
        db.getToMeMails();
    }

    public static void printTrashCan(DBA db, BufferedReader rs) throws IOException{
        System.out.println("------------------------");
        System.out.println(""+ db.loginID + "'s Trash Can");
        db.getTrashCan();
    }
    //각각 메일함 별로 기능 정리
    public static void printMailBoxMenu(DBA db, BufferedReader rs, int select){
        System.out.println("------------------------");
        System.out.println("Select Menu:");

        if(select == Main.AlLMAILS){

            System.out.println("0. Select");
            System.out.println("1. Go back");
        }
        else if(select == Main.MAILBOX){

            System.out.println("0. Select");
            System.out.println("1. Go Back");
        }
        else if(select == Main.SPAMMAILBOX){

            System.out.println("0. Select");
            System.out.println("1. Go Back");

        }
        else if(select == Main.SENTMAILBOX){

            System.out.println("0. Select");
            System.out.println("1. Go Back");
        }
        else if(select == Main.TEMPORARYMAILBOX){

            System.out.println("0. Select");
            System.out.println("1. Go Back");
        }
        else if(select == Main.TOMEMAILBOX){

            System.out.println("0. Select");
            System.out.println("1. Go Back");
        }
        else if(select == Main.TRASHCAN){
            System.out.println("0. Select");
            System.out.println("1. Clean up");
            System.out.println("2. Go Back");
        }
    }

    public static void getAllMailcontents(int AllmailInput, DBA db, BufferedReader rs){
        db.getAllMailcontent(AllmailInput);
    }

    public static void getMailcontents(int mailInput, DBA db, BufferedReader rs){
        db.getMailcontent(mailInput);
    }

    public static void getSentMailcontents(int mailInput, DBA db, BufferedReader rs){
        db.getSentMailcontent(mailInput);
    }

    public static void getTempMailcontents(int mailInput, DBA db, BufferedReader rs){
        db.getTempMailcontent(mailInput);
    }

    public static void getToMeMailcontents(int mailInput, DBA db, BufferedReader rs){
        db.getToMeMailcontent(mailInput);
    }

    public static void getTrashcontents(int mailInput, DBA db, BufferedReader rs){
        db.getTrashcontent(mailInput);
    }

    public static void getSpamcontents(int mailInput, DBA db, BufferedReader rs){
        db.getSpamcontent(mailInput);
    }

    public static void replyInAllMail(int id, DBA db, BufferedReader rs)throws IOException{
       try{
           System.out.println("Title (INPUT TITLE OF YOUR MAIL<30)");
           setTitle(rs.readLine());

        System.out.println("Contents (INPUT CONTENTS OF YOUR MAIL<300 without Enter)");
        setContents(rs.readLine());

        if (db.replyInAllMailQ(id, getTitle(), getContents())) {
            System.out.println(" \nSaved....\n ");
            printSendMenu();
            rs = new BufferedReader(new InputStreamReader(System.in));
            sendStatus = rs.readLine();
            if(sendStatus.equalsIgnoreCase("Y")){
                //move the mail from temp mail to sent mail
                if(db.send())
                    System.out.println("Sent "+
                            ansi().fg(BLUE).a("SUCCESSFULLY").reset());
            }
            else{
                System.out.println("This mail is saved in Temporary Mail Box");
            }
        } /*else {
                if (db.add(user))
                    System.out.println(" Sign up success ! Welcome to NAVER :) ");
                else
                    System.out.println(" Sign up fail... Please try again");
                    }*/
        setContents(null);

    } catch(Exception e) {

    }
    }

    public static void replyInMail(int id, DBA db, BufferedReader rs)throws IOException{
        try{
            System.out.println("Title (INPUT TITLE OF YOUR MAIL<30)");
            setTitle(rs.readLine());

            System.out.println("Contents (INPUT CONTENTS OF YOUR MAIL<300 without Enter)");
            setContents(rs.readLine());

            if (db.replyInMailQ(id, getTitle(), getContents())) {
                System.out.println(" \nSaved....\n ");
                printSendMenu();
                rs = new BufferedReader(new InputStreamReader(System.in));
                sendStatus = rs.readLine();
                if(sendStatus.equalsIgnoreCase("Y")){
                    //move the mail from temp mail to sent mail
                    if(db.send())
                        System.out.println("Sent " +
                                ansi().fg(BLUE).a("SUCCESSFULLY").reset());
                }
                else{
                    System.out.println("This mail is saved in Temporary Mail Box");
                }
            } /*else {
                if (db.add(user))
                    System.out.println(" Sign up success ! Welcome to NAVER :) ");
                else
                    System.out.println(" Sign up fail... Please try again");
                    }*/
            setContents(null);

        } catch(Exception e) {

        }
    }

    public static void passInAllMail(int id, DBA db, BufferedReader rs)throws IOException{
        try{
            System.out.println("Receiver (INPUT ID:INT)");
            setReceiver(Integer.parseInt(rs.readLine()));

            if (db.passInAllMailQ(id, getReceiver())) {
                System.out.println(" \nSaved....\n ");
                printSendMenu();
                rs = new BufferedReader(new InputStreamReader(System.in));
                sendStatus = rs.readLine();
                if(sendStatus.equalsIgnoreCase("Y")){
                    //move the mail from temp mail to sent mail
                    if(db.send())
                        System.out.println("Sent "+
                                ansi().fg(BLUE).a("SUCCESSFULLY").reset());
                }
                else{
                    System.out.println("This mail is saved in Temporary Mail Box");
                }
            } /*else {
                if (db.add(user))
                    System.out.println(" Sign up success ! Welcome to NAVER :) ");
                else
                    System.out.println(" Sign up fail... Please try again");
                    }*/
            setContents(null);

        } catch(Exception e) {

        }
    }

    public static void passInMail(int id, DBA db, BufferedReader rs)throws IOException{
        try{
            System.out.println("Receiver (INPUT ID:INT)");
            setReceiver(Integer.parseInt(rs.readLine()));

            if (db.passInMailQ(id, getReceiver())) {
                System.out.println(" \nSaved....\n ");
                printSendMenu();
                rs = new BufferedReader(new InputStreamReader(System.in));
                sendStatus = rs.readLine();
                if(sendStatus.equalsIgnoreCase("Y")){
                    //move the mail from temp mail to sent mail
                    if(db.send())
                        System.out.println("Sent " +
                                ansi().fg(BLUE).a("SUCCESSFULLY").reset());
                }
                else{
                    System.out.println("This mail is saved in Temporary Mail Box");
                }
            } /*else {
                if (db.add(user))
                    System.out.println(" Sign up success ! Welcome to NAVER :) ");
                else
                    System.out.println(" Sign up fail... Please try again");
                    }*/
            setContents(null);

        } catch(Exception e) {

        }
    }

    public static void passInSentMail(int id, DBA db, BufferedReader rs)throws IOException{
        try{
            System.out.println("Receiver (INPUT ID:INT)");
            setReceiver(Integer.parseInt(rs.readLine()));

            if (db.passInSentMailQ(id, getReceiver())) {
                System.out.println(" \nSaved....\n ");
                printSendMenu();
                rs = new BufferedReader(new InputStreamReader(System.in));
                sendStatus = rs.readLine();
                if(sendStatus.equalsIgnoreCase("Y")){
                    //move the mail from temp mail to sent mail
                    if(db.send())
                        System.out.println("Sent " +
                                ansi().fg(BLUE).a("SUCCESSFULLY").reset());
                }
                else{
                    System.out.println("This mail is saved in Temporary Mail Box");
                }
            } /*else {
                if (db.add(user))
                    System.out.println(" Sign up success ! Welcome to NAVER :) ");
                else
                    System.out.println(" Sign up fail... Please try again");
                    }*/
            setContents(null);

        } catch(Exception e) {

        }
    }

    public static void passInToMeMail(int id, DBA db, BufferedReader rs)throws IOException{
        try{
            System.out.println("Receiver (INPUT ID:INT)");
            setReceiver(Integer.parseInt(rs.readLine()));

            if (db.passInToMeMailQ(id, getReceiver())) {
                System.out.println(" \nSaved....\n ");
                printSendMenu();
                rs = new BufferedReader(new InputStreamReader(System.in));
                sendStatus = rs.readLine();
                if(sendStatus.equalsIgnoreCase("Y")){
                    //move the mail from temp mail to sent mail
                    if(db.send())

                        System.out.println("Sent "+
                                ansi().fg(BLUE).a("SUCCESSFULLY").reset());
                }
                else{
                    System.out.println("This mail is saved in Temporary Mail Box");
                }
            } /*else {
                if (db.add(user))
                    System.out.println(" Sign up success ! Welcome to NAVER :) ");
                else
                    System.out.println(" Sign up fail... Please try again");
                    }*/
            setContents(null);

        } catch(Exception e) {

        }
    }

    public static void markStarInAllMail(int id, DBA db, BufferedReader rs)throws IOException{
            db.markStarInAllMailQ(id);
    }

    public static void markStarInMail(int id, DBA db, BufferedReader rs)throws IOException{
        db.markStarInMailQ(id);
    }

    public static void markStarInSentMail(int id, DBA db, BufferedReader rs)throws IOException{
        db.markStarInSentMailQ(id);
    }

    public static void markStarInToMeMail(int id, DBA db, BufferedReader rs)throws IOException{
        db.markStarInToMeMailQ(id);
    }

    public static void deleteFromAllMail(int id, DBA db, BufferedReader rs)throws IOException{
        db.deleteFromAllMailQ(id);
    }

    public static void deleteFromMail(int id, DBA db, BufferedReader rs)throws IOException{
        db.deleteFromMailQ(id);
    }

    public static void deleteFromSentMail(int id, DBA db, BufferedReader rs)throws IOException{
        db.deleteFromSentMailQ(id);
    }

    public static void deleteFromTempMail(int id, DBA db, BufferedReader rs)throws IOException{
        db.deleteFromTempMailQ(id);
    }

    public static void deleteFromToMeMail(int id, DBA db, BufferedReader rs)throws IOException{
        db.deleteFromToMeMailQ(id);
    }

    public static void deleteFromTrashCan(int id, DBA db, BufferedReader rs)throws IOException{
        db.deleteFromTrashCanQ(id);
    }

    public static void deleteFromSpam(int id, DBA db, BufferedReader rs)throws IOException{
       if( db.deleteFromSpamQ(id)) {
           System.out.printf("The mail is removed ");
           System.out.println(ansi().fg(BLUE).a("COMPLETELY").reset());
       }

    }

    public static void toSpamFromAll(int id, DBA db, BufferedReader rs)throws IOException{
        db.toSpamFromAllQ(id);
    }

    public static void toSpamFromMail(int id, DBA db, BufferedReader rs)throws IOException{
        db.toSpamFromMailQ(id);
    }

    public static void toSpamFromToMeMail(int id, DBA db, BufferedReader rs)throws IOException{
        db.toSpamFromToMeMailQ(id);
    }

    public static void modifyTempMail(int id, DBA db, BufferedReader rs)throws IOException{
        try{
            System.out.println("Contents (INPUT String)");
            setContents(rs.readLine());

            if (db.modifyTempMailQ(id, getContents())) {
                System.out.println(" \nSaved....\n ");

                }
            /*else {
                if (db.add(user))
                    System.out.println(" Sign up success ! Welcome to NAVER :) ");
                else
                    System.out.println(" Sign up fail... Please try again");
                    }*/
            setContents(null);

        } catch(Exception e) {

        }
    }

    public static void sendTempMail(int id, DBA db, BufferedReader rs)throws IOException{

            try {
                    printSendMenu();
                    rs = new BufferedReader(new InputStreamReader(System.in));
                    sendStatus = rs.readLine();
                    if(sendStatus.equalsIgnoreCase("Y")){
                        //move the mail from temp mail to sent mail
                        if(db.sendTemp(id)) {
                            System.out.println("Sent "+
                                    ansi().fg(BLUE).a("SUCCESSFULLY").reset());
                            setContents(null);
                        }
                        else{
                        System.out.println("Failed");
                          }
                } /*else {
                if (db.add(user))
                    System.out.println(" Sign up success ! Welcome to NAVER :) ");
                else
                    System.out.println(" Sign up fail... Please try again");
                    }*/


            } catch(Exception e) {

            }

    }


}
