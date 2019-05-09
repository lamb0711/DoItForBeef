import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int USER_ADD = 1;
    static int USER_UPDATE = 2;
    static int USER_DEL = 3;
    static int USER_FIND = 4;

    public static void main(String[] args) throws IOException {
        BufferedReader rs = new BufferedReader(new InputStreamReader(System.in));
        DBA db = new DBA();

        if (!db.connect()) {
            System.out.println("연결 실패...");
            return ;
        }

        printMenu();
        int menu = Integer.parseInt(rs.readLine());

        if (menu == USER_ADD){
            user_add(db, rs);
        } else if (menu == USER_UPDATE){

        } else if (menu == USER_FIND) {
            user_search(db, rs);
        } else if (menu == USER_DEL) {

        }
    }

    public static void printMenu() {
        System.out.println(" ************");
        System.out.println(" 1. 유저 추가");
        System.out.println(" 2. 유저 수정");
        System.out.println(" 3. 유저 삭제");
        System.out.println(" 4. 유저 조회");
        System.out.println(" ************");
        System.out.print(" 메뉴를 입력하세요: ");
    }

    public static void user_add(DBA db, BufferedReader rs) throws IOException {
        User user = new User();
        try {
            System.out.print("아이디 이름 비밀번호를 순서대로 입력하세요 :");
            StringTokenizer token = new StringTokenizer(rs.readLine(), " ");
            user.setID(Integer.parseInt(token.nextToken()));
            user.setName(token.nextToken());
            user.setPW(Integer.parseInt(token.nextToken()));

            if (db.add(user))
                System.out.println("가입 성공...");
            else
                System.out.println("가입 실패...");
        } catch(Exception e) {

        }
    }

    public static void user_search(DBA db, BufferedReader rs) throws IOException {
        User user = new User();
        try {
            System.out.print("아이디를 입력하세요 :");

            int id = Integer.parseInt(rs.readLine());

            user = db.search_One(id);

            if (user != null)
                System.out.println(user.toString());
        } catch(Exception e) {

        }

    }

}
