package fei.robot.message;

public class Perception {

    private InputText inputText;

    public Perception() {
    }

    public Perception(InputText inputText) {
        this.inputText = inputText;
    }

    public InputText getInputText() {
        return inputText;
    }

    public void setInputText(InputText inputText) {
        this.inputText = inputText;
    }

    @Override
    public String toString() {
        return "Perception{" +
                "inputText=" + inputText +
                '}';
    }
}
