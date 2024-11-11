package conveniencestore.service;

import conveniencestore.Product.ProductInterface;

import java.util.ArrayList; //동적배열
import java.util.List;

public class manager implements ManagerInterface {
    private List<ProductInterface> products;

    public manager() {
        products = new ArrayList<>();   // products라는 ArrayList 객체를 생성합니다
    }

    @Override
    // 제품 추가
    public void addProduct(ProductInterface product) {
        products.add(product);
    }

    @Override
    // 제품 삭제
    public void removeProduct(String id) {
        products.removeIf(product -> product.getId().equals(id));
    }

    @Override
    // 제품 조회
    public ProductInterface getProduct(String id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
                // 조건에 맞는 제품 반환, 없으면 null값 반환
    }

    @Override
    // 모든 제품 목록 반환
    public List<ProductInterface> getAllProducts() {
        return products;
    }
}