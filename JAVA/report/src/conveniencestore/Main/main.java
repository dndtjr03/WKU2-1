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
        loadProducts();     // ���α׷� ���� �� ������ ��� �ҷ�����

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. ��ǰ �߰�");
            System.out.println("2. ��ǰ ����");
            System.out.println("3. ��� ��ǰ ����");
            System.out.println("4. ��� ����");
            System.out.println("5. ���� ��� �ҷ�����");
            System.out.println("0. ������");
            System.out.print("�� �ɼ� �� ���ϴ� ��ȣ�� �Է��Ͻÿ�. : ");
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
                    System.out.println("�߸��� ��ȣ�Դϴ�. �ٽ� �Է����ּ���.");
            }
        }
    }

    // ��ǰ �߰� �޼���
    private static void addProduct(Scanner scanner) {
        System.out.print("��ǰ ID(ǰ�� ��ȣ)�� �Է��ϼ��� : ");
        String id = scanner.nextLine();
        System.out.print("��ǰ �̸��� �Է��ϼ��� : ");
        String name = scanner.nextLine();
        System.out.print("��ǰ ������ �Է��ϼ��� : ");
        int quantity = scanner.nextInt();
        System.out.print("��ǰ ������ �Է��ϼ��� : ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        //���ο� ��ǰ ��ü ����
        product product = new product(id, name, quantity, price) {
            @Override
            public String getCategory() {
                return "General";
            }
        };
        manager.addProduct(product);
    }

    // ��ǰ�� �����ϴ� �޼���
    private static void removeProduct(Scanner scanner) {
        System.out.print("������ ��ǰ�� ID(ǰ�� ��ȣ)�� �Է��ϼ��� : ");
        String id = scanner.nextLine();
        manager.removeProduct(id);
    }

    // ��� ��ǰ�� ����ϴ� �޼���
    private static void displayAllProducts() {
        for (ProductInterface product : manager.getAllProducts()) {
            System.out.println("ID : " + product.getId() + System.lineSeparator() + " �̸� : " + product.getName() + 
            System.lineSeparator() + " ���� : " + product.getQuantity() + 
            System.lineSeparator() + " ����: " + product.getPrice()+"��" + System.lineSeparator());
        }
    }

    // ��ǰ ����� ���Ͽ� �����ϴ� �޼���
    private static void saveProducts() {
        fileHandler.saveToFile(manager.getAllProducts(), FILENAME);
        System.out.println("��� ����Ǿ����ϴ�.");
    }
    // ���Ͽ��� ��ǰ ����� �ҷ����� �޼���
     private static void loadProducts() {
        List<ProductInterface> products = fileHandler.readFromFile(FILENAME);
        if (products != null) {
            manager.getAllProducts().clear();
            manager.getAllProducts().addAll(products);
            System.out.println("��� �ҷ��������ϴ�.");
        } else {
            System.out.println("����� ��� �����ϴ�.");  // ����ó���� �߻����� ���
        }
    }
}