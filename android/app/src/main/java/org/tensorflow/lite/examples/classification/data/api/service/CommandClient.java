package org.tensorflow.lite.examples.classification.data.api.service;

import org.tensorflow.lite.examples.classification.data.api.model.Command;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CommandClient {
    @POST("/insert")
    Call<Command> createCommand(@Body Command command);
}
