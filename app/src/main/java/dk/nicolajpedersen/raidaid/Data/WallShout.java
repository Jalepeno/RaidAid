package dk.nicolajpedersen.raidaid.Data;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nicolaj on 07-04-2015.
 */
public class WallShout {
    String message;
    Membership member;

    public WallShout(String message,Membership member){
        this.message = message;
        this.member = member;
    }

    public WallShout(JSONObject o) {
        try {
            this.message = o.getString("Message");
            this.member = new Membership(o.getJSONObject("Member"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Membership getMember() {
        return member;
    }

    public void setMember(Membership member) {
        this.member = member;
    }
}
