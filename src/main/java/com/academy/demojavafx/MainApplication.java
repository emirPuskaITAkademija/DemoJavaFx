package com.academy.demojavafx;

import com.academy.demojavafx.controller.ProductController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.math.BigDecimal;

/**
 * <li>1. MainApplication extends Application
 * <li>2. start(Stage stage)</li>
 * <li>3.
 * <li>3.1 VBox je kontejner koji odmah slaže kontrole vertikalno</li>
 * <li>3.2 Button je kontrola pomoću koje korisnik kontroliše aplikaciju</li>
 *
 * <li>3.3 Postavljamo Scenu -> Scene scene = new Scene(kontejner)</li>
 * <li>3.4 Stage stage -> stage.setScene(scene);</li>
 *
 * </li>
 */
public class MainApplication extends Application {

    private final ProductController productController = new ProductController();
    private final TableView<Product> productTableView = new TableView<>();
    private final TextField nameInput = new TextField();
    private final TextField priceInput = new TextField();
    private final TextField quanityInStockInput = new TextField();


    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Products");
        //Kreirati VIEW odnosno table kao view
        configureTable();
        //Zakačiti na izvor podataka-> ProductController
        ObservableList<Product> products = productController.loadProducts();
        productTableView.setItems(products);

        //Forma
        nameInput.setPromptText("Ime proizvoda..");
        nameInput.setMinWidth(100);
        priceInput.setPromptText("Jedinična cijena..");
        quanityInStockInput.setPromptText("Količina na stanju..");
        Button addButton = new Button("Add product");
        addButton.setOnAction(e->onAddButtonClick());
        Button deleteButton = new Button("Delete product");
        deleteButton.setOnAction(e->onDeleteButtonClick());
        HBox formBox = new HBox();
        formBox.setPadding(new Insets(10));
        formBox.setSpacing(10);
        formBox.getChildren().addAll(nameInput, quanityInStockInput, priceInput, addButton, deleteButton);
        //glavni kontejner
        VBox vbox = new VBox();
        vbox.getChildren().addAll(productTableView, formBox);
        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.show();
    }

    private void onAddButtonClick() {
        Product product = new Product();
        product.setName(nameInput.getText());
        product.setQuantityInStock(Integer.parseInt(quanityInStockInput.getText()));
        product.setUnitPrice(new BigDecimal(priceInput.getText()));
        //DB table
        product = productController.addProduct(product);
        //VIEW table
        productTableView.getItems().add(product);

        nameInput.clear();
        quanityInStockInput.clear();
        priceInput.clear();
    }

    private void onDeleteButtonClick(){
        ObservableList<Product> selectedProducts = productTableView.getSelectionModel().getSelectedItems();
        productController.deleteSelectedProducts(selectedProducts);
        ObservableList<Product> allProducts = productTableView.getItems();
        allProducts.removeAll(selectedProducts);
    }

    //Product,....properties(columns)
    private void configureTable() {
        //ProductID
        TableColumn<Product, Integer> productIdColumn = new TableColumn<>("Product ID");
        productIdColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productId"));
        //Name column
        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        //Quantity in stock
        TableColumn<Product, Integer> quantityInStockColumn = new TableColumn<>("Quantity in stock");
        quantityInStockColumn.setMinWidth(200);
        quantityInStockColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantityInStock"));
        //Unit price
        TableColumn<Product, BigDecimal> unitPriceColumn = new TableColumn<>("Unit price");
        unitPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, BigDecimal>("unitPrice"));
        //veza između tableView i columns
        productTableView.getColumns().addAll(productIdColumn, nameColumn, quantityInStockColumn, unitPriceColumn);
    }


    public static void main(String[] args) {
        launch();
    }
}

/**
 * private Stage stage;
 *
 * @Override public void start(Stage stage) throws Exception {
 * this.stage = stage;
 * this.stage.setTitle("Demonstracija upotrebe kontejnera");
 * GridPane gridPane = new GridPane();
 * gridPane.setPadding(new Insets(20));
 * gridPane.setVgap(15);
 * gridPane.setHgap(10);
 * <p>
 * //label username
 * Label usernameLabel = new Label("Username");
 * GridPane.setConstraints(usernameLabel, 0, 0);
 * //username text input
 * TextField usernameTextField = new TextField();
 * usernameTextField.setPromptText("Enter username...");
 * GridPane.setConstraints(usernameTextField, 1, 0);
 * //labela password
 * Label passwordLabel = new Label("Password");
 * GridPane.setConstraints(passwordLabel, 0, 1);
 * //Password input
 * PasswordField passwordField = new PasswordField();
 * GridPane.setConstraints(passwordField, 1, 1);
 * <p>
 * <p>
 * //VBox
 * CheckBox cevapiCheckBox = new CheckBox("Čevapi");
 * CheckBox pizzaCheckBox = new CheckBox("Pizza");
 * cevapiCheckBox.setSelected(true);
 * HBox meni1Hbox = new HBox(20);
 * meni1Hbox.setPadding(new Insets(10));
 * <p>
 * <p>
 * meni1Hbox.getChildren().addAll(cevapiCheckBox, pizzaCheckBox);
 * GridPane.setConstraints(meni1Hbox, 1, 2);
 * //ChoiceBox<String>
 * ChoiceBox<String> fruitChoiceBox = new ChoiceBox<>();
 * fruitChoiceBox.getItems().addAll("Banana", "Kiwi", "Ananas");
 * fruitChoiceBox.getItems().add("Apples");
 * fruitChoiceBox.getItems().addAll("Orange", "Lemon");
 * fruitChoiceBox.setValue("Apples");
 * fruitChoiceBox.getSelectionModel()
 * .selectedItemProperty()
 * .addListener((observableValue, oldValue, newValue) -> {
 * System.out.println("PREVIOUSLY selected: " + oldValue);
 * System.out.println("NEW selected: " + newValue);
 * });
 * GridPane.setConstraints(fruitChoiceBox, 1, 3);
 * <p>
 * ComboBox<String> movieComboBox = new ComboBox<>();
 * movieComboBox.getItems().addAll("Prohujalo s vihorom",
 * "Good Will Hunting", "I am legend", "Spiderman", "Batman");
 * movieComboBox.setPromptText("What is your favourite movie ? ");
 * movieComboBox.setEditable(true);
 * GridPane.setConstraints(movieComboBox, 1, 4);
 * <p>
 * ListView<String> listView = new ListView<>();
 * listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
 * listView.getItems().addAll("Iron Man", "Titan", "Notebook",
 * "Batman", "Superman", "Iron Man 2");
 * GridPane.setConstraints(listView, 1, 5);
 * <p>
 * Button orderButton = new Button("Order");
 * orderButton.setOnAction(e -> handleOrder(pizzaCheckBox, cevapiCheckBox));
 * GridPane.setConstraints(orderButton, 1, 6);
 * //login button
 * Button loginButton = new Button("Login");
 * GridPane.setConstraints(loginButton, 1, 7);
 * gridPane.getChildren().addAll(usernameLabel, usernameTextField,
 * passwordLabel, passwordField, loginButton, meni1Hbox, orderButton, fruitChoiceBox,
 * movieComboBox, listView);
 * <p>
 * Scene scene = new Scene(gridPane, 400, 600);
 * this.stage.setScene(scene);
 * this.stage.show();
 * }
 * <p>
 * private void handleOrder(CheckBox pizzaCheckBox, CheckBox cevapiCheckBox) {
 * String message = "User's order: " + System.lineSeparator();
 * if (cevapiCheckBox.isSelected()) {
 * message += "čevape" + System.lineSeparator();
 * }
 * if (pizzaCheckBox.isSelected()) {
 * message += "pizzu" + System.lineSeparator();
 * }
 * System.out.println(message);
 * }
 * <p>
 * <p>
 * public static void main(String[] args) {
 * launch();
 * }
 * }
 * <p>
 * private boolean isInteger(TextField textField, String message){
 * try{
 * Integer broj = Integer.parseInt(textField.getText());
 * System.out.println("Age: " + broj);
 * return true;
 * }catch (NumberFormatException exception){
 * System.err.println(exception.getMessage());
 * }
 * return false;
 * }
 * <p>
 * Kontejneri uvod
 * <p>
 * Confirmation Window
 * <p>
 * Primjer 4
 * <p>
 * Kontejneri uvod
 * <p>
 * Confirmation Window
 * <p>
 * Primjer 4
 * <p>
 * Kontejneri uvod
 * <p>
 * Confirmation Window
 * <p>
 * Primjer 4
 */

/**
 * private boolean isInteger(TextField textField, String message){
 * try{
 * Integer broj = Integer.parseInt(textField.getText());
 * System.out.println("Age: " + broj);
 * return true;
 * }catch (NumberFormatException exception){
 * System.err.println(exception.getMessage());
 * }
 * return false;
 * }
 * <p>
 * Kontejneri uvod
 * <p>
 * Confirmation Window
 * <p>
 * Primjer 4
 * <p>
 * Kontejneri uvod
 * <p>
 * Confirmation Window
 * <p>
 * Primjer 4
 */

/**
 * Kontejneri uvod
 */

//top
//HBox topMenu = new HBox();
//    Button buttonA = new Button("File");
//    Button buttonB = new Button("Edit");
//    Button buttonC = new Button("View");
//        topMenu.getChildren().addAll(buttonA, buttonB, buttonC);
//                //left
//                VBox leftMenu = new VBox();
//                Button buttonAA = new Button("File");
//                Button buttonBB = new Button("Edit");
//                Button buttonCC = new Button("View");
//                leftMenu.getChildren().addAll(buttonAA, buttonBB, buttonCC);
//
//                BorderPane borderPane = new BorderPane();
//                borderPane.setTop(topMenu);
//                borderPane.setLeft(leftMenu);
//
//                Scene scene = new Scene(borderPane, 400, 300);
//                stage.setScene(scene);
//                stage.show();

/**
 * Confirmation Window
 */
//private Stage stage;
//
//    @Override
//    public void start(Stage stage) {
//        this.stage = stage;
//        this.stage.setTitle("Glavni prozor");
//        //kontejnerska komponenta
//        StackPane stackPane = new StackPane();
//        //UI kontrole Node primiMeButton
//        Button primiMeButton = new Button("Primi me");
//        //primiMeListener
//        PrimiMeListener primiMeListener = new PrimiMeListener();
//        //primiMeButton -> primiMeListener
//        primiMeButton.setOnAction(primiMeListener);
//        ObservableList<Node> nodeList = stackPane.getChildren();
//        nodeList.add(primiMeButton);
//        Scene scene = new Scene(stackPane, 500, 400);
//        this.stage.setScene(scene);
//        this.stage.show();
//    }
//
//private class PrimiMeListener implements EventHandler<ActionEvent> {
//    @Override
//    public void handle(ActionEvent event) {
//        ConfirmationWindow confirmationWindow = new ConfirmationWindow("Primljen/Odbijen", "Jeste li sigurni da sam primljen ?");
//        PrimiMeConfirmationListener confirmationListener = new PrimiMeConfirmationListener();
//        confirmationWindow.show(confirmationListener);
//    }
//
//    private class PrimiMeConfirmationListener implements EventHandler<ActionEvent> {
//        @Override
//        public void handle(ActionEvent event) {
//            System.out.println("Potvrđeno je ... Primljen si...");
//        }
//    }
//}


/**
 * Primjer 4
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