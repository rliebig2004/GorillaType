package persistence;

import model.Entry;
import model.Scoreboard;
import model.Event;
import model.EventLog;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads scoreboard from JSON data stored in file
public class JsonReader {
    private String source;
    private Event event;
    private EventLog eventLog;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
        this.eventLog = EventLog.getInstance();
    }

    // EFFECTS: reads scoreboard from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Scoreboard read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        this.event = new Event("All attempts loaded!");
        this.eventLog.logEvent(this.event);
        return parseScoreboard(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses scoreboard from JSON object and returns it
    private Scoreboard parseScoreboard(JSONObject jsonObject) {
        Scoreboard scrbrd = new Scoreboard();
        addEntries(scrbrd, jsonObject);
        return scrbrd;
    }

    // MODIFIES: scrbrd
    // EFFECTS: parses entries from JSON object and adds them to scoreboard
    private void addEntries(Scoreboard scrbrd, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("entries");
        for (Object json : jsonArray) {
            JSONObject nextEntry = (JSONObject) json;
            addEntry(scrbrd, nextEntry);
        }
    }

    // MODIFIES: scrbrd
    // EFFECTS: parses entry from JSON object and adds it to scoreboard
    private void addEntry(Scoreboard scrbrd, JSONObject jsonObject) {
        float time = Float.valueOf(jsonObject.getFloat("time"));
        double wpm = Double.valueOf(jsonObject.getDouble("wpm"));
        double acc = Double.valueOf(jsonObject.getDouble("accuracy"));
        Entry entry = new Entry(time, wpm, acc);
        scrbrd.addEntries(entry);
    }
}
