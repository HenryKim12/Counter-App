import model.Counter;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Counter counter = new Counter();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testWriterCounterZero() {
        try {
            Counter counter = new Counter();
            int count = counter.getCount();
            JsonWriter writer = new JsonWriter("./data/testWriterCounterZero.json");
            writer.open();
            writer.writeCounter(counter);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterCounterZero.json");
            count = reader.readCounter();

            assertEquals(0, count);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterCounterNonZero() {
        try {
            Counter counter = new Counter();
            counter.increase();
            int count = counter.getCount();
            JsonWriter writer = new JsonWriter("./data/testWriterCounterNonZero.json");
            writer.open();
            writer.writeCounter(counter);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterCounterNonZero.json");
            count = reader.readCounter();

            assertEquals(1, count);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
