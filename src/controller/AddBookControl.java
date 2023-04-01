package controller;

import java.io.IOException;
import java.util.ArrayList;

import LibPack.dataLib;
import dataPack.Book;
import dataPack.Tag;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddBookControl extends GobalContro {
    private static boolean succed = false;

    public AddBookControl() {
        super();
    }

    @FXML
    Group tag_G;
    @FXML
    TextField name_newbook, price_newbook, amount_newbook, id_T;
    @FXML
    Label err_name, err_price, err_amount, err_id, err_addBook, err_tag, succ_addBook;

    @Override
    public boolean controlSpecial() {
        if (succed)
            succ_addBook.setVisible(succed);
        return false;
    }

    @FXML
    public void addBook(Event e) throws IOException {
        succed = false;
        succ_addBook.setVisible(succed);

        String name = name_newbook.getText();
        if (!dataLib.validString(name, err_name))
            return;

        String id = id_T.getText();
        if (!dataLib.validString(id, err_id))
            return;
        if (dataLib.bookIdSeach(shelf.getBooks(), id) != null) {
            err_id.setVisible(true);
            ;
            return;
        }

        double price = dataLib.validDouble(price_newbook.getText(), err_price);
        if (price < -998)
            return;

        int amount = dataLib.validInt(amount_newbook.getText(), err_amount);
        if (amount < -998)
            return;

        ArrayList<Tag> tags = getTags(tag_G);
        if (tags.size() < 1) {
            err_tag.setVisible(true);
            return;
        }

        Book book = new Book(name, price, id, amount, tags);
        if (shelf.getBooks().contains(book)) {
            err_addBook.setVisible(true);
            return;
        }

        shelf.addNewBook(name, price, id, amount, tags);
        succed = true;
        adminGo(e);
    }
}
