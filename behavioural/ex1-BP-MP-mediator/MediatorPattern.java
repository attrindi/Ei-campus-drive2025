import java.util.ArrayList;
import java.util.List;

// Mediator
interface ChatRoom {
    void showMessage(User user, String message);
}

// ConcreteMediator
class ChatRoomMediator implements ChatRoom {
    @Override
    public void showMessage(User user, String message) {
        System.out.println(user.getName() + ": " + message);
    }
}

// Colleague
abstract class User {
    protected ChatRoom chatRoom;
    protected String name;

    public User(ChatRoom chatRoom, String name) {
        this.chatRoom = chatRoom;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void sendMessage(String message);
}

// ConcreteColleague
class ConcreteUser extends User {
    public ConcreteUser(ChatRoom chatRoom, String name) {
        super(chatRoom, name);
    }

    @Override
    public void sendMessage(String message) {
        chatRoom.showMessage(this, message);
    }
}

// Client code
public class MediatorPattern {
    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoomMediator();

        User user1 = new ConcreteUser(chatRoom, "Rindhia");
        User user2 = new ConcreteUser(chatRoom, "Dhivya");

        user1.sendMessage("Hi Dhivya!");
        user2.sendMessage("Hello Rindhia!");
    }
}