package persistence;

import model.components.GameBoard;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String fileLocation = "./data/savedGame.json";

    // MODIFIES: this
    // EFFECTS: writes JSON representation of board to file
    public void saveGame(GameBoard board, boolean redGoesNext) throws FileNotFoundException {
        writer = new PrintWriter(new File(fileLocation));
        JSONObject json = board.toJson();
        json.put("red starts?", redGoesNext);
        writer.print(json.toString(TAB));
        writer.close();
    }
}
