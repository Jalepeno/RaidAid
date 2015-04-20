package dk.nicolajpedersen.raidaid.Data;

/**
 * Created by Nicolaj on 07-04-2015.
 */
public class WallShout {
    String message;
    User user;

    public WallShout(String message,User user){
        this.message = message;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
