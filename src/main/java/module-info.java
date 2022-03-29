module com.academy.demojavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.academy.demojavafx to javafx.fxml;
    exports com.academy.demojavafx;
}