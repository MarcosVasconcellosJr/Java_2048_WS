package com.example.myapplication;

import java.io.Serializable;

public class Requisition implements Serializable {
    private String opcao;
    public Requisition(){
    
    }

    public String getOpcao() {
        return opcao;
    }

    public void setOpcao(String opcao) {
        this.opcao = opcao;
    }
}
