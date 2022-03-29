package com.academy.demojavafx.confirmation;

import javafx.beans.property.IntegerProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmationWindow {

    private String title;
    private String message;

    public ConfirmationWindow(String title, String message) {
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
        Button yesButton = new Button("YES");
        yesButton.setOnAction(this::handleYes);
        Button noButton = new Button("NO");
        noButton.setOnAction(e->stage.close());

        VBox container = new VBox(20);
        container.setPadding(new Insets(20));
        container.setAlignment(Pos.CENTER);
        HBox buttonBox = new HBox(20);
        buttonBox.setPadding(new Insets(5));
        buttonBox.getChildren().addAll(yesButton, noButton);
        buttonBox.setAlignment(Pos.CENTER);



        container.getChildren().addAll(messageLabel, buttonBox);

        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();
    }

    private void handleYes(ActionEvent e){
        System.out.println("Odoše pare s računa...");
    }
}
