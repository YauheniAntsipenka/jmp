package task5.domain;

import java.util.StringJoiner;

/**
 * Message
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class Message {

    private final int id;
    private final String data;

    public Message(int id, String data) {
        this.id = id;
        this.data = data;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Message.class.getSimpleName() + "[", "]")
            .add("id=" + id)
            .add("data='" + data + "'")
            .toString();
    }
}
