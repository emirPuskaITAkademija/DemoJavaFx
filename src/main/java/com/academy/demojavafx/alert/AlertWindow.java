package com.academy.demojavafx.alert;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertWindow {
    private final String title;
    private final String message;

    public AlertWindow(String title, String message){
        this.title = title;
        this.message = message;
    }

    public void show(){
        //kreiram kompletan novi prozor
        Stage stage = new Stage();
        //blokiraj sve druge prozore i evente koji se mogu desit na njima
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(350);

        Label messageLabel = new Label();
        messageLabel.setText(message);
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e->stage.close());

        VBox container = new VBox(20);
        container.setPadding(new Insets(20));
        container.setAlignment(Pos.CENTER);
        container.getChildren().addAll(messageLabel, closeButton);

        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();
    }
}
