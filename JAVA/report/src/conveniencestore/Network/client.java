package conveniencestore.Network;

import conveniencestore.Product.ProductInterface;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
            List<ProductInterface> products = (List<ProductInterface>) in.readObject();
            for (ProductInterface product : products) {
                System.out.println("ID: " + product.getId() + ", Name: " + product.getName() +
                                   ", Quantity: " + product.getQuantity() + ", Price: " + product.getPrice());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();    // ����� ���� �Ǵ� Ŭ���� ã�� ���ܰ� �߻��ϸ� ���� Ʈ���̽��� ����մϴ�.
        }
    }
}