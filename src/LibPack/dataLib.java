package LibPack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dataPack.Book;
import dataPack.Holder;
import dataPack.Rent;
import dataPack.Shelf;
import dataPack.Tag;
import dataPack.User;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class dataLib {

    public static Rent rentSeach(ArrayList<Rent> rents, String name) {
        for (int i = 0; i < rents.size(); i++) {
            Rent r = rents.get(i);
            if (r.getName().equals(name)) {
                return r;
            }
        }
        return null;
    }

    public static Book bookIdSeach(ArrayList<Book> books, String id) {
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            if (b.getId().equals(id)) {
                return b;
            }
        }
        return null;
    }

    public static Tag tagSeach(ArrayList<Tag> tags, String name) {
        for (int i = 0; i < tags.size(); i++) {
            Tag t = tags.get(i);
            if (t.getName().equals(name)) {
                return t;
            }
        }
        return new Tag();
    }

    public static List<Book> bookSearch(List<Book> books, String name, ArrayList<Tag> tags) {
        List<Book> bookFound;

        bookFound = books.stream().filter(b -> b.filterNameAndTag(name, tags)).toList();
        return bookFound;
    }

    public static void sendData(Shelf shelf, User user) throws IOException {
        Holder hold = Holder.getInstance();
        hold.setShelf(shelf);
        hold.setUser(user);

        IOLib.writerAllFile(CVLib.user2Data(hold.getUsers()), Holder.getUserPath(), false);
        IOLib.writerAllFile(CVLib.book2Data(shelf.getBooks()), Holder.getBookPath(), false);
        IOLib.writerAllFile(CVLib.tag2Data(shelf.getTags()), Holder.getTagPath(), false);
    }

    public static void setScene(Event e, Parent root) {
        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static User userCheck(String name) {
        User user = null;
        for (var i : Holder.getInstance().getUsers()) {
            if (!name.equals(i.getName()))
                continue;
            user = i;
            break;
        }
        return user;
    }

    public static boolean validString(String data, Label err) {
        if (data.equals("") || data == null) {
            err.setVisible(true);
            return false;
        }
        return true;
    }

    public static double validDouble(String data, Label err) {
        double d = -999.0;
        try {
            d = Double.parseDouble(data);
        } catch (Exception er) {
            err.setVisible(true);
            return -999.0;
        }
        if (d < 0)
            return -999.0;
        return d;
    }

    public static int validInt(String data, Label err) {
        int d;
        try {
            d = Integer.parseInt(data);
        } catch (Exception er) {
            err.setVisible(true);
            return -999;
        }
        if (d < 0)
            return -999;
        return d;
    }
}
