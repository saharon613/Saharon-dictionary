package org.aharon.dictionary;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

// Define the API interface
interface DictionaryApi {
    @POST("/") // The endpoint path
    Call<DictionaryResponse> lookupWord(@Body DictionaryRequest request);
}

