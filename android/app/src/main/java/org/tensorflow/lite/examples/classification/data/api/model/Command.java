package org.tensorflow.lite.examples.classification.data.api.model;

public class Command {


    private String option;

    public Command(String option) {
        this.option = option;
    }

    public String getOption(){
        return option;
    }
}
