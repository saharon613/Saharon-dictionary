package org.aharon.dictionary;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

public class DictionaryRequestHandler implements RequestHandler<APIGatewayProxyRequestEvent,
        APIGatewayProxyResponseEvent>
{
    private TouroDictionary dictionary;
    private Gson gson;

//    public DictionaryRequestHandler()
//    {
//        this.dictionary = new TouroDictionary();
//        this.gson = new Gson();
//    }

    public DictionaryRequestHandler()
    {
        try {
            // Read dictionary from S3
            S3Client s3Client = S3Client.create();

            String bucketName = "aharon-dictionary";
            String key = "dictionary.txt";

            GetObjectRequest getObjectRequest = GetObjectRequest
                    .builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            InputStream in = s3Client.getObject(getObjectRequest);

            // Create dictionary with S3 input stream
            this.dictionary = new TouroDictionary(in);
            this.gson = new Gson();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load dictionary from S3", e);
        }
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context)
    {
        try
        {
            // retrieve the body and change Json into a new object
            String body = event.getBody();
            DictionaryRequest request = gson.fromJson(body, DictionaryRequest.class);

            // get the definition and create a DictionaryResponse
            String definition = dictionary.lookup(request.getWord());
            DictionaryResponse response = new DictionaryResponse(request.getWord(), definition);

            // create the HTTP response with the DictionaryResponse
            String responseJson = gson.toJson(response);
            APIGatewayProxyResponseEvent apiResponse = new APIGatewayProxyResponseEvent();
            apiResponse.setStatusCode(200);
            apiResponse.setBody(responseJson);
            return apiResponse;

        } catch (Exception e)
        {
            // this prints the error to the AWS log file , this prints the error (stack track) to the AWS log file
            e.printStackTrace();

            // this outputs the stack trace to the client - whoever initiated the request - my computer
            return toResponseEvent(e);
        }
    }

    private APIGatewayProxyResponseEvent toResponseEvent(Exception e)
    {
        // this outputs the stack trace to the client - whoever initiated the request - my computer
        APIGatewayProxyResponseEvent apiResponse = new APIGatewayProxyResponseEvent();
        apiResponse.setStatusCode(500);
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        apiResponse.setBody(stringWriter.toString());
        return apiResponse;
    }
}