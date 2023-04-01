package LibPack;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import dataPack.Book;
import dataPack.Coin;
import dataPack.Data;
import dataPack.Holder;
import dataPack.Rent;
import dataPack.Shelf;
import dataPack.Tag;
import dataPack.User;

public class IOLib {
    public static void writeFile(Data data,String path,Boolean app) throws IOException{
        File f = new File(path);
        f.createNewFile();
        PrintWriter writer = new PrintWriter(new FileWriter(f,app));
        writer.println(data.toString());

        writer.close();
    }

    public static void writerAllFile(ArrayList<Data> datas,String path,Boolean app)throws IOException{
        File f = new File(path);
        f.createNewFile();
        PrintWriter writer = new PrintWriter(new FileWriter(f,app));
        for(var i:datas) writer.println(i.toString());

        writer.close();
    }


    public static ArrayList<Data> readFile(String path) throws FileNotFoundException{
        ArrayList<Data> datas = new ArrayList<Data>();

        File f = new File(path);
        Scanner reader = new Scanner(f);
        
        while (reader.hasNextLine()){
            Data d;
            String data = reader.nextLine();
            if (path.contains("user")) d = userDecode(data);
            else if(path.contains("book")) d = bookDecode(data);
            else d = tagDecode(data);

            datas.add(d);
        }
        reader.close();
        return datas;
    }

    private static Tag tagDecode(String data){
        return new Tag(data);
    }

    private static Book bookDecode(String data){
        String[] datas = data.split(";");

        String name = datas[0];
        double price = Double.parseDouble(datas[1]);

        String id = datas[2];
        int count = Integer.parseInt(datas[3]);

        ArrayList<Tag> tag = Holder.getInstance().getShelf().getTags();
        ArrayList<Tag> tags = new ArrayList<Tag>();

        if(!data.contains(":")) return new Book(name, price, id, count, tags);;
        String[] dataTag = datas[4].split(":");
        for(var i:dataTag){
            Tag t = dataLib.tagSeach(tag, i);
            tags.add(t);
        }

        return new Book(name, price, id, count, tags);
    }

    private static User userDecode(String data){
        String[] datas = data.split(";");
        String name = datas[0];
        Coin coin = new Coin(Double.parseDouble(datas[1]));
        
        User user = new User(name,coin);
        if(!data.contains(":")) return user;

        String [] rent = datas[2].split(":");
        ArrayList<Book> books = Holder.getInstance().getShelf().getBooks();
        for(var i:rent){
            String[] rs = i.split("#");
            Book b = dataLib.bookIdSeach(books,rs[0]);
            Rent r = new Rent(b,user,rs[1]);
            user.addRent(r);
        }
        
        return user;
    }

    private static ArrayList<Data> loadFile(String path) throws IOException{
        File dir = new File("data");
        if(!dir.exists()) dir.mkdirs();

        File f = new File(path);
        if(!f.exists()) f.createNewFile();
        ArrayList<Data> data = readFile(path);
        return data;
    }

    public static void firstLoad() throws IOException{
        Holder ins = Holder.getInstance();

        ArrayList<Tag> tag = CVLib.data2Tag(loadFile(Holder.getTagPath()));
        Shelf shelf;

        if(tag.size()!=0) shelf = new Shelf(tag);
        else shelf = new Shelf();
        ins.setShelf(shelf);

        ArrayList<Book> books = CVLib.data2Books(loadFile(Holder.getBookPath()));
        shelf.setBooks(books);

        ArrayList<User> user = CVLib.data2Users(loadFile(Holder.getUserPath()));
        ins.setUsers(user);
    }
}
