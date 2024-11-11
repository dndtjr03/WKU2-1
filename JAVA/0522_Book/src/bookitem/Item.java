package bookitem;

public abstract class Item {
    String bookId;
    String name;
    int uintPrice;
    public Item(String bookId, String name, int uintPrice){
        this.bookId = bookId;
        this.name = name;
        this.uintPrice = uintPrice;
    }
    abstract String getBookId();
    abstract String getName();
    abstract int getUintPrice();
    abstract void setBookId(String bookId);
    abstract void setName(String name);
    abstract void setUintPrice(int unitPrice);
}
