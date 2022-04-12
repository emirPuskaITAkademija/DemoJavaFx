package com.academy.demojavafx;

import com.academy.demojavafx.confirmation.ConfirmationWindow;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class WindowCloseListener implements EventHandler<WindowEvent> {
    private final Stage stage;

    public WindowCloseListener(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void handle(WindowEvent windowEvent) {
        //ja od sad preuzimam
        windowEvent.consume();

        ConfirmationWindow confirmationWindow = new ConfirmationWindow("Potvrda zatvaranja",
                "Da li ste sigurni da želite izaći iz aplikacije ?");
        confirmationWindow.show(e -> {
            stage.close();
        });
    }
}
