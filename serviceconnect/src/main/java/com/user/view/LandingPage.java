package com.user.view;


import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import java.util.*;

public class LandingPage extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane rootLayout = new BorderPane();
        ImageView backgroundImage = new ImageView(
        new Image("Assets\\Images\\backg.jpg") // Replace with your background image path
    );
    backgroundImage.fitWidthProperty().bind(stage.widthProperty());
backgroundImage.fitHeightProperty().bind(stage.heightProperty());
    backgroundImage.setPreserveRatio(false);
    backgroundImage.setOpacity(0.9); // light overlay
    backgroundImage.setSmooth(true);
    backgroundImage.setEffect(new BoxBlur(8, 5, 9));

        ImageView logoView = new ImageView(new Image("Assets\\Images\\Logo.png"));
        logoView.setFitWidth(100);
        logoView.setPreserveRatio(true);

        Label appName = new Label("ServiceConnect");
        appName.setFont(Font.font("Poppins", FontWeight.BOLD, 35));
        appName.setTextFill(Color.WHITE);

        Button aboutusbtn = new Button("About Us");
aboutusbtn.setTextFill(Color.WHITE);
aboutusbtn.setAlignment(Pos.CENTER_RIGHT);

// Default style
aboutusbtn.setStyle("""
    -fx-background-color: linear-gradient(to right, #56ab2f, #a8e063);
    -fx-font-size: 14px;
    -fx-font-weight: bold;
    -fx-background-radius: 10;
    -fx-cursor: hand;
""");

// Hover effect using mouse events
aboutusbtn.setOnMouseEntered(e -> {
    aboutusbtn.setStyle("""
        -fx-background-color: linear-gradient(to right, #7ed957, #b5f08e);
        -fx-font-size: 14px;
        -fx-font-weight: bold;
        -fx-background-radius: 10;
        -fx-cursor: hand;
    """);
});

aboutusbtn.setOnMouseExited(e -> {
    aboutusbtn.setStyle("""
        -fx-background-color: linear-gradient(to right, #56ab2f, #a8e063);
        -fx-font-size: 14px;
        -fx-font-weight: bold;
        -fx-background-radius: 10;
        -fx-cursor: hand;
    """);
});

