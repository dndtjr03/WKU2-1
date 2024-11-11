package onlineCart;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import bookitem.Book; // ?ˆ˜? • - ?Œ¨?‚¤ì§? ?ˆ˜? •
import cart.Cart; // ?ˆ˜? • - ?Œ¨?‚¤ì§? ?ˆ˜? •
import member.User; // ì¶”ê??/?ˆ˜? • - ?Œ¨?‚¤ì§? ?´?˜?Š¤ ì¶”ê?? ë°? ?ˆ˜? •
import member.Admin;
import exception.*;
public class exProject1 {
    static final int NUM_BOOK = 3;
    static final int NUM_ITEM = 7;
    static Cart mCart = new Cart();
    static User mUser; // ?‚¬?š©? ê°ì²´ ë³??ˆ˜ ì¶”ê??
    public static void main(String[] args) {
        //Book[] mBookList = new Book[NUM_BOOK]; *.?ˆ˜? • : ?„?„œ ? •ë³´ë?? ????¥?•˜ê¸? ?œ„?•œ ? •?  ë°°ì—´ ?‚­? œ, ?™?¼ ë°°ì—´ ?„ ?–¸ë§Œìœ¼ë¡? ?ˆ˜? •
        //?ˆ˜? • - ì¶”ê?? ë¶?ë¶?, ?„?„œ ?ˆ˜?Ÿ‰ ë³??ˆ˜ ì¶”ê??
        Book[] mBookList;
        int mTotalBook = 0;

        Scanner input = new Scanner(System.in);
        //?‚¬?š©? ? •ë³? ?…? ¥
        System.out.print("?‹¹?‹ ?˜ ?´ë¦„ì„ ?…? ¥?•˜?„¸?š” : ");
        String userName = input.next();
        System.out.print("?—°?½ì²˜ë?? ?…? ¥?•˜?„¸?š” : ");
        int userMobile = input.nextInt();
        mUser = new User(userName, userMobile);

        String greeting = "Welcome to Shopping Mall";
        String tagline = "Welcome to Book Market!";
        boolean quit = false;
        while (!quit) {
            System.out.println("***********************************************");
            System.out.println("\t" + greeting);
            System.out.println("\t" + tagline);
            menuIntroduction(); // ë©”ë‰´ ì¶œë ¥ ë©”ì†Œ?“œ ì¶”ê??

            //?˜ˆ?™¸ì²˜ë¦¬ ì¶”ê??(ë©”ë‰´ ?„ ?ƒ ?˜¤ë¥? ë°œìƒ?‹œ ì²˜ë¦¬)
            try {
                System.out.print("ë©”ë‰´ ë²ˆí˜¸ë¥? ?„ ?ƒ?•˜?„¸?š”.");
                int n = input.nextInt();
                if (n < 1 || n > 9) {
                    System.out.println("1~9ë²ˆê¹Œì§? ?…? ¥ ê°??Š¥?•©?‹ˆ?‹¤.");
                } else {
                    switch (n) {
                        case 1:
                            System.out.println("ê³ ê°? •ë³? ?™•?¸");
                            //ê³ ê° ? •ë³? ?™•?¸ ë©”ì†Œ?“œ ì¶”ê??
                            menuGuestInfo(userName, userMobile);
                            break;
                        case 2:
                            System.out.println("?¥ë°”êµ¬?‹ˆ ?ƒ?’ˆ ëª©ë¡ ë³´ê¸°");
                            //?¥ë°”êµ¬?‹ˆ ?•„?´?…œ ì¶œë ¥ ë©”ì†Œ?“œ ì¶”ê??
                            menuCartItemList();
                            break;
                        case 3:
                            System.out.println("?¥ë°”êµ¬?‹ˆ ë¹„ìš°ê¸?");
                            //?¥ë°”êµ¬?‹ˆ ?•„?´?…œ ?‚­? œ ë©”ì†Œ?“œ ì¶”ê??
                            menuCartClear();
                            break;
                        case 4:
                            System.out.println("?¥ë°”êµ¬?‹ˆ ?•­ëª? ì¶”ê??");
                            //?¥ë°”êµ¬?‹ˆ ?•„?´?…œ ì¶”ê?? ë©”ì†Œ?“œ ?ˆ˜? •
                            //?ŒŒ?¼?„ ?½?–´ ?“±ë¡ëœ ì±? ? •ë³? ì¹´ìš´?Š¸ë¡? ë°°ì—´?„ ì´ˆê¸°?™”(?ŒŒ?¼?— ????¥?œ ? •ë³´ì— ?”°?¼ ë±…ë ¤ ?¬ê¸? ì¡°ì •)
                            mTotalBook = totalFileToBookList();
                            mBookList = new Book[mTotalBook];
                            menuCartAddItem(mBookList);
                            break;
                        case 5:
                            System.out.println("?¥ë°”êµ¬?‹ˆ ?•­ëª? ?ˆ˜?Ÿ‰ ì¤„ì´ê¸?");
                            break;
                        case 6:
                            System.out.println("?¥ë°”êµ¬?‹ˆ ?•­ëª? ?‚­? œ");
                            break;
                        case 7:
                            System.out.println("?˜?ˆ˜ì¦? ?‘œ?‹œ");
                            break;
                        case 8:
                            System.out.println("ì¢…ë£Œ");
                            quit = true;
                            break;
                        case 9:
                            menuAdminLogin();
                            break;
                    }
                }
            }
            catch(Exception ex){
                System.out.println("?˜¬ë°”ë¥´ì§? ?•Š??? ë©”ë‰´ ?„ ?ƒ?œ¼ë¡? ì¢…ë£Œ");
                quit = true;
            }
        }
    }
    public static void menuIntroduction() {
        System.out.println("***********************************************");
        System.out.println(" 1. ê³ ê° ? •ë³? ?™•?¸?•˜ê¸? \t4. ë°”êµ¬?‹ˆ?— ?•­ëª? ì¶”ê???•˜ê¸?");
        System.out.println(" 2. ?¥ë°”êµ¬?‹ˆ ?ƒ?’ˆ ëª©ë¡ ë³´ê¸° \t5. ?¥ë°”êµ¬?‹ˆ?˜ ?•­ëª? ?ˆ˜?Ÿ‰ ì¤„ì´ê¸?");
        System.out.println(" 3. ?¥ë°”êµ¬?‹ˆ ë¹„ìš°ê¸? \t6. ?¥ë°”êµ¬?‹ˆ?˜ ?•­ëª? ?‚­? œ?•˜ê¸?");
        System.out.println(" 7. ?˜?ˆ˜ì¦? ?‘œ?‹œ?•˜ê¸? \t8. ì¢…ë£Œ");
        System.out.println(" 9. ê´?ë¦¬ì ë¡œê·¸?¸");
        System.out.println("***********************************************");
    }
    public static void menuGuestInfo(String name, int mobile) {
        System.out.println("?˜„?¬ ê³ ê° ? •ë³? : ");
        System.out.println("?´ë¦? " + mUser.getName() + "   ?—°?½ì²? " + mUser.getPhone());
    }
    public static void menuCartItemList() {
        if (mCart.mCartCount >= 0) {
            mCart.printCart();
        }
    }

