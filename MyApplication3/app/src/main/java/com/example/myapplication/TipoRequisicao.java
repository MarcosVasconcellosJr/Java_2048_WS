package com.example.myapplication;

import java.io.Serializable;

public class TipoRequisicao implements Serializable {
    private String opcao;
    public  TipoRequisicao(){
    
    }

    public String getOpcao() {
        return opcao;
    }

    public void setOpcao(String opcao) {
        this.opcao = opcao;
    }
}
