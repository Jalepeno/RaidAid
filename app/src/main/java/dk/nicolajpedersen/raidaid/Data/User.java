package dk.nicolajpedersen.raidaid.Data;

import org.json.JSONObject;

/**
 * Created by Nicolaj on 13-04-2015.
 */
public class User {
    String userName;
    String userID;


    public User(String userName, String userID) {
        this.userName = userName;
        this.userID = userID;

    }

    public User(JSONObject jsonObject) {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
