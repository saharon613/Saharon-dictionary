package org.aharon.dictionary;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TouroDictionaryTest {
    private TouroDictionary dictionary = new TouroDictionary();

    @Test
    public void lookupExistingWord() {
        String result = dictionary.lookup("AH");
        assertEquals("used to express delight, relief, or contempt [interj]", result);
    }

    @Test
    public void lookupCaseInsensitive() {
        assertEquals("used to express delight, relief, or contempt [interj]", dictionary.lookup("ah"));
    }

    @Test
    public void lookupNonExistentWord() {
        assertNull(dictionary.lookup("XYZNONEXISTENT"));
    }
}

