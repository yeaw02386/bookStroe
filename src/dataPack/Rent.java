package dataPack;

import java.sql.Timestamp;

public class Rent {
    private Book book;
    private String id = "";
    private String name = "";
    private String bookId = "";

    public Rent() {
    }

    public Rent(Book book, User user) {
        this.book = book;
        this.name = book.getName();
        this.bookId = book.getId();
        Timestamp t = new Timestamp(System.currentTimeMillis());
        this.id = bookId + user.getName() + t.getTime();
    }

    public Rent(Book book, User user, String id) {
        this.book = book;
        this.name = book.getName();
        this.bookId = book.getId();
        this.id = id;
    }

    public double cost() {
        return 0.02 * book.getPrice();
    }

    public double calCost(int day) {
        double cost = 0.02 * book.getPrice() * day + 10;
        return cost;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Book getBook() {
        return book;
    }

    @Override
    public String toString() {
        return bookId + "#" + id;
    }

}