package at.htl.mockito;


public class Messenger {
    MessageList ml;

    public Messenger(MessageList ml) {
        this.ml = ml;
    }

    public String printMessage() {
        return getMl().pull();
    }

    public MessageList getMl() {
        return ml;
    }
}