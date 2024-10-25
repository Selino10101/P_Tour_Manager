package manager;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import java.security.SecureRandom;

import java.util.ArrayList;

public class tableHandler {
    static SecureRandom rand = new SecureRandom();
    public static void seater(TextField personsTextField, TextField tablesTextField, HBox pl_box1, HBox pl_box2, HBox pl_box3, HBox pl_box4, HBox pl_box5, HBox pl_box6, ArrayList<Button> tablePlayersList, boolean twoTables, ArrayList<String> table1Players, ArrayList<String> table2Players) {
        int players = Integer.parseInt(personsTextField.getText());
        int tables = Integer.parseInt(tablesTextField.getText());
        if(tables > 1) {
            //state = state.nextState();
            //state = tableState.TwoTable;
            twoTables = true;
        }
        double table1 = 0;
        double table2 = 0;
        ArrayList<TextField> playerNames = new ArrayList<TextField>();
        if(players > 20) {
            for (int i = 0; i < pl_box1.getChildren().size(); i++) {
                playerNames.add((TextField)pl_box1.getChildren().get(i));
            }
            for (int i = 0; i < pl_box2.getChildren().size(); i++) {
                playerNames.add((TextField)pl_box2.getChildren().get(i));
            }
            for (int i = 0; i < pl_box3.getChildren().size(); i++) {
                playerNames.add((TextField)pl_box3.getChildren().get(i));
            }
            for (int i = 0; i < pl_box4.getChildren().size(); i++) {
                playerNames.add((TextField)pl_box4.getChildren().get(i));
            }
            for (int i = 0; i < pl_box5.getChildren().size(); i++) {
                playerNames.add((TextField)pl_box5.getChildren().get(i));
            }
            for (int i = 0; i < pl_box6.getChildren().size(); i++) {
                playerNames.add((TextField)pl_box6.getChildren().get(i));
            }
        }
        if(players > 16 && players <= 20) {
            for (int i = 0; i < pl_box1.getChildren().size(); i++) {
                playerNames.add((TextField)pl_box1.getChildren().get(i));
            }
            for (int i = 0; i < pl_box2.getChildren().size(); i++) {
                playerNames.add((TextField)pl_box2.getChildren().get(i));
            }
            for (int i = 0; i < pl_box3.getChildren().size(); i++) {
                playerNames.add((TextField)pl_box3.getChildren().get(i));
            }
            for (int i = 0; i < pl_box4.getChildren().size(); i++) {
                playerNames.add((TextField)pl_box4.getChildren().get(i));
            }
            for (int i = 0; i < pl_box5.getChildren().size(); i++) {
                playerNames.add((TextField)pl_box5.getChildren().get(i));
            }

        }
        if(players > 12 && players <= 16) {
            for (int i = 0; i < pl_box1.getChildren().size(); i++) {
                playerNames.add((TextField)pl_box1.getChildren().get(i));
            }
            for (int i = 0; i < pl_box2.getChildren().size(); i++) {
                playerNames.add((TextField)pl_box2.getChildren().get(i));
            }
            for (int i = 0; i < pl_box3.getChildren().size(); i++) {
                playerNames.add((TextField)pl_box3.getChildren().get(i));
            }
            for (int i = 0; i < pl_box4.getChildren().size(); i++) {
                playerNames.add((TextField)pl_box4.getChildren().get(i));
            }
        }
        if(players > 8 && players <= 12) {
            for (int i = 0; i < pl_box1.getChildren().size(); i++) {
                playerNames.add((TextField)pl_box1.getChildren().get(i));
            }
            for (int i = 0; i < pl_box2.getChildren().size(); i++) {
                playerNames.add((TextField)pl_box2.getChildren().get(i));
            }
            for (int i = 0; i < pl_box3.getChildren().size(); i++) {
                playerNames.add((TextField)pl_box3.getChildren().get(i));
            }

        }
        if(players > 4 && players <= 8) {
            for (int i = 0; i < pl_box1.getChildren().size(); i++) {
                playerNames.add((TextField)pl_box1.getChildren().get(i));
            }
            for (int i = 0; i < pl_box2.getChildren().size(); i++) {
                playerNames.add((TextField)pl_box2.getChildren().get(i));
            }    
    
        }
        if(players > 0 && players <= 4) {
            for (int i = 0; i < pl_box1.getChildren().size(); i++) {
                playerNames.add((TextField)pl_box1.getChildren().get(i));
            }       
        }

        ArrayList<String> randomSeats = new ArrayList<String>();
            randomSeats = funtionality.seatHandler(playerNames, players, tables);

            if(tables == 2) {
                int count = 0;
                int table2Count = 12;
                if(players % 2 == 0) {
                    table1 = (double)players / 2;
                    table2 = (double)players / 2;
                }
                else if (players % 2 != 0) {
                    table1 = Math.ceil((double)players / (double)tables);
                    table2 = Math.floor((double)players / (double)tables);
                }
            
                for(int i = 0; i < (int)table1; i++) {
                    tablePlayersList.get(i).setText(randomSeats.get(i));
                    table1Players.add(randomSeats.get(i));
                    count++;
                }
                for(int i = 0; i < (int)table2; i++) {
                    tablePlayersList.get(table2Count).setText(randomSeats.get(count));
                    table2Players.add(randomSeats.get(count));
                    count++;
                    table2Count++;
                }
                dealer(table1Players, table2Players);
            }
            if(tables == 1) {
                for(int i = 0; i < players; i++) {
                    tablePlayersList.get(i).setText(randomSeats.get(i));
                    table1Players.add(randomSeats.get(i));
                }
                oneTableDealer(table1Players);
            }
            
    }

    public static void dealer(ArrayList<String> table1Players, ArrayList<String> table2Players) {
        int upperBound1 = table1Players.size();
        int upperBound2 = table2Players.size();
        int index1 = rand.nextInt(upperBound1);
        int index2 = rand.nextInt(upperBound2);
        String table1Name = table1Players.get(index1).toString();
        String tableName2 = table2Players.get(index2);
        Alert a = new Alert(AlertType.INFORMATION);
        a.setHeaderText("Dealers");
        a.setContentText("Dealer på bord 1: " + table1Name + "\nDealer på bord 2: " + tableName2);
        a.show();
    }
    public static void oneTableDealer(ArrayList<String> table1Players) {
        int upperBound1 = table1Players.size();
        int index1 = rand.nextInt(upperBound1);
        String table1Name = table1Players.get(index1).toString();
        Alert a = new Alert(AlertType.INFORMATION);
        a.setHeaderText("Dealer");
        a.setContentText("Dealer: " + table1Name);
        a.show();
    }
}
