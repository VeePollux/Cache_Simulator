module com.cache_simulator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.cache_simulator to javafx.fxml;
    exports com.cache_simulator;
}