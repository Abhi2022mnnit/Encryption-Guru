module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires AnimateFX;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.Hello;
    opens com.example.demo.Hello to javafx.fxml;
}