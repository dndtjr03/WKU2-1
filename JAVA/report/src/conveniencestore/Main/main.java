package conveniencestore.Main;

import conveniencestore.Product.ProductInterface;
import conveniencestore.Product.product;
import conveniencestore.service.manager;
import conveniencestore.service.FileHandler;

import java.util.List;
import java.util.Scanner;

public class main {

    private static manager manager = new manager();
    private static FileHandler fileHandler = new FileHandler();
    private static final String FILENAME = "inventory.dat";

    public static void main(String[] args) {
        loadProducts();     // 프로그램 시작 시 기존의 재고 불러오기

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. 제품 추가");
            System.out.println("2. 제품 삭제");
            System.out.println("3. 모든 제품 보기");
            System.out.println("4. 재고 저장");
            System.out.println("5. 현재 재고 불러오기");
            System.out.println("0. 나가기");
            System.out.print("위 옵션 중 원하는 번호를 입력하시오. : ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addProduct(scanner);
                    break;
                case 2:
                    removeProduct(scanner);
                    break;
                case 3:
                    displayAllProducts();
                    break;
                case 4:
                    fileHandler.saveToFile(manager.getAllProducts(), FILENAME);
                    break;
                case 5:
                    manager.getAllProducts().clear();
                    manager.getAllProducts().addAll(fileHandler.readFromFile(FILENAME));
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("잘못된 번호입니다. 다시 입력해주세요.");
            }
        }
    }

    // 제품 추가 메서드
    private static void addProduct(Scanner scanner) {
        System.out.print("제품 ID(품목 번호)를 입력하세요 : ");
        String id = scanner.nextLine();
        System.out.print("제품 이름을 입력하세요 : ");
        String name = scanner.nextLine();
        System.out.print("제품 수량을 입력하세요 : ");
        int quantity = scanner.nextInt();
        System.out.print("제품 가격을 입력하세요 : ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        //새로운 제품 객체 생성
        product product = new product(id, name, quantity, price) {
            @Override
            public String getCategory() {
                return "General";
            }
        };
        manager.addProduct(product);
    }

    // 제품을 제거하는 메서드
    private static void removeProduct(Scanner scanner) {
        System.out.print("삭제할 제품에 ID(품목 번호)를 입력하세요 : ");
        String id = scanner.nextLine();
        manager.removeProduct(id);
    }

    // 모든 제품을 출력하는 메서드
    private static void displayAllProducts() {
        for (ProductInterface product : manager.getAllProducts()) {
            System.out.println("ID : " + product.getId() + System.lineSeparator() + " 이름 : " + product.getName() + 
            System.lineSeparator() + " 수량 : " + product.getQuantity() + 
            System.lineSeparator() + " 가격: " + product.getPrice()+"원" + System.lineSeparator());
        }
    }

    // 제품 목록을 파일에 저장하는 메서드
    private static void saveProducts() {
        fileHandler.saveToFile(manager.getAllProducts(), FILENAME);
        System.out.println("재고가 저장되었습니다.");
    }
    // 파일에서 제품 목록을 불러오는 메서드
     private static void loadProducts() {
        List<ProductInterface> products = fileHandler.readFromFile(FILENAME);
        if (products != null) {
            manager.getAllProducts().clear();
            manager.getAllProducts().addAll(products);
            System.out.println("재고가 불러와졌습니다.");
        } else {
            System.out.println("저장된 재고가 없습니다.");  // 예외처리가 발생했을 경우
        }
    }
}