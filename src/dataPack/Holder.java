package dataPack;

import java.util.ArrayList;

import javafx.stage.Stage;

public final class Holder {
    private Shelf shelf;
    private User user;
    private ArrayList<User> users;
    private Stage gstage = null;
    private Book selectBook;

    private final static Holder INSTANCE = new Holder();

    private static String userPath = "data/user.txt";
    private static String bookPath = "data/book.txt";
    private static String tagPath = "data/tag.txt";

    private Holder() {
    }

    public static Holder getInstance() {
        return INSTANCE;
    }

    public Shelf getShelf() {
        return this.shelf;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static void setUserPath(String path) {
        userPath = path;
    }

    public static void setBookPath(String path) {
        bookPath = path;
    }

    public static void setTagPath(String path) {
        tagPath = path;
    }

    public static String getUserPath() {
        return userPath;
    }

    public static String getBookPath() {
        return bookPath;
    }

    public static String getTagPath() {
        return tagPath;
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public Stage getGstage() {
        return this.gstage;
    }

    public void setGstage(Stage gstage) {
        this.gstage = gstage;
    }

    public Book getSelectBook() {
        return this.selectBook;
    }

    public void setSelectBook(Book selectBook) {
        this.selectBook = selectBook;
    }

}
