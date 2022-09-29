package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents the counter
public class Counter implements Writable {

    private int count;

    // EFFECTS: constructs a counter starting at 0
    public Counter() {
        this.count = 0;
    }

    // MODIFIES: this
    // EFFECTS: increases the count by 1
    public void increase() {
        this.count++;
    }

    // MODIFIES: this
    // EFFECTS: decreases the count by 1
    public void decrease() {
        this.count--;
    }

    // MODIFIES: this
    // EFFECTS: sets the count back to 0
    public void reset() {
        this.count = 0;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int i) {
        this.count = i;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("count", count);
        return json;
    }
}
