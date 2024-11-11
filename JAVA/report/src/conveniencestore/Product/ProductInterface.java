package conveniencestore.Product;

public interface ProductInterface {
    String getId();
    String getName();
    int getQuantity();
    void setQuantity(int quantity);
    double getPrice();
    String getCategory();
}