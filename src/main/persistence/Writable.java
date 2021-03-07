package persistence;

import org.json.JSONObject;

/**
 * Represents a writable object
 * Code adapted from JsonSerializationDemo by Paul Carter
 */
public interface Writable {

    // EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
