package dk.nicolajpedersen.raidaid.Data;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Comparator;
import java.util.UUID;

/**
 * Created by Nicolaj on 29-04-2015.
 */
public class Membership extends User implements Comparator<Membership>{
    int rank;
    String clanID;


    public Membership(String userName, UUID userID) {
        super(userName, userID);
    }

    public Membership() {

    }
    public Membership(User theGuy,String clanID){
        super(theGuy.userName,theGuy.userID);
        this.clanID = clanID;
    }
    public Friend convertToFriend(){
        Friend thisGuy = new Friend();
        thisGuy.setUserName(this.getUserName());
        thisGuy.setUserID(this.getUserID());
        return thisGuy;
    }
    public Friend converToFriendReady(){
        Friend thisGuy = convertToFriend();
        thisGuy.setIsInvited(true);
        return thisGuy;
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

    @Override
    public int compare(Membership lhs, Membership rhs) {
        if(lhs.getRank()>rhs.getRank()){return 1;
        }else if(lhs.getRank()<rhs.getRank()) {
            return -1;
        }else return lhs.getUserName().compareTo(rhs.getUserName());
    }
}
