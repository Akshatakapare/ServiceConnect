package com.user.view;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SubmitComplaintPage {

    public void show(Stage stage) {
       
        
        // === Sidebar ===
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #f4f4f4;");
        sidebar.setPrefWidth(180);

        Button toggleBtn = new Button("☰");
        toggleBtn.setFont(Font.font(16));
        toggleBtn.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        HBox toggleContainer = new HBox(toggleBtn);
        toggleContainer.setAlignment(Pos.TOP_LEFT);
        toggleContainer.setPadding(new Insets(5));

        VBox profileBox = new VBox();
        profileBox.setAlignment(Pos.CENTER);
        try {
            Image profileImg = new Image(StarRatingUI.class.getResourceAsStream("/Assets//Images//akshata.png"));
            ImageView profileImgView = new ImageView(profileImg);
            profileImgView.setFitWidth(80);
            profileImgView.setFitHeight(80);
            profileImgView.setClip(new Circle(40, 40, 40));
            profileBox.getChildren().add(profileImgView);
        } catch (Exception e) {
            System.out.println("Profile image not found!");
        }

        VBox menuContainer = new VBox(10);
        menuContainer.setPadding(new Insets(10, 0, 0, 0));
        List<Label> menuLabels = new ArrayList<>();
        String[] menuItems = {"Dashboard", "Profile", "Help", "Logout"};

        for (String item : menuItems) {
            Label label = new Label(item);
            label.setFont(Font.font("Arial", FontWeight.MEDIUM, 14));
            label.setTextFill(Color.web("#333"));
            label.setStyle("-fx-cursor: hand;");
            label.setOnMouseEntered(e -> label.setTextFill(Color.web("#007ACC")));
            label.setOnMouseExited(e -> label.setTextFill(Color.web("#333")));

            label.setOnMouseClicked(event -> {
                switch (item) {
                    case "Dashboard":
                        new DashboardView().showDashboard(stage, item);
                        break;
                    case "Profile":
                        //showAlert("Profile", "Profile page not yet implemented.");
                        break;
                    
                    
                    case "Help":
                        new helppageS().launchHelp(stage);
                        
                        break;
                    case "Logout":
                        LandingPage lp=new LandingPage();
                        lp.start(stage);
                        break;
                    default:
                        System.out.println(item + " clicked");
                }
            });

            menuLabels.add(label);
            menuContainer.getChildren().add(label);
        }

        toggleBtn.setOnAction(e -> {
            boolean currentlyVisible = menuContainer.isVisible();
            menuContainer.setVisible(!currentlyVisible);
            menuContainer.setManaged(!currentlyVisible);
        });

        sidebar.getChildren().addAll(toggleContainer, profileBox, menuContainer);


        Text title = new Text("📢 Submit a Complaint");
        title.setFont(Font.font("arial", 25));
        title.setFill(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#0f2027"))
        ));
        title.setStyle("-fx-font-weight: bold;");

        TextField nameField = new TextField();
        nameField.setPromptText("Your Name / User Name");
        nameField.setMaxHeight(20);
        nameField.setMaxWidth(350);

        TextField emailField = new TextField();
        emailField.setPromptText("Email or Contact Number");
        emailField.setMaxHeight(20);
        emailField.setMaxWidth(350);

        TextField providerField = new TextField();
        providerField.setPromptText("Service Providers Name");
        providerField.setMaxHeight(50);
        providerField.setMaxWidth(350);

        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Date of Incident");

        TextField subjectField = createStyledField("Subject / Title");

        ComboBox<String> complaintType = new ComboBox<>();
        complaintType.getItems().addAll("Service Delay", "Rude Behavior", "Overcharging", "Incomplete Work", "Other");
        complaintType.setPromptText("Select Complaint Type");
        styleComboBox(complaintType);

        ComboBox<String> priorityBox = new ComboBox<>();
        priorityBox.getItems().addAll("Low", "Medium", "High", "Urgent");
        priorityBox.setPromptText("Select Priority");
        styleComboBox(priorityBox);

        TextArea descriptionField = new TextArea();
        descriptionField.setPromptText("Describe your issue in detail...");
        descriptionField.setWrapText(true);
        descriptionField.setMaxWidth(320);
        descriptionField.setStyle("""
                -fx-border-color: #ccc;
                -fx-border-radius: 8;
                -fx-background-radius: 8;
                -fx-font-size: 14px;
                -fx-padding: 8;
        """);

        Button attachBtn = new Button("📁 Attach File");
        attachBtn.setStyle(buttonStyle("#28a745", "white"));
        Label attachedFileLabel = new Label();
        attachBtn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                attachedFileLabel.setText("Attached: " + file.getName());
            }
        });

        Button submitBtn = new Button("✔ Submit");
        submitBtn.setStyle(buttonStyle("#0fa4af", "white"));
        submitBtn.setOnAction(e->{
            new DashboardView().showDashboard(stage, "seeker");
        });

        Button resetBtn = new Button("⟲ Reset");
        resetBtn.setStyle(buttonStyle("#6c757d", "white"));

        addHoverEffect(submitBtn, "#0056b3");
        addHoverEffect(resetBtn, "#5a6268");
        addHoverEffect(attachBtn, "#218838");

        resetBtn.setOnAction(e -> {
            nameField.clear(); emailField.clear(); providerField.clear();
            datePicker.setValue(null); subjectField.clear();
            complaintType.setValue(null); priorityBox.setValue(null);
            descriptionField.clear(); attachedFileLabel.setText("");
        });

        HBox buttonBox = new HBox(20, submitBtn, resetBtn);
        buttonBox.setAlignment(Pos.CENTER);

        VBox innerBox = new VBox(15, title, nameField, emailField, providerField, datePicker, subjectField,
                complaintType, priorityBox, descriptionField,
                new HBox(12, attachBtn, attachedFileLabel),
                buttonBox
        );

        innerBox.setPadding(new Insets(40));
        innerBox.setAlignment(Pos.CENTER);
        innerBox.setMaxWidth(500);
        innerBox.setStyle("""
                -fx-background-color: #f4f0eeff;
                -fx-border-color: #dedede;
                -fx-border-radius: 15;
                -fx-background-radius: 15;
                -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.15), 12, 0.3, 0, 4);
        """);

        VBox root = new VBox(innerBox);
        root.setPadding(new Insets(50));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #f6dfd3ff");

        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent;");

        Scene scene = new Scene(scrollPane, 1540, 795);
        stage.setTitle("Complaint Submission Portal");
        stage.setScene(scene);
        stage.show();
    }

    private TextField createStyledField(String prompt) {
        TextField tf = new TextField();
        tf.setPromptText(prompt);
        tf.setMaxWidth(320);
        tf.setStyle("""
                -fx-border-color: #ccc;
                -fx-border-radius: 8;
                -fx-background-radius: 8;
                -fx-font-size: 14px;
                -fx-padding: 8;
        """);
        return tf;
    }

    private void styleComboBox(ComboBox<String> box) {
        box.setMaxWidth(320);
        box.setStyle("""
                -fx-border-color: #ccc;
                -fx-border-radius: 8;
                -fx-background-radius: 8;
                -fx-font-size: 14px;
                -fx-padding: 5;
        """);
    }

    private String buttonStyle(String bgColor, String textColor) {
        return "-fx-background-color: " + bgColor + ";" +
                "-fx-text-fill: " + textColor + ";" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 10 20;" +
                "-fx-cursor: hand;";
    }

    private void addHoverEffect(Button btn, String hoverColor) {
        btn.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->
                btn.setStyle(btn.getStyle().replaceFirst("#[a-fA-F0-9]{6}", hoverColor)));
        btn.addEventHandler(MouseEvent.MOUSE_EXITED, e ->
                btn.setStyle(btn.getStyle().replaceFirst("#[a-fA-F0-9]{6}", hoverColor.equals("#0056b3") ? "#007BFF"
                        : hoverColor.equals("#5a6268") ? "#6c757d" : "#28a745")));
    }
}
