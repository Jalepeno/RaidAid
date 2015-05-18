package dk.nicolajpedersen.raidaid.Data;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

/**
 * Created by Nicolaj on 13-04-2015.
 */
public class User {
    String userName;
    UUID userID;


    public User(String userName, UUID userID) {
        this.userName = userName;
        this.userID = userID;

    }

    public User(JSONObject jsonObject) {
        try {
            userName = jsonObject.getString("Username");
            userID = UUID.fromString(jsonObject.getString("UserID"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public User() {

    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
