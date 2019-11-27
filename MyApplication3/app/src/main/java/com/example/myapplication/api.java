package com.example.myapplication;

import com.example.myapplication.TipoRequisicao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface api {
@GET("withdraw")
    Call <TipoRequisicao> recuperar();

}
