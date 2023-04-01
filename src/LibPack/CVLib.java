package LibPack;

import java.util.ArrayList;

import dataPack.Book;
import dataPack.Data;
import dataPack.Rent;
import dataPack.Tag;
import dataPack.User;

public class CVLib {
    public static ArrayList<Book> data2Books(ArrayList<Data> data) {
        ArrayList<Book> books = new ArrayList<Book>();
        for (Data i : data)
            books.add((Book) i);
        return books;
    }

    public static ArrayList<User> data2Users(ArrayList<Data> data) {
        ArrayList<User> users = new ArrayList<User>();
        for (Data i : data)
            users.add((User) i);
        return users;
    }

    public static ArrayList<Tag> data2Tag(ArrayList<Data> data) {
        ArrayList<Tag> tag = new ArrayList<Tag>();
        for (Data i : data)
            tag.add((Tag) i);
        return tag;
    }

    public static ArrayList<Data> tag2Data(ArrayList<Tag> tag) {
        ArrayList<Data> data = new ArrayList<Data>();
        for (Data i : tag)
            data.add(i);
        return data;
    }

    public static ArrayList<Data> book2Data(ArrayList<Book> book) {
        ArrayList<Data> data = new ArrayList<Data>();
        for (Data i : book)
            data.add(i);
        return data;
    }

    public static ArrayList<Data> user2Data(ArrayList<User> tag) {
        ArrayList<Data> data = new ArrayList<Data>();
        for (Data i : tag)
            data.add(i);
        return data;
    }

    public static ArrayList<String> rent2String(ArrayList<Rent> rent) {
        ArrayList<String> s = new ArrayList<String>();
        for (var i : rent)
            s.add(i.getName());

        return s;
    }

    public static ArrayList<Tag> str2Tag(String[] s) {
        ArrayList<Tag> t = new ArrayList<Tag>();
        for (var i : s)
            t.add(new Tag(i));

        return t;
    }

}
