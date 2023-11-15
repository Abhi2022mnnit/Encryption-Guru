package com.example.demo.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageCont {
    private Stage stage;
   @FXML
    public void Encrypt(ActionEvent event) throws IOException {
        System.out.println("Encrypt");
       Parent root = FXMLLoader.load(getClass().getResource("encrypt.fxml"));
       stage = (Stage)((Node)event.getSource()).getScene().getWindow();
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
    }

    @FXML
    public void Decrypt(ActionEvent event)throws IOException{
        System.out.println("Decrypt");
        Parent root = FXMLLoader.load(getClass().getResource("decrypt.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}

