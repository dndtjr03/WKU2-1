package onlineCart;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import bookitem.Book; // ?��?�� - ?��?���? ?��?��
import cart.Cart; // ?��?�� - ?��?���? ?��?��
import member.User; // 추�??/?��?�� - ?��?���? ?��?��?�� 추�?? �? ?��?��
import member.Admin;
import exception.*;
public class exProject1 {
    static final int NUM_BOOK = 3;
    static final int NUM_ITEM = 7;
    static Cart mCart = new Cart();
    static User mUser; // ?��?��?�� 객체 �??�� 추�??
    public static void main(String[] args) {
        //Book[] mBookList = new Book[NUM_BOOK]; *.?��?�� : ?��?�� ?��보�?? ????��?���? ?��?�� ?��?�� 배열 ?��?��, ?��?�� 배열 ?��?��만으�? ?��?��
        //?��?�� - 추�?? �?�?, ?��?�� ?��?�� �??�� 추�??
        Book[] mBookList;
        int mTotalBook = 0;

        Scanner input = new Scanner(System.in);
        //?��?��?�� ?���? ?��?��
        System.out.print("?��?��?�� ?��름을 ?��?��?��?��?�� : ");
        String userName = input.next();
        System.out.print("?��?��처�?? ?��?��?��?��?�� : ");
        int userMobile = input.nextInt();
        mUser = new User(userName, userMobile);

        String greeting = "Welcome to Shopping Mall";
        String tagline = "Welcome to Book Market!";
        boolean quit = false;
        while (!quit) {
            System.out.println("***********************************************");
            System.out.println("\t" + greeting);
            System.out.println("\t" + tagline);
            menuIntroduction(); // 메뉴 출력 메소?�� 추�??

            //?��?��처리 추�??(메뉴 ?��?�� ?���? 발생?�� 처리)
            try {
                System.out.print("메뉴 번호�? ?��?��?��?��?��.");
                int n = input.nextInt();
                if (n < 1 || n > 9) {
                    System.out.println("1~9번까�? ?��?�� �??��?��?��?��.");
                } else {
                    switch (n) {
                        case 1:
                            System.out.println("고객?���? ?��?��");
                            //고객 ?���? ?��?�� 메소?�� 추�??
                            menuGuestInfo(userName, userMobile);
                            break;
                        case 2:
                            System.out.println("?��바구?�� ?��?�� 목록 보기");
                            //?��바구?�� ?��?��?�� 출력 메소?�� 추�??
                            menuCartItemList();
                            break;
                        case 3:
                            System.out.println("?��바구?�� 비우�?");
                            //?��바구?�� ?��?��?�� ?��?�� 메소?�� 추�??
                            menuCartClear();
                            break;
                        case 4:
                            System.out.println("?��바구?�� ?���? 추�??");
                            //?��바구?�� ?��?��?�� 추�?? 메소?�� ?��?��
                            //?��?��?�� ?��?�� ?��록된 �? ?���? 카운?���? 배열?�� 초기?��(?��?��?�� ????��?�� ?��보에 ?��?�� 뱅려 ?���? 조정)
                            mTotalBook = totalFileToBookList();
                            mBookList = new Book[mTotalBook];
                            menuCartAddItem(mBookList);
                            break;
                        case 5:
                            System.out.println("?��바구?�� ?���? ?��?�� 줄이�?");
                            break;
                        case 6:
                            System.out.println("?��바구?�� ?���? ?��?��");
                            break;
                        case 7:
                            System.out.println("?��?���? ?��?��");
                            break;
                        case 8:
                            System.out.println("종료");
                            quit = true;
                            break;
                        case 9:
                            menuAdminLogin();
                            break;
                    }
                }
            }
            catch(Exception ex){
                System.out.println("?��바르�? ?��??? 메뉴 ?��?��?���? 종료");
                quit = true;
            }
        }
    }
    public static void menuIntroduction() {
        System.out.println("***********************************************");
        System.out.println(" 1. 고객 ?���? ?��?��?���? \t4. 바구?��?�� ?���? 추�???���?");
        System.out.println(" 2. ?��바구?�� ?��?�� 목록 보기 \t5. ?��바구?��?�� ?���? ?��?�� 줄이�?");
        System.out.println(" 3. ?��바구?�� 비우�? \t6. ?��바구?��?�� ?���? ?��?��?���?");
        System.out.println(" 7. ?��?���? ?��?��?���? \t8. 종료");
        System.out.println(" 9. �?리자 로그?��");
        System.out.println("***********************************************");
    }
    public static void menuGuestInfo(String name, int mobile) {
        System.out.println("?��?�� 고객 ?���? : ");
        System.out.println("?���? " + mUser.getName() + "   ?��?���? " + mUser.getPhone());
    }
    public static void menuCartItemList() {
        if (mCart.mCartCount >= 0) {
            mCart.printCart();
        }
    }

    public static void menuCartClear() throws CartException {
        // System.out.println("?��바구?�� 비우�?");
        if (mCart.mCartCount == 0)
            //System.out.println("?��바구?��?�� ?��목이 ?��?��?��?��");
            //?��?�� 처리
            throw new CartException("?��바구?��?�� ?��목이 ?��?��?��?��.");
        else {
            System.out.println("?��바구?��?�� 모든 ?��목을 ?��?��?��겠습?���??  Y  | N ");
            Scanner input = new Scanner(System.in);
            String str = input.nextLine();

            if (str.toUpperCase().equals("Y")) {
                System.out.println("?��바구?��?�� 모든 ?��목을 ?��?��?��?��?��?��");
                // mCart.mCartItem = new CartItemBook[NUM_BOOK];
                mCart.deleteBook();
            }
        }
    }
    public static void menuCartAddItem(Book[] booklist) {
        BookList(booklist);
        mCart.printBookList(booklist);
        //?��바구?��?�� ?��?�� 추�?? 기능 ?��?��
        boolean quit = false;
        while (!quit) {
            System.out.print("?��바구?��?�� 추�???�� ?��?��?�� ID�? ?��?��?��?��?�� :");
            Scanner input = new Scanner(System.in);
            String str = input.nextLine();
            boolean flag = false;
            int numId = -1;
            for (int i = 0; i < NUM_BOOK; i++) {
                if (str.equals(booklist[i].getBookId())) {
                    numId = i;
                    flag = true;
                    break;
                }
            }
            if (flag) {
                System.out.println("?��바구?��?�� 추�???��겠습?���??  Y  | N ");
                str = input.nextLine();
                if (str.toUpperCase().equals("Y")) {
                    System.out.println(booklist[numId].getBookId() + " ?��?���? ?��바구?��?�� 추�???��?��?��?��?��.");
                    // 카트?�� ?���?
                    if (/*중복 �? ?���??���? ?��?��(메소?�� 추�??)*/!isCartInBook(booklist[numId].getBookId())) {
                        mCart.insertBook(booklist[numId]);
                    }
                }
                quit = true;
            } else {
                System.out.println("?��보�?? ?��치하�? ?��?��?��?��.");
                break;
            }
        }
    }
    public static boolean isCartInBook(String bookId) {
        return mCart.isCartInBook(bookId);
    }
    public static void BookList(Book[] booklist) {
        setFileToBookList(booklist); // ?��?�� ?��보�?? ?��?�� ?��?�� ?���? 초기?�� ?��?�� 메서?�� ?���?

      /*  booklist[0] = new Book("ISBN1234", "Java Script", 27000);
        booklist[0].setAuthor("�?춘자");
        booklist[0].setDescription("java script 3.0 최신");
        booklist[0].setCategory("?��로그?���? ?��?��");
        booklist[0].setReleaseDate("2018/10/08");

        booklist[1] = new Book("ISBN1235", "PYTHON", 33000);
        booklist[1].setAuthor("�?길자");
        booklist[1].setDescription("PYTHON 중고�?");
        booklist[1].setCategory("?��로그?���? ?��?��");
        booklist[1].setReleaseDate("2022/01/22");

        booklist[2] = new Book("ISBN1236", "java", 22000);
        booklist[2].setAuthor("?��길동");
        booklist[2].setDescription("java ?��문서");
        booklist[2].setCategory("?��로그?��밍언?��");
        booklist[2].setReleaseDate("2019/06/10");*/
    }
    //추�?? : ?��?��?�� ?���? ?��?��?�� �??���? 카운?�� ?��?�� 메소?���? 추�???��.
    public static int totalFileToBookList() {
        try {
            FileReader fr = new FileReader("./src/book.txt");
            BufferedReader reader = new BufferedReader(fr);
            String str;
            int num = 0;
            while ((str = reader.readLine()) != null) {
                if (str.contains("ISBN")) // ?�� ?��?�� 문자?�� ISBN?�� ?��?��?��?�� ?��?���? ?��?�� 카운?��
                    ++num;
            }
            reader.close();
            fr.close();
            return num;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println(System.getProperty("user.dir"));
        }
        return 0;
    }
    //추�?? : ?��?��?�� ?���?, ?��?�� ?���? 목록?�� 배열?�� ????��?��?�� 메서?���? 추�???��.
    public static void setFileToBookList(Book[] booklist) {
        try {
            FileReader fr = new FileReader("./src/book.txt");
            BufferedReader reader = new BufferedReader(fr);
            String str2;
            String[] readBook = new String[7];
            int count = 0;
            while ((str2 = reader.readLine()) != null) {
                if (str2.contains("ISBN")) { // ?��?��?��?�� ?��??? ?�� ?��?�� 문자?�� ISBN?�� ?��?��?��?�� ?��?���? ?��?��?��보에????�� ?�� ?��?�� ?��?�� ????��
                    readBook[0] = str2;
                    readBook[1] = reader.readLine();
                    readBook[2] = reader.readLine();
                    readBook[3] = reader.readLine();
                    readBook[4] = reader.readLine();
                    readBook[5] = reader.readLine();
                    readBook[6] = reader.readLine();
                }
                booklist[count++] = new Book(readBook[0], readBook[1], Integer.parseInt(readBook[2]), readBook[3],
                        readBook[4], readBook[5], readBook[6]);
            }
            reader.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void menuAdminLogin() {
        System.out.println("�?리자 ?��보�?? ?��?��?��?��?��.");

        Scanner input = new Scanner(System.in);
        System.out.print("?��?��?�� : ");
        String adminId = input.next();

        System.out.print("비�??번호 : ");
        String adminPW = input.next();

        Admin admin = new Admin(mUser.getName(), mUser.getPhone());
        if (adminId.equals(admin.getId()) && adminPW.equals(admin.getPassword())) {
            String[] writeBook = new String[7];
            System.out.println("?��?��?��보�?? 추�???��?��겠습?���??  Y  | N ");
            String str = input.next();
            if (str.toUpperCase().equals("Y")) {
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyMMddhhmmss");
                String strDate = formatter.format(date);
                writeBook[0] = "ISBN" + strDate; // ?��?�� ID�? ISBN 문자?���? ?��?��?�� ?���? ?���? ?��보로 ?��?�� ?��?��
                System.out.println("?��?��ID : " + writeBook[0]);
                String st1 = input.nextLine(); // ?�� ?��?�� ?��보�?? ?��?�� 받아 ?��?�� 처리
                System.out.print("?��?���? : ");
                writeBook[1] = input.nextLine();
                System.out.print("�?�? : ");
                writeBook[2] = input.nextLine();
                System.out.print("????�� : ");
                writeBook[3] = input.nextLine();
                System.out.print("?���? : ");
                writeBook[4] = input.nextLine();
                System.out.print("분야 : ");
                writeBook[5] = input.nextLine();
                System.out.print("출판?�� : ");
                writeBook[6] = input.nextLine();
                try {
                    FileWriter fw = new FileWriter("./src/book.txt", true); // 기존 ?��?��?�� ?��보�?? ?��?��?���?(true)

                    for (int i = 0; i < 7; i++)
                        fw.write(writeBook[i] + "\n");
                    fw.close();
                    System.out.println("?�� ?��?�� ?��보�?? ????��?��?��?��?��?��.");
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                System.out.println("?���? " + admin.getName() + " ?��?���? " + admin.getPhone());
                System.out.println("?��?��?�� " + admin.getId() + " 비�??번호 " + admin.getPassword());
            }
        } else
            System.out.println("�?리자 ?��보�?? ?��치하�? ?��?��?��?��.");
    }
}
