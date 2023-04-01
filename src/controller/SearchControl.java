package controller;

import java.util.ArrayList;
import java.util.List;

import LibPack.dataLib;
import dataPack.Book;
import dataPack.Tag;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SearchControl extends GobalContro {
    public SearchControl() {
        super();
    }

    @FXML
    Group tag_G;
    @FXML
    TextField search_T;
    @FXML
    TableView<Book> table_search;
    @FXML
    TableColumn<Book, String> column_name;
    @FXML
    TableColumn<Book, Double> column_price;
    @FXML
    TableColumn<Book, Integer> column_total;

    public boolean controlSpecial() {
        tableInit(table_search, column_name, column_price, column_total);
        table_search.getItems().addAll(shelf.getBooks());
        return false;
    }

    @FXML
    public void searchBook(Event e) {
        String data = search_T.getText();
        ArrayList<Tag> tag = getTags(tag_G);
        List<Book> books = dataLib.bookSearch(shelf.getBooks(), data, tag);

        table_search.getItems().clear();
        table_search.getItems().addAll(books);
    }

    @FXML
    public void searchRealTime(Event e) {
        searchBook(e);
    }

}
