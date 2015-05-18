package dk.nicolajpedersen.raidaid.Data;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nicolaj on 12-05-2015.
 */
public class Friend extends User {

    private boolean isInvited;

    /*
    The friend object serves two puposes, it fills the firendlist, and
    allows you to select who to invite.
    since this is a user with an added boolean value, it also serves as
    an appointment invite.
     */
    public Friend(JSONObject jsonObject) {
        super(jsonObject);
        try{
            isInvited = jsonObject.getBoolean("IsReady");
        } catch (JSONException e) {
            isInvited =false;
        }

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

    public Membership convertToMember(){
        Membership thisGuy = new Membership();
        thisGuy.setUserName(this.userName);
        thisGuy.setUserID(this.userID);
        return thisGuy;
    }
}
