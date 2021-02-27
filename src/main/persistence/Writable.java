package persistence;

import org.json.JSONObject;

/**
 * Code adapted from JsonSerializationDemo by Paul Carter
 *
 * Represents a writable object
 */
public interface Writable {

    // EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
