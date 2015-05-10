package dk.nicolajpedersen.raidaid.Data;

import org.json.JSONArray;
import org.json.JSONException;
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
    private String clanName,welcomeMessage;
    private UUID clanID;

    private ArrayList<Membership> members;
    private ArrayList<Appointment> appointments;
    private ArrayList<WallShout> shouts;


    public Clan (JSONObject newClan){
        try {
            game = Game.fromInteger(newClan.getInt("Game"));
            myRank = newClan.getInt("MyRank");
            clanID = UUID.fromString(newClan.getString("ClanID"));
            clanName = newClan.getString("ClanName");
            welcomeMessage = newClan.getString("MessageOfDay");
            members = new ArrayList<>();
            appointments = new ArrayList<>();
            shouts = new ArrayList<>();
            JSONArray membersArray = newClan.getJSONArray("Members");
            for(int n = 0; n < membersArray.length(); n++)
            {
                members.add(new Membership(membersArray.getJSONObject(n)));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public Clan(Game game, int myRank, ArrayList<Membership> members,String clanName,
                String welcomeMessage, ArrayList<WallShout> shouts, UUID clanID) {
        this.game = game;
        this.myRank = myRank;
        this.members = members;
        this.clanName = clanName;
        this.welcomeMessage = welcomeMessage;
        this.shouts = shouts;
        this.clanID = clanID;
    }

    public ArrayList<WallShout> getShoutsFromJSON(JSONArray wallShouts){
        if(wallShouts != null){
        ArrayList<WallShout> returnShouts = new ArrayList<>();
            try {
                for(int i = 0;i<wallShouts.length();i++){
                    returnShouts.add(new WallShout(wallShouts.getJSONObject(i)));
                }
            }catch (JSONException e){
                e.printStackTrace();
            }

            return returnShouts;
        }
        return  null;

    }

    public ArrayList<Membership> getMembersFromJSON(JSONArray members){
        if(members != null){
            ArrayList<Membership> returnMembers = new ArrayList<>();
            try{
                for(int i =0;i<members.length();i++){
                    returnMembers.add(new Membership(members.getJSONObject(i)));
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public ArrayList<Appointment> getAppointmentsFromJSON(JSONArray clanAppointments){
        if(clanAppointments != null){
            try {
                ArrayList<Appointment> returnAppointments = new ArrayList<>();
                for(int i =0;i>clanAppointments.length();i++){
                    returnAppointments.add(new Appointment(clanAppointments.getJSONObject(i)));
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }


        return null;
    }



    public boolean createNewClan(Game game,String newClanName){
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
        if(myRank > 6){
            HTTPLogic httpLogic=new HTTPLogic();
        }
        return isSuccess;
    }


    // need admin rights check for this to happen
    public boolean kickMember(String username){
        boolean isSuccess=false;
        if(myRank > 6){
            HTTPLogic httpLogic=new HTTPLogic();
        }
        return isSuccess;
    }

    // need admin rights check for this to happen
    public boolean makeNewAppointment() {
        boolean isSuccess=false;
        if(myRank > 6){
            HTTPLogic httpLogic=new HTTPLogic();
        }
        return isSuccess;
    }

    // need admin rights check for this to happen
    public boolean giveAdmin(String newAdminUser) {
        boolean isSuccess=false;
        if(myRank > 6){
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

    public ArrayList<Membership> getMembers() {
        return members;
    }

    public String getClanName() {
        return clanName;
    }

    public void setClanName(String clanName) {
        this.clanName = clanName;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
