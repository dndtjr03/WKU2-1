package onlineCart;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import bookitem.Book; // ??  - ?¨?€μ§? ?? 
import cart.Cart; // ??  - ?¨?€μ§? ?? 
import member.User; // μΆκ??/??  - ?¨?€μ§? ?΄??€ μΆκ?? λ°? ?? 
import member.Admin;
import exception.*;
public class exProject1 {
    static final int NUM_BOOK = 3;
    static final int NUM_ITEM = 7;
    static Cart mCart = new Cart();
    static User mUser; // ?¬?©? κ°μ²΄ λ³?? μΆκ??
    public static void main(String[] args) {
        //Book[] mBookList = new Book[NUM_BOOK]; *.??  : ?? ? λ³΄λ?? ????₯?κΈ? ?? ? ?  λ°°μ΄ ?­? , ??Ό λ°°μ΄ ? ?Έλ§μΌλ‘? ?? 
        //??  - μΆκ?? λΆ?λΆ?, ?? ?? λ³?? μΆκ??
        Book[] mBookList;
        int mTotalBook = 0;

        Scanner input = new Scanner(System.in);
        //?¬?©? ? λ³? ?? ₯
        System.out.print("?Ή? ? ?΄λ¦μ ?? ₯??Έ? : ");
        String userName = input.next();
        System.out.print("?°?½μ²λ?? ?? ₯??Έ? : ");
        int userMobile = input.nextInt();
        mUser = new User(userName, userMobile);

        String greeting = "Welcome to Shopping Mall";
        String tagline = "Welcome to Book Market!";
        boolean quit = false;
        while (!quit) {
            System.out.println("***********************************************");
            System.out.println("\t" + greeting);
            System.out.println("\t" + tagline);
            menuIntroduction(); // λ©λ΄ μΆλ ₯ λ©μ? μΆκ??

            //??Έμ²λ¦¬ μΆκ??(λ©λ΄ ? ? ?€λ₯? λ°μ? μ²λ¦¬)
            try {
                System.out.print("λ©λ΄ λ²νΈλ₯? ? ???Έ?.");
                int n = input.nextInt();
                if (n < 1 || n > 9) {
                    System.out.println("1~9λ²κΉμ§? ?? ₯ κ°??₯?©??€.");
                } else {
                    switch (n) {
                        case 1:
                            System.out.println("κ³ κ°? λ³? ??Έ");
                            //κ³ κ° ? λ³? ??Έ λ©μ? μΆκ??
                            menuGuestInfo(userName, userMobile);
                            break;
                        case 2:
                            System.out.println("?₯λ°κ΅¬? ?? λͺ©λ‘ λ³΄κΈ°");
                            //?₯λ°κ΅¬? ??΄? μΆλ ₯ λ©μ? μΆκ??
                            menuCartItemList();
                            break;
                        case 3:
                            System.out.println("?₯λ°κ΅¬? λΉμ°κΈ?");
                            //?₯λ°κ΅¬? ??΄? ?­?  λ©μ? μΆκ??
                            menuCartClear();
                            break;
                        case 4:
                            System.out.println("?₯λ°κ΅¬? ?­λͺ? μΆκ??");
                            //?₯λ°κ΅¬? ??΄? μΆκ?? λ©μ? ?? 
                            //??Ό? ?½?΄ ?±λ‘λ μ±? ? λ³? μΉ΄μ΄?Έλ‘? λ°°μ΄? μ΄κΈ°?(??Ό? ????₯? ? λ³΄μ ?°?Ό λ±λ € ?¬κΈ? μ‘°μ )
                            mTotalBook = totalFileToBookList();
                            mBookList = new Book[mTotalBook];
                            menuCartAddItem(mBookList);
                            break;
                        case 5:
                            System.out.println("?₯λ°κ΅¬? ?­λͺ? ?? μ€μ΄κΈ?");
                            break;
                        case 6:
                            System.out.println("?₯λ°κ΅¬? ?­λͺ? ?­? ");
                            break;
                        case 7:
                            System.out.println("??μ¦? ??");
                            break;
                        case 8:
                            System.out.println("μ’λ£");
                            quit = true;
                            break;
                        case 9:
                            menuAdminLogin();
                            break;
                    }
                }
            }
            catch(Exception ex){
                System.out.println("?¬λ°λ₯΄μ§? ???? λ©λ΄ ? ??Όλ‘? μ’λ£");
                quit = true;
            }
        }
    }
    public static void menuIntroduction() {
        System.out.println("***********************************************");
        System.out.println(" 1. κ³ κ° ? λ³? ??Έ?κΈ? \t4. λ°κ΅¬?? ?­λͺ? μΆκ???κΈ?");
        System.out.println(" 2. ?₯λ°κ΅¬? ?? λͺ©λ‘ λ³΄κΈ° \t5. ?₯λ°κ΅¬?? ?­λͺ? ?? μ€μ΄κΈ?");
        System.out.println(" 3. ?₯λ°κ΅¬? λΉμ°κΈ? \t6. ?₯λ°κ΅¬?? ?­λͺ? ?­? ?κΈ?");
        System.out.println(" 7. ??μ¦? ???κΈ? \t8. μ’λ£");
        System.out.println(" 9. κ΄?λ¦¬μ λ‘κ·Έ?Έ");
        System.out.println("***********************************************");
    }
    public static void menuGuestInfo(String name, int mobile) {
        System.out.println("??¬ κ³ κ° ? λ³? : ");
        System.out.println("?΄λ¦? " + mUser.getName() + "   ?°?½μ²? " + mUser.getPhone());
    }
    public static void menuCartItemList() {
        if (mCart.mCartCount >= 0) {
            mCart.printCart();
        }
    }

