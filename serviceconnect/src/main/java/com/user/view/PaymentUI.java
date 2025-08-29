package com.user.view;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class PaymentUI {
    VBox dynamicForm = new VBox(10); // Common form container
    VBox paymentMethods = new VBox(10);

    private String providerName;
    private String skills;

    public PaymentUI(String providerName, String skills) {
        this.providerName = providerName;
        this.skills = skills;
    }

    public void start(Stage stage) {
        //  BorderPane rootLayout = new BorderPane();

        
        ImageView backgroundImage = new ImageView(
        new Image("Assets\\Images\\yoga.jpg") // Replace with your background image path
    );
    backgroundImage.setFitWidth(1540); // match your scene size
    backgroundImage.setFitHeight(795);
    backgroundImage.setPreserveRatio(false);
    backgroundImage.setOpacity(0.9); // light overlay
    backgroundImage.setSmooth(true);
    backgroundImage.setEffect(new BoxBlur(8, 5, 9));

        // === Sidebar (copied from Booking_Scheduling) ===
      BorderPane rootLayout = new BorderPane();

        // Sidebar with toggle
        // VBox sidebar = createSidebar(stage, rootLayout, serviceName);
        // rootLayout.setLeft(sidebar);

        VBox sidebar = createSidebar(stage, rootLayout);

        Button toggleBtn = new Button("☰");
        toggleBtn.setFont(Font.font(16));
        toggleBtn.setStyle("-fx-background-color: white; -fx-text-fill: #4B2E2E; -fx-font-weight: bold; -fx-background-radius: 8; -fx-cursor: hand; -fx-padding: 6 14; -fx-border-color: #4B2E2E; -fx-border-radius: 8; -fx-border-width: 1;");

        toggleBtn.setOnMouseEntered(e -> toggleBtn.setStyle("-fx-background-color: #F5F5F5; -fx-text-fill: #4B2E2E; -fx-font-weight: bold; -fx-background-radius: 8; -fx-cursor: hand; -fx-padding: 6 14; -fx-border-color: #4B2E2E; -fx-border-radius: 8; -fx-border-width: 1;"));
        toggleBtn.setOnMouseExited(e -> toggleBtn.setStyle("-fx-background-color: white; -fx-text-fill: #4B2E2E; -fx-font-weight: bold; -fx-background-radius: 8; -fx-cursor: hand; -fx-padding: 6 14; -fx-border-color: #4B2E2E; -fx-border-radius: 8; -fx-border-width: 1;"));

        toggleBtn.setOnAction(e -> {
            boolean isVisible = sidebar.isVisible();
            sidebar.setVisible(!isVisible);
            sidebar.setManaged(!isVisible);
        });

        VBox toggleBox = new VBox(toggleBtn);
        toggleBox.setPadding(new Insets(10));
        toggleBox.setAlignment(Pos.TOP_CENTER);
        toggleBox.setStyle("-fx-background-color: linear-gradient(to bottom, rgba(161,136,127,0.85), rgba(93,64,55,0.85)); -fx-border-color: rgba(62,39,35,0.8); -fx-border-width: 0 1 0 0;");

        HBox leftPanel = new HBox(toggleBox, sidebar);


        // === Main Content ===
        VBox content = new VBox(15);
        content.setPadding(new Insets(20));
        content.setStyle("-fx-background-color:rgba(255,255,255,0.3);");

        Label title = new Label("Payment Page");
        title.setFont(new Font(40));
        title.setStyle("-fx-text-fill:#333333;");
        HBox hb1 = new HBox(title);
        hb1.setAlignment(Pos.CENTER);

        Label paymentMethodLabel = new Label("Select Payment Method");
        paymentMethodLabel.setFont(Font.font("Poppins", FontWeight.BOLD, 14));

        paymentMethods.setPadding(new Insets(10));
        paymentMethods.setStyle("-fx-border-color: #ccc; -fx-border-radius: 5;");

        addPaymentMethod("UPI");
        addPaymentMethod("Cards");
        addPaymentMethod("Wallets");
        addPaymentMethod("Net Banking");

        Button cancel = new Button("Cancel");
        cancel.setStyle("-fx-background-color: -fx-background-color: Grey -fx-text-fill: white; -fx-font-size: 14; -fx-padding: 8 16;");
        cancel.setOnAction(e -> Booking_Scheduling.show(stage, providerName, skills));

        content.getChildren().addAll(hb1, paymentMethodLabel, paymentMethods, dynamicForm, cancel);

        // === Main Layout ===
        BorderPane layout = new BorderPane();
        //layout.setTop(topBar);
        layout.setLeft(leftPanel);
        layout.setCenter(content);
        


        StackPane root = new StackPane();

        root.getChildren().addAll(backgroundImage, layout);
        StackPane.setAlignment(layout, Pos.CENTER);
        backgroundImage.fitWidthProperty().bind(root.widthProperty());
        backgroundImage.fitHeightProperty().bind(root.heightProperty());


        Scene scene = new Scene(root, 1540, 795);
        stage.setScene(scene);
        stage.show();
    }

    private void addPaymentMethod(String method) {
        VBox rowWrapper = new VBox();
        HBox row = new HBox(10);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(10));
        row.setStyle("-fx-background-color: #f9f9f9; -fx-border-color: #ddd; -fx-background-radius: 8;");

        Label methodLabel = new Label(method);
        methodLabel.setFont(Font.font("Poppins", 14));
        Region space = new Region();
        HBox.setHgrow(space, Priority.ALWAYS);
        Label priceLabel = new Label("\u20B9180.00\n\u20B960 shipping included");
        priceLabel.setFont(Font.font("Poppins", 12));

        row.getChildren().addAll(methodLabel, space, priceLabel);
        rowWrapper.getChildren().add(row);

        row.setOnMouseClicked(e -> {
            dynamicForm.getChildren().clear();
            switch (method) {
                case "Cards" -> showCardForm();
                case "UPI" -> showUpiForm();
                case "Wallets" -> showWalletForm();
                case "Net Banking" -> showBankForm();
            }
        });

        paymentMethods.getChildren().add(rowWrapper);
    }

    private void showCardForm() {
        dynamicForm.setPadding(new Insets(10));
        dynamicForm.setStyle("-fx-background-color: #fffaf0; -fx-border-color: #f4c95d; -fx-border-radius: 8;");

        Label title = new Label("Enter Card Details");
        title.setFont(Font.font("Poppins", FontWeight.BOLD, 14));
        TextField cardNumber = new TextField("1234 5678 9098");
        TextField name = new TextField("seeker");
        HBox row = new HBox(10, new TextField("09/27"), new TextField("123"));

        Button payBtn = getPayButton();

        dynamicForm.getChildren().addAll(title, cardNumber, name, row, payBtn);
    }

    private void showUpiForm() {
        dynamicForm.setPadding(new Insets(10));
        dynamicForm.setStyle("-fx-background-color: #fffaf0; -fx-border-color: #a3c9a8; -fx-border-radius: 8;");

        Label title = new Label("Enter UPI ID");
        title.setFont(Font.font("Poppins", FontWeight.BOLD, 14));
        TextField upi = new TextField("seeker@okaxis");

        Button payBtn = getPayButton();

        dynamicForm.getChildren().addAll(title, upi, payBtn);
    }

    private void showWalletForm() {
        dynamicForm.setPadding(new Insets(10));
        dynamicForm.setStyle("-fx-background-color: #fffaf0; -fx-border-color: #f26b6b; -fx-border-radius: 8;");

        Label title = new Label("Select Wallet");
        title.setFont(Font.font("Poppins", FontWeight.BOLD, 14));

        ComboBox<String> wallets = new ComboBox<>();
        wallets.getItems().addAll("PhonePe", "Paytm", "Amazon Pay");
        wallets.setValue("PhonePe");

        Button payBtn = getPayButton();

        dynamicForm.getChildren().addAll(title, wallets, payBtn);
    }

    private void showBankForm() {
        dynamicForm.setPadding(new Insets(10));
        dynamicForm.setStyle("-fx-background-color: #fffaf0; -fx-border-color: #b2dafa; -fx-border-radius: 8;");

        Label title = new Label("Select Bank");
        title.setFont(Font.font("Poppins", FontWeight.BOLD, 14));

        ComboBox<String> banks = new ComboBox<>();
        banks.getItems().addAll("SBI", "HDFC", "ICICI", "Axis Bank");
        banks.setValue("HDFC");

        Button payBtn = getPayButton();

        dynamicForm.getChildren().addAll(title, banks, payBtn);
    }

    private Button getPayButton() {
        Button btn = new Button("Pay ₹180.00");
        btn.setStyle("-fx-background-color: #f26b6b; -fx-text-fill: white; -fx-font-size: 14; -fx-padding: 8 16;");
        btn.setOnAction(e -> {
            LoaderUI.showLoaderAndProceed((Stage) btn.getScene().getWindow(), providerName);
        });
        return btn;
    }
    private static VBox createSidebar(Stage primaryStage, BorderPane rootLayout ) {
    String[] menuItems = {"Dashboard", "Profile","Complaint", "Help", "Logout"};
    VBox sidebar = new VBox(20);
    sidebar.setPadding(new Insets(20));
    sidebar.setStyle("""
        -fx-background-color: linear-gradient(to bottom, rgba(161,136,127,0.85), rgba(93,64,55,0.85));
        -fx-border-color: rgba(62,39,35,0.8);
        -fx-border-width: 0 1 0 0;
        -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0.3, 2, 2);
    """);
    sidebar.setPrefWidth(180);

  

    // Profile Image
    ImageView profileImgView;
    try {
        Image profileImg = new Image(ServiceListView.class.getResourceAsStream("/Assets//Images//akshata.png"));
        profileImgView = new ImageView(profileImg);
    } catch (Exception e) {
        profileImgView = new ImageView();
        System.out.println("Profile image not found!");
    }
    profileImgView.setFitWidth(80);
    profileImgView.setFitHeight(80);
    profileImgView.setClip(new Circle(40, 40, 40));

    VBox profileBox = new VBox(profileImgView);
    profileBox.setAlignment(Pos.CENTER);

sidebar.getChildren().add(profileBox);

    for (String item : menuItems) {
        Label label = new Label(item);
        label.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        label.setTextFill(Color.WHITE);
        label.setStyle("""
            -fx-background-color: transparent;
            -fx-padding: 8 16;
            -fx-background-radius: 10;
            -fx-cursor: hand;
        """);

        // Hover effects
        label.setOnMouseEntered(e -> label.setStyle("""
            -fx-background-color: rgba(255, 255, 255, 0.25);
            -fx-text-fill: #3E2723;
            -fx-background-radius: 10;
            -fx-padding: 8 16;
            -fx-cursor: hand;
            -fx-font-weight: bold;
        """));

        label.setOnMouseExited(e -> label.setStyle("""
            -fx-background-color: transparent;
            -fx-text-fill: white;
            -fx-background-radius: 10;
            -fx-padding: 8 16;
            -fx-cursor: hand;
        """));

        //String username = "Guest";
        label.setOnMouseClicked(event -> {
            switch (item) {
                case "Dashboard" -> new DashboardView().showDashboard(primaryStage,"seeker");
                case "Profile" -> {
                    // Optional: showProfileInfo() or EditProfile
                }
                case "Complaint"->{
                        new complaintsubmitpage().start(primaryStage);
                    }
                    case "Help"->{
                        new helppageS().launchHelp(primaryStage);
                    }
                case "Logout" -> new LandingPage().start(primaryStage);
                default -> System.out.println(item + " clicked");
            }
        });

        sidebar.getChildren().add(label);
    }

    return sidebar;
}
}
