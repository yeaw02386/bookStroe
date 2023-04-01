package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import LibPack.dataLib;
import dataPack.Book;
import dataPack.Holder;
import dataPack.Shelf;
import dataPack.Tag;
import dataPack.User;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public abstract class GobalContro implements Initializable {
	protected User user;
	protected Shelf shelf;
	protected static boolean stateAdmin = false;

	@FXML
	Label coin;
	@FXML
	Label username_L;
	@FXML
	Button home_B, search_B, borrow_B, Newbook_B;
	@FXML
	Group user_G;

	public GobalContro() {
		shelf = Holder.getInstance().getShelf();
		user = Holder.getInstance().getUser();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (controlSpecial())
			return;
		borrow_B.setDisable(stateAdmin);
		Newbook_B.setVisible(stateAdmin);
		user_G.setVisible(!stateAdmin);

		if (stateAdmin)
			return;

		user = Holder.getInstance().getUser();
		coin.setText((String.valueOf(user.getCoin())));
		username_L.setText(user.getName());
	}

	@FXML
	public void toRegUserPage(Event e) throws IOException {
		dataLib.sendData(shelf, user);
		Parent root = FXMLLoader.load(getClass().getResource("/resources/Create.fxml"));
		dataLib.setScene(e, root);
	}

	@FXML
	public void toHomePage(Event e) throws IOException {
		dataLib.sendData(shelf, user);
		Parent root = FXMLLoader.load(getClass().getResource("/resources/home.fxml"));
		dataLib.setScene(e, root);
	}

	@FXML
	public void toLoginPage(Event e) throws IOException {
		Holder.getInstance().setUser(null);
		if (stateAdmin)
			stateAdmin = false;

		dataLib.sendData(shelf, user);
		Parent root = FXMLLoader.load(getClass().getResource("/resources/login.fxml"));
		dataLib.setScene(e, root);
	}

	@FXML
	public void adminGo(Event e) throws IOException {
		dataLib.sendData(shelf, user);
		Parent root = FXMLLoader.load(getClass().getResource("/resources/addbook.fxml"));
		dataLib.setScene(e, root);
	}

	@FXML
	public void toReturnBookPage(Event e) throws IOException {
		dataLib.sendData(shelf, user);
		Parent root = FXMLLoader.load(getClass().getResource("/resources/returnBook.fxml"));
		dataLib.setScene(e, root);
	}

	@FXML
	public void toBookPage() throws IOException {
		dataLib.sendData(shelf, user);
		Stage stage = Holder.getInstance().getGstage();
		Parent root = FXMLLoader.load(getClass().getResource("/resources/bookpage.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	public void toSearchPage(Event e) throws IOException {
		dataLib.sendData(shelf, user);
		Parent root = FXMLLoader.load(getClass().getResource("/resources/search.fxml"));
		dataLib.setScene(e, root);
	}

	@FXML
	public void topUpCoin(Event e) throws IOException {
		user.topUp(100);
		coin.setText((String.valueOf(user.getCoin())));
	}

	public ArrayList<Tag> getTags(Group tagCheck) {
		ArrayList<Tag> t = new ArrayList<Tag>();
		ObservableList<Node> child = tagCheck.getChildren();

		for (int i = 0; i < child.size(); i++) {
			CheckBox c = (CheckBox) child.get(i);
			if (c.isSelected())
				t.add(shelf.getTags().get(i));
		}

		return t;
	}

	public void tableInit(TableView<Book> table, TableColumn<Book, String> name, TableColumn<Book, Double> price,
			TableColumn<Book, Integer> total) {
		name.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
		price.setCellValueFactory(new PropertyValueFactory<Book, Double>("price"));
		total.setCellValueFactory(new PropertyValueFactory<Book, Integer>("count"));

		name.setSortType(TableColumn.SortType.DESCENDING);
		price.setSortType(TableColumn.SortType.DESCENDING);
		total.setSortType(TableColumn.SortType.DESCENDING);

		TableViewSelectionModel<Book> selectionModel = table.getSelectionModel();
		ObservableList<Book> s = selectionModel.getSelectedItems();

		s.addListener(
				new ListChangeListener<Book>() {
					@Override
					public void onChanged(Change<? extends Book> change) {
						Book b = change.getList().get(0);
						int i = shelf.getBooks().indexOf(b);
						b = shelf.getBooks().get(i);
						Holder.getInstance().setSelectBook(b);

						try {
							toBookPage();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
	}

	public boolean controlSpecial() {
		return false;
	}
}