aboutusbtn.setOnAction(e -> {
    AboutUsPage aboutus = new AboutUsPage();
    aboutus.showAboutPage(stage);
});

        HBox appBar = new HBox(500, logoView, appName,aboutusbtn);
        appBar.setAlignment(Pos.CENTER_LEFT);
        appBar.setPadding(new Insets(30));
        appBar.setStyle("-fx-background-color: #4B7B4F;");

        Label quote = new Label("\"Connecting Generations, Empowering Lives\"");
        quote.setFont(Font.font("Poppins", FontPosture.ITALIC, 35));
        quote.setTextFill(Color.BROWN);
        quote.setWrapText(true);
        quote.setAlignment(Pos.CENTER);

        Label about = new Label("ServiceConnect is a platform where experienced elders can share their skills. In return, they receive respect, engagement, and sometimes even tech help or assistance.");
        about.setFont(Font.font("Segoe UI", 18));
        about.setTextFill(Color.BLACK);
        about.setWrapText(true);
        about.setAlignment(Pos.CENTER);

        VBox headerBox = new VBox(20, quote, about);
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setPadding(new Insets(20));

        // VBox providerBox = createRoleBox("Assets\\Images\\provider.jpg", "👴 I’m a Provider", "Continue as Provider");
        // VBox seekerBox = createRoleBox("Assets\\Images\\seeker.jpg", "🙋 I’m a Seeker", "Continue as Seeker");


        VBox providerBox = createRoleBox(
    "Assets\\Images\\provider.jpg",
    "👴 I’m a Provider",
    "Continue as Provider",
    () -> {
        try {
            new loginPageP().start(new Stage()); // ✅ Provider Login Page
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
);

VBox seekerBox = createRoleBox(
    "Assets\\Images\\seeker.jpg",
    "🙋 I’m a Seeker",
    "Continue as Seeker",
    () -> {
        try {
            new loginPageS().start(new Stage()); // ✅ Seeker Login Page
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
);

        HBox roleBox = new HBox(80, providerBox, seekerBox);
        roleBox.setMaxWidth(800); // avoids overflow
        roleBox.setAlignment(Pos.CENTER);
        roleBox.setPadding(new Insets(20));

        VBox testimonials = createTestimonialSlider();
        VBox gallerySection = createGallerySection();

        String[][] advantages = {
            {"Bridges Generation Gap", "Through regular interactions, younger generations understand traditional values while elders feel a sense of belonging and recognition."},
            {"Boosts Confidence", "Providers feel a renewed purpose, and seekers gain knowledge directly from lived experiences."},
            {"Cultural Continuity", "Through cooking, crafts, or language, culture is preserved and passed on lovingly."},
            {"Emotional Wellness", "Providers feel less lonely, and seekers gain empathy through meaningful exchanges."},
            {"Tech Exchange", "Elders provide life lessons; in return, they learn tech from youngsters—creating mutual growth."}
        };
        VBox advantagesSection = createZigzagSection(advantages, "Advantages", true);
        applyHoverEffect(advantagesSection);

        VBox servicesSection = createServiceSection();

        Label footerLabel = new Label("\u00a9 2025 ServiceConnect | support@serviceconnect.in");
        footerLabel.setFont(Font.font("Arial", 20));
        footerLabel.setTextFill(Color.WHITE);

        HBox footer = new HBox(footerLabel);
        footer.setAlignment(Pos.CENTER);
        footer.setPadding(new Insets(30));
        footer.setStyle("-fx-background-color: #4B7B4F;");

        VBox content = new VBox(40,appBar, headerBox, roleBox, testimonials, gallerySection, advantagesSection, servicesSection,footer);
        content.setAlignment(Pos.TOP_CENTER);
        content.setMaxWidth(1100); // within visible safe area
        //content.setPadding(new Insets(20));
        content.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 16, 0.2, 0, 8);\n" + //
                        "    -fx-border-color: transparent;");
       

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        
        rootLayout.setCenter(scrollPane);

        StackPane root = new StackPane();
    
    root.getChildren().addAll(backgroundImage,rootLayout);
    StackPane.setAlignment(rootLayout, Pos.CENTER);
    backgroundImage.fitWidthProperty().bind(root.widthProperty());
    backgroundImage.fitHeightProperty().bind(root.heightProperty());

        
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
        scene.setFill(Color.TRANSPARENT);
        stage.setTitle("ServiceConnect - Welcome");
        stage.setScene(scene);
        stage.show();
    }

    private VBox createRoleBox(String imagePath, String labelText, String buttonText, Runnable action) {
    ImageView img = new ImageView(new Image(imagePath));
    img.setFitHeight(200);
    img.setPreserveRatio(true);
    applyHoverEffect(img);

    Label label = new Label(labelText);
    label.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
    label.setTextFill(Color.WHITE);

    // Button button = new Button(buttonText);
    // button.setStyle("-fx-text-fill: white; -fx-background-color: green; -fx-font-weight: bold;");
    // button.setMaxWidth(200);
    // applyHoverEffect(button);

    Button button = new Button(buttonText);
button.setMaxWidth(200);

// 🌿 Default style
String originalStyle =
    "-fx-background-color: linear-gradient(to right, #56ab2f, #a8e063);" +
    "-fx-text-fill: white;" +
    "-fx-font-weight: bold;" +
    "-fx-background-radius: 30;" +
    "-fx-padding: 10 20;" +
    "-fx-font-size: 14px;" +
    "-fx-cursor: hand;" +
    "-fx-focus-color: transparent;" +
    "-fx-faint-focus-color: transparent;" +
    "-fx-background-insets: 0;" +
    "-fx-effect: none;";

// 🌱 Hover style
String hoverStyle =
    "-fx-background-color: linear-gradient(to right, #28652aff, #81c784);" +
    "-fx-text-fill: white;" +
    "-fx-font-weight: bold;" +
    "-fx-background-radius: 30;" +
    "-fx-padding: 10 20;" +
    "-fx-font-size: 14px;" +
    "-fx-cursor: hand;" +
    "-fx-focus-color: transparent;" +
    "-fx-faint-focus-color: transparent;" +
    "-fx-background-insets: 0;" +
    "-fx-effect: none;";

// 🌳 Pressed style
String pressedStyle =
    "-fx-background-color: linear-gradient(to right, #2e7d32, #66bb6a);" +
    "-fx-text-fill: white;" +
    "-fx-font-weight: bold;" +
    "-fx-background-radius: 30;" +
    "-fx-padding: 10 20;" +
    "-fx-font-size: 14px;" +
    "-fx-cursor: hand;" +
    "-fx-focus-color: transparent;" +
    "-fx-faint-focus-color: transparent;" +
    "-fx-background-insets: 0;" +
    "-fx-effect: none;";

    // ➕ Apply default style
button.setStyle(originalStyle);

// 🖱 Hover events
button.setOnMouseEntered(e -> button.setStyle(hoverStyle));
button.setOnMouseExited(e -> button.setStyle(originalStyle));

// 🖱 Click events
button.setOnMousePressed(e -> button.setStyle(pressedStyle));
button.setOnMouseReleased(e -> {
    if (button.isHover()) {
        button.setStyle(hoverStyle);
    } else {
        button.setStyle(originalStyle);
    }
});

    // ✅ Navigation logic
    button.setOnAction(e -> {
        action.run(); // run passed action (open loginPageP or loginPageS)
        ((Stage) button.getScene().getWindow()).close(); // close LandingPage
    });

    VBox box = new VBox(10, img, label, button);
    box.setAlignment(Pos.CENTER);
    applyHoverEffect(box);
    return box;
}


    private VBox createGallerySection() {
        Label title = new Label("Our Community");
        title.setFont(Font.font("Poppins", FontWeight.BOLD, 24));
        title.setTextFill(Color.BROWN);

        HBox gallery = new HBox(20);
        gallery.setAlignment(Pos.CENTER);
        gallery.setPadding(new Insets(10));

        for (int i = 1; i <= 5; i++) {
            ImageView imageView = new ImageView(new Image("Assets\\Images\\Slide" + i + ".jpg"));
            imageView.setFitWidth(450);
            imageView.setFitHeight(300);
            applyHoverEffect(imageView);
            gallery.getChildren().add(imageView);
        }

        ScrollPane scrollPane = new ScrollPane(gallery);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background: white;");
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // no horizontal bar
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // only show when needed

        VBox section = new VBox(15, title, scrollPane);
        section.setPadding(new Insets(10));
        section.setAlignment(Pos.CENTER);
        return section;
    }

    private VBox createTestimonialSlider() {
        String[] testimonials = {
            "\"This platform gave me a sense of purpose again!\" – Mr. Joshi",
            "\"I never knew my storytelling could inspire!\" – Mrs. Iyer",
            "\"Thank you ServiceConnect for connecting me to real mentors!\" – Rohan"
        };

        Label testimonialLabel = new Label(testimonials[0]);
        testimonialLabel.setWrapText(true);
        testimonialLabel.setFont(Font.font("Segoe UI", FontPosture.ITALIC, 20));
        testimonialLabel.setTextFill(Color.WHITE);
        //testimonialLabel.setMaxWidth(200);
        applyHoverEffect(testimonialLabel);
        testimonialLabel.setAlignment(Pos.TOP_LEFT);

        HBox dotBox = new HBox(5);
        dotBox.setAlignment(Pos.CENTER);
        Circle[] circleDots = new Circle[testimonials.length];
        for (int i = 0; i < testimonials.length; i++) {
            circleDots[i] = new Circle(5);
            circleDots[i].setFill(i == 0 ? Color.DARKVIOLET : Color.LIGHTGRAY);
            dotBox.getChildren().add(circleDots[i]);
        }

        Timeline slider = new Timeline(new KeyFrame(Duration.seconds(4), e -> {
            int current = Arrays.asList(testimonials).indexOf(testimonialLabel.getText());
            int next = (current + 1) % testimonials.length;
            testimonialLabel.setText(testimonials[next]);
            for (int i = 0; i < circleDots.length; i++) {
                circleDots[i].setFill(i == next ? Color.DARKVIOLET : Color.LIGHTGRAY);
            }
        }));
        slider.setCycleCount(Timeline.INDEFINITE);
        slider.play();

        VBox testimonialBox = new VBox(10, testimonialLabel, dotBox);
        testimonialBox.setAlignment(Pos.CENTER);
        testimonialBox.setPadding(new Insets(20));
        testimonialBox.setMaxWidth(500);
        testimonialBox.setStyle("-fx-background-color: rgba(55, 67, 8, 0.6); -fx-background-radius: 10;");
        return testimonialBox;
    }

    private VBox createZigzagSection(String[][] items, String titleText, boolean animate) {
        VBox section = new VBox(20);
        section.setPadding(new Insets(20));
        section.setAlignment(Pos.CENTER);

        Label title = new Label(titleText);
        title.setFont(Font.font("Poppins", FontWeight.BOLD, 24));
        title.setTextFill(Color.BROWN);
        section.getChildren().add(title);

        
        for (String[] item : items) {
            Label boldTitle = new Label(item[0]);
            boldTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 17));
            boldTitle.setTextFill(Color.DARKRED);

            Label description = new Label(item[1]);
            description.setFont(Font.font("Segoe UI", 15));
            description.setWrapText(true);
            description.setMaxWidth(450);

            VBox box = new VBox(5, boldTitle, description);
            box.setPadding(new Insets(20));
            box.setStyle("-fx-background-color: #4B7B4F; -fx-background-radius: 15;");
            box.setMaxSize(400,300);
            section.getChildren().add(box);
            
        }
        return section;
    }

    private VBox createServiceSection() {
    Label title = new Label("Our Services");
    title.setFont(Font.font("Poppins", FontWeight.BOLD, 24));
    title.setTextFill(Color.BROWN);

    // Images and labels
    String[][] services = {
        {"Assets\\Images\\cook.jpg", "Home-Cooked Meals\nTaste of tradition in every bite passed lovingly through generations."},
        {"Assets\\Images\\garden.jpg", "Gardening Help\nNurture plants while nurturing bonds with experienced green thumbs."},
        {"Assets\\Images\\story.jpg", "Storytelling\nStories from the past that shape the wisdom of tomorrow."},
        {"Assets\\Images\\yoga.jpg", "Yoga\nIt is key of happy and healthy lifestyle."}
        
        
    };

    HBox serviceBox = new HBox(100);
    serviceBox.setAlignment(Pos.CENTER);
    serviceBox.setPadding(new Insets(10));

    for (String[] service : services) {
        ImageView imageView = new ImageView(new Image(service[0]));
        imageView.setFitWidth(300);
        imageView.setFitHeight(200);
        applyHoverEffect(imageView);

        Label label = new Label(service[1]);
        label.setWrapText(false);
        label.setFont(Font.font("Segoe UI", 14));
        label.setTextFill(Color.DARKSLATEBLUE);
        label.setAlignment(Pos.CENTER);
        label.setMaxWidth(300);

        VBox item = new VBox(10, imageView, label);
        item.setAlignment(Pos.CENTER);
        item.setPadding(new Insets(10));
        item.setStyle("-fx-background-color: rgba(60, 58, 58, 0.7); -fx-background-radius: 10;");

        serviceBox.getChildren().add(item);
    }


    VBox section = new VBox(20, title,serviceBox);
    section.setPadding(new Insets(20));
    section.setAlignment(Pos.CENTER);
    return section;
}


    private void applyHoverEffect(Node node) {
        DropShadow shadow = new DropShadow(20, Color.DARKGRAY);
        node.setOnMouseEntered((MouseEvent e) -> {
            node.setScaleX(1.05);
            node.setScaleY(1.05);
            node.setEffect(shadow);
            node.setStyle("-fx-cursor: hand;");
        });
        node.setOnMouseExited((MouseEvent e) -> {
            node.setScaleX(1.0);
            node.setScaleY(1.0);
            node.setEffect(null);
        });
    }
}
