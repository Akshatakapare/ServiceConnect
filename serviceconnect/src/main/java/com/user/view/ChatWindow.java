package com.user.view;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.user.Controller.FirebaseChatController;
import com.user.model.Chat;

public class ChatWindow  {

        private String senderId;
        private String receiverId;

    // public ChatWindow(String senderId, String receiverId) {
    //     this.senderId = senderId;
    //     this.receiverId = receiverId;
    // }

    // private String senderId;
    // private String receiverId;
    private FirebaseChatController controller = new FirebaseChatController();

    private VBox messageContainer;
    private ScrollPane scrollPane;
    private TextField inputField;
    private Button sendButton;

    private Timer refreshTimer;

    public ChatWindow() {
        // Empty constructor for JavaFX
    }

    public ChatWindow(String senderId, String receiverId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

  
    public void show() {
    Stage stage = new Stage();
    stage.setTitle("Chat with " + receiverId);

    BorderPane root = new BorderPane();
    messageContainer = new VBox(10);
    messageContainer.setPadding(new Insets(10));
    messageContainer.setFillWidth(true);

    scrollPane = new ScrollPane(messageContainer);
    scrollPane.setFitToWidth(true);
    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

    inputField = new TextField();
    inputField.setPromptText("Type a message...");
    inputField.setPrefWidth(400);

    sendButton = new Button("Send");
    sendButton.setOnAction(e -> sendMessage());

    HBox inputBox = new HBox(10, inputField, sendButton);
    inputBox.setPadding(new Insets(10));

    root.setCenter(scrollPane);
    root.setBottom(inputBox);

    Scene scene = new Scene(root, 500, 600);
    stage.setScene(scene);
    stage.show();

    loadMessages();
    startAutoRefresh();

    // Stop timer on close
    stage.setOnCloseRequest(e -> stop());
}


    // private void sendMessage() {
    //     String text = inputField.getText().trim();
    //     if (text.isEmpty()) return;

    //     String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    //     Chat chat = new Chat(senderId, receiverId, text, timestamp);
    //     boolean success = controller.sendMessage(chat);

    //     if (success) {
    //         inputField.clear();
    //         loadMessages();
    //     } else {
    //         Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to send message.");
    //         alert.show();
    //     }
    // }

    private void sendMessage() {
    String text = inputField.getText().trim();
    
    if (text.isEmpty()) return;

    // Null check for senderId and receiverId
    if (senderId == null || receiverId == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Sender or Receiver ID is not set.");
        alert.show();
        return;
    }

    String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    Chat chat = new Chat(senderId, receiverId, text, timestamp);
    boolean success = controller.sendMessage(chat);

    if (success) {
        inputField.clear();
        loadMessages();
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to send message.");
        alert.show();
    }
}


    // private void loadMessages() {
    //     List<Chat> messages = controller.getMessagesBetweenUsers(senderId, receiverId);

    //     Platform.runLater(() -> {
    //         messageContainer.getChildren().clear();
    //         for (Chat msg : messages) {
    //             Label msgLabel = new Label();
    //             msgLabel.setWrapText(true);
    //             msgLabel.setMaxWidth(450);

    //             if (msg.getSenderId().equals(senderId)) {
    //                 msgLabel.setText("You: " + msg.getMessage());
    //                 msgLabel.setStyle("-fx-background-color: lightblue; -fx-padding: 5; -fx-background-radius: 5;");
    //             } else {
    //                 msgLabel.setText(receiverId + ": " + msg.getMessage());
    //                 msgLabel.setStyle("-fx-background-color: lightgreen; -fx-padding: 5; -fx-background-radius: 5;");
    //             }

    //             messageContainer.getChildren().add(msgLabel);
    //         }

    //         scrollPane.setVvalue(1.0); // Scroll to bottom
    //     });
    // }
    private void loadMessages() {
    // Step 4: Null check
    if (senderId == null || receiverId == null) {
        System.out.println("⚠️ senderId or receiverId is null. Cannot load messages.");
        return;  // Don't continue if IDs are missing
    }

    // Safe to proceed — get messages from Firebase
    List<Chat> messages = controller.getMessagesBetweenUsers(senderId, receiverId);

    // Update chat window
    Platform.runLater(() -> {
        messageContainer.getChildren().clear();

        for (Chat msg : messages) {
            Label msgLabel = new Label();
            msgLabel.setWrapText(true);
            msgLabel.setMaxWidth(450);

            if (msg.getSenderId().equals(senderId)) {
                msgLabel.setText("You: " + msg.getMessage());
                msgLabel.setStyle("-fx-background-color: lightblue; -fx-padding: 5; -fx-background-radius: 5;");
            } else {
                msgLabel.setText(receiverId + ": " + msg.getMessage());
                msgLabel.setStyle("-fx-background-color: lightgreen; -fx-padding: 5; -fx-background-radius: 5;");
            }

            messageContainer.getChildren().add(msgLabel);
        }

        scrollPane.setVvalue(1.0);  // Auto scroll to bottom
    });
}


    private void startAutoRefresh() {
        refreshTimer = new Timer(true);
        refreshTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                loadMessages();
            }
        }, 0, 3000); // Refresh every 3 seconds
    }

   // @Override
    public void stop() {
        if (refreshTimer != null) {
            refreshTimer.cancel();
        }
    }

}