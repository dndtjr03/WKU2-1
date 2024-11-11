package conveniencestore.Product;

import java.io.Serializable;

// 제품의 기본 정보를 저장하는 추상 클래스
public abstract class product implements Serializable, ProductInterface {
    private String id;  // 제품 번호(바코드)
    private String name;    // 제품명
    private int quantity;   // 제품 수량
    private double price;   // 제품 가격

    // 제품의 기본 정보를 초기화하는 생성자
    public product(String id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public double getPrice() {
        return price;
    }

     // 제품의 카테고리를 반환하는 추상 메서드
    @Override
    public abstract String getCategory();
}