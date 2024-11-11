package conveniencestore.Network;

import conveniencestore.Product.ProductInterface;
import conveniencestore.service.manager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    private static final int PORT = 12345;
    private manager manager;

    public server(manager manager) {
        this.manager = manager;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {
                    out.writeObject(manager.getAllProducts());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();    // ����� ���ܰ� �߻��ϸ� ���� Ʈ���̽��� ����մϴ�
        }
    }

    public static void main(String[] args) {
        manager manager = new manager();
        server server = new server(manager);
        server.start();
    }
}