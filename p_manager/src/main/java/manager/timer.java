package manager;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.event.EventHandler;



public class timer {
    /* ------------------------------------------------------------------------------------------------------------------------------------- */
    // Primitives
    /* ------------------------------------------------------------------------------------------------------------------------------------- */
    private Timeline timeline;
    private int timeRemaining;
    private int small;
    private int big;
    int defaultTime = 30;
    //boolean timeChanged = false;

    /* ------------------------------------------------------------------------------------------------------------------------------------- */
    // Timer funktionen som startar timern med rätt värden
    /* ------------------------------------------------------------------------------------------------------------------------------------- */
    public void timer(Label label, int time, int smallBlind, int bigBlind, Label smallBlindLabel, Label bigBlindLabel, MediaPlayer player, TextField pauseField, boolean timeChanged) {
        if(!timeChanged) {
            small = smallBlind;
            big = bigBlind;
        }
        
        timeRemaining = time;
        //smallBlindLabel.setText(String.format("%02d", smallBlind));
        smallBlindLabel.setText(String.format("%02d", small));
        //bigBlindLabel.setText(String.format("%02d", bigBlind));
        bigBlindLabel.setText(String.format("%02d", big));
        timeline = new Timeline(
            new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    timeRemaining--;
                    label.setText(String.format("%02d:%02d", timeRemaining / 60, timeRemaining % 60));
                    if(timeRemaining <= 0) {
                        if(!pauseField.getText().isEmpty()) {
                            defaultTime = Integer.parseInt(pauseField.getText());
                        }
                        // Stoppa in de nya funktionerna smallBlindCalculator och
                        // bigBlindCalculator.
                        small = blindCalculator(small);
                        big = blindCalculator(big);
                        smallBlindLabel.setText(String.format("%02d", small));
                        bigBlindLabel.setText(String.format("%02d", big));
                        //timeline.stop();
                        timeline.pause();
                        player.play();
                        player.setOnEndOfMedia(player::stop);
                        // Öka sekunderna för att öka pausen.
                        pauseTimer(defaultTime, time, label);
                        // Extra saker här typ blinds och ljud.
                        timeRemaining = time;
                    }
                
                    
                }
            })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }
    /* ------------------------------------------------------------------------------------------------------------------------------------- */
    // Tids ändrare som sparar blindsen.
    /* ------------------------------------------------------------------------------------------------------------------------------------- */
    public boolean timerChanger() {
        pause();
        //timeChanged = true;
        return true;
    }
    /* ------------------------------------------------------------------------------------------------------------------------------------- */
    // Blind kalkylerare som dubblar den inlagda blinden.
    /* ------------------------------------------------------------------------------------------------------------------------------------- */
    public int blindCalculator(int blind) {
        if(blind < 200) {
            return blind + blind;
        }
        else {
            return blind + 100;
        }
        
    }
    public int bigBlindCalculator(int bigBlind, int smallBlind) {
        if(bigBlind < 200) {
            return bigBlind + bigBlind;
        }
        else {
            return smallBlind * 2;
        }
        
    }// 25/50, 50/100, 100/200, 150/300, 200/400
    // Måste nog slänga in cases eller if-satser för att kolla olika värden.
    // Kanske skapa några konstanter som används beroende på hur höga blindsen är.
    public int smallBlindCalculator(int bigBlind, int smallBlind) {
        if(bigBlind < 200) {
            return smallBlind + smallBlind;
        }
        else {
            return smallBlind + (smallBlind/2);
        }
        
    }
    /*
     * Test blindscalculator 
     */
    public static int calculateBlind(int startBB) {
        int 
    }
    /* ------------------------------------------------------------------------------------------------------------------------------------- */
    // Timer paus mellan varje blindhöjning
    /* ------------------------------------------------------------------------------------------------------------------------------------- */
    private void pauseTimer(int seconds, int startTime, Label timerLabel) {
        Timeline pauseTimeline = new Timeline(new KeyFrame(Duration.seconds(seconds)));
        pauseTimeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Resume the timer with the original time
                timeRemaining = startTime;
                timerLabel.setText(String.format("%02d:%02d", startTime / 60, startTime % 60));
                timeline.play();
            }
        });
        pauseTimeline.play();     
    }
    /* ------------------------------------------------------------------------------------------------------------------------------------- */
    // Timer pausare.
    /* ------------------------------------------------------------------------------------------------------------------------------------- */
    public void pause() {
        timeline.pause();
    }
    /* ------------------------------------------------------------------------------------------------------------------------------------- */
    // Timer startare efter paus.
    /* ------------------------------------------------------------------------------------------------------------------------------------- */
    public void resume() {
        timeline.play();
    }
    /* ------------------------------------------------------------------------------------------------------------------------------------- */
    // Timer reseter som tekniskt sätt gör samma som start. Används ej.
    /* ------------------------------------------------------------------------------------------------------------------------------------- */
    public void reset(int time, Label label) {
        timeline.stop();
        timeRemaining = time;
        label.setText(String.format("%02d:%02d", timeRemaining / 60, timeRemaining % 60));
        timeline.play();
    }
}
