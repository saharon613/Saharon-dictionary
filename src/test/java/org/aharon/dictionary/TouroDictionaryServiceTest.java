package org.aharon.dictionary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TouroDictionaryServiceTest {

    private TouroDictionaryService service = new TouroDictionaryService(
            "https://p736leot54rmvuzafvpaahcb240uldif.lambda-url.us-east-2.on.aws/"
    );

    @Test
    public void lookupWord() throws Exception {
        DictionaryResponse response = service.lookupWord("AH");
        assertEquals("used to express delight, relief, or contempt [interj]", response.getDefinition());
    }

    @Test
    public void lookupCaseInsensitive() throws Exception {
        DictionaryResponse response = service.lookupWord("ah");
        assertEquals("used to express delight, relief, or contempt [interj]", response.getDefinition());
    }

    @Test
    public void lookupNonExistentWord() throws Exception {
        DictionaryResponse response = service.lookupWord("ahh");
        assertNull(response.getDefinition());
    }
}

