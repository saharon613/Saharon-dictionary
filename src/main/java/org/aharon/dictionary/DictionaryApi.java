package org.aharon.dictionary;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

// Define the API interface
public interface DictionaryApi {
    @POST("/")
    Single<DictionaryResponse> lookupWord(@Body DictionaryRequest request);
}

