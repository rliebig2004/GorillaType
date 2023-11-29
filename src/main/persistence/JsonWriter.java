package persistence;

import model.Scoreboard;
import model.Event;
import model.EventLog;
import org.json.JSONObject;


import java.io.*;

// Represents a writer that writes JSON representation of scoreboard to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;
    private Event event;
    private EventLog eventLog;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
        this.eventLog = EventLog.getInstance();
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of scoreboard to file
    public void write(Scoreboard scrbrd) {
        JSONObject json = scrbrd.toJson();
        saveToFile(json.toString(TAB));
        this.event = new Event("Attempt Saved!");
        this.eventLog.logEvent(this.event);
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
