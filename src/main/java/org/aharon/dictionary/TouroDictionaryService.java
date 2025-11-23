package org.aharon.dictionary;

import io.reactivex.rxjava3.schedulers.Schedulers;

public class TouroDictionaryService {

    private DictionaryApi api;

    public TouroDictionaryService(String lambdaUrl) {
        DictionaryServiceFactory factory = new DictionaryServiceFactory(lambdaUrl);
        api = factory.create();
    }

    public DictionaryResponse lookupWord(String word) throws Exception {
        DictionaryRequest request = new DictionaryRequest(word);
        return api.lookupWord(request)
                .subscribeOn(Schedulers.io())
                .blockingGet();
    }
}
