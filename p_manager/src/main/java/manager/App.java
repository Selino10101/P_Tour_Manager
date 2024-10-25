package manager;

import java.lang.Math;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.shape.Rectangle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Timer;
import java.time.Duration;
import java.time.LocalTime;
import java.util.TimerTask;
import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.animation.KeyFrame;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.media.Media;
import javafx.scene.media.MediaMarkerEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;



/**
 * JavaFX App
 */
public class App extends Application {
    /* ------------------------------------------------------------------------------------------------------------------------------------- */
    // Primitives
    /* ------------------------------------------------------------------------------------------------------------------------------------- */
    private static Scene scene;
    Timeline timeline;
    private int minutes;
    private int seconds;
    private int hours;
    private Label timerLabel = new Label();
    Button timerStart = new Button("Start");
    Button timerStop = new Button("Stop");
    Button timerChange = new Button("Change");
    //Button timerReset = new Button("Reset");
    Button timerResume = new Button("Resume");
    timer timer = new timer();
    String song = "file:///C:/Users/selin/Documents/P_Tour_Manager/P_Tour_Manager/p_manager/alarm.WAV";
    Media media;
    MediaPlayer player;
    MediaView mediaView;
    Image pokerChip;
    Image pokerChip2;
    ImageView view;
    ImageView view2;
    Image table1;
    Image table2;
    ImageView tableOneview;
    ImageView tableTwoView;
    Image test;
    ImageView testView;
    int finalKonstant;
    Image dealerButton;
    ImageView dealerButtonView;
    // public enum tableState {
    //     OneTable {
    //         @Override
    //         public tableState nextState() {
    //             return TwoTable;
    //         }
    //     },
    //     TwoTable {
    //         @Override
    //         public tableState nextState() {
    //             return OneTable;
    //         }
    //     };

    //     public abstract tableState nextState();
    // }
    boolean twoTables;
    boolean timeChanged;
    
