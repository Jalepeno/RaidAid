package dk.nicolajpedersen.raidaid.Data;

import org.json.JSONObject;

import java.util.ArrayList;

import dk.nicolajpedersen.raidaid.Logic.HTTPLogic;

/**
 * Created by Nicolaj on 13-03-2015.
 */
public class Clan {
    private Game game;
    private boolean isadmin;
    private ArrayList<String> members;
    private ArrayList<Appointment> appointments;
    private String clanName,welcomeMessage;
    private ArrayList<WallShout> shouts;

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
        if(!isadmin){
            HTTPLogic httpLogic=new HTTPLogic();
        }
        return isSuccess;
    }



    // need admin rights check for this to happen
    public boolean kickMember(String username){
        boolean isSuccess=false;
        if(isadmin){
            HTTPLogic httpLogic=new HTTPLogic();
        }
        return isSuccess;
    }

    // need admin rights check for this to happen
    public boolean makeNewAppointment() {
        boolean isSuccess=false;
        if(isadmin){
            HTTPLogic httpLogic=new HTTPLogic();
        }
        return isSuccess;
    }

    // need admin rights check for this to happen
    public boolean setNewPassword(String newPassword){
        boolean isSuccess=false;
        if(isadmin){
            HTTPLogic httpLogic=new HTTPLogic();
        }
        return isSuccess;
    }

    // need admin rights check for this to happen
    public boolean giveAdmin(String newAdminUser) {
        boolean isSuccess=false;
        if(isadmin){
            HTTPLogic httpLogic=new HTTPLogic();
        }
        return isSuccess;
    }

}
