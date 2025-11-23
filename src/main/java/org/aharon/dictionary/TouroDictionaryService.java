package org.aharon.dictionary;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TouroDictionaryService {

    private DictionaryApi api;

    public TouroDictionaryService(String lambdaUrl) {
        // Create Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(lambdaUrl) // functional URL
                .addConverterFactory(GsonConverterFactory.create()) // Use Gson for JSON conversion
                .build();

        // Create the API implementation
        api = retrofit.create(DictionaryApi.class);
    }

    public DictionaryResponse lookupWord(String word) throws Exception {
        // Create the request object
        DictionaryRequest request = new DictionaryRequest();
        request.setWord(word);

        // Make the HTTP call
        Call<DictionaryResponse> call = api.lookupWord(request);

        // get the response
        retrofit2.Response<DictionaryResponse> response = call.execute();

        // Return the response body
        if (response.isSuccessful()) {
            return response.body();
        } else {
            throw new Exception("Request failed: " + response.code());
        }
    }
}
