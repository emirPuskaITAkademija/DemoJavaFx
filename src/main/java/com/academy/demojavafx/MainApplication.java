package com.academy.demojavafx;

import com.academy.demojavafx.alert.AlertWindow;
import com.academy.demojavafx.confirmation.ConfirmationWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * <li>1. MainApplication extends Application
 * <li>2. start(Stage stage)</li>
 * <li>3.
 *              <li>3.1 VBox je kontejner koji odmah slaže kontrole vertikalno</li>
 *              <li>3.2 Button je kontrola pomoću koje korisnik kontroliše aplikaciju</li>
 *
 *              <li>3.3 Postavljamo Scenu -> Scene scene = new Scene(kontejner)</li>
 *              <li>3.4 Stage stage -> stage.setScene(scene);</li>
 *
 * </li>
 */
public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Glavni");

        Button button = new Button("Glavni");
        button.setOnAction(e->new ConfirmationWindow("Problem", "Imal para na računu").show());

        StackPane container = new StackPane();
        container.getChildren().add(button);

        Scene glavnuScenu = new Scene(container, 300, 250);
        stage.setScene(glavnuScenu);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

/**
 *
 * Primjer 4
 *
 *
 */


//private Stage window;
//    private Scene scene1;
//    private Scene scene2;
//
//    @Override
//    public void start(Stage stage) {
//        this.window = stage;
//        /*
//        Kreiranje scene 1
//         */
//        Label scene1Label = new Label("Dobro došli na scenu 1:");
//        Button scene1Button = new Button("Idi na scenu 2");
//        scene1Button.setOnAction(this::switchToScene1);
//        VBox scene1VBox = new VBox(20);
//        scene1VBox.setPadding(new Insets(20));
//        scene1VBox.getChildren().addAll(scene1Label, scene1Button);
//        scene1 = new Scene(scene1VBox, 200, 200);
//
//        /**
//         * Kreiranje scene 2
//         */
//        Button scene2Button = new Button("Vrati se na scenu 1");
//        scene2Button.setOnAction(this::switchToScene2);
//        StackPane scene2Pane = new StackPane();
//        scene2Pane.getChildren().add(scene2Button);
//        scene2 = new Scene(scene2Pane, 600, 300);
//
//        stage.setScene(scene1);
//        stage.setTitle("Switch između scena");
//        stage.show();
//    }
//
//    private void switchToScene2(ActionEvent event){
//        window.setScene(scene2);
//    }
//
//    private void switchToScene1(ActionEvent event){
//        window.setScene(scene1);
//    }

/**
 *
 */

//Primjer 3
//private Button button;
//
//    @Override
//    public void start(Stage stage) {
//        button = new Button("KLIKNI NA ME !!!");
//        button.setOnAction(this::handleOnButtonClick);
//        StackPane stackPane = new StackPane();
//        ObservableList<Node> children = stackPane.getChildren();
//        children.add(button);
//
//        Scene scene = new Scene(stackPane, 300, 300);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    private void handleOnButtonClick(ActionEvent actionEvent){
//        System.out.println("Desio se klik...");
//    }


//    Primjer 2


//    private class HelloButtonListener implements EventHandler<ActionEvent>{
//
//        private final Label welcomeText;
//
//        public HelloButtonListener(Label welcomeText){
//            this.welcomeText = welcomeText;
//        }
//
//        @Override
//        public void handle(ActionEvent actionEvent) {
//            welcomeText.setText("Welcome to JavaFX Application!");
//        }
//    }

//    //kreirati GUI
//    VBox vBox = new VBox();
//        vBox.setAlignment(Pos.CENTER);
//        vBox.setSpacing(20);
//    Insets insets = new Insets(20);
//        vBox.setPadding(insets);
//    Label welcomeText = new Label();
//    Button helloButton = new Button("Hello!");
//        helloButton.setOnAction(new HelloButtonListener(welcomeText));
//        vBox.getChildren().add(welcomeText);
//        vBox.getChildren().add(helloButton);
//    //Scena
//    Scene scene = new Scene(vBox, 320, 240);
//    //STage
//        stage.setScene(scene);
//        stage.show();


//Primjer 1


//    FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("hello-view.fxml"));
//    Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();