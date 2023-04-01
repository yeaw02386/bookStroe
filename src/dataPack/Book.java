package dataPack;

import java.util.ArrayList;

public class Book implements Data {
    private String name = "";
    private double price = 0.0;
    private String id = "";
    private int count = 0;
    private ArrayList<Tag> tag = new ArrayList<Tag>();

    public Book() {
    }

    public Book(String name, double price, String id, int count, ArrayList<Tag> tag) {
        this.name = name;
        this.price = price;
        this.id = id;
        this.count = count;
        this.tag = tag;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public String getSPreice() {
        return String.valueOf(this.price);
    }

    public String getSCount() {
        return String.valueOf(this.count);
    }

    public int getCount() {
        return this.count;
    }

    public ArrayList<Tag> getTag() {
        return this.tag;
    }

    public void addBook(int num) {
        this.count += num;
    }

    public void delBook(int num) {
        this.count -= num;
    }

    public boolean filterNameAndTag(String data, ArrayList<Tag> tags) {
        name = name.toLowerCase();
        data = data.toLowerCase();
        boolean t = tags.containsAll(tag);
        boolean n = this.name.contains(data);
        return t && n;
    }

    @Override
    public String toString() {
        String t = "";
        for (var i : tag)
            t += i.toString() + ":";
        return name + ";" + price + ";" + id + ";" + count + ";" + t + ";";
    }

}
