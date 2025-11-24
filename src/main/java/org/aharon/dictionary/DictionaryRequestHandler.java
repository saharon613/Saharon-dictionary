package org.aharon.dictionary;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.google.gson.Gson;

public class DictionaryRequestHandler implements RequestHandler<APIGatewayProxyRequestEvent, DictionaryResponse> {

    private TouroDictionary dictionary;

    public DictionaryRequestHandler() {
        dictionary = new TouroDictionary();
    }

    @Override
    public DictionaryResponse handleRequest(APIGatewayProxyRequestEvent event, Context context) {
        // Get the JSON body from the request
        String body = event.getBody();

        // Convert JSON to DictionaryRequest object
        Gson gson = new Gson();
        DictionaryRequest request = gson.fromJson(body, DictionaryRequest.class);

        // Get the word from the request
        String word = request.getWord();

        // Look up the word in the dictionary
        String definition = dictionary.lookup(word);

        // Create and return the response
        return new DictionaryResponse(word, definition);
    }
}