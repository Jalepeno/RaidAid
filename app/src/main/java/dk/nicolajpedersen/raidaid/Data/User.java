package dk.nicolajpedersen.raidaid.Data;

import org.json.JSONException;
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
        try {
            userName = jsonObject.getString("Username");
            userID = jsonObject.getString("UserID");
            System.out.println("\tmember: "+jsonObject.getString("Username"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public User() {

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
