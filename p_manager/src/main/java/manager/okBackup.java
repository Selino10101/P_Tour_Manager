// package manager;

// import javafx.scene.control.TextField;

// public class okBackup {
//     int players = Integer.parseInt(personsTextField.getText());
//             int tables = Integer.parseInt(tablesTextField.getText());
//             double table1 = 0;
//             double table2 = 0;
//             ArrayList<TextField> playerList = new ArrayList<TextField>();
//             if(players > 20) {
//                 for (int i = 0; i < pl_box1.getChildren().size(); i++) {
//                     playerList.add((TextField)pl_box1.getChildren().get(i));
//                 }
//                 for (int i = 0; i < pl_box2.getChildren().size(); i++) {
//                     playerList.add((TextField)pl_box2.getChildren().get(i));
//                 }
//                 for (int i = 0; i < pl_box3.getChildren().size(); i++) {
//                     playerList.add((TextField)pl_box3.getChildren().get(i));
//                 }
//                 for (int i = 0; i < pl_box4.getChildren().size(); i++) {
//                     playerList.add((TextField)pl_box4.getChildren().get(i));
//                 }
//                 for (int i = 0; i < pl_box5.getChildren().size(); i++) {
//                     playerList.add((TextField)pl_box5.getChildren().get(i));
//                 }
//                 for (int i = 0; i < pl_box6.getChildren().size(); i++) {
//                     playerList.add((TextField)pl_box6.getChildren().get(i));
//                 }
//             }
//             if(players > 16 && players <= 20) {
//                 for (int i = 0; i < pl_box1.getChildren().size(); i++) {
//                     playerList.add((TextField)pl_box1.getChildren().get(i));
//                 }
//                 for (int i = 0; i < pl_box2.getChildren().size(); i++) {
//                     playerList.add((TextField)pl_box2.getChildren().get(i));
//                 }
//                 for (int i = 0; i < pl_box3.getChildren().size(); i++) {
//                     playerList.add((TextField)pl_box3.getChildren().get(i));
//                 }
//                 for (int i = 0; i < pl_box4.getChildren().size(); i++) {
//                     playerList.add((TextField)pl_box4.getChildren().get(i));
//                 }
//                 for (int i = 0; i < pl_box5.getChildren().size(); i++) {
//                     playerList.add((TextField)pl_box5.getChildren().get(i));
//                 }

//             }
//             if(players > 12 && players <= 16) {
//                 for (int i = 0; i < pl_box1.getChildren().size(); i++) {
//                     playerList.add((TextField)pl_box1.getChildren().get(i));
//                 }
//                 for (int i = 0; i < pl_box2.getChildren().size(); i++) {
//                     playerList.add((TextField)pl_box2.getChildren().get(i));
//                 }
//                 for (int i = 0; i < pl_box3.getChildren().size(); i++) {
//                     playerList.add((TextField)pl_box3.getChildren().get(i));
//                 }
//                 for (int i = 0; i < pl_box4.getChildren().size(); i++) {
//                     playerList.add((TextField)pl_box4.getChildren().get(i));
//                 }
//             }
//             if(players > 8 && players <= 12) {
//                 for (int i = 0; i < pl_box1.getChildren().size(); i++) {
//                     playerList.add((TextField)pl_box1.getChildren().get(i));
//                 }
//                 for (int i = 0; i < pl_box2.getChildren().size(); i++) {
//                     playerList.add((TextField)pl_box2.getChildren().get(i));
//                 }
//                 for (int i = 0; i < pl_box3.getChildren().size(); i++) {
//                     playerList.add((TextField)pl_box3.getChildren().get(i));
//                 }

//             }
//             if(players > 4 && players <= 8) {
//                 for (int i = 0; i < pl_box1.getChildren().size(); i++) {
//                     playerList.add((TextField)pl_box1.getChildren().get(i));
//                 }
//                 for (int i = 0; i < pl_box2.getChildren().size(); i++) {
//                     playerList.add((TextField)pl_box2.getChildren().get(i));
//                 }    
        
//             }
//             if(players > 0 && players <= 4) {
//                 for (int i = 0; i < pl_box1.getChildren().size(); i++) {
//                     playerList.add((TextField)pl_box1.getChildren().get(i));
//                 }       
//             }
//             ArrayList<String> randomSeats = new ArrayList<String>();
//             randomSeats = funtionality.seatHandler(playerList, players, tables);

//             if(tables == 2) {
//                 int count = 0;
//                 int tableCount = 12;
//                 if(players % 2 == 0) {
//                     table1 = (double)players / 2;
//                     table2 = (double)players / 2;
//                 }
//                 else if (players % 2 != 0) {
//                     table1 = Math.ceil((double)players / (double)tables);
//                     table2 = Math.floor((double)players / (double)tables);
//                 }
            
//                 for(int i = 0; i < (int)table1; i++) {
//                     tableTexts.get(i).setText(randomSeats.get(i));
//                     userWindow.getChildren().add(tableTexts.get(i));
//                     count++;
//                 }
//                 for(int i = 0; i < (int)table2; i++) {
//                     tableTexts.get(tableCount).setText(randomSeats.get(count));
//                     userWindow.getChildren().add(tableTexts.get(tableCount));
//                     count++;
//                     tableCount++;
//                 }
//                 okButton.getScene().setRoot(userWindow);
//             }
//             if(tables == 1) {
//                 for(int i = 0; i < players; i++) {
//                     tableOneTexts.get(i).setText(randomSeats.get(i));
//                     userWindow.getChildren().add(tableOneTexts.get(i));
//                 }
//             }
// }
