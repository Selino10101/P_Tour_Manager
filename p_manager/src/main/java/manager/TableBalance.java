package manager;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Alert.AlertType;

import java.security.SecureRandom;

public class TableBalance {
    static SecureRandom rand = new SecureRandom();
    
    public static void eliminate(ArrayList<String> table1, ArrayList<String> table2, String name, boolean twoTables, ArrayList<Button> tablePlayersList, int finalKonstant, timer timer) {
        Font font = Font.font("Arial", FontWeight.BOLD, 16);
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
             new Insets(50)
             );
    
        BackgroundFill backgroundFill3 =
            new BackgroundFill(
                Color.valueOf("#3c573d"),
                new CornerRadii(100),
                new Insets(100)
                );

        Background background =
        new Background(backgroundFill1, backgroundFill2, backgroundFill3);
        Alert a = new Alert(AlertType.INFORMATION);
        DialogPane pane = a.getDialogPane();
        pane.lookupButton(ButtonType.OK).setStyle("-fx-font-family: Arial;"+ "-fx-font-size: 16px;" + "-fx-font-weight: bold;");
        
        //pane.setBackground(background);
        if(twoTables) {
            int index1 = table1.indexOf(name);
            int index2 = table2.indexOf(name);
            if(index1 != -1) {
                table1.remove(index1);
            }
            else if(index2 != -1) {
                table2.remove(index2);
            }
            balanceTables(table1, table2, tablePlayersList);        
        }
        if((table1.size() + table2.size()) <= finalKonstant && twoTables) {
            int tableCount = 12;
            twoTables = false;
            int index1 = table1.indexOf(name);
            int index2 = table2.indexOf(name);
            if(index1 != -1) {
                table1.remove(index1);
            }
            else if(index2 != -1) {
                table2.remove(index2);
            }
            ArrayList<String> finalTable = new ArrayList<String>();
            for(int i = 0; i < table1.size(); i++) {
                finalTable.add(table1.get(i));
            }
            for(int i = 0; i < table2.size(); i++) {
                finalTable.add(table2.get(i));
                table1.add(table2.get(i));
            }
            for(int i = tableCount; i < tablePlayersList.size(); i++) {
                tablePlayersList.get(i).setText("");
            }
            /* Pausa timer och när man trycker ok på medelandet startas den igen. */
            int upperBound = finalTable.size();
            int randDealer = rand.nextInt(upperBound);
            String dealer = finalTable.get(randDealer);
            a.setHeaderText("Final Bordet");
            a.setContentText("Följande spelare är på finalbordet: \n" + finalTable + "\nDealer är: " + dealer);
            timer.pause();
            a.show();
            FinalTable(finalTable, tablePlayersList);
        }
        else if(!twoTables){
            int index11 = table1.indexOf(name);
            table1.remove(index11);    
        }
    }
    // MÅSTE KOLLAS IGENOM!
    // arraylist.remove() ELIM kanske här och sen skicka in table1 och 2 in i balance för att
    // kolla om t1 - t2 >= 2 eller -2, om det är så balansera borden(pausa timern isf?).
    public static void balanceTables(ArrayList<String> table1, ArrayList<String> table2, ArrayList<Button> tablePlayersList) {
        if((table1.size() - table2.size()) >= 2 || (table1.size() - table2.size()) <= -2) {
            if(table1.size() > table2.size()) {
                int tableCount = 12;
                int upperbound = table1.size();
                int randomIndex = rand.nextInt(upperbound);
                String player = table1.get(randomIndex);
                table2.add(player);
                int playerIn = table1.indexOf(player); 
                table1.remove(playerIn);
                for(int i = 0; i < tablePlayersList.size(); i++) {
                    if(tablePlayersList.get(i).getText().equals(player)) {
                        tablePlayersList.get(i).setText("");
                    }
                }
                for(int i = 0; i < tablePlayersList.size(); i++) {
                    if(tablePlayersList.get(tableCount).getText().isEmpty()) {
                        tablePlayersList.get(tableCount).setText(player);
                        break;
                    }
                    tableCount++;
                }
            }
            if(table1.size() < table2.size()) {
                int tableCount = 12;
                int upperbound = table2.size();
                int randomIndex = rand.nextInt(upperbound);
                String player = table2.get(randomIndex);
                table1.add(player);
                int playerIn = table2.indexOf(player);
                table2.remove(playerIn);
                for(int i = 12; i < tablePlayersList.size(); i++) {
                    if(tablePlayersList.get(i).getText().equals(player)) {
                        tablePlayersList.get(i).setText("");
                    }
                    //tableCount++;
                }
                for(int i = 0; i < 12; i++) {
                    if(tablePlayersList.get(i).getText().isEmpty()) {
                        tablePlayersList.get(i).setText(player);
                        break;
                    }
                }
            }

        }
        
        // Koll om borden är obalanserade.
        // Om de är det flyttas x antal spelare från bordet med mest spelare till bordet med minst spelare.
        // Helst ska nya spelare få samma platser som borttagna spelare.
    }
    public static void FinalTable(ArrayList<String> finalTable, ArrayList<Button> tablePlayersList) {
        for(int i = 0; i < finalTable.size(); i++) {
            tablePlayersList.get(i).setText(finalTable.get(i));
        }
    }
    
}
