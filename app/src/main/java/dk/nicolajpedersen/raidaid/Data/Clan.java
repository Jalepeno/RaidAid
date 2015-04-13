package dk.nicolajpedersen.raidaid.Data;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.UUID;

import dk.nicolajpedersen.raidaid.Logic.HTTPLogic;

/**
 * Created by Nicolaj on 13-03-2015.
 */
public class Clan {
    private Game game;
    private int myRank;
    private ArrayList<User> members;
    private ArrayList<Appointment> appointments;
    private String clanName,welcomeMessage;
    private ArrayList<WallShout> shouts;
    private UUID clanID;

    public Clan (JSONObject newClan){

    }
    public boolean createNewClan(Game game,String newClanName, String setPassword){
        HTTPLogic httpLogic=new HTTPLogic();
        boolean isSuccess=false;
        return isSuccess;
    }

    public boolean newInvite(String message){
        HTTPLogic httpLogic=new HTTPLogic();
        boolean isSuccess=false;
        return isSuccess;
    }

    public boolean newShout(String message){
        HTTPLogic httpLogic=new HTTPLogic();
        boolean isSuccess=false;
        return isSuccess;
    }

    public boolean leaveClan(){
        boolean isSuccess=false;
        if(myRank > 8){
            HTTPLogic httpLogic=new HTTPLogic();
        }
        return isSuccess;
    }



    // need admin rights check for this to happen
    public boolean kickMember(String username){
        boolean isSuccess=false;
        if(myRank > 8){
            HTTPLogic httpLogic=new HTTPLogic();
        }
        return isSuccess;
    }

    // need admin rights check for this to happen
    public boolean makeNewAppointment() {
        boolean isSuccess=false;
        if(myRank > 8){
            HTTPLogic httpLogic=new HTTPLogic();
        }
        return isSuccess;
    }

    // need admin rights check for this to happen
    public boolean setNewPassword(String newPassword){
        boolean isSuccess=false;
        if(myRank > 8){
            HTTPLogic httpLogic=new HTTPLogic();
        }
        return isSuccess;
    }

    // need admin rights check for this to happen
    public boolean giveAdmin(String newAdminUser) {
        boolean isSuccess=false;
        if(myRank > 8){
            HTTPLogic httpLogic=new HTTPLogic();
        }
        return isSuccess;
    }

    public ArrayList<WallShout> getShouts() {
        return shouts;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }


    public UUID getClanID() {
        return clanID;
    }

    public ArrayList<User> getMembers() {
        return members;
    }

    public User findMember(String userName){
        for(User member:members){
            if(member.getUserName().equalsIgnoreCase(userName)){
                return member;
            }
        }
        return null;
    }
}
