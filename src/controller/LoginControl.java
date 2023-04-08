package controller;

import java.io.IOException;

import LibPack.dataLib;
import dataPack.User;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginControl extends GobalContro {

	public LoginControl() {
		super();
	}

	@FXML
	Button admin_B, newuser_B, admin_B1, join_B;
	@FXML
	Label admin_pass_txt, err_pass, err_username,showpass_L;
	@FXML
	TextField user_input;
	@FXML
	PasswordField admin_pass;

	@FXML
	public void isAdmin(Event e) {
		var b = true;
		if (admin_pass.isVisible()) {
			b = false;
		}
		newuser_B.setDisable(b);
		join_B.setDisable(b);
		user_input.setDisable(b);
		admin_pass.setVisible(b);
		showpass_L.setVisible(b);
		admin_pass_txt.setVisible(b);
		admin_B1.setVisible(b);
		err_pass.setVisible(false);
	}

	@Override
	public void adminGo(Event e) throws IOException {
		var pass = admin_pass.getText();
		if (!pass.equals("1234")) {
			err_pass.setVisible(true);
			return;
		}

		stateAdmin = true;
		dataLib.sendData(shelf, user);
		Parent root = FXMLLoader.load(getClass().getResource("/resources/home.fxml"));
		dataLib.setScene(e, root);
	}

	@Override
	public void toHomePage(Event e) throws IOException {
		String in = user_input.getText();

		if (!dataLib.validString(in, err_username))
			return;
		User u = dataLib.userCheck(in);
		if (u == null) {
			err_username.setVisible(true);
			return;
		}

		dataLib.sendData(shelf, u);
		Parent root = FXMLLoader.load(getClass().getResource("/resources/home.fxml"));
		dataLib.setScene(e, root);
	}

	@Override
	public boolean controlSpecial() {
		return true;
	}

}
