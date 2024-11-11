package conveniencestore.service;

import conveniencestore.Product.ProductInterface;
import java.util.List;

public interface ManagerInterface {
    void addProduct(ProductInterface product);
    void removeProduct(String id);
    ProductInterface getProduct(String id);
    List<ProductInterface> getAllProducts();
}