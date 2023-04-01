package controller;

import LibPack.CVLib;
import LibPack.dataLib;
import dataPack.Book;
import dataPack.Rent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ReturnBook extends GobalContro {
    private Rent rent;
    private int day;

    public ReturnBook() {
        super();
    }

    @FXML
    Label bookname_L, id_L, day_L, perday_L, coin_L, idRent_L;
    @FXML
    Label err_return, succ_return, err_day, err_rent;
    @FXML
    Button return_B, ok_B;
    @FXML
    ComboBox<String> rent_C;
    @FXML
    Group cost_G;
    @FXML
    TextField day_return;

    @Override
    public boolean controlSpecial() {
        rent_C.getItems().addAll(CVLib.rent2String(user.getRents()));
        cost_G.setVisible(false);

        return false;
    }

    @FXML
    public void showCost(Event e) {
        String rentData = rent_C.getValue();
        if (!dataLib.validString(rentData, err_rent))
            return;

        day = dataLib.validInt(day_return.getText(), err_day);
        if (day < -998)
            return;

        rent = dataLib.rentSeach(user.getRents(), rentData);
        Book b = rent.getBook();
        bookname_L.setText(b.getName());
        id_L.setText(b.getId());
        idRent_L.setText(rent.getId());
        day_L.setText(String.valueOf(day));
        perday_L.setText(String.valueOf(rent.cost()));
        coin_L.setText(String.valueOf(rent.calCost(day)));
        return_B.setDisable(false);
        cost_G.setVisible(true);
    }

    public void bookReturn(Event e) {
        succ_return.setVisible(false);
        err_day.setVisible(false);

        if (!user.bookReturn(rent, day)) {
            err_return.setVisible(true);
            return;
        }

        succ_return.setVisible(true);
        return_B.setDisable(true);
        coin.setText(String.valueOf(user.getCoin()));
        rent_C.getItems().clear();
        rent_C.getItems().addAll(CVLib.rent2String(user.getRents()));

        Book b = dataLib.bookIdSeach(shelf.getBooks(), rent.getBook().getId());
        b.addBook(1);

    }

}
