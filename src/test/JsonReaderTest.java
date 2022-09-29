import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/fileDNE.json");
        try {
            int count = reader.readCounter();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderCounterZero() {
        JsonReader reader = new JsonReader("./data/testReaderCounterZero");
        try {
            int count = reader.readCounter();
            assertEquals(0, count);
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderCounterNonZero() {
        JsonReader reader = new JsonReader("./data/testReaderCounterNonZero");
        try {
            int count = reader.readCounter();
            assertEquals(1, count);
        } catch (IOException e) {
            // pass
        }
    }
}
