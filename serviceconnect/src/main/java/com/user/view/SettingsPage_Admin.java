package com.user.view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

public class SettingsPage_Admin {

    boolean isDarkMode = false;

    public SettingsPage_Admin(Stage userStage) {
        BorderPane root = new BorderPane();

        // ---------- Top Bar ----------
        HBox topbar = new HBox();
        topbar.setPadding(new Insets(20));
        topbar.setStyle("-fx-background-color: #2c3e50;");
        Label title = new Label("Admin Settings");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");
        topbar.getChildren().add(title);
        root.setTop(topbar);

        // ---------- Sidebar (Copied from Dashboard_Admin) ----------
        VBox sidebar = new VBox(15);
        sidebar.setStyle("-fx-background-color: #34495E; -fx-padding: 20;");
        sidebar.setPrefWidth(220);
        sidebar.setAlignment(Pos.TOP_CENTER);

        Label sidebarTitle = new Label("MENU");
        sidebarTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white;");
        sidebarTitle.setPadding(new Insets(0, 0, 20, 0));

        Button[] navButtons = {
            createSidebarButton("Dashboard", "fas-tachometer-alt", e -> {
                try { new Dashboard_Admin().show(userStage); } catch (Exception ex) { ex.printStackTrace(); }
            }),
            createSidebarButton("User Management", "fas-users", e -> new UserManagementPage_Admin(userStage)),
            createSidebarButton("Service Management", "fas-tools", e -> new ServiceManagementPage_Admin(userStage)),
            createSidebarButton("Bookings", "fas-calendar-check", e -> {
                try { new Bookings_Admin(userStage); } catch (Exception e1) { e1.printStackTrace(); }
            }),
            createSidebarButton("Complaints", "fas-exclamation-circle", e -> {
                try { new Complaints_Admin().show(); } catch (Exception ex) { ex.printStackTrace(); }
            }),
            createSidebarButton("Notifications", "fas-bell", e -> {
                try { new Notifications_Admin(userStage); } catch (Exception e1) { e1.printStackTrace(); }
            }),
            createSidebarButton("Settings", "fas-cogs", null) // already in settings
        };

        for (Button btn : navButtons) {
            btn.setMaxWidth(Double.MAX_VALUE);
            VBox.setMargin(btn, new Insets(5, 0, 5, 0));
        }

        sidebar.getChildren().add(sidebarTitle);
        sidebar.getChildren().addAll(navButtons);
        root.setLeft(sidebar);

        // ---------- Settings Content ----------
        VBox centerContent = new VBox(30);
        centerContent.setPadding(new Insets(30));
        centerContent.setAlignment(Pos.TOP_LEFT);
        centerContent.setStyle("-fx-background-color: #f9f9f9;");

        Label accountLabel = new Label("⚙️ Account Settings");
        accountLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        Separator separator1 = new Separator();

        VBox settingsCard = new VBox(20);
        settingsCard.setPadding(new Insets(20));
        settingsCard.setStyle("-fx-background-color: white; -fx-background-radius: 12; "
                + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 4);");

        // Dark Mode Toggle
        CheckBox darkModeToggle = new CheckBox("Enable Dark Mode");
        darkModeToggle.setStyle("-fx-font-size: 15px;");
        darkModeToggle.setOnAction(e -> {
            isDarkMode = darkModeToggle.isSelected();
            if (isDarkMode) {
                centerContent.setStyle("-fx-background-color: #1f1f1f;");
                settingsCard.setStyle("-fx-background-color: #2c3e50; -fx-background-radius: 12;");
                darkModeToggle.setTextFill(Color.WHITE);
                accountLabel.setTextFill(Color.WHITE);
            } else {
                centerContent.setStyle("-fx-background-color: #f9f9f9;");
                settingsCard.setStyle("-fx-background-color: white; -fx-background-radius: 12;");
                darkModeToggle.setTextFill(Color.BLACK);
                accountLabel.setTextFill(Color.BLACK);
            }
        });

        // Password Change
        PasswordField currentPassword = new PasswordField();
        currentPassword.setPromptText("Current Password");
        currentPassword.setStyle("-fx-pref-height: 40; -fx-background-radius: 8;");

        PasswordField newPassword = new PasswordField();
        newPassword.setPromptText("New Password");
        newPassword.setStyle("-fx-pref-height: 40; -fx-background-radius: 8;");

        Button updatePasswordBtn = new Button("Update Password");
        updatePasswordBtn.setPrefHeight(40);
        updatePasswordBtn.setStyle("-fx-background-color: #2980b9; -fx-text-fill: white; "
                + "-fx-font-weight: bold; -fx-background-radius: 8;");
        updatePasswordBtn.setOnMouseEntered(e -> updatePasswordBtn.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; "
                + "-fx-font-weight: bold; -fx-background-radius: 8;"));
        updatePasswordBtn.setOnMouseExited(e -> updatePasswordBtn.setStyle("-fx-background-color: #2980b9; -fx-text-fill: white; "
                + "-fx-font-weight: bold; -fx-background-radius: 8;"));
        updatePasswordBtn.setOnAction(e -> {
            System.out.println("Update password logic here.");
        });

        VBox passwordBox = new VBox(10, currentPassword, newPassword, updatePasswordBtn);

        // Logout Button
        Button logoutBtn = new Button("Logout");

        logoutBtn.setOnAction(e->{
            LandingPage lp = new LandingPage();
            lp.start(userStage);
            System.out.println("Logout logic.");
        });

        logoutBtn.setPrefHeight(40);
        logoutBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8;");
        logoutBtn.setOnMouseEntered(e -> logoutBtn.setStyle("-fx-background-color: #c0392b; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8;"));
        logoutBtn.setOnMouseExited(e -> logoutBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8;"));
        // logoutBtn.setOnAction(e -> {
        //     System.out.println("Logout logic.");
        // });

        settingsCard.getChildren().addAll(darkModeToggle, passwordBox, new Separator(), logoutBtn);

        centerContent.getChildren().addAll(accountLabel, separator1, settingsCard);

        ScrollPane scrollPane = new ScrollPane(centerContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        root.setCenter(scrollPane);

        Scene scene = new Scene(root, 1540, 795);
        userStage.setScene(scene);
        userStage.setTitle("Admin Settings");
        userStage.getIcons().add(new Image("Assets\\ServiceConnectLogo.jpeg"));
        userStage.show();
    }

    private Button createSidebarButton(String text, String iconLiteral, javafx.event.EventHandler<javafx.event.ActionEvent> handler) {
        Button btn = (iconLiteral != null)
            ? new Button(text, new FontIcon(iconLiteral))
            : new Button(text);
        btn.setPrefHeight(40);
        btn.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white; -fx-font-size: 14px;");
        btn.setAlignment(Pos.CENTER_LEFT);
        btn.setGraphicTextGap(12);
        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: #3e556e; -fx-text-fill: white; -fx-font-size: 14px;"));
        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white; -fx-font-size: 14px;"));

        if (handler != null) btn.setOnAction(handler);
        return btn;
    }
}
