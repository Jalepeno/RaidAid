package dk.nicolajpedersen.raidaid.Data;


import org.json.JSONObject;

/**
 * Created by Nicolaj on 12-05-2015.
 */
public class Friend extends User {

    private boolean isInvited;

    public Friend(JSONObject jsonObject) {
        super(jsonObject);
        isInvited =false;
    }

    public Friend() {
        this.isInvited = false;
    }

    public boolean isInvited() {
        return isInvited;
    }

    public void setIsInvited(boolean isInvited) {
        this.isInvited = isInvited;
    }
}
