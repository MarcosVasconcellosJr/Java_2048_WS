package org.tensorflow.lite.examples.classification.controller.MovimentController;

import org.tensorflow.lite.examples.classification.data.api.service.WebService;
import java.util.concurrent.ExecutionException;

public class MovimentController {

    public MovimentController() {

    }

    String MOVIMENT;

    public void MovimentVerify(String moviment) {

        String Data2 = "{\n" + "    \"option\": \"" + moviment + "\"\n" + "}";

        if(validMove(moviment)){
            WebService WS = new WebService(Data2);
            try {
                WS.execute().get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean validMove(String moviment) {
        if (moviment.equals("Up")
                || moviment.equals("Down")
                || moviment.equals("Left")
                || moviment.equals("Right")
                || moviment.equals("Restart")
                || moviment.equals("Undo")
                || moviment.equals("Redo")
                || moviment.equals("Bonus")) {
            return true;
        } else {
            return false;
        }
    }
}
