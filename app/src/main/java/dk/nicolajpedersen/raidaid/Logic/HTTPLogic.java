package dk.nicolajpedersen.raidaid.Logic;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;

import java.util.UUID;

/**
 * Created by Nicolaj on 13-03-2015.
 */

/**
 * Save HTTP Call files instead of setting up local database.
 */


public class HTTPLogic {

    public HTTPLogic(){
    }

    public int getProfile(String username,String password){
        getClanList();
        getFriendList();
        getFriendRequests();
        getClanInvites();
        return 1;
    }
    public int getClan(){
        return 1;
    }

    public int getClanList(){
        return 1;
    }


    public void sendShout(UUID clanID, Editable text) {
    }

    public int getFriendList() {
        return 1;
    }

    public int getFriendRequests() {
        return 1;
    }

    public int getClanInvites() {
        return 1;
    }
}