    public static void menuCartClear() throws CartException {
        // System.out.println("?₯λ°κ΅¬? λΉμ°κΈ?");
        if (mCart.mCartCount == 0)
            //System.out.println("?₯λ°κ΅¬?? ?­λͺ©μ΄ ??΅??€");
            //??Έ μ²λ¦¬
            throw new CartException("?₯λ°κ΅¬?? ?­λͺ©μ΄ ??΅??€.");
        else {
            System.out.println("?₯λ°κ΅¬?? λͺ¨λ  ?­λͺ©μ ?­? ?κ² μ΅?κΉ??  Y  | N ");
            Scanner input = new Scanner(System.in);
            String str = input.nextLine();

            if (str.toUpperCase().equals("Y")) {
                System.out.println("?₯λ°κ΅¬?? λͺ¨λ  ?­λͺ©μ ?­? ??΅??€");
                // mCart.mCartItem = new CartItemBook[NUM_BOOK];
                mCart.deleteBook();
            }
        }
    }
    public static void menuCartAddItem(Book[] booklist) {
        BookList(booklist);
        mCart.printBookList(booklist);
        //?₯λ°κ΅¬?? ?? μΆκ?? κΈ°λ₯ ?? 
        boolean quit = false;
        while (!quit) {
            System.out.print("?₯λ°κ΅¬?? μΆκ???  ??? IDλ₯? ?? ₯??Έ? :");
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
                System.out.println("?₯λ°κ΅¬?? μΆκ???κ² μ΅?κΉ??  Y  | N ");
                str = input.nextLine();
                if (str.toUpperCase().equals("Y")) {
                    System.out.println(booklist[numId].getBookId() + " ??κ°? ?₯λ°κ΅¬?? μΆκ?????΅??€.");
                    // μΉ΄νΈ? ?£κΈ?
                    if (/*μ€λ³΅ μ±? ?¬λΆ??Έμ§? ??Έ(λ©μ? μΆκ??)*/!isCartInBook(booklist[numId].getBookId())) {
                        mCart.insertBook(booklist[numId]);
                    }
                }
                quit = true;
            } else {
                System.out.println("? λ³΄κ?? ?ΌμΉνμ§? ??΅??€.");
                break;
            }
        }
    }
    public static boolean isCartInBook(String bookId) {
        return mCart.isCartInBook(bookId);
    }
    public static void BookList(Book[] booklist) {
        setFileToBookList(booklist); // ?? ? λ³΄λ?? ?½?΄ ?? ? λ³? μ΄κΈ°? ?? λ©μ? ?ΈμΆ?

      /*  booklist[0] = new Book("ISBN1234", "Java Script", 27000);
        booklist[0].setAuthor("κΉ?μΆμ");
        booklist[0].setDescription("java script 3.0 μ΅μ ");
        booklist[0].setCategory("?λ‘κ·Έ?λ°? ?Έ?΄");
        booklist[0].setReleaseDate("2018/10/08");

        booklist[1] = new Book("ISBN1235", "PYTHON", 33000);
        booklist[1].setAuthor("κΉ?κΈΈμ");
        booklist[1].setDescription("PYTHON μ€κ³ κΈ?");
        booklist[1].setCategory("?λ‘κ·Έ?λ°? ?Έ?΄");
        booklist[1].setReleaseDate("2022/01/22");

        booklist[2] = new Book("ISBN1236", "java", 22000);
        booklist[2].setAuthor("?κΈΈλ");
        booklist[2].setDescription("java ?λ¬Έμ");
        booklist[2].setCategory("?λ‘κ·Έ?λ°μΈ?΄");
        booklist[2].setReleaseDate("2019/06/10");*/
    }
    //μΆκ?? : ??Ό? ?½κ³? ??? κ°??λ₯? μΉ΄μ΄? ?? λ©μ?λ₯? μΆκ???¨.
    public static int totalFileToBookList() {
        try {
            FileReader fr = new FileReader("./src/book.txt");
            BufferedReader reader = new BufferedReader(fr);
            String str;
            int num = 0;
            while ((str = reader.readLine()) != null) {
                if (str.contains("ISBN")) // ? ?? λ¬Έμ?΄ ISBN?΄ ?¬?¨??΄ ??Όλ©? ?? μΉ΄μ΄?Έ
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
    //μΆκ?? : ??Ό? ?½κ³?, ?? ? λ³? λͺ©λ‘? λ°°μ΄? ????₯?? λ©μ?λ₯? μΆκ???¨.
    public static void setFileToBookList(Book[] booklist) {
        try {
            FileReader fr = new FileReader("./src/book.txt");
            BufferedReader reader = new BufferedReader(fr);
            String str2;
            String[] readBook = new String[7];
            int count = 0;
            while ((str2 = reader.readLine()) != null) {
                if (str2.contains("ISBN")) { // ??Ό?? ?½??? ? ?? λ¬Έμ?΄ ISBN?΄ ?¬?¨??΄ ??Όλ©? ??? λ³΄μ????΄ ? ??© ?½?΄ ????₯
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
        System.out.println("κ΄?λ¦¬μ ? λ³΄λ?? ?? ₯???€.");

        Scanner input = new Scanner(System.in);
        System.out.print("??΄? : ");
        String adminId = input.next();

        System.out.print("λΉλ??λ²νΈ : ");
        String adminPW = input.next();

        Admin admin = new Admin(mUser.getName(), mUser.getPhone());
        if (adminId.equals(admin.getId()) && adminPW.equals(admin.getPassword())) {
            String[] writeBook = new String[7];
            System.out.println("??? λ³΄λ?? μΆκ????κ² μ΅?κΉ??  Y  | N ");
            String str = input.next();
            if (str.toUpperCase().equals("Y")) {
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyMMddhhmmss");
                String strDate = formatter.format(date);
                writeBook[0] = "ISBN" + strDate; // ?? IDλ₯? ISBN λ¬Έμ?΄κ³? ???¬ ? μ§? ?κ°? ? λ³΄λ‘ ?? ??±
                System.out.println("??ID : " + writeBook[0]);
                String st1 = input.nextLine(); // ? ??© ? λ³΄λ?? ?? ₯ λ°μ ?? ₯ μ²λ¦¬
                System.out.print("??λͺ? : ");
                writeBook[1] = input.nextLine();
                System.out.print("κ°?κ²? : ");
                writeBook[2] = input.nextLine();
                System.out.print("???? : ");
                writeBook[3] = input.nextLine();
                System.out.print("?€λͺ? : ");
                writeBook[4] = input.nextLine();
                System.out.print("λΆμΌ : ");
                writeBook[5] = input.nextLine();
                System.out.print("μΆν?Ό : ");
                writeBook[6] = input.nextLine();
                try {
                    FileWriter fw = new FileWriter("./src/book.txt", true); // κΈ°μ‘΄ ??Ό? ? λ³΄λ?? ?΄?΄?°κΈ?(true)

                    for (int i = 0; i < 7; i++)
                        fw.write(writeBook[i] + "\n");
                    fw.close();
                    System.out.println("? ?? ? λ³΄κ?? ????₯???΅??€.");
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                System.out.println("?΄λ¦? " + admin.getName() + " ?°?½μ²? " + admin.getPhone());
                System.out.println("??΄? " + admin.getId() + " λΉλ??λ²νΈ " + admin.getPassword());
            }
        } else
            System.out.println("κ΄?λ¦¬μ ? λ³΄κ?? ?ΌμΉνμ§? ??΅??€.");
    }
}
