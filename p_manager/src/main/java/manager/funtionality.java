package manager;
import java.util.ArrayList;
import javafx.scene.control.TextField;
import java.security.SecureRandom;


public class funtionality {
    /* ------------------------------------------------------------------------------------------------------------------------------------- */
    // Funktion som randomizar bordsplaceringen och skickar ut en lista på det.
    /* ------------------------------------------------------------------------------------------------------------------------------------- */
    public static ArrayList seatHandler(ArrayList<TextField> playerlist, int seats, int tables) {
        // Tar in antal spelare, och alla spelaren namn.
        // Skapar antal bord och sen kör random placering på alla spelare
        SecureRandom rand = new SecureRandom();
        ArrayList<String> playerNames = new ArrayList<String>();
        for (int i = 0; i < playerlist.size(); i++) {
            if(playerlist.get(i).getText().isEmpty()) {
                break;
            }
            playerNames.add(playerlist.get(i).getText());
        }
        int upperbound = playerNames.size();
        ArrayList<Integer> usedNumbers = new ArrayList<Integer>();
        ArrayList<String> randomPlayers = new ArrayList<String>();

        while((randomPlayers.size() != seats)) {
            int randomNumber = rand.nextInt(upperbound);
            if(usedNumbers.contains(randomNumber)) {
                continue;
            }
            if(usedNumbers.size() >= upperbound) {
                break;
            }
            else {
                randomPlayers.add(playerNames.get(randomNumber));
                usedNumbers.add(randomNumber);
                continue;
            }
        }
        return randomPlayers;
    }

    
}
