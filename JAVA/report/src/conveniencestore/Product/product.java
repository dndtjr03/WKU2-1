package conveniencestore.Product;

import java.io.Serializable;

// ��ǰ�� �⺻ ������ �����ϴ� �߻� Ŭ����
public abstract class product implements Serializable, ProductInterface {
    private String id;  // ��ǰ ��ȣ(���ڵ�)
    private String name;    // ��ǰ��
    private int quantity;   // ��ǰ ����
    private double price;   // ��ǰ ����

    // ��ǰ�� �⺻ ������ �ʱ�ȭ�ϴ� ������
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

     // ��ǰ�� ī�װ��� ��ȯ�ϴ� �߻� �޼���
    @Override
    public abstract String getCategory();
}