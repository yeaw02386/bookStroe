package controller;

import dataPack.Book;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class HomeControl extends GobalContro {
    public HomeControl(){
        super();
    }

    @FXML
    TableView<Book> table_output;
    @FXML
    TableColumn<Book,String> column_name;
    @FXML
    TableColumn<Book,Double> column_price;
    @FXML
    TableColumn<Book,Integer> column_total;

    @Override
    public boolean controlSpecial(){
        tableInit(table_output, column_name, column_price, column_total);
        table_output.getItems().addAll(shelf.getBooks());
        
        return false;
    }

}
