package com.example.rest;

class Comandos {
    private String direction;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Direcao:" + getDirection();
    }
}
