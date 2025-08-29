
package com.user.view;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AboutUsPage {

    Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showAboutPage(Stage primaryStage) {
        VBox mainLayout = new VBox(40);
        mainLayout.setPadding(new Insets(30));
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #fdf2deff, #ffffff);");

        Button backButton = new Button("⟵ Back");
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        backButton.setStyle("-fx-background-color: white; -fx-text-fill: #4B7B4F; -fx-background-radius: 25;");
        backButton.setOnAction(e -> {
            LandingPage lp = new LandingPage();
            lp.start(primaryStage);
        });

        Label heading = new Label("About Us");
        heading.setFont(Font.font("Verdana", FontWeight.BOLD, 38));
        heading.setStyle("-fx-text-fill: white;");

        BorderPane topsection = new BorderPane();
        topsection.setPadding(new Insets(20));
        topsection.setStyle("-fx-background-color: #4B7B4F; -fx-background-radius: 20;");
        topsection.setMaxWidth(1600);
        topsection.setLeft(backButton);
        BorderPane.setAlignment(backButton, Pos.CENTER_LEFT);
        topsection.setCenter(heading);
        BorderPane.setAlignment(heading, Pos.CENTER);

        ImageView logoImage = new ImageView(new Image("/Assets/Images/logo.jpg"));
        logoImage.setFitWidth(300);
        logoImage.setFitHeight(200);
        logoImage.setPreserveRatio(true);
        addHoverEffect(logoImage);

        Label appDesc = new Label(
                "\u2728 ServiceConnect is a unique intergenerational platform that empowers elderly individuals to offer their life-long skills and experiences\n" +
                        "to the younger generation in exchange for technical help, companionship, or simple engagement.\n\n" +
                        "Users can register as Providers (elders) or Seekers (youth) and exchange services such as:\n" +
                        "\u2022 Traditional Cooking, Gardening, Tutoring, Storytelling, Spiritual Guidance\n" +
                        "\u2022 Music & Singing, Language Teaching, Child Care and more!\n\n" +
                        "Our vision is to bridge the generation gap while fostering empathy, learning, and companionship.\n" +
                        "From booking and payments to reviews, chats, and dispute resolutions – everything is streamlined and easy to use!"
        );
        appDesc.setFont(Font.font("Arial", 22));
        appDesc.setStyle("-fx-text-fill: #222222;");
        appDesc.setWrapText(true);

        HBox aboutBox = new HBox(100, appDesc, new StackPane(logoImage));
        aboutBox.setAlignment(Pos.CENTER);
        aboutBox.setPadding(new Insets(30));
        aboutBox.setStyle("-fx-background-color: linear-gradient(to right, #d9e3d4ff, #a8e063); -fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 15, 0, 0, 8);");
        aboutBox.setMaxWidth(1500);
        aboutBox.setPrefHeight(450);

        ImageView missionImage = new ImageView(new Image("/Assets/Images/core2web.jpeg"));
        missionImage.setFitWidth(300);
        missionImage.setFitHeight(150);
        missionImage.setPreserveRatio(true);
        addHoverEffect(missionImage);

        Label missionDesc = new Label("We are proud learners at Core2Web, an exceptional edtech platform that teaches code to the core.\n" +
                "It has become one of the most valuable and transformative parts of our journey.");
        missionDesc.setFont(Font.font("Arial", 22));
        missionDesc.setStyle("-fx-text-fill: #222222;");
        missionDesc.setWrapText(true);

        HBox missionBox = new HBox(100, new StackPane(missionImage), missionDesc);
        missionBox.setAlignment(Pos.CENTER);
        missionBox.setPadding(new Insets(30));
        missionBox.setStyle("-fx-background-color: linear-gradient(to right, #cedec7ff, #a8e063); -fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 15, 0, 0, 8);");
        missionBox.setMaxWidth(1500);
        missionBox.setPrefHeight(300);

        ImageView sirImage = new ImageView(new Image("Assets\\Images\\sir.jpeg"));
        sirImage.setFitWidth(300);
        sirImage.setPreserveRatio(true);
        addHoverEffect(sirImage);

        // Label sirDesc = new Label("Shashikant Bagal (Shashi Sir) is the founder of Core2Web. Since 2017, he has trained thousands of students\n" +
        //         "in C, C++, Java, Python, and Operating Systems, focusing on building core concepts from scratch.\n\n" +
        //         "We extend our heartfelt gratitude to Shashi Sir and Core2Web for their constant source of knowledge and growth.");
        // sirDesc.setFont(Font.font("Arial", 22));
        // sirDesc.setStyle("-fx-text-fill: #222222;");
        // sirDesc.setWrapText(true);
        
// Bold part
Text boldText = new Text("Shashikant Bagal (Shashi Sir)");
boldText.setFont(Font.font("System", FontWeight.BOLD, 22));
boldText.setStyle("-fx-text-fill: #222222;");


// Normal text
Text normalText1 = new Text(" is the founder of Core2Web. Since 2017, he has trained thousands of students\n"
        + "in C, C++, Java, Python, and Operating Systems, focusing on building core concepts from scratch.\n\n");

Text normalText2 = new Text("We extend our heartfelt gratitude to Shashi Sir and Core2Web for their constant source of knowledge and growth.");

// Set same font for all
normalText1.setFont(Font.font("System", 22));
normalText1.setStyle("-fx-text-fill: #222222;");
normalText2.setFont(Font.font("System", 22));
normalText2.setStyle("-fx-text-fill: #222222;");
        

// Combine into TextFlow
TextFlow sirDesc = new TextFlow(boldText, normalText1, normalText2);



        HBox sirBox = new HBox(100, sirDesc, new StackPane(sirImage));
        sirBox.setAlignment(Pos.CENTER);
        sirBox.setPadding(new Insets(30));
        sirBox.setStyle("-fx-background-color: linear-gradient(to right, #e7f9dfff, #a8e063); -fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 15, 0, 0, 8);");
        sirBox.setMaxWidth(1500);
        sirBox.setPrefHeight(300);

        // Label desc2 = new Label(
        //         "We would also like to extend our heartfelt gratitude to Sachin Sir and Pramod Sir for their mentorship and support.\n" +
        //                 "Their teaching has helped us grow as developers and problem-solvers.\n\n" +
        //                 "We truly appreciate their contribution to shaping our technical foundation and boosting our confidence."
        // );
        // desc2.setFont(Font.font("Arial", 22));
        // desc2.setStyle("-fx-text-fill: #222222;");
        // desc2.setWrapText(true);
        Text part1 = new Text("We would also like to extend our heartfelt gratitude to ");
Text sachin = new Text("Sachin Sir");
sachin.setFont(Font.font("Arial", FontWeight.BOLD, 22));

Text part2 = new Text(" and ");
Text pramod = new Text("Pramod Sir");
pramod.setFont(Font.font("Arial", FontWeight.BOLD, 22));

Text part3 = new Text(" for their mentorship and support.\nTheir teaching has helped us grow as developers and problem-solvers.\n\nWe truly appreciate their contribution to shaping our technical foundation and boosting our confidence.");

part1.setFont(Font.font("Arial", 22));
part2.setFont(Font.font("Arial", 22));
part3.setFont(Font.font("Arial", 22));

// Optional text color
part1.setStyle("-fx-fill: #222222;");
sachin.setStyle("-fx-fill: #222222;");
part2.setStyle("-fx-fill: #222222;");
pramod.setStyle("-fx-fill: #222222;");
part3.setStyle("-fx-fill: #222222;");

// Wrap it all in a TextFlow
TextFlow desc2 = new TextFlow(part1, sachin, part2, pramod, part3);
desc2.setMaxWidth(800); // or whatever suits your layout

        HBox gratitudeSection = new HBox(desc2);
        gratitudeSection.setAlignment(Pos.CENTER);
        gratitudeSection.setPadding(new Insets(30));
        gratitudeSection.setStyle("-fx-background-color: linear-gradient(to right, #e7f9dfff, #a8e063); -fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 15, 0, 0, 8);");
        gratitudeSection.setMaxWidth(1500);
        gratitudeSection.setPrefHeight(300);

        // --- Team Members Section ---
        

        ImageView member1Img = createTeamImage("/Assets//Images//akshata.png");
        Label member1Name = new Label("Akshata Kapare");

        ImageView member2Img = createTeamImage("/Assets//Images//bhumika.jpg");
        Label member2Name = new Label("Bhumika Nangare");

        ImageView member3Img = createTeamImage("/Assets//Images//pooja.jpg");
        Label member3Name = new Label("Pooja Gaikwad");

        ImageView member4Img = createTeamImage("/Assets//Images//vaishnavi.jpg");
        Label member4Name = new Label("Vaishnavi Patil");

        VBox member1 = createMemberBox(member1Img, member1Name);
        VBox member2 = createMemberBox(member2Img, member2Name);
        VBox member3 = createMemberBox(member3Img, member3Name);
        VBox member4 = createMemberBox(member4Img, member4Name);

        HBox teamHBox = new HBox(40,member1, member2, member3, member4);
        teamHBox.setAlignment(Pos.CENTER);
        teamHBox.setPadding(new Insets(20));
        teamHBox.setStyle("-fx-background-color: linear-gradient(to right, #cfdec7ff, #a8e063); " +
                "-fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 15, 0, 0, 8); " +
                "-fx-border-color: #ffffff; -fx-border-width: 2; -fx-border-radius: 20;");
        teamHBox.setMaxWidth(1000);
        teamHBox.setPrefHeight(300);

        mainLayout.getChildren().addAll(topsection, aboutBox, missionBox, sirBox, gratitudeSection,teamHBox);

        ScrollPane scrollPane = new ScrollPane(mainLayout);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: linear-gradient(to bottom, #fefefe, #e0ffe0);");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        Scene scene = new Scene(scrollPane, 1540, 795);
        primaryStage.setScene(scene);
        primaryStage.setTitle("About Us - ServiceConnect");
        primaryStage.show();
    }

    private void addHoverEffect(ImageView imageView) {
        imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), imageView);
            st.setToX(1.1);
            st.setToY(1.1);
            st.play();
        });
        imageView.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), imageView);
            st.setToX(1);
            st.setToY(1);
            st.play();
        });
    }

    private ImageView createTeamImage(String path) {
        ImageView img = new ImageView(new Image(path));
        img.setFitWidth(120);
        img.setFitHeight(120);
        img.setPreserveRatio(true);
        img.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 5); -fx-background-radius: 50;");
        addHoverEffect(img);
        return img;
    }

    private VBox createMemberBox(ImageView image, Label name) {
        name.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        name.setStyle("-fx-text-fill: #333;");
        VBox box = new VBox(10, image, name);
        box.setAlignment(Pos.CENTER);
        return box;
    }
}
