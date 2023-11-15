package com.example.demo;

import animatefx.animation.BounceIn;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;

public class EncryptCont implements Initializable {

    @FXML
    private TableColumn<Data, Data> delete_Button;
    @FXML
    private TableColumn<Data, Data> select_Button;
    @FXML
    private TableColumn<Data, Integer> sno = new TableColumn<>("S.No.");;
    @FXML
    private TableColumn<Data, String> path;
    @FXML
    private TableColumn<Data, String> name;
    @FXML
    private TableView<Data> table;

    // Common Variables...
    private List<File> files;
    private List<String> selectedFiles = new ArrayList<>();

    //Password...
    @FXML
    PasswordField passwordField = new PasswordField();
    @FXML
    private TextField textField = new TextField();

//    Image eyeImage = new Image(getClass().getResourceAsStream("/Eye.png"));
//    Image eye_SlashImage = new Image(getClass().getResourceAsStream("/Eye_Slash.png"));
//    @FXML
//    ImageView imageView = new ImageView();

    @FXML
    public void togglePassword(ActionEvent event) throws Exception {
        if (passwordField.isManaged()) {
            passwordField.setManaged(false);
            passwordField.setVisible(false);
            String password = passwordField.getText();
            textField.setText(password);
//            imageView.setImage(eyeImage);
        } else {
            passwordField.setVisible(true);
            passwordField.setManaged(true);
            textField.setText("");
        }
    }

    private Stage stage;
    private Scene scene;

    public void homeScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        new BounceIn(root).play();
        stage.show();
    }


    ObservableList<Data> list = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle) {

//        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
try {

    sno.setCellValueFactory(cellData -> new SimpleIntegerProperty(table.getItems().indexOf(cellData.getValue()) + 1).asObject());
    sno.setResizable(false);
    name.setCellValueFactory(new PropertyValueFactory<Data, String>("name"));
    name.setResizable(false);
    path.setCellValueFactory(new PropertyValueFactory<Data, String>("path"));       // The name in "" must be same as Data class attribute's  name
    path.setResizable(false);


    select_Button.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
    select_Button.setResizable(false);
    select_Button.setCellFactory(param -> new TableCell<Data, Data>() {
        private final Button selectButton = new Button("Select");

        @Override
        protected void updateItem(Data person, boolean empty) {
            super.updateItem(person, empty);

            if (person == null) {
                setGraphic(null);
                return;
            }

            setGraphic(selectButton);
            selectButton.setOnAction(event -> {
                System.out.println(getItem().getPath());
                selectedFiles.add(getItem().getPath());

                if (selectButton.getStyle().isEmpty()) {
                    selectButton.setStyle("-fx-background-color: #F46036; -fx-text-fill: white;");
                } else {
                    selectButton.setStyle("");
                }
            });
        }

    });


    delete_Button.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
    delete_Button.setResizable(false);
    delete_Button.setCellFactory(param -> new TableCell<Data, Data>() {
        private final Button deleteButton = new Button("Delete");

        @Override
        protected void updateItem(Data person, boolean empty) {
            super.updateItem(person, empty);

            if (person == null) {
                setGraphic(null);
                return;
            }

            setGraphic(deleteButton);
            deleteButton.setOnAction(event -> list.remove(person));
        }

    });

    SortedList<Data> sortedData = new SortedList<>(list);
    sortedData.comparatorProperty().bind(table.comparatorProperty());

    FilteredList<Data> filteredData = new FilteredList<>(sortedData, p -> true);

    table.setItems(filteredData);

    // Sort the table when clicking on the "Last Name" column header
    name.setComparator(String::compareToIgnoreCase);
    name.setSortType(TableColumn.SortType.ASCENDING);
    table.getSortOrder().add(name);
    table.sort();
}
catch(Exception e){
    e.printStackTrace();
}


    }

    public void addElement(ActionEvent event) {
        files = new FileChooser().showOpenMultipleDialog(null);
//        files.sort(new newComparator());
        if (files != null) {
            for (var oneFile : files) {
                String PATH = oneFile.getAbsolutePath();
                String NAME = oneFile.getName();
                list.add(new Data(PATH, NAME));
            }
        } else {
            System.out.println("No File Taken");
        }
        event.consume();
    }

    @FXML
    public void handleDragOver(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
        event.consume();
    }

    @FXML
    public void handleDragDropped(DragEvent event) {
        files = event.getDragboard().getFiles();
//        files.sort(new newComparator());
        for (var myfile : files) {
            String PATH = myfile.getAbsolutePath();
            String NAME = myfile.getName();
            list.add(new Data(PATH, NAME));
            System.out.println("Dropped file: " + myfile.getAbsolutePath());
        }

        event.setDropCompleted(true);
        event.consume();
    }


    public void encryptFiles() throws Exception{
        String key = passwordField.getText();
        for(var file : selectedFiles){
            EncryptAlgo obj = new EncryptAlgo(file);
            obj.print();
            System.out.println(file + "-> " + key);
            obj.encryptFile(file, key);
        }

    }

}