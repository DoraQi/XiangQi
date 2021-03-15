package persistence;

import exception.IllegalInputException;
import model.components.GameBoard;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static model.components.PieceFactory.createPiece;

/**
 * Represents a reader that reads Gameboard from JSON data stored in file
 * Code adapted from JsonSerializationDemo by Paul Carter
 */
public class JsonReader {
    private static final int TAB = 4;
    private String fileLocation = "./data/savedGame.json";

    // EFFECTS: reads gameboard from file and returns it;
    // throws IOException if an error occurs reading data from file
    public GameBoard loadGame() throws IOException, IllegalInputException {
        String jsonData = readFile(fileLocation);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBoard(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private GameBoard parseBoard(JSONObject jsonObject) throws IllegalInputException {
        GameBoard board = new GameBoard();
        addPieces(board, jsonObject);
        addCapturedPiecesToPlayers(board, jsonObject);
        return board;
    }

    // MODIFIES: board
    // EFFECTS: parses player information from JSON object and update them in board
    private void addCapturedPiecesToPlayers(GameBoard board, JSONObject jsonObject) throws IllegalInputException {
        JSONArray jsonArray = jsonObject.getJSONArray("players");
        // Add captured pieces for red player
        JSONObject redPlayerDetails = (JSONObject) jsonArray.get(0);
        JSONArray redCapturedPieces = redPlayerDetails.getJSONArray("captured");
        for (Object json : redCapturedPieces) {
            JSONObject nextPiece = (JSONObject) json;
            addCapturedPiece(board, nextPiece, true);
        }
        // Add captured pieces for black player
        JSONObject blackPlayerDetails = (JSONObject) jsonArray.get(1);
        JSONArray blackCapturedPieces = blackPlayerDetails.getJSONArray("captured");
        for (Object json : blackCapturedPieces) {
            JSONObject nextPiece = (JSONObject) json;
            addCapturedPiece(board, nextPiece, false);
        }
    }

    // MODIFIES: board
    // EFFECTS: parses player information from JSON object and update them in board
    private void addCapturedPiece(GameBoard board, JSONObject nextPiece, boolean capturedByRed)
            throws IllegalInputException {
        String pieceClass = nextPiece.getString("pieceClass");
        int x = nextPiece.getInt("posX");
        int y = nextPiece.getInt("posY");
        board.addCapturedPiece(pieceClass, x, y, capturedByRed);
    }

    // MODIFIES: board
    // EFFECTS: parses pieces from JSON array and adds them to workroom
    private void addPieces(GameBoard board, JSONObject jsonObject) throws IllegalInputException {
        JSONArray jsonArray = jsonObject.getJSONArray("pieces");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addPiece(board, nextThingy);
        }
    }

    // MODIFIES: board
    // EFFECTS: parses piece from JSON object and adds it to workroom
    private void addPiece(GameBoard board, JSONObject jsonObject) throws IllegalInputException {
        String pieceClass = jsonObject.getString("pieceClass").toLowerCase();
        int posX = jsonObject.getInt("posX");
        int posY = jsonObject.getInt("posY");
        String isRed = jsonObject.getString("isRed");
        createPiece(pieceClass + " [" + posX + "," + posY + "]" + isRed, board);
    }

    // EFFECTS: returns true if file indicates red should go first, false otherwise
    public boolean getFirstStart() throws IOException {
        String jsonData = readFile(fileLocation);
        JSONObject jsonObject = new JSONObject(jsonData);
        return jsonObject.getBoolean("redStarts");
    }
}