    public static void menuCartClear() throws CartException {
        // System.out.println("?¥ë°”êµ¬?‹ˆ ë¹„ìš°ê¸?");
        if (mCart.mCartCount == 0)
            //System.out.println("?¥ë°”êµ¬?‹ˆ?— ?•­ëª©ì´ ?—†?Šµ?‹ˆ?‹¤");
            //?˜ˆ?™¸ ì²˜ë¦¬
            throw new CartException("?¥ë°”êµ¬?‹ˆ?— ?•­ëª©ì´ ?—†?Šµ?‹ˆ?‹¤.");
        else {
            System.out.println("?¥ë°”êµ¬?‹ˆ?— ëª¨ë“  ?•­ëª©ì„ ?‚­? œ?•˜ê² ìŠµ?‹ˆê¹??  Y  | N ");
            Scanner input = new Scanner(System.in);
            String str = input.nextLine();

            if (str.toUpperCase().equals("Y")) {
                System.out.println("?¥ë°”êµ¬?‹ˆ?— ëª¨ë“  ?•­ëª©ì„ ?‚­? œ?–ˆ?Šµ?‹ˆ?‹¤");
                // mCart.mCartItem = new CartItemBook[NUM_BOOK];
                mCart.deleteBook();
            }
        }
    }
    public static void menuCartAddItem(Book[] booklist) {
        BookList(booklist);
        mCart.printBookList(booklist);
        //?¥ë°”êµ¬?‹ˆ?— ?„?„œ ì¶”ê?? ê¸°ëŠ¥ ?ˆ˜? •
        boolean quit = false;
        while (!quit) {
            System.out.print("?¥ë°”êµ¬?‹ˆ?— ì¶”ê???•  ?„?„œ?˜ IDë¥? ?…? ¥?•˜?„¸?š” :");
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
                System.out.println("?¥ë°”êµ¬?‹ˆ?— ì¶”ê???•˜ê² ìŠµ?‹ˆê¹??  Y  | N ");
                str = input.nextLine();
                if (str.toUpperCase().equals("Y")) {
                    System.out.println(booklist[numId].getBookId() + " ?„?„œê°? ?¥ë°”êµ¬?‹ˆ?— ì¶”ê???˜?—ˆ?Šµ?‹ˆ?‹¤.");
                    // ì¹´íŠ¸?— ?„£ê¸?
                    if (/*ì¤‘ë³µ ì±? ?—¬ë¶??¸ì§? ?™•?¸(ë©”ì†Œ?“œ ì¶”ê??)*/!isCartInBook(booklist[numId].getBookId())) {
                        mCart.insertBook(booklist[numId]);
                    }
                }
                quit = true;
            } else {
                System.out.println("? •ë³´ê?? ?¼ì¹˜í•˜ì§? ?•Š?Šµ?‹ˆ?‹¤.");
                break;
            }
        }
    }
    public static boolean isCartInBook(String bookId) {
        return mCart.isCartInBook(bookId);
    }
    public static void BookList(Book[] booklist) {
        setFileToBookList(booklist); // ?„?„œ ? •ë³´ë?? ?½?–´ ?„?„œ ? •ë³? ì´ˆê¸°?™” ?•˜?Š” ë©”ì„œ?“œ ?˜¸ì¶?

      /*  booklist[0] = new Book("ISBN1234", "Java Script", 27000);
        booklist[0].setAuthor("ê¹?ì¶˜ì");
        booklist[0].setDescription("java script 3.0 ìµœì‹ ");
        booklist[0].setCategory("?”„ë¡œê·¸?˜ë°? ?–¸?–´");
        booklist[0].setReleaseDate("2018/10/08");

        booklist[1] = new Book("ISBN1235", "PYTHON", 33000);
        booklist[1].setAuthor("ê¹?ê¸¸ì");
        booklist[1].setDescription("PYTHON ì¤‘ê³ ê¸?");
        booklist[1].setCategory("?”„ë¡œê·¸?˜ë°? ?–¸?–´");
        booklist[1].setReleaseDate("2022/01/22");

        booklist[2] = new Book("ISBN1236", "java", 22000);
        booklist[2].setAuthor("?™ê¸¸ë™");
        booklist[2].setDescription("java ?…ë¬¸ì„œ");
        booklist[2].setCategory("?”„ë¡œê·¸?˜ë°ì–¸?–´");
        booklist[2].setReleaseDate("2019/06/10");*/
    }
    //ì¶”ê?? : ?ŒŒ?¼?„ ?½ê³? ?„?„œ?˜ ê°??ˆ˜ë¥? ì¹´ìš´?Œ… ?•˜?Š” ë©”ì†Œ?“œë¥? ì¶”ê???•¨.
    public static int totalFileToBookList() {
        try {
            FileReader fr = new FileReader("./src/book.txt");
            BufferedReader reader = new BufferedReader(fr);
            String str;
            int num = 0;
            while ((str = reader.readLine()) != null) {
                if (str.contains("ISBN")) // ?•œ ?–‰?— ë¬¸ì?—´ ISBN?´ ?¬?•¨?˜?–´ ?ˆ?œ¼ë©? ?„?„œ ì¹´ìš´?Š¸
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
    //ì¶”ê?? : ?ŒŒ?¼?„ ?½ê³?, ?„?„œ ? •ë³? ëª©ë¡?„ ë°°ì—´?— ????¥?•˜?Š” ë©”ì„œ?“œë¥? ì¶”ê???•¨.
    public static void setFileToBookList(Book[] booklist) {
        try {
            FileReader fr = new FileReader("./src/book.txt");
            BufferedReader reader = new BufferedReader(fr);
            String str2;
            String[] readBook = new String[7];
            int count = 0;
            while ((str2 = reader.readLine()) != null) {
                if (str2.contains("ISBN")) { // ?ŒŒ?¼?—?„œ ?½??? ?•œ ?–‰?— ë¬¸ì?—´ ISBN?´ ?¬?•¨?˜?–´ ?ˆ?œ¼ë©? ?„?„œ? •ë³´ì—????•´ ?•œ ?–‰?”© ?½?–´ ????¥
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
        System.out.println("ê´?ë¦¬ì ? •ë³´ë?? ?…? ¥?•˜?‹œ?˜¤.");

        Scanner input = new Scanner(System.in);
        System.out.print("?•„?´?”” : ");
        String adminId = input.next();

        System.out.print("ë¹„ë??ë²ˆí˜¸ : ");
        String adminPW = input.next();

        Admin admin = new Admin(mUser.getName(), mUser.getPhone());
        if (adminId.equals(admin.getId()) && adminPW.equals(admin.getPassword())) {
            String[] writeBook = new String[7];
            System.out.println("?„?„œ? •ë³´ë?? ì¶”ê???•˜?‹œê² ìŠµ?‹ˆê¹??  Y  | N ");
            String str = input.next();
            if (str.toUpperCase().equals("Y")) {
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyMMddhhmmss");
                String strDate = formatter.format(date);
                writeBook[0] = "ISBN" + strDate; // ?„?„œ IDë¥? ISBN ë¬¸ì?—´ê³? ?…?˜„?¬ ?‚ ì§? ?‹œê°? ? •ë³´ë¡œ ??™ ?ƒ?„±
                System.out.println("?„?„œID : " + writeBook[0]);
                String st1 = input.nextLine(); // ?•œ ?–‰?”© ? •ë³´ë?? ?…? ¥ ë°›ì•„ ?…? ¥ ì²˜ë¦¬
                System.out.print("?„?„œëª? : ");
                writeBook[1] = input.nextLine();
                System.out.print("ê°?ê²? : ");
                writeBook[2] = input.nextLine();
                System.out.print("???? : ");
                writeBook[3] = input.nextLine();
                System.out.print("?„¤ëª? : ");
                writeBook[4] = input.nextLine();
                System.out.print("ë¶„ì•¼ : ");
                writeBook[5] = input.nextLine();
                System.out.print("ì¶œíŒ?¼ : ");
                writeBook[6] = input.nextLine();
                try {
                    FileWriter fw = new FileWriter("./src/book.txt", true); // ê¸°ì¡´ ?ŒŒ?¼?— ? •ë³´ë?? ?´?–´?“°ê¸?(true)

                    for (int i = 0; i < 7; i++)
                        fw.write(writeBook[i] + "\n");
                    fw.close();
                    System.out.println("?ƒˆ ?„?„œ ? •ë³´ê?? ????¥?˜?—ˆ?Šµ?‹ˆ?‹¤.");
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                System.out.println("?´ë¦? " + admin.getName() + " ?—°?½ì²? " + admin.getPhone());
                System.out.println("?•„?´?”” " + admin.getId() + " ë¹„ë??ë²ˆí˜¸ " + admin.getPassword());
            }
        } else
            System.out.println("ê´?ë¦¬ì ? •ë³´ê?? ?¼ì¹˜í•˜ì§? ?•Š?Šµ?‹ˆ?‹¤.");
    }
}
