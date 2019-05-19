package edu.handong.database.team6;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.StringTokenizer;

public class Main {



    //start menu

    static int USER_LOGIN = 1;
    static int USER_ADD = 2;
    static int USER_CHANGE = 3;
    static int PROGRAM_EXIT = 4;

    //log-in menu
    private static final int JISIKIN = 1;
    private static final int MAIL = 2;
    private static final int MUSIC = 3;
    private static final int SHOPPING = 4;
    private static final int LOGOUT = 5;
    static final int LOG_IN = 99;

    
    public static int user_id;

    static final int LOG_OUT = 98;
    private static int userStatus = LOG_OUT;

    //mail menu
    private static final int WRITEMAIL = 0;
    protected static final int AlLMAILS = 1;
    protected static final int MAILBOX = 2;
    protected static final int SENTMAILBOX = 3;
    protected static final int TEMPORARYMAILBOX = 4;
    protected static final int TOMEMAILBOX = 5;
    protected static final int TRASHCAN = 6;
    protected static final int SPAMMAILBOX = 7;
    protected static final int GOTOMAILHOME = 8;
    protected static final int GOTOHOME = 9;

    //Mail Box
    protected static int currentMailID = -999;



    public static void main(String[] args) throws Exception {
        BufferedReader rs = new BufferedReader(new InputStreamReader(System.in));
        DBA db = new DBA();
        Mail mail = new Mail();
        Shopping shopping = new Shopping();

        if (!db.connect()) {
            System.out.println(" Connection fails ... Please try again ...");
            return;
        }

        int menu = 0;
        int loginMenu = -999;
        while (menu != PROGRAM_EXIT) {
            printMenu();

            menu = Integer.parseInt(rs.readLine());

            if (menu == USER_LOGIN) {
                user_login(db, rs);
                if (userStatus == LOG_IN) {
                    printLoginMenu();
                    loginMenu = Integer.parseInt(rs.readLine());

                    if (loginMenu == JISIKIN) {
                        JisikIn jisikIn = new JisikIn();
                        jisikIn.selectMenu();
                    } else if (loginMenu == MAIL) {
                        while (mail.getMailInput() != GOTOHOME) {
                            mail.printMailMenu();
                            mail.setMailInput(Integer.parseInt(rs.readLine()));

                            if (mail.getMailInput() == WRITEMAIL) {
                                mail.writeMail(db, rs);
                            } else if (mail.getMailInput() == AlLMAILS) {
                                //code
                                mail.printAllMails(db, rs);
                                mail.printMailBoxMenu(db, rs, mail.getMailInput());
                                int AllmailInput = Integer.parseInt(rs.readLine());
                                if (mail.getMailInput() == AlLMAILS && 0 == AllmailInput) {
                                    //select
                                    System.out.println("Input Mail ID");
                                    AllmailInput = Integer.parseInt(rs.readLine());
                                    mail.getAllMailcontents(AllmailInput, db, rs);
                                    //get title and get mail contents
                                    System.out.println("1. Reply");
                                    System.out.println("2. Pass to others");
                                    System.out.println("3. Star mark"); //Trigger
                                    System.out.println("4. Delete a Mail"); //Trigger
                                    System.out.println("5. Move to Spam"); //Trigger
                                    System.out.println("6. Go Back");
                                    System.out.println("------------------------");
                                    currentMailID = AllmailInput;
                                    AllmailInput = Integer.parseInt(rs.readLine());
                                    if (AllmailInput == 1) {
                                        //reply
                                        mail.replyInAllMail(currentMailID, db, rs);
                                    } else if (AllmailInput == 2) {
                                        //pass
                                        mail.passInAllMail(currentMailID, db, rs);
                                    } else if (AllmailInput == 3) {
                                        //star -> trigger
                                        mail.markStarInAllMail(currentMailID, db, rs);
                                    } else if (AllmailInput == 4) {
                                        //delete -> trigger
                                        mail.deleteFromAllMail(currentMailID, db, rs);
                                    } else if (AllmailInput == 5) {
                                        //spam -> trigger
                                        mail.toSpamFromAll(currentMailID, db, rs);
                                    }

                                }


                            } else if (mail.getMailInput() == MAILBOX) {
                                //code
                                mail.printMails(db, rs);
                                mail.printMailBoxMenu(db, rs, mail.getMailInput());
                                int mailInput = Integer.parseInt(rs.readLine());
                                if (0 == mailInput) {
                                    //select
                                    System.out.println("Input Mail ID");
                                    mailInput = Integer.parseInt(rs.readLine());
                                    mail.getMailcontents(mailInput, db, rs);
                                    //get title and get mail contents
                                    System.out.println("1. Reply");
                                    System.out.println("2. Pass to others");
                                    System.out.println("3. Star mark"); //Trigger to AllMails
                                    System.out.println("4. Delete a Mail");
                                    System.out.println("5. Move to Spam");
                                    System.out.println("6. Go Back");
                                    System.out.println("------------------------");

                                    currentMailID = mailInput;
                                    mailInput = Integer.parseInt(rs.readLine());

                                    if (1 == mailInput) {
                                        //reply
                                        mail.replyInMail(currentMailID, db, rs);
                                    } else if (2 == mailInput) {
                                        //pass
                                        mail.passInMail(currentMailID, db, rs);
                                    } else if (3 == mailInput) {
                                        //star -> trigger
                                        mail.markStarInMail(currentMailID, db, rs);
                                    } else if (4 == mailInput) {
                                        //delete
                                        mail.deleteFromMail(currentMailID, db, rs);
                                    } else if (5 == mailInput) {
                                        //spam
                                        mail.toSpamFromMail(currentMailID, db, rs);
                                    }
                                }
                            } else if (mail.getMailInput() == SENTMAILBOX) {
                                //code
                                mail.printSentMails(db, rs);
                                mail.printMailBoxMenu(db, rs, mail.getMailInput());
                                int SentmailInput = Integer.parseInt(rs.readLine());
                                //select
                                if (SentmailInput == 0) {
                                    System.out.println("Input Mail ID");
                                    SentmailInput = Integer.parseInt(rs.readLine());
                                    mail.getSentMailcontents(SentmailInput, db, rs);
                                    System.out.println("1. Pass to others");
                                    System.out.println("2. Star mark");
                                    System.out.println("3. Delete a Mail");
                                    System.out.println("4. Go Back");
                                    System.out.println("------------------------");
                                    currentMailID = SentmailInput;
                                    SentmailInput = Integer.parseInt(rs.readLine());
                                    if (SentmailInput == 1) {
                                        //pass
                                        mail.passInSentMail(currentMailID, db, rs);
                                    } else if (SentmailInput == 2) {
                                        //star
                                        mail.markStarInSentMail(currentMailID, db, rs);
                                    } else if (SentmailInput == 3) {
                                        //delete
                                        mail.deleteFromSentMail(currentMailID, db, rs);
                                    }
                                }

                            } else if (mail.getMailInput() == TEMPORARYMAILBOX) {
                                //code
                                mail.printTemporaryMails(db, rs);
                                mail.printMailBoxMenu(db, rs, mail.getMailInput());
                                int TempMailInput = Integer.parseInt(rs.readLine());
                                if (TempMailInput == 0) {
                                    System.out.println("Input Mail ID");
                                    TempMailInput = Integer.parseInt(rs.readLine());
                                    mail.getTempMailcontents(TempMailInput, db, rs);
                                    System.out.println("1. Modify");
                                    System.out.println("2. Send");
                                    System.out.println("3. Delete a Mail");
                                    System.out.println("4. Go Back");
                                    System.out.println("------------------------");

                                    currentMailID = TempMailInput;
                                    TempMailInput = Integer.parseInt(rs.readLine());
                                    if (TempMailInput == 1) {
                                        //modifiy
                                        mail.modifyTempMail(currentMailID, db, rs);
                                    } else if (TempMailInput == 2) {
                                        //send
                                        mail.sendTempMail(currentMailID, db, rs);
                                    } else if (TempMailInput == 3) {
                                        //Delete
                                        mail.deleteFromTempMail(currentMailID, db, rs);
                                    }
                                }
                            } else if (mail.getMailInput() == TOMEMAILBOX) {
                                //code
                                mail.printToMeMails(db, rs);
                                mail.printMailBoxMenu(db, rs, mail.getMailInput());
                                int ToMeMailInput = Integer.parseInt(rs.readLine());
                                if (ToMeMailInput == 0) {
                                    System.out.println("Input Mail ID");
                                    ToMeMailInput = Integer.parseInt(rs.readLine());
                                    mail.getToMeMailcontents(ToMeMailInput, db, rs);
                                    System.out.println("1. Pass to others");
                                    System.out.println("2. Star mark");
                                    System.out.println("3. Delete a Mail");
                                    System.out.println("4. Move to Spam");
                                    System.out.println("5. Go Back");
                                    System.out.println("------------------------");
                                    currentMailID = ToMeMailInput;
                                    ToMeMailInput = Integer.parseInt(rs.readLine());
                                    if (ToMeMailInput == 1) {
                                        //pass
                                        mail.passInToMeMail(currentMailID, db, rs);
                                    } else if (ToMeMailInput == 2) {
                                        //star
                                        mail.markStarInToMeMail(currentMailID, db, rs);
                                    } else if (ToMeMailInput == 3) {
                                        //Delete
                                        mail.deleteFromToMeMail(currentMailID, db, rs);
                                    } else if (ToMeMailInput == 4) {
                                        //Spam
                                        mail.toSpamFromToMeMail(currentMailID, db, rs);

                                    }
                                }
                            } else if (mail.getMailInput() == TRASHCAN) {
                                //code
                                mail.printTrashCan(db, rs);
                                mail.printMailBoxMenu(db, rs, mail.getMailInput());
                                int TrashInput = Integer.parseInt(rs.readLine());
                                if (TrashInput == 0) {
                                    System.out.println("Input Mail ID");
                                    TrashInput = Integer.parseInt(rs.readLine());
                                    mail.getTrashcontents(TrashInput, db, rs);
                                    System.out.println("1. Completely Delete a Mail");
                                    System.out.println("2. Go Back");
                                    System.out.println("------------------------");

                                    currentMailID = TrashInput;
                                    TrashInput = Integer.parseInt(rs.readLine());
                                    if (TrashInput == 1) {
                                        //delete
                                        mail.deleteFromTrashCan(currentMailID, db, rs);
                                    }

                                } else if (TrashInput == 1) {
                                    System.out.println("Do you want to Clean up ALL Mail?(Y/N)");
                                    rs = new BufferedReader(new InputStreamReader(System.in));
                                    String selection = rs.readLine();
                                    if (selection.equalsIgnoreCase("Y")) {
                                        //move the mail from temp mail to sent mail
                                        if (db.cleanUp())
                                            System.out.println("Work " +
                                                    ansi().fg(BLUE).a("SUCCESSFULLY").reset());
                                    } else {
                                        System.out.println("Failed");
                                    }
                                }
                            } else if (mail.getMailInput() == SPAMMAILBOX) {
                                //code
                                mail.printSpamMails(db, rs);
                                mail.printMailBoxMenu(db, rs, mail.getMailInput());
                                int SpamInput = Integer.parseInt(rs.readLine());

                                if (SpamInput == 0) {
                                    System.out.println("Input Mail ID");
                                    SpamInput = Integer.parseInt(rs.readLine());
                                    mail.getSpamcontents(SpamInput, db, rs);
                                    System.out.println("1. Delete a Mail");
                                    System.out.println("2. Go Back");
                                    System.out.println("------------------------");
                                    currentMailID = SpamInput;
                                    SpamInput = Integer.parseInt(rs.readLine());

                                    if (SpamInput == 1) {
                                        //delete
                                        mail.deleteFromSpam(currentMailID, db, rs);
                                    }
                                }
                            } else if (mail.getMailInput() == GOTOMAILHOME) {
                                continue;
                            } else if (mail.getMailInput() == GOTOHOME) {
                                continue;
                            }
                        }
                    } else if (loginMenu == MUSIC) {
                        //do somethings
                        Music music = new Music();
                        music.seletMenu();
                    } else if (loginMenu == SHOPPING) {
                        Shopping shoppingUser = new Shopping();
                        shopping.printShoppingMenu();
                    } else if (loginMenu == LOGOUT) {
                        userStatus = LOG_OUT;
                    }
                }
            } else if (menu == USER_ADD) {
                user_add(db, rs);
            } else if (menu == USER_CHANGE) {
                user_search(db, rs);
            }
            db.disconnect();
        }
    }

