module com.academy.demojavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;
    requires java.sql;


    opens com.academy.demojavafx to javafx.fxml;
    exports com.academy.demojavafx;
}