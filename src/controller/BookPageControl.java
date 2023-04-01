package controller;

import LibPack.dataLib;
import dataPack.Book;
import dataPack.Holder;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BookPageControl extends GobalContro {
    private Book book;

    public BookPageControl() {
        super();
        book = Holder.getInstance().getSelectBook();
    }

    @FXML
    Label bookname_L, coin_L, amount_L, id_L, more_tag;
    @FXML
    Group all_tag_G, userbuy_G, admin_G;
    @FXML
    Label err_buyBook, err_addBook, err_rent, succ_buybook, succ_addbook, succ_rent;
    @FXML
    TextField num_T;

    @Override
    public boolean controlSpecial() {
        userbuy_G.setDisable(stateAdmin);
        admin_G.setVisible(stateAdmin);

        bookname_L.setText(book.getName());
        id_L.setText(book.getId());
        coin_L.setText(book.getSPreice());
        amount_L.setText(book.getSCount());

        ObservableList<Node> child = all_tag_G.getChildren();

        boolean c = true;
        for (int i = 0; i < child.size(); i++) {
            Label l = (Label) child.get(i);
            try {
                l.setText(book.getTag().get(i).getName());
            } catch (Exception e) {
                c = false;
                l.setVisible(false);
            }
            more_tag.setVisible(c);

        }

        return false;
    }

    @FXML
    public void buyBook(Event e) {
        succ_buybook.setVisible(false);
        err_buyBook.setVisible(false);

        if (user.buyBook(book))
            succ_buybook.setVisible(true);
        else
            err_buyBook.setVisible(true);

        amount_L.setText(book.getSCount());
        coin.setText((String.valueOf(user.getCoin())));
    }

    @FXML
    public void addMoreBook(Event e) {
        succ_addbook.setVisible(false);
        err_addBook.setVisible(false);

        String data = num_T.getText();
        int add = dataLib.validInt(data, err_addBook);
        if (add < -998)
            return;

        book.addBook(add);
        amount_L.setText(book.getSCount());
        succ_addbook.setVisible(true);
    }

    @FXML
    public void addRent(Event e) {
        err_rent.setVisible(false);
        succ_rent.setVisible(false);

        if (book.getCount() < 1)
            err_rent.setVisible(true);
        else {
            user.addRent(book);
            book.delBook(1);
            amount_L.setText(book.getSCount());
            ;
            succ_rent.setVisible(true);
        }
    }

}
