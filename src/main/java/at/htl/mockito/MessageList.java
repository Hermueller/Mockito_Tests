package at.htl.mockito;

import java.util.*;

public class MessageList {

    private List<String> messages;

    public MessageList() {

    }

    public void printStaticMessages(String message1, String message2) {
        System.out.println(message1 + " -- " + message2);
    }

    public String pull() {
        return "";
    }

    public void addMessageAt(int index, String message) {

    }

    public List<String> getMessages() {
        return messages;
    }
}
