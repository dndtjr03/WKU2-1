package conveniencestore.service;

import conveniencestore.Product.ProductInterface;

import java.io.*;
import java.util.List;

public class FileHandler {

    // ��ǰ ����� ���Ͽ� �����ϴ� �޼���
    public void saveToFile(List<ProductInterface> products, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(products);
        } catch (IOException e) {
            e.printStackTrace();    // ����� ���ܰ� �߻��ϸ� ���� Ʈ���̽��� ����մϴ�.
        }
    }

    // ���Ͽ��� ��ǰ ����� �ҷ����� �޼���
    public List<ProductInterface> readFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<ProductInterface>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();    // ����� ���� �Ǵ� Ŭ���� ã�� ���ܰ� �߻��ϸ� ���� Ʈ���̽��� ����մϴ�
        }
        return null;    // ���� �߻� �� null�� ��ȯ�մϴ�.
    }
}