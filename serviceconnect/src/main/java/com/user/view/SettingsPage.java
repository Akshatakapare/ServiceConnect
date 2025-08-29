package com.user.view;

import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class SettingsPage  {

    public void start(Stage stage){
        VBox layout = new VBox(30);
        layout.setPadding(new Insets(40));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #ffffff;");

        Label title = new Label("Settings");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 32));
        title.setTextFill(Color.BLACK);

        ToggleGroup statusGroup = new ToggleGroup();
        RadioButton online = new RadioButton("Online");
        RadioButton offline = new RadioButton("Offline");
        online.setToggleGroup(statusGroup);
        offline.setToggleGroup(statusGroup);
        online.setSelected(true);
        styleRadio(online);
        styleRadio(offline);
        VBox statusCard = sectionGroup("Status", new HBox(30, online, offline));

        VBox notifCard = sectionGroup("Notifications", new VBox(20,
            createMandyaToggle("Email Alerts"),
            createMandyaToggle("SMS Alerts"),
            createMandyaToggle("App Notifications")
        ));

        VBox serviceCard = sectionGroup("Service Preferences", new VBox(20,
            createMandyaToggle("Auto-Sync Services"),
            createMandyaToggle("Availability Reminders"),
            createMandyaToggle("Profile Suggestions")
        ));

        ComboBox<String> langBox = new ComboBox<>();
        langBox.getItems().addAll("English", "Marathi", "Hindi");
        langBox.setPromptText("Choose Language");
        langBox.setPrefWidth(300);

        ComboBox<String> themeBox = new ComboBox<>();
        themeBox.getItems().addAll("Light", "Dark", "Professional", "Mint");
        themeBox.setPromptText("Choose Theme");
        themeBox.setPrefWidth(300);

        VBox uiCard = sectionGroup("Interface Preferences", new VBox(20, langBox, themeBox));

        Button passwordBtn = new Button("Change Password");
        styleButton(passwordBtn);
        VBox securityCard = sectionGroup("Security", new VBox(passwordBtn));
          styleButton(passwordBtn);
             passwordBtn.setOnAction(e -> {
                Changepass changePage = new Changepass();
                 try {
                  changePage.start(stage);
                } catch (Exception ex) {
                   ex.printStackTrace();
    }
});


        Button deleteBtn = new Button("Delete Account");
        deleteBtn.setStyle("-fx-background-color: #e41814ff; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 8;");
        VBox deleteCard = sectionGroup("Delete My Account", new VBox(deleteBtn));
       
          deleteBtn.setOnAction(e -> {
            DeleteAcc deletePage = new DeleteAcc();
              try {
              deletePage.start(stage);
              } catch (Exception ex) {
                 ex.printStackTrace();
    }
});

        Button saveBtn = new Button("Save");
        saveBtn.setOnAction(e -> {
  
    dashboardpageP dashboard = new dashboardpageP();
    try {
        dashboard.launchdashboard(stage, "Ram");
    } catch (Exception ex) {
        ex.printStackTrace();
    }
});

        Button resetBtn = new Button("Reset");
        resetBtn.setStyle("-fx-background-color:gray");
        resetBtn.setTextFill(Color.WHITE);
        styleButton(saveBtn);
        
        
        HBox actionBox = new HBox(25, saveBtn, resetBtn);
        actionBox.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(
            title,
            statusCard,
            notifCard,
            serviceCard,
            uiCard,
            securityCard,
            deleteCard,
            actionBox
        );

        ScrollPane scrollPane = new ScrollPane(layout);
        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);
        scrollPane.setStyle("-fx-background: #f8e1e1ff;");

        Scene scene = new Scene(scrollPane,1540,795);
        stage.setScene(scene);
        stage.setTitle("Provider Settings - ServiceConnect");
        stage.setMaximized(true);
        stage.show();
    }

    private VBox sectionGroup(String title, Node content) {
        Label header = new Label(title);
        header.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        header.setTextFill(Color.BLACK);

        VBox box = new VBox(15, header, content);
        box.setPadding(new Insets(20));
        box.setMaxWidth(1000);
        box.setStyle("-fx-background-color: #ffffff; -fx-border-color: #ddd; -fx-border-radius: 10; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, #ccc, 10, 0.2, 0, 4);");
        return box;
    }

    private HBox createMandyaToggle(String labelText) {
        Label label = new Label(labelText);
        label.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));
        label.setTextFill(Color.BLACK);

        StackPane toggle = new StackPane();
        toggle.setPrefSize(52, 28);
        toggle.setStyle("-fx-background-color: #5A5A5A; -fx-background-radius: 50;");
        toggle.setCursor(javafx.scene.Cursor.HAND);

        Circle knob = new Circle(12, Color.WHITE);
        knob.setEffect(new javafx.scene.effect.DropShadow(4, Color.GRAY));
        StackPane.setAlignment(knob, Pos.CENTER_LEFT);
        StackPane.setMargin(knob, new Insets(0, 0, 0, 3));
        toggle.getChildren().add(knob);

        final boolean[] isOn = {false};

        toggle.setOnMouseClicked(e -> {
            isOn[0] = !isOn[0];
            if (isOn[0]) {
                toggle.setStyle("-fx-background-color: #1E88E5; -fx-background-radius: 50;");
                StackPane.setAlignment(knob, Pos.CENTER_RIGHT);
                StackPane.setMargin(knob, new Insets(0, 3, 0, 0));
            } else {
                toggle.setStyle("-fx-background-color: #5A5A5A; -fx-background-radius: 50;");
                StackPane.setAlignment(knob, Pos.CENTER_LEFT);
                StackPane.setMargin(knob, new Insets(0, 0, 0, 3));
            }
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox row = new HBox(20, label, spacer, toggle);
        row.setAlignment(Pos.CENTER_LEFT);
        return row;
    }

    private void styleRadio(RadioButton rb) {
        rb.setTextFill(Color.BLACK);
        rb.setFont(Font.font("Segoe UI", 14));
    }

    private void styleButton(Button btn) {
        btn.setStyle("-fx-background-color: #1580e4ff; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 8;");
    }

    
}