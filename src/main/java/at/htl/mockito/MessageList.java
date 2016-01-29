package at.htl.mockito;

import java.util.*;

public class MessageList {

    private List<String> messages;

    public MessageList() {
        fillList();
    }

    public void printStaticMessages(String message1, String message2) {
        System.out.println(message1 + " -- " + message2);
    }

    public void fillList() {
        messages = new LinkedList<String>() {{
            add("Message A");
            add("Message B");
            add("Message C");
        }};
    }

    public String pull() {
        String string = getMessages().get(0);
        getMessages().remove(0);
        return string;
    }

    public void addMessageAt(int index, String message) {
        getMessages().add(index, message);
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }
}