        public static void printMenu () {
            System.out.println("\tNAVER ");
            System.out.println(" ------------------------");
            System.out.println(" 1. Login");
            System.out.println(" 2. Sign Up");
            System.out.println(" 3. Change the password");
            System.out.println(" 4. Exit the program");
            System.out.println(" ------------------------");
            System.out.println(" Select the Menu : "); //print function doesn't work in my PC, please check.
        }

    public static void printLoginMenu(){
        System.out.println("\tUser Menu ");
        System.out.println(" ------------------------");
        System.out.println(" 1. JisikIn");
        System.out.println(" 2. Mail");
        System.out.println(" 3. Music");
        System.out.println(" 4. Shopping");
        System.out.println(" 5. Log-out");
        System.out.println(" ------------------------");
        System.out.println(" Select the Menu : ");
    }

    public static void user_login(DBA db, BufferedReader rs) throws IOException{
        User user = new User();
        try {
            System.out.println(" Please put ID (integer) and Password IN ORDER  :"); //print function doesn't work in my PC, please check.
            StringTokenizer token = new StringTokenizer(rs.readLine(), " ");
            user.setID(Integer.parseInt(token.nextToken()));
            user.setPW(Integer.parseInt(token.nextToken()));
            
            user_id = user.getID();

            if (db.logIn(user.getID(), user.getPW())) {
                System.out.println(" \nWelcome " + user_id + ".\n ");
                userStatus = LOG_IN;
            } else {
                    System.out.println("Wrong ID or Password, Please Try again or sign up");
                }
            } catch (Exception e) {

            }
        }

        public static void user_add (DBA db, BufferedReader rs) throws IOException {
            User user = new User();
            try {
                System.out.println(" Please put ID (integer), User name, Password, Phone number, and Email IN ORDER  :"); //print function doesn't work in my PC, please check.
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
                        System.out.println(" Sign up " +
                                ansi().fg(BLUE).a("SUCCESSFULLY").reset() +
                                "Welcome to NAVER :) ");
                    else
                        System.out.println(" Sign up fail... Please try again");

                }
            } catch (Exception e) {

            }
        }

        public static void user_search (DBA db, BufferedReader rs) throws IOException {
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
                        System.out.println("change the password " +
                                ansi().fg(BLUE).a("SUCCESSFULLY").reset());
                    } else {
                        System.out.println("Cannot change the password ... Try again! ");
                    }

                } else {
                    System.out.println(" Cannot find the user with provided information.");
                }


            } catch (Exception e) {
            }

        }

    }


