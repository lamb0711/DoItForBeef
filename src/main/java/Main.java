import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int USER_LOGIN = 1;
    static int USER_ADD = 2;
    static int USER_CHANGE = 3;
    static int PROGRAM_EXIT = 4;

    public static void main(String[] args) throws IOException {
        BufferedReader rs = new BufferedReader(new InputStreamReader(System.in));
        DBA db = new DBA();

        if (!db.connect()) {
            System.out.println(" Connection fails ... Please try again ...");
            return ;
        }

        int menu = 0;

        while(menu != PROGRAM_EXIT) {
            printMenu();

            menu = Integer.parseInt(rs.readLine());

            if (menu == USER_LOGIN) {
                user_add(db, rs);
            } else if (menu == USER_ADD) {
                user_add(db, rs);
            } else if (menu == USER_CHANGE) {
                user_search(db, rs);
            }
        }

        db.disconnect();

    }

    public static void printMenu() {
        System.out.println("\tNAVER ");
        System.out.println(" ------------------------");
        System.out.println(" 1. Login");
        System.out.println(" 2. Sign Up");
        System.out.println(" 3. Change the password");
        System.out.println(" 4. Exit the program");
        System.out.println(" ------------------------");
        System.out.print(" Select the Menu : "); //print function doesn't work in my PC, please check.
    }

    public static void user_add(DBA db, BufferedReader rs) throws IOException {
        User user = new User();
        try {
            System.out.print(" Please put ID (integer), User name, Password, Phone number, and Email IN ORDER  :"); //print function doesn't work in my PC, please check.
            StringTokenizer token = new StringTokenizer(rs.readLine(), " ");
            user.setID(Integer.parseInt(token.nextToken()));
            user.setName(token.nextToken());
            user.setPW(Integer.parseInt(token.nextToken()));
            user.setPhone(Integer.parseInt(token.nextToken()));
            user.setEmail(token.nextToken());

            if (db.isExist(user.getID(), user.getName(), user.getEmail())) { //there couldn't be !db.isExist when we add
                System.out.println(" \nThere are already registered user with either same id, same name, or email.\n ");
            } else {
                if (db.add(user))
                    System.out.println(" Sign up success ! Welcome to NAVER :) ");
                else
                    System.out.println(" Sign up fail... Please try again");

            }
        } catch(Exception e) {

        }
    }

    public static void user_search(DBA db, BufferedReader rs) throws IOException {
        User user = new User();
        boolean correct = false;

        try {
            System.out.print(" Please put your ID :");

            int id = Integer.parseInt(rs.readLine());

            System.out.print(" Please put your phone number :");

            int phone = Integer.parseInt(rs.readLine());

            correct = db.check_user(id, phone);

            if (correct) {
                System.out.print(" Please put the new password : ");
                int pw = Integer.parseInt(rs.readLine());
                if (db.change_pw(id, pw)) {
                    System.out.println(" Successfully change the password! ");
                }
                else {
                    System.out.println("Cannot change the password ... Try again! ");
                }

            } else {
                System.out.println(" Cannot find the user with provided information.");
            }


        } catch(Exception e) {}

    }

}
