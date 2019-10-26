package modelos;

public class GameCommands {

    public enum Commands {

        Left, Right, Up, Down, Restart, Desfazer, Refazer, Bonus
    }

    public String option;

    public String getOption() {
        return option;
    }

    public void setOption(String command) {
        this.option = command;
    }

    @Override
    public String toString() {
        return "GameCommands{" + "option=" + option + '}';
    }

    public boolean validMove() {
        if (this.option.equals("Up")
                || this.option.equals("Down")
                || this.option.equals("Left")
                || this.option.equals("Right")
                || this.option.equals("Restart")
                || this.option.equals("Undo")
                || this.option.equals("Redo")
                || this.option.equals("Bonus")) {
            return true;
        } else {
            return false;
        }
    }
}