    /* ------------------------------------------------------------------------------------------------------------------------------------- */
    @Override
    public void start(Stage stage) throws IOException {
        //tableState state = tableState.OneTable;
        twoTables = false;
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        double width = bounds.getMaxX();
        double height = bounds.getMaxY();
        media = new Media(song);
        player = new MediaPlayer(media);
        mediaView = new MediaView(player);
        pokerChip  = new Image("file:///C:/Users/selin/Documents/P_Tour_Manager/P_Tour_Manager/p_manager/poker-chip.png");
        pokerChip2 = new Image("file:///C:/Users/selin/Documents/P_Tour_Manager/P_Tour_Manager/p_manager/casino-chip.png");
        table1 = new Image("file:///C:/Users/selin/Documents/P_Tour_Manager/P_Tour_Manager/p_manager/pokerTable2.png");
        table2 = new Image("file:///C:/Users/selin/Documents/P_Tour_Manager/P_Tour_Manager/p_manager/pokerTable2.png");
        view = new ImageView(pokerChip);
        view2 = new ImageView(pokerChip2);
        tableOneview = new ImageView(table1);
        tableTwoView = new ImageView(table2);
        view.setFitHeight(100);
        view.setPreserveRatio(true);
        view2.setFitHeight(75);
        view2.setPreserveRatio(true);
        test = new Image("file:///C:/Users/selin/Documents/P_Tour_Manager/P_Tour_Manager/p_manager/pokerTable2.png");
        testView = new ImageView(test);
        dealerButton = new Image("file:///C:/Users/selin/Documents/P_Tour_Manager/P_Tour_Manager/p_manager/dealer_button.png");
        dealerButtonView = new ImageView(dealerButton);
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        // Title Label
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        Label titleLabel = new Label("M.Selin Tournament Manager. v1.0");
        titleLabel.setTextFill(Color.BLACK);
        titleLabel.setFont(new Font("Arial", 40));
        titleLabel.setTranslateX(width / 5);
        titleLabel.setTranslateY((height / 4 )* 3);
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        // Blinds input
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        Label smallBlindLabel = new Label("Smallblind: ");
        smallBlindLabel.setTextFill(Color.BLACK);
        smallBlindLabel.setFont(new Font("Arial",24));
        TextField smallBlindTextField = new TextField();
        smallBlindTextField.setPromptText("Enter preffered smallblind");
        smallBlindTextField.setMinSize(width/8, smallBlindLabel.getHeight());
        HBox smallBlindBox = new HBox(smallBlindLabel, smallBlindTextField);
        smallBlindBox.setLayoutY(height/10);
        smallBlindBox.setLayoutX(width/10);
        smallBlindBox.setAlignment(Pos.BASELINE_RIGHT);
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        // Blinds Labels & Box
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        Label smallBlind = new Label();
        smallBlind.setTextFill(Color.BLACK);
        smallBlind.setFont(new Font("Arial", 80));
        Label slash = new Label("/");
        slash.setTextFill(Color.BLACK);
        slash.setFont(new Font("Arial", 80));
        Label bigBlind = new Label();
        bigBlind.setTextFill(Color.BLACK);
        bigBlind.setFont(new Font("Arial", 80));
        HBox blindsBox = new HBox(smallBlind, slash, bigBlind);
        blindsBox.setLayoutX(width / 7);
        blindsBox.setLayoutY(height / 9);
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        // Personer som ska spela
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        Label personsLabel = new Label("Players: ");
        personsLabel.setTextFill(Color.BLACK);
        personsLabel.setFont(new Font("Arial",24));
        TextField personsTextField = new TextField();
        personsTextField.setPromptText("Number of players");
        personsTextField.setMinSize(width / 8, personsLabel.getHeight());
        HBox personsBox = new HBox(personsLabel, personsTextField);
        personsBox.setLayoutY(height/10);
        personsBox.setLayoutX(width/10);
        personsBox.setAlignment(Pos.BASELINE_RIGHT);
        Label finalLabel = new Label("Final Bord: ");
        finalLabel.setTextFill(Color.BLACK);
        finalLabel.setFont(new Font("'Arial'", 24));
        TextField numberOnFinal = new TextField();
        numberOnFinal.setPromptText("Antal på Final bordet");
        numberOnFinal.setMinSize(width / 8, personsLabel.getHeight());
        HBox finalTBox = new HBox(finalLabel, numberOnFinal);
        finalTBox.setLayoutY(height/10);
        finalTBox.setLayoutX(width/10);
        finalTBox.setAlignment(Pos.BASELINE_RIGHT);
        
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        // Tables in scene 1
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        Label tablesLabel = new Label("Tables: ");
        tablesLabel.setTextFill(Color.BLACK);
        tablesLabel.setFont(new Font("Arial",24));
        TextField tablesTextField = new TextField();
        tablesTextField.setAlignment(tablesLabel.getAlignment());
        tablesTextField.setPromptText("Number of tables");
        tablesTextField.setMinSize(width / 8, tablesLabel.getHeight());
        HBox tablesBox = new HBox(tablesLabel, tablesTextField);
        tablesBox.setAlignment(Pos.BASELINE_RIGHT);
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        // vBox for inputs
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        VBox inputs = new VBox(15, personsBox, smallBlindBox, tablesBox, finalTBox);
        inputs.setLayoutY(height / 7);
        inputs.setLayoutX(width / 10);
        inputs.setMinSize( width / 4, height);
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        // Players
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        Label playersLabel = new Label("Players");
        playersLabel.setTextFill(Color.BLACK);
        playersLabel.setFont(new Font("Arial", 30));
        playersLabel.setLayoutX(width - (width / 2));
        playersLabel.setLayoutY(height / 7);
        TextField playerOne = new TextField();
        TextField playerTwo = new TextField();
        TextField playerThree = new TextField();
        TextField playerFour = new TextField();
        TextField playerFive = new TextField();
        TextField playerSix = new TextField();
        TextField playerSeven = new TextField();
        TextField playerEight = new TextField();  
        TextField playerNine = new TextField();
        TextField playerTen = new TextField();
        TextField playerEleven = new TextField();
        TextField playerTwelve = new TextField();
        TextField playerThirteen = new TextField();
        TextField playerFourteen = new TextField();
        TextField playerFifteen = new TextField();
        TextField playerSixteen = new TextField();
        TextField playerSeventeen = new TextField();
        TextField playerEighteen = new TextField();
        TextField playerNineteen = new TextField();
        TextField playerTwenty = new TextField();
        TextField playerTwentyOne = new TextField();
        TextField playerTwentyTwo = new TextField();
        TextField playerTwentyThree = new TextField();
        TextField playerTwentyFour = new TextField();
        HBox pl_box1 = new HBox(playerOne, playerTwo, playerThree, playerFour);
        HBox pl_box2 = new HBox(playerFive, playerSix, playerSeven, playerEight);
        HBox pl_box3 = new HBox(playerNine, playerTen, playerEleven, playerTwelve);
        HBox pl_box4 = new HBox(playerThirteen, playerFourteen, playerFifteen, playerSixteen);
        HBox pl_box5 = new HBox(playerSeventeen, playerEighteen, playerNineteen, playerTwenty);
        HBox pl_box6 = new HBox(playerTwentyOne, playerTwentyTwo, playerTwentyThree, playerTwentyFour);
        pl_box1.setSpacing(10);
        pl_box2.setSpacing(10);
        pl_box3.setSpacing(10);
        pl_box4.setSpacing(10);
        pl_box5.setSpacing(10);
        pl_box6.setSpacing(10);
        VBox playerBox = new VBox(15,pl_box1,pl_box2,pl_box3,pl_box4,pl_box5, pl_box6);
        playerBox.setLayoutY(playersLabel.getLayoutY() + (playersLabel.getLayoutY()) / 2);
        playerBox.setLayoutX(playersLabel.getLayoutX() - (playersLabel.getLayoutX() / 5));
        playerBox.setPadding(new Insets(10));
        playerBox.setSpacing(20);
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        // Background for the application.
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        BackgroundFill backgroundFill1 =
        new BackgroundFill(
             Color.valueOf("#ff0000"),
             new CornerRadii(100),
             new Insets(0)
             );

        BackgroundFill backgroundFill2 =
         new BackgroundFill(
             Color.valueOf("#000000"),
             new CornerRadii(100),
             new Insets(25)
             );
    
        BackgroundFill backgroundFill3 =
            new BackgroundFill(
                Color.valueOf("#3c573d"),
                new CornerRadii(100),
                new Insets(50)
                );

        Background background =
        new Background(backgroundFill1, backgroundFill2, backgroundFill3);
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        // Table1 scene 2
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        Rectangle tableOne = new Rectangle((double)(width / 4), (double)((height / 2) -(height / 7)), (double)(width / 10), (double)(height / 3));
        double t1Height = tableOne.getHeight() / 4;
        // Rectangle tableTwo = new Rectangle((double)width - (double)(width / 4) - (double)(width / 10), (double)((height / 2) -(height / 7)), (double)(width / 10), (double)(height / 3));
        tableOne.setFill(Color.valueOf("#3c573d"));
        
        tableOneview.setTranslateX((double)(width / 4) - 15);
        tableOneview.setTranslateY((double)((height / 2) -(height / 7)) - 15);
        // tableOneview.setFitHeight((double)(height / 3) + (double)(height / 35) );
        // tableOneview.setFitWidth((double)(width / 10) + (double)(width / 60));
        tableOneview.setPreserveRatio(false);
        tableOneview.setFitHeight(tableOne.getHeight() + 30);
        tableOneview.setFitWidth(tableOne.getWidth() + 30);

        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        // TESTKNAPPAR FÖR PLAYERS
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        Font font = Font.font("Arial", FontWeight.BOLD, 16);
        // BTN1
        Button player1Btn = new Button();
        player1Btn.setStyle("-fx-radius: 5em;" + "-fx-min-width: 100px;" + "-fx-min-height: 35px;" + "-fx-max-width: 100px;" + "-fx-max-height: 35px;");
        player1Btn.setFont(font);
        // BTN2
        Button player2Btn = new Button();
        player2Btn.setStyle("-fx-radius: 5em;" + "-fx-min-width: 100px;" + "-fx-min-height: 35px;" + "-fx-max-width: 100px;" + "-fx-max-height: 35px;");
        player2Btn.setFont(font);
        // BTN3
        Button player3Btn = new Button();
        player3Btn.setStyle("-fx-radius: 5em;" + "-fx-min-width: 100px;" + "-fx-min-height: 35px;" + "-fx-max-width: 100px;" + "-fx-max-height: 35px;");
        player3Btn.setFont(font);
        // BTN4
        Button player4Btn = new Button();
        player4Btn.setStyle("-fx-radius: 5em;" + "-fx-min-width: 100px;" + "-fx-min-height: 35px;" + "-fx-max-width: 100px;" + "-fx-max-height: 35px;");
        player4Btn.setFont(font);
        // BTN5
        Button player5Btn = new Button();
        player5Btn.setStyle("-fx-radius: 5em;" + "-fx-min-width: 100px;" + "-fx-min-height: 35px;" + "-fx-max-width: 100px;" + "-fx-max-height: 35px;");
        player5Btn.setFont(font);
        // BTN6
        Button player6Btn = new Button();
        player6Btn.setStyle("-fx-radius: 5em;" + "-fx-min-width: 100px;" + "-fx-min-height: 35px;" + "-fx-max-width: 100px;" + "-fx-max-height: 35px;");
        player6Btn.setFont(font);
        // BTN7
        Button player7Btn = new Button();
        player7Btn.setStyle("-fx-radius: 5em;" + "-fx-min-width: 100px;" + "-fx-min-height: 35px;" + "-fx-max-width: 100px;" + "-fx-max-height: 35px;");
        player7Btn.setFont(font);
        // BTN8
        Button player8Btn = new Button();
        player8Btn.setStyle("-fx-radius: 5em;" + "-fx-min-width: 100px;" + "-fx-min-height: 35px;" + "-fx-max-width: 100px;" + "-fx-max-height: 35px;");
        player8Btn.setFont(font);
        // BTN9
        Button player9Btn = new Button();
        player9Btn.setStyle("-fx-radius: 5em;" + "-fx-min-width: 100px;" + "-fx-min-height: 35px;" + "-fx-max-width: 100px;" + "-fx-max-height: 35px;");
        player9Btn.setFont(font);
        // BTN10
        Button player10Btn = new Button();
        player10Btn.setStyle("-fx-radius: 5em;" + "-fx-min-width: 100px;" + "-fx-min-height: 35px;" + "-fx-max-width: 100px;" + "-fx-max-height: 35px;");
        player10Btn.setFont(font);
        // BTN11
        Button player11Btn = new Button();
        player11Btn.setStyle("-fx-radius: 5em;" + "-fx-min-width: 100px;" + "-fx-min-height: 35px;" + "-fx-max-width: 100px;" + "-fx-max-height: 35px;");
        player11Btn.setFont(font);
        // BTN12
        Button player12Btn = new Button();
        player12Btn.setStyle("-fx-radius: 5em;" + "-fx-min-width: 100px;" + "-fx-min-height: 35px;" + "-fx-max-width: 100px;" + "-fx-max-height: 35px;");
        player12Btn.setFont(font);
        ArrayList<Button> table1Buttons = new ArrayList<Button>();
        ArrayList<String> table1Players = new ArrayList<String>();
        table1Buttons.add(player1Btn);
        table1Buttons.add(player2Btn);
        table1Buttons.add(player3Btn);
        table1Buttons.add(player4Btn);
        table1Buttons.add(player5Btn);
        table1Buttons.add(player6Btn);
        table1Buttons.add(player7Btn);
        table1Buttons.add(player8Btn);
        table1Buttons.add(player9Btn);
        table1Buttons.add(player10Btn);
        table1Buttons.add(player11Btn);
        table1Buttons.add(player12Btn);
        ArrayList<Button> table2Buttons = new ArrayList<Button>();
        ArrayList<String> table2Players = new ArrayList<String>();
        // BTN13
        Button player13Btn = new Button();
        player13Btn.setStyle("-fx-radius: 5em;" + "-fx-min-width: 100px;" + "-fx-min-height: 35px;" + "-fx-max-width: 100px;" + "-fx-max-height: 35px;");
        player13Btn.setFont(font);
        // BTN14
        Button player14Btn = new Button();
        player14Btn.setStyle("-fx-radius: 5em;" + "-fx-min-width: 100px;" + "-fx-min-height: 35px;" + "-fx-max-width: 100px;" + "-fx-max-height: 35px;");
        player14Btn.setFont(font);
        // BTN15
        Button player15Btn = new Button();
        player15Btn.setStyle("-fx-radius: 5em;" + "-fx-min-width: 100px;" + "-fx-min-height: 35px;" + "-fx-max-width: 100px;" + "-fx-max-height: 35px;");
        player15Btn.setFont(font);
        // BTN16
        Button player16Btn = new Button();
        player16Btn.setStyle("-fx-radius: 5em;" + "-fx-min-width: 100px;" + "-fx-min-height: 35px;" + "-fx-max-width: 100px;" + "-fx-max-height: 35px;");
        player16Btn.setFont(font);
        // BTN17
        Button player17Btn = new Button();
        player17Btn.setStyle("-fx-radius: 5em;" + "-fx-min-width: 100px;" + "-fx-min-height: 35px;" + "-fx-max-width: 100px;" + "-fx-max-height: 35px;");
        player17Btn.setFont(font);
        // BTN18
        Button player18Btn = new Button();
        player18Btn.setStyle("-fx-radius: 5em;" + "-fx-min-width: 100px;" + "-fx-min-height: 35px;" + "-fx-max-width: 100px;" + "-fx-max-height: 35px;");
        player18Btn.setFont(font);
        // BTN19
        Button player19Btn = new Button();
        player19Btn.setStyle("-fx-radius: 5em;" + "-fx-min-width: 100px;" + "-fx-min-height: 35px;" + "-fx-max-width: 100px;" + "-fx-max-height: 35px;");
        player19Btn.setFont(font);
        // BTN20
        Button player20Btn = new Button();
        player20Btn.setStyle("-fx-radius: 5em;" + "-fx-min-width: 100px;" + "-fx-min-height: 35px;" + "-fx-max-width: 100px;" + "-fx-max-height: 35px;");
        player20Btn.setFont(font);
        // BTN21
        Button player21Btn = new Button();
        player21Btn.setStyle("-fx-radius: 5em;" + "-fx-min-width: 100px;" + "-fx-min-height: 35px;" + "-fx-max-width: 100px;" + "-fx-max-height: 35px;");
        player21Btn.setFont(font);
        // BTN22
        Button player22Btn = new Button();
        player22Btn.setStyle("-fx-radius: 5em;" + "-fx-min-width: 100px;" + "-fx-min-height: 35px;" + "-fx-max-width: 100px;" + "-fx-max-height: 35px;");
        player22Btn.setFont(font);
        // BTN23
        Button player23Btn = new Button();
        player23Btn.setStyle("-fx-radius: 5em;" + "-fx-min-width: 100px;" + "-fx-min-height: 35px;" + "-fx-max-width: 100px;" + "-fx-max-height: 35px;");
        player23Btn.setFont(font);
        // BTN24
        Button player24Btn = new Button();
        player24Btn.setStyle("-fx-radius: 5em;" + "-fx-min-width: 100px;" + "-fx-min-height: 35px;" + "-fx-max-width: 100px;" + "-fx-max-height: 35px;");
        player24Btn.setFont(font);
        ArrayList<Button> tablePlayersList = new ArrayList<Button>();
        table2Buttons.add(player13Btn);
        table2Buttons.add(player14Btn);
        table2Buttons.add(player15Btn);
        table2Buttons.add(player16Btn);
        table2Buttons.add(player17Btn);
        table2Buttons.add(player18Btn);
        table2Buttons.add(player19Btn);
        table2Buttons.add(player20Btn);
        table2Buttons.add(player21Btn);
        table2Buttons.add(player22Btn);
        table2Buttons.add(player23Btn);
        table2Buttons.add(player24Btn);
        tablePlayersList.add(player1Btn);
        tablePlayersList.add(player2Btn);
        tablePlayersList.add(player3Btn);
        tablePlayersList.add(player4Btn);
        tablePlayersList.add(player5Btn);
        tablePlayersList.add(player6Btn);
        tablePlayersList.add(player7Btn);
        tablePlayersList.add(player8Btn);
        tablePlayersList.add(player9Btn);
        tablePlayersList.add(player10Btn);
        tablePlayersList.add(player11Btn);
        tablePlayersList.add(player12Btn);
        tablePlayersList.add(player13Btn);
        tablePlayersList.add(player14Btn);
        tablePlayersList.add(player15Btn);
        tablePlayersList.add(player16Btn);
        tablePlayersList.add(player17Btn);
        tablePlayersList.add(player18Btn);
        tablePlayersList.add(player19Btn);
        tablePlayersList.add(player20Btn);
        tablePlayersList.add(player21Btn);
        tablePlayersList.add(player22Btn);
        tablePlayersList.add(player23Btn);
        tablePlayersList.add(player24Btn);


        VBox btnBox1 = new VBox(player1Btn, player2Btn, player3Btn, player4Btn);
        btnBox1.setSpacing(height / 35);
        btnBox1.setAlignment(Pos.CENTER_LEFT);
        HBox btnBox2 = new HBox(player5Btn, player6Btn);
        btnBox2.setSpacing(width / 70);
        VBox btnBox3 = new VBox(player10Btn, player9Btn, player8Btn, player7Btn);
        btnBox3.setSpacing(height / 35);
        btnBox3.setAlignment(Pos.CENTER_LEFT);
        HBox btnBox4 = new HBox(player12Btn, player11Btn);
        btnBox4.setSpacing(width / 70);
        VBox upAndDown = new VBox(btnBox4, btnBox2);
        upAndDown.setSpacing(height / 3);
        HBox coll = new HBox(btnBox1, upAndDown, btnBox3);
        coll.setSpacing(width / 60);
        //coll.setTranslateX(width / 2);
        //coll.setTranslateY(height / 4);
        coll.setTranslateX((double)(width / 7));
        coll.setTranslateY((double)((height / 2) -(height / 10)));
        testView.setTranslateX(coll.getTranslateX() + (tableOne.getWidth() * 1.27));
        testView.setTranslateY(coll.getTranslateY() + (tableOne.getHeight() / 5) + (tableOne.getHeight() / 35));
        testView.setFitHeight(tableOne.getHeight() - 30);
        testView.setFitWidth(tableOne.getWidth());
  
        VBox btnBox11 = new VBox(player13Btn, player14Btn, player15Btn, player16Btn);
        btnBox11.setSpacing(height / 35);
        btnBox11.setAlignment(Pos.CENTER_LEFT);
        HBox btnBox22 = new HBox(player17Btn, player18Btn);
        btnBox22.setSpacing(width / 70);
        VBox btnBox33 = new VBox(player22Btn, player21Btn, player20Btn, player19Btn);
        btnBox33.setSpacing(height / 35);
        btnBox33.setAlignment(Pos.CENTER_LEFT);
        HBox btnBox44 = new HBox(player24Btn, player23Btn);
        btnBox44.setSpacing(width / 70);
        VBox upAndDown2 = new VBox(btnBox44, btnBox22);
        upAndDown2.setSpacing(height / 3);
        HBox coll2 = new HBox(btnBox11, upAndDown2, btnBox33);
        coll2.setSpacing(width / 60);
        coll2.setTranslateX((double)width - (double)(width / 4) - (double)(width / 5));
        coll2.setTranslateY((double)((height / 2) -(height / 10)));
        tableTwoView.setTranslateX(coll2.getTranslateX() + (tableOne.getWidth() * 1.27));
        tableTwoView.setTranslateY(coll2.getTranslateY() + (tableOne.getHeight() / 5) + (tableOne.getHeight() / 35));
        tableTwoView.setFitHeight(tableOne.getHeight() - 30);
        tableTwoView.setFitWidth(tableOne.getWidth());
        
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        Text player1Text = new Text(0, 20, "");
        player1Text.setLayoutX((width / 4) - (tableOne.getLayoutBounds().getWidth() / 2));
        player1Text.setLayoutY(tableOne.getLayoutBounds().getMinY() +(t1Height/2));
        player1Text.setText("Player1");
        player1Text.setFill(Color.RED);
        player1Text.setFont(new Font("Arial", 20));

        Text player2Text = new Text(0, 20, "");
        player2Text.setLayoutX((width / 4) - (tableOne.getLayoutBounds().getWidth() / 2));
        player2Text.setLayoutY(tableOne.getLayoutBounds().getMinY() + t1Height +(t1Height/2));
        player2Text.setText("Player2");
        player2Text.setFill(Color.RED);
        player2Text.setFont(new Font("Arial", 20));

        Text player3Text = new Text(0, 20, "");
        player3Text.setLayoutX((width / 4) - (tableOne.getLayoutBounds().getWidth() / 2));
        player3Text.setLayoutY(tableOne.getLayoutBounds().getMinY() + t1Height + t1Height + (t1Height/2));
        player3Text.setText("Player3");
        player3Text.setFill(Color.RED);
        player3Text.setFont(new Font("Arial", 20));

        Text player4Text = new Text(0, 20, "");
        player4Text.setLayoutX((width / 4) - (tableOne.getLayoutBounds().getWidth() / 2));
        player4Text.setLayoutY(tableOne.getLayoutBounds().getMinY() + t1Height + t1Height + t1Height + (t1Height/2));
        player4Text.setText("Player4");
        player4Text.setFill(Color.RED);
        player4Text.setFont(new Font("Arial", 20));

        Text player5Text = new Text(0, 20, "");
        player5Text.setLayoutX((tableOne.getLayoutBounds().getCenterX()) - (tableOne.getLayoutBounds().getWidth() / 2));
        player5Text.setLayoutY(tableOne.getLayoutBounds().getMaxY() + (t1Height / 4));
        player5Text.setText("Player5");
        player5Text.setFill(Color.RED);
        player5Text.setFont(new Font("Arial", 20));

        Text player6Text = new Text(0, 20, "");
        player6Text.setLayoutX((tableOne.getLayoutBounds().getCenterX()));
        player6Text.setLayoutY(tableOne.getLayoutBounds().getMaxY() + (t1Height / 4));
        player6Text.setText("Player6");
        player6Text.setFill(Color.RED);
        player6Text.setFont(new Font("Arial", 20));

        Text player7Text = new Text(0, 20, "");
        player7Text.setLayoutX((width / 4) + (tableOne.getLayoutBounds().getWidth()) + (t1Height / 4));
        player7Text.setLayoutY(tableOne.getLayoutBounds().getMinY() + t1Height + t1Height + t1Height + (t1Height/2));
        player7Text.setText("Player7");
        player7Text.setFill(Color.RED);
        player7Text.setFont(new Font("Arial", 20));

        Text player8Text = new Text(0, 20, "");
        player8Text.setLayoutX((width / 4) + (tableOne.getLayoutBounds().getWidth()) + (t1Height / 4));
        player8Text.setLayoutY(tableOne.getLayoutBounds().getMinY() + t1Height + t1Height + (t1Height/2));
        player8Text.setText("Player2");
        player8Text.setFill(Color.RED);
        player8Text.setFont(new Font("Arial", 20));

        Text player9Text = new Text(0, 20, "");
        player9Text.setLayoutX((width / 4) + (tableOne.getLayoutBounds().getWidth()) + (t1Height / 4));
        player9Text.setLayoutY(tableOne.getLayoutBounds().getMinY() + t1Height +(t1Height/2));
        player9Text.setText("Player3");
        player9Text.setFill(Color.RED);
        player9Text.setFont(new Font("Arial", 20));

        Text player10Text = new Text(0, 20, "");
        player10Text.setLayoutX((width / 4) + (tableOne.getLayoutBounds().getWidth()) + (t1Height / 4));
        player10Text.setLayoutY(tableOne.getLayoutBounds().getMinY() +(t1Height/2));
        player10Text.setText("Player4");
        player10Text.setFill(Color.RED);
        player10Text.setFont(new Font("Arial", 20));

        Text player11Text = new Text(0, 20, "");
        player11Text.setLayoutX((tableOne.getLayoutBounds().getCenterX()));
        player11Text.setLayoutY(tableOne.getLayoutBounds().getMinY() - (t1Height /2));
        player11Text.setText("Player11");
        player11Text.setFill(Color.RED);
        player11Text.setFont(new Font("Arial", 20));

        Text player12Text = new Text(0, 20, "");
        player12Text.setLayoutX((tableOne.getLayoutBounds().getCenterX()) - (tableOne.getLayoutBounds().getWidth() / 2));
        player12Text.setLayoutY(tableOne.getLayoutBounds().getMinY() - (t1Height /2));
        player12Text.setText("Player12");
        player12Text.setFill(Color.RED);
        player12Text.setFont(new Font("Arial", 20));

        ArrayList<Text> tableOneTexts = new ArrayList<Text>();
        tableOneTexts.add(player1Text);
        tableOneTexts.add(player2Text);
        tableOneTexts.add(player3Text);
        tableOneTexts.add(player4Text);
        tableOneTexts.add(player5Text);
        tableOneTexts.add(player6Text);
        tableOneTexts.add(player7Text);
        tableOneTexts.add(player8Text);
        tableOneTexts.add(player9Text);
        tableOneTexts.add(player10Text);
        tableOneTexts.add(player11Text);
        tableOneTexts.add(player12Text);
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        // Table2 scene 2
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        Rectangle tableTwo = new Rectangle((double)width - (double)(width / 4) - (double)(width / 10), (double)((height / 2) -(height / 7)), (double)(width / 10), (double)(height / 3));
        tableTwo.setFill(Color.valueOf("#3c573d"));

        // tableTwoView.setTranslateX((double)width - (double)(width / 4) - (double)(width / 10) - 15);
        // tableTwoView.setTranslateY((double)((height / 2) -(height / 7)) - 15);
        // tableTwoView.setFitHeight(tableTwo.getHeight() + 30);
        // tableTwoView.setFitWidth(tableTwo.getWidth() + 30);
        // tableTwoView.setPreserveRatio(false);

        Text player13Text = new Text(0, 20, "");
        player13Text.setLayoutX((double)width - (double)(width / 4) - (double)(width / 10) - (tableTwo.getLayoutBounds().getWidth() / 2));
        player13Text.setLayoutY(tableTwo.getLayoutBounds().getMinY() +(t1Height/2));
        player13Text.setText("Player13");
        player13Text.setFill(Color.RED);
        player13Text.setFont(new Font("Arial", 20));

        Text player14Text = new Text(0, 20, "");
        player14Text.setLayoutX((double)width - (double)(width / 4) - (double)(width / 10) - (tableTwo.getLayoutBounds().getWidth() / 2));
        player14Text.setLayoutY(tableTwo.getLayoutBounds().getMinY() + t1Height +(t1Height/2));
        player14Text.setText("Player14");
        player14Text.setFill(Color.RED);
        player14Text.setFont(new Font("Arial", 20));

        Text player15Text = new Text(0, 20, "");
        player15Text.setLayoutX((double)width - (double)(width / 4) - (double)(width / 10) - (tableTwo.getLayoutBounds().getWidth() / 2));
        player15Text.setLayoutY(tableTwo.getLayoutBounds().getMinY() + t1Height + t1Height + (t1Height/2));
        player15Text.setText("Player15");
        player15Text.setFill(Color.RED);
        player15Text.setFont(new Font("Arial", 20));

        Text player16Text = new Text(0, 20, "");
        player16Text.setLayoutX((double)width - (double)(width / 4) - (double)(width / 10) - (tableTwo.getLayoutBounds().getWidth() / 2));
        player16Text.setLayoutY(tableTwo.getLayoutBounds().getMinY() + t1Height + t1Height + t1Height + (t1Height/2));
        player16Text.setText("Player16");
        player16Text.setFill(Color.RED);
        player16Text.setFont(new Font("Arial", 20));

        Text player17Text = new Text(0, 20, "");
        player17Text.setLayoutX((tableTwo.getLayoutBounds().getCenterX()) - (tableTwo.getLayoutBounds().getWidth() / 2));
        player17Text.setLayoutY(tableTwo.getLayoutBounds().getMaxY() + (t1Height / 4));
        player17Text.setText("Player17");
        player17Text.setFill(Color.RED);
        player17Text.setFont(new Font("Arial", 20));

        Text player18Text = new Text(0, 20, "");
        player18Text.setLayoutX((tableTwo.getLayoutBounds().getCenterX()));
        player18Text.setLayoutY(tableTwo.getLayoutBounds().getMaxY() + (t1Height / 4));
        player18Text.setText("Player18");
        player18Text.setFill(Color.RED);
        player18Text.setFont(new Font("Arial", 20));

        Text player19Text = new Text(0, 20, "");
        player19Text.setLayoutX((double)width - (double)(width / 4) - (double)(width / 10) + (tableTwo.getLayoutBounds().getWidth()) + (t1Height / 4));
        player19Text.setLayoutY(tableTwo.getLayoutBounds().getMinY() + t1Height + t1Height + t1Height + (t1Height/2));
        player19Text.setText("Player19");
        player19Text.setFill(Color.RED);
        player19Text.setFont(new Font("Arial", 20));

        Text player20Text = new Text(0, 20, "");
        player20Text.setLayoutX((double)width - (double)(width / 4) - (double)(width / 10) + (tableTwo.getLayoutBounds().getWidth()) + (t1Height / 4));
        player20Text.setLayoutY(tableTwo.getLayoutBounds().getMinY() + t1Height + t1Height + (t1Height/2));
        player20Text.setText("Player20");
        player20Text.setFill(Color.RED);
        player20Text.setFont(new Font("Arial", 20));

        Text player21Text = new Text(0, 20, "");
        player21Text.setLayoutX((double)width - (double)(width / 4) - (double)(width / 10) + (tableTwo.getLayoutBounds().getWidth()) + (t1Height / 4));
        player21Text.setLayoutY(tableTwo.getLayoutBounds().getMinY() + t1Height +(t1Height/2));
        player21Text.setText("Player21");
        player21Text.setFill(Color.RED);
        player21Text.setFont(new Font("Arial", 20));

        Text player22Text = new Text(0, 20, "");
        player22Text.setLayoutX((double)width - (double)(width / 4) - (double)(width / 10) + (tableTwo.getLayoutBounds().getWidth()) + (t1Height / 4));
        player22Text.setLayoutY(tableTwo.getLayoutBounds().getMinY() +(t1Height/2));
        player22Text.setText("Player22");
        player22Text.setFill(Color.RED);
        player22Text.setFont(new Font("Arial", 20));

        Text player23Text = new Text(0, 20, "");
        player23Text.setLayoutX((tableTwo.getLayoutBounds().getCenterX()));
        player23Text.setLayoutY(tableTwo.getLayoutBounds().getMinY() - (t1Height /2));
        player23Text.setText("Player23");
        player23Text.setFill(Color.RED);
        player23Text.setFont(new Font("Arial", 20));

        Text player24Text = new Text(0, 20, "");
        player24Text.setLayoutX((tableTwo.getLayoutBounds().getCenterX()) - (tableTwo.getLayoutBounds().getWidth() / 2));
        player24Text.setLayoutY(tableTwo.getLayoutBounds().getMinY() - (t1Height /2));
        player24Text.setText("Player24");
        player24Text.setFill(Color.RED);
        player24Text.setFont(new Font("Arial", 20));

        ArrayList<Text> tableTwoTexts = new ArrayList<Text>();
        tableTwoTexts.add(player13Text);
        tableTwoTexts.add(player14Text);
        tableTwoTexts.add(player15Text);
        tableTwoTexts.add(player16Text);
        tableTwoTexts.add(player17Text);
        tableTwoTexts.add(player18Text);
        tableTwoTexts.add(player19Text);
        tableTwoTexts.add(player20Text);
        tableTwoTexts.add(player21Text);
        tableTwoTexts.add(player22Text);
        tableTwoTexts.add(player23Text);
        tableTwoTexts.add(player24Text);
        ArrayList<Text> tableTexts = new ArrayList<Text>();
        tableTexts.add(player1Text);
        tableTexts.add(player2Text);
        tableTexts.add(player3Text);
        tableTexts.add(player4Text);
        tableTexts.add(player5Text);
        tableTexts.add(player6Text);
        tableTexts.add(player7Text);
        tableTexts.add(player8Text);
        tableTexts.add(player9Text);
        tableTexts.add(player10Text);
        tableTexts.add(player11Text);
        tableTexts.add(player12Text);
        tableTexts.add(player13Text);
        tableTexts.add(player14Text);
        tableTexts.add(player15Text);
        tableTexts.add(player16Text);
        tableTexts.add(player17Text);
        tableTexts.add(player18Text);
        tableTexts.add(player19Text);
        tableTexts.add(player20Text);
        tableTexts.add(player21Text);
        tableTexts.add(player22Text);
        tableTexts.add(player23Text);
        tableTexts.add(player24Text);

        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        // Timer
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        timerLabel.setTextFill(Color.BLACK);
        timerLabel.setFont(new Font("Arial", 50));

        timerStart.setMaxSize(width, height);
        timerStart.setPrefSize((width / 14), (height / 18));
        timerStart.setStyle("-fx-font: 24 arial; -fx-base: #b6e7c9;");

        timerStop.setMaxSize(width, height);
        timerStop.setPrefSize((width / 14), (height / 18));
        timerStop.setStyle("-fx-font: 24 arial; -fx-base: #b6e7c9;");

        // timerStart gör typ samma sak ändå
        // timerReset.setMaxSize(width, height);
        // timerReset.setPrefSize((width / 16), (height / 20));
        // timerReset.setStyle("-fx-font: 24 arial; -fx-base: #b6e7c9;");

        timerResume.setMaxSize(width, height);
        timerResume.setPrefSize((width / 14), (height / 18));
        timerResume.setStyle("-fx-font: 24 arial; -fx-base: #b6e7c9;");

        timerChange.setMaxSize(width, height);
        timerChange.setPrefSize((width / 14), (height / 18));
        timerChange.setStyle("-fx-font: 24 arial; -fx-base: #b6e7c9;");

        TextField minutesField = new TextField();
        minutesField.setPromptText("Minutes");
        minutesField.setPrefWidth(width / 14);
        TextField secondsField = new TextField();
        secondsField.setPromptText("Seconds");
        secondsField.setPrefWidth(width / 14);
        TextField pauseField = new TextField();
        pauseField.setPromptText("Pause time");
        pauseField.setPrefWidth(width / 14);

        HBox timerButtons = new HBox(timerStart, timerStop, timerResume, timerChange);
        timerButtons.setSpacing(10);
        HBox timerFields = new HBox(15, pauseField, minutesField, secondsField);
        timerFields.setSpacing(10);
        VBox timerBox = new VBox(15, timerLabel, timerButtons, timerFields);
        timerBox.setLayoutX(2*(width / 3) - width / 10);
        timerBox.setLayoutY(height / 8);
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        // Scene one Buttons
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        Button okButton = new Button("Ok");
        
        okButton.setLayoutX((width - (width/4)));
        okButton.setLayoutY(height - (height / 5));
        okButton.setMaxSize(width, height);
        okButton.setPrefSize((width / 8), (height / 10));
        okButton.setStyle("-fx-font: 24 arial; -fx-base: #b6e7c9;");
        okButton.setGraphic(view2);
        
        Button testButton = new Button();
        testButton.setTranslateX(200);
        testButton.setTranslateY(500);
        testButton.setPrefSize(100, 100);
        testButton.setStyle("-fx-background-radius: 5em;" + "-fx-min-width: 100px" + "-fx-min-height: 100px" + "-fx-max-width: 100px" + "-fx-max-height: 100px");
        testButton.setGraphic(view);
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        // Tests
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        Button testButton2 = new Button();
        testButton2.setText("PlayerName");
        testButton2.setPrefSize(80, 30);
        testButton2.setTranslateX(width/2);
        testButton2.setTranslateY(height/2);
        testButton2.setStyle("-fx-background-radius: 10em;");
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        // Tests för dealer button
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        // Button dealerBtn1 = new Button();
        // dealerBtn1.setGraphic(dealerButtonView);
        // dealerBtn1.setPrefSize(30, 30);
        // dealerBtn1.setStyle("-fx-background-radius: 5em;" + "-fx-min-width: 30px" + "-fx-min-height: 30px" + "-fx-max-width: 30px" + "-fx-max-height: 30px");
        // Button dealerBtn2 = new Button();
        // VBox delaerButtonBox = new VBox(dealerBtn1, dealerBtn2);
        // dealerBtn2.setGraphic(dealerButtonView);
        // dealerBtn1.setPrefSize(30, 30);
        // dealerBtn1.setStyle("-fx-background-radius: 5em;" + "-fx-min-width: 30px" + "-fx-min-height: 30px" + "-fx-max-width: 30px" + "-fx-max-height: 30px");
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        // Bygger upp scenen
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        Pane testPane = new Pane();
        Pane root = new Pane();
        Pane userWindow = new Pane();
        testPane.setBackground(background);
        //testPane.getChildren().addAll(coll, testView, coll2, tableTwoView);
        userWindow.setBackground(background);
        root.setBackground(background);
        root.getChildren().add(okButton);
        root.getChildren().add(inputs);
        root.getChildren().add(playersLabel);
        root.getChildren().add(playerBox);
        //root.getChildren().add(testButton);
        root.getChildren().add(titleLabel);
        userWindow.getChildren().add(mediaView);
        userWindow.getChildren().add(tableOne);
        userWindow.getChildren().add(tableTwo);
        userWindow.getChildren().add(timerBox);
        userWindow.getChildren().add(blindsBox);
        userWindow.getChildren().addAll(coll, testView, coll2, tableTwoView);
    
        //userWindow.getChildren().addAll(player13Text, player14Text, player15Text, player16Text, player17Text, player18Text, player19Text, player20Text, player21Text, player22Text, player23Text, player24Text);
        scene = new Scene(root, width, height);
        //scene = new Scene(userWindow, width, height);
        //scene = new Scene(testPane, width, height);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        // Timer buttons
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        timerStart.setOnAction(event -> {
            // hours = Integer.parseInt(pauseField.getText());
            minutes = Integer.parseInt(minutesField.getText());
            seconds = Integer.parseInt(secondsField.getText());
            int time = (minutes * 60) + seconds;
            int smallBlindValue = Integer.parseInt(smallBlindTextField.getText());
            int bigBlindValue = smallBlindValue * 2;
            timer.timer(timerLabel, time, smallBlindValue, bigBlindValue, smallBlind, bigBlind, player, pauseField, timeChanged);
            
        });

        timerStop.setOnAction(event -> {
            timer.pause();
        });

        timerResume.setOnAction(event -> {
            timer.resume();
        });
        timerChange.setOnAction(event -> {
            timeChanged = timer.timerChanger();
        });
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        // Alert debugger
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        Alert a = new Alert(AlertType.INFORMATION);

        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        // Ok button handler som sköter bordsplaceringen
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        okButton.setOnAction(event -> {
            finalKonstant = Integer.parseInt(numberOnFinal.getText());
            tableHandler.seater(personsTextField, tablesTextField, pl_box1, pl_box2, pl_box3, pl_box4, pl_box5, pl_box6, tablePlayersList, twoTables, table1Players, table2Players);
            int tables = Integer.parseInt(tablesTextField.getText());
            if(tables > 1) {
                //state = state.nextState();
                //state = tableState.TwoTable;
                twoTables = true;
            }
            //okButton.getScene().setRoot(userWindow);
            okButton.getScene().setRoot(userWindow);

        });
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        // Test buttons
        /* ------------------------------------------------------------------------------------------------------------------------------------- */
        player1Btn.setOnAction(event -> {
            if(!player1Btn.getText().isEmpty()) {
                String name = player1Btn.getText();
                player1Btn.setText("");
                TableBalance.eliminate(table1Players, table2Players, name, twoTables, tablePlayersList, finalKonstant, timer);

            }
        });
        player2Btn.setOnAction(event -> {
            if(!player2Btn.getText().isEmpty()) {
                String name = player2Btn.getText();
                player2Btn.setText("");
                TableBalance.eliminate(table1Players, table2Players, name, twoTables, tablePlayersList, finalKonstant, timer);

            }
            
        });
        player3Btn.setOnAction(event -> {
            if(!player3Btn.getText().isEmpty()) {
                String name = player3Btn.getText();
                player3Btn.setText("");
                TableBalance.eliminate(table1Players, table2Players, name, twoTables, tablePlayersList, finalKonstant, timer);  
            }
            
        });
        player4Btn.setOnAction(event -> {
            if(!player4Btn.getText().isEmpty()) {
                String name = player4Btn.getText();
                player4Btn.setText("");
                TableBalance.eliminate(table1Players, table2Players, name, twoTables, tablePlayersList, finalKonstant, timer);      
            }
        });
        player5Btn.setOnAction(event -> {
            if(!player5Btn.getText().isEmpty()) {
                String name = player5Btn.getText();
                player5Btn.setText("");
                TableBalance.eliminate(table1Players, table2Players, name, twoTables, tablePlayersList, finalKonstant, timer);  
            }
        });
        player6Btn.setOnAction(event -> {
            if(!player6Btn.getText().isEmpty()) {
                String name = player6Btn.getText();
                player6Btn.setText("");
                TableBalance.eliminate(table1Players, table2Players, name, twoTables, tablePlayersList, finalKonstant, timer);    
            }      
        });
        player7Btn.setOnAction(event -> {
            if(!player7Btn.getText().isEmpty()) {
                String name = player7Btn.getText();
                player7Btn.setText("");
                TableBalance.eliminate(table1Players, table2Players, name, twoTables, tablePlayersList, finalKonstant, timer);      
            }           
        });
        player8Btn.setOnAction(event -> {
            if(!player8Btn.getText().isEmpty()) {
                String name = player8Btn.getText();
                player8Btn.setText("");
                TableBalance.eliminate(table1Players, table2Players, name, twoTables, tablePlayersList, finalKonstant, timer);      
            }        
        });
        player9Btn.setOnAction(event -> {
            if(!player9Btn.getText().isEmpty()) {
                String name = player9Btn.getText();
                player9Btn.setText("");
                TableBalance.eliminate(table1Players, table2Players, name, twoTables, tablePlayersList, finalKonstant, timer);   
            }        
        });
        player10Btn.setOnAction(event -> {
            if(!player10Btn.getText().isEmpty()) {
                String name = player10Btn.getText();
                player10Btn.setText("");
                TableBalance.eliminate(table1Players, table2Players, name, twoTables, tablePlayersList, finalKonstant, timer);   
            }
        });
        player11Btn.setOnAction(event -> {
            if(!player11Btn.getText().isEmpty()) {
                String name = player11Btn.getText();
                player11Btn.setText("");
                TableBalance.eliminate(table1Players, table2Players, name, twoTables, tablePlayersList, finalKonstant, timer);    
            }
        });
        player12Btn.setOnAction(event -> {
            if(!player12Btn.getText().isEmpty()) {
                String name = player12Btn.getText();
                player12Btn.setText("");
                TableBalance.eliminate(table1Players, table2Players, name, twoTables, tablePlayersList, finalKonstant, timer);   
            }
        });
        player13Btn.setOnAction(event -> {
            if(!player13Btn.getText().isEmpty()) {
                String name = player13Btn.getText();
                player13Btn.setText("");
                TableBalance.eliminate(table1Players, table2Players, name, twoTables, tablePlayersList, finalKonstant, timer);  

            }
        });
        player14Btn.setOnAction(event -> {
            if(!player14Btn.getText().isEmpty()) {
                String name = player14Btn.getText();
                player14Btn.setText("");
                TableBalance.eliminate(table1Players, table2Players, name, twoTables, tablePlayersList, finalKonstant, timer);  

            }
        });
        player15Btn.setOnAction(event -> {
            if(!player15Btn.getText().isEmpty()) {
                String name = player15Btn.getText();
                player15Btn.setText("");
                TableBalance.eliminate(table1Players, table2Players, name, twoTables, tablePlayersList, finalKonstant, timer);

            }
        });
        player16Btn.setOnAction(event -> {
            if(!player16Btn.getText().isEmpty()) {
                String name = player16Btn.getText();
                player16Btn.setText("");
                TableBalance.eliminate(table1Players, table2Players, name, twoTables, tablePlayersList, finalKonstant, timer);  
            }
        });
        player17Btn.setOnAction(event -> {
            if(!player17Btn.getText().isEmpty()) {
                String name = player17Btn.getText();
                player17Btn.setText("");
                TableBalance.eliminate(table1Players, table2Players, name, twoTables, tablePlayersList, finalKonstant, timer);  
            }
        });
        player18Btn.setOnAction(event -> {
            if(!player18Btn.getText().isEmpty()) {
                String name = player18Btn.getText();
                player18Btn.setText("");
                TableBalance.eliminate(table1Players, table2Players, name, twoTables, tablePlayersList, finalKonstant, timer);  
            }
        });
        player19Btn.setOnAction(event -> {
            if(!player19Btn.getText().isEmpty()) {
                String name = player19Btn.getText();
                player19Btn.setText("");
                TableBalance.eliminate(table1Players, table2Players, name, twoTables, tablePlayersList, finalKonstant, timer);   
            }
        });
        player20Btn.setOnAction(event -> {
            if(!player20Btn.getText().isEmpty()) {
                String name = player20Btn.getText();
                player20Btn.setText("");
                TableBalance.eliminate(table1Players, table2Players, name, twoTables, tablePlayersList, finalKonstant, timer);   
            }
        });
        player21Btn.setOnAction(event -> {
            if(!player21Btn.getText().isEmpty()) {
                String name = player21Btn.getText();
                player21Btn.setText("");
                TableBalance.eliminate(table1Players, table2Players, name, twoTables, tablePlayersList, finalKonstant, timer);   
            }
        });
        player22Btn.setOnAction(event -> {
            if(!player22Btn.getText().isEmpty()) {
                String name = player22Btn.getText();
                player22Btn.setText("");
                TableBalance.eliminate(table1Players, table2Players, name, twoTables, tablePlayersList, finalKonstant, timer);    
            }
        });
        player23Btn.setOnAction(event -> {
            if(!player23Btn.getText().isEmpty()) {
                String name = player23Btn.getText();
                player23Btn.setText("");
                TableBalance.eliminate(table1Players, table2Players, name, twoTables, tablePlayersList, finalKonstant, timer);   
            }
        });
        player24Btn.setOnAction(event -> {
            if(!player24Btn.getText().isEmpty()) {
                String name = player24Btn.getText();
                player1Btn.setText("");
                TableBalance.eliminate(table1Players, table2Players, name, twoTables, tablePlayersList, finalKonstant, timer);   
            }
        });

    }
    /* ------------------------------------------------------------------------------------------------------------------------------------- */
    // Oanvänd standard funktion
    /* ------------------------------------------------------------------------------------------------------------------------------------- */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    /* ------------------------------------------------------------------------------------------------------------------------------------- */
    // Oanvänd standard funktion
    /* ------------------------------------------------------------------------------------------------------------------------------------- */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    /* ------------------------------------------------------------------------------------------------------------------------------------- */
    // Main
    /* ------------------------------------------------------------------------------------------------------------------------------------- */
    public static void main(String[] args) {
        launch();
    }

}