package conveniencestore.service;

import conveniencestore.Product.ProductInterface;

import java.util.ArrayList; //�����迭
import java.util.List;

public class manager implements ManagerInterface {
    private List<ProductInterface> products;

    public manager() {
        products = new ArrayList<>();   // products��� ArrayList ��ü�� �����մϴ�
    }

    @Override
    // ��ǰ �߰�
    public void addProduct(ProductInterface product) {
        products.add(product);
    }

    @Override
    // ��ǰ ����
    public void removeProduct(String id) {
        products.removeIf(product -> product.getId().equals(id));
    }

    @Override
    // ��ǰ ��ȸ
    public ProductInterface getProduct(String id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
                // ���ǿ� �´� ��ǰ ��ȯ, ������ null�� ��ȯ
    }

    @Override
    // ��� ��ǰ ��� ��ȯ
    public List<ProductInterface> getAllProducts() {
        return products;
    }
}