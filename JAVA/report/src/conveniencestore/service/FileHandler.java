package conveniencestore.service;

import conveniencestore.Product.ProductInterface;

import java.io.*;
import java.util.List;

public class FileHandler {

    // 제품 목록을 파일에 저장하는 메서드
    public void saveToFile(List<ProductInterface> products, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(products);
        } catch (IOException e) {
            e.printStackTrace();    // 입출력 예외가 발생하면 스택 트레이스를 출력합니다.
        }
    }

    // 파일에서 제품 목록을 불러오는 메서드
    public List<ProductInterface> readFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<ProductInterface>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();    // 입출력 예외 또는 클래스 찾기 예외가 발생하면 스택 트레이스를 출력합니다
        }
        return null;    // 예외 발생 시 null을 반환합니다.
    }
}