package edu.handong.database.team6;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Mail {

    private static int mailCount ;//= 0;
    private int mailID = mailCount;
    private static int mailBoxCount;// = 0;
    private static int input;// = -99;
    private static String contents;
    private static int receiver;
    //private static int sender;
    private static String title;
    private static String[] mailBoxName; //= new String[30];

    public Mail(){
        mailCount = 0;
        mailBoxCount = 0;
        input = -99;
        mailBoxName = new String[30];
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
        System.out.println("------------------------");
        System.out.println("***** My Mail Box ******");
        if(mailBoxCount == 0)
            System.out.println("$No customized Mail Box");
        else {
            for(int i = 0 ; i < mailBoxCount; i++) {
                System.out.println(mailBoxName[i]);
            }
        }

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

            if (db.write(++mailCount,getReceiver(), getTitle(), getContents())) {
                System.out.println(" \nThere are already registered user with either same id, same name, or email.\n ");

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

}
