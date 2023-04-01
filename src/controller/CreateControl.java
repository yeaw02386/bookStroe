package controller;

import java.io.IOException;

import LibPack.IOLib;
import LibPack.dataLib;
import dataPack.Holder;
import dataPack.User;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreateControl extends GobalContro {
    public CreateControl() {
        super();
    }

    @FXML
    Button create_B;
    @FXML
    TextField regUser_T;
    @FXML
    Label err_username;

    @FXML
    public void regNewUser(Event e) throws IOException {
        String name = regUser_T.getText();

        if (!dataLib.validString(name, err_username))
            return;
        User u = dataLib.userCheck(name);
        if (u != null) {
            err_username.setVisible(true);
            return;
        }

        user = new User(name);
        Holder.getInstance().setUser(user);
        IOLib.writeFile(user, Holder.getUserPath(), true);
        Holder.getInstance().getUsers().add(user);
        toHomePage(e);
    }

    @Override
    public boolean controlSpecial() {
        return true;
    }
}
