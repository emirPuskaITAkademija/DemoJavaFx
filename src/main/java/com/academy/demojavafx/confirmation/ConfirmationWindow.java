package com.academy.demojavafx.confirmation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

    private final String title;
    private final String message;
    private boolean confirmation = false;

    public ConfirmationWindow(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public void show(EventHandler<ActionEvent> yesListener) {
        //kreiram kompletan novi prozor
        Stage stage = new Stage();
        //blokiraj sve druge prozore i evente koji se mogu desit na njima
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(350);

        Label messageLabel = new Label();
        messageLabel.setText(message);
        final boolean condition = false;
        Button yesButton = new Button("YES");
        yesButton.setOnAction(e -> {
            yesListener.handle(e);
            stage.close();
        });
        Button noButton = new Button("NO");
        NoListener noListener = new NoListener(stage);
        noButton.setOnAction(noListener);

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

    private class NoListener implements EventHandler<ActionEvent> {

        private Stage stage;

        public NoListener(Stage stage) {
            this.stage = stage;
        }

        @Override
        public void handle(ActionEvent event) {
            this.stage.close();
        }
    }
}
