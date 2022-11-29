module com.example.droneguidemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.droneguidemo to javafx.fxml;
    exports com.example.droneguidemo;
}