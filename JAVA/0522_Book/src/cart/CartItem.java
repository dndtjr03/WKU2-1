package cart;

import bookitem.Book;

public class CartItem {
    private Book itemBook;
    private String bookID;
    private int quantity;
    private int totalPrice;
    public CartItem(){
    }
    public CartItem(Book booklist){
        this.itemBook = booklist;
        this.bookID = booklist.getBookId();
        this.quantity = 1;
        updateTotalPrice();
    }
    public void updateTotalPrice(){
        totalPrice = this.itemBook.getUintPrice() * this.quantity;
    }
    public Book getItemBook(){
        return itemBook;
    }
    public void setItemBook(Book itemBook){
        this.itemBook = itemBook;
    }
    public void setTotalPrice(int totalPrice){
        this.totalPrice = totalPrice;
    }
    public String getBookID(){
        return bookID;
    }
    public void setBookID(String bookID){
        this.bookID = bookID;
        this.updateTotalPrice();
    }
    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
        this.updateTotalPrice();
    }
    public int getTotalPrice(){
        return totalPrice;
    }
}
