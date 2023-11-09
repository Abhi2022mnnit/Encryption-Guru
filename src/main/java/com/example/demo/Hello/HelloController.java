package com.example.demo.Hello;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

import animatefx.animation.AnimationFX;

public class HelloController implements Initializable {

//    @FXML
//    private TableColumn<Person, void> Action;

    @FXML
    private TableColumn<Person, Integer> id;

    @FXML
    private TableColumn<Person, String> name;

    @FXML
    private TableView<Person> table;

    ObservableList<Person> list = FXCollections.observableArrayList(
            new Person(1,"Abhishek"),
            new Person(2,"Neha"),
            new Person(3,"Chetan")
    );

    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<Person, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));

        table.setItems(list);
    }



}
