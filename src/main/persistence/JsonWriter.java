package persistence;

import model.Counter;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JsonWriter {

    private PrintWriter writer;
    private String destination;
    private Counter counter = new Counter();

    public JsonWriter(String destination) {
        this.destination = destination;
    }

    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    public void writeCounter(Counter counter) {
        JSONObject jsonCounter = counter.toJson();
        saveToFile(jsonCounter.toString());
    }

    public void close() {
        writer.close();
    }

    public void saveToFile(String json) {
        writer.print(json);
    }
}
