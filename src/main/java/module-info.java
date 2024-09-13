module com.cache_simulator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.cache_simulator to javafx.fxml;
    exports com.cache_simulator;
}