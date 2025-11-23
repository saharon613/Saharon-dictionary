package org.aharon.dictionary;

import io.reactivex.rxjava3.schedulers.Schedulers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TouroDictionaryServiceTest {

    private DictionaryService api;

    public TouroDictionaryServiceTest() {
        DictionaryServiceFactory factory = new DictionaryServiceFactory(
                "https://p736leot54rmvuzafvpaahcb240uldif.lambda-url.us-east-2.on.aws/"
        );
        api = factory.create();
    }

    @Test
    public void lookupWord() throws Exception {
        DictionaryRequest request = new DictionaryRequest("AH");
        DictionaryResponse response = api.lookupWord(request)
                .subscribeOn(Schedulers.io())
                .blockingGet();
        assertEquals("used to express delight, relief, or contempt [interj]", response.getDefinition());
    }

    @Test
    public void lookupCaseInsensitive() throws Exception {
        DictionaryRequest request = new DictionaryRequest("ah");
        DictionaryResponse response = api.lookupWord(request)
                .subscribeOn(Schedulers.io())
                .blockingGet();
        assertEquals("used to express delight, relief, or contempt [interj]", response.getDefinition());
    }

    @Test
    public void lookupNonExistentWord() throws Exception {
        DictionaryRequest request = new DictionaryRequest("ahh");
        DictionaryResponse response = api.lookupWord(request)
                .subscribeOn(Schedulers.io())
                .blockingGet();
        assertNull(response.getDefinition());
    }
}