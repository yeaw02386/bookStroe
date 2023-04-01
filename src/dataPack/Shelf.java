package dataPack;

import java.util.ArrayList;

import LibPack.CVLib;

public class Shelf {
    private ArrayList<Book> allBook = new ArrayList<Book>();
    private String[] defTag = {"Fiction","General","Economics","Business",
                            "Mystery","Thrillers","Comics","Self-help","Psychology","Success"
                            ,"Computers","Cozy"};
    private ArrayList<Tag> tags;;

    public Shelf() {
        this.tags = CVLib.str2Tag(defTag);
    }

    public Shelf(ArrayList<Tag> tags, ArrayList<Book> books) {
        this.allBook = books;
        this.tags = tags;
    }

    public Shelf(ArrayList<Tag> tag) {
        this.tags = tag;
    }

    public void addNewBook(String name, double price, String id, int count, ArrayList<Tag> tag) {
        Book book = new Book(name, price, id, count, tag);
        allBook.add(book);
    }

    public void incBook(Book book, int inc) {
        int i = allBook.indexOf(book);
        book.addBook(inc);
        allBook.set(i, book);
    }

    public void decBook(Book book, int dec) {
        int i = allBook.indexOf(book);
        book.delBook(dec);
        allBook.set(i, book);
    }

    public void setBooks(ArrayList<Book> books) {
        this.allBook = books;
    }

    public ArrayList<Book> getBooks() {
        return this.allBook;
    }

    public ArrayList<Tag> getTags() {
        return this.tags;
    }

}
