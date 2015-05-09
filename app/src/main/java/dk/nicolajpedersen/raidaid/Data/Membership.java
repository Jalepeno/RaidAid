package dk.nicolajpedersen.raidaid.Data;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nicolaj on 29-04-2015.
 */
public class Membership extends User {
    int rank;
    String clanID;


    public Membership(String userName, String userID) {
        super(userName, userID);
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Membership(JSONObject jsonObject){
        super(jsonObject);
        try {
            clanID = jsonObject.getString("ClanID");
            rank = jsonObject.getInt("Rank");
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
