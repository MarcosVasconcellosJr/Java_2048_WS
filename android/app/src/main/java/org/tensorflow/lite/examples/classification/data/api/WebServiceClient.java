package org.tensorflow.lite.examples.classification.data.api;

import android.util.Log;
import android.widget.Toast;

import org.tensorflow.lite.examples.classification.MainActivity;
import org.tensorflow.lite.examples.classification.data.api.model.Command;
import org.tensorflow.lite.examples.classification.data.api.service.CommandClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.tensorflow.lite.examples.classification.data.api.service.CommandClient.*;

public class WebServiceClient extends MainActivity {

    public void sendNetworkRequest(Command command){
        Log.i("TAGGGGGGGGGGGG", "ENTROU AQUI Ó");
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://" + super.IP + ":8080/Java_2048_WS/webresources")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

/*
        call.enqueue(new Callback<Command>() {
            @Override
            public void onResponse(Call<Command> call, Response<Command> response) {
                Log.i("TAGGGGGGGGGGGG", "CERTO");
                Log.i("TAGGGGGGGGGGGG", "==========================================");
                Log.i("TAGGGGGGGGGGGG", "------------------------------------------");
            }

            @Override
            public void onFailure(Call<Command> call, Throwable t) {

                Log.i("TAGGGGGGGGGGGG", "ERRADO" + t);
                Log.i("TAGGGGGGGGGGGG", "==========================================");
                Log.i("TAGGGGGGGGGGGG", "------------------------------------------");
            }
        });*/
    }
}