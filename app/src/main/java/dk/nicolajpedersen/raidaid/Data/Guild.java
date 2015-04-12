package dk.nicolajpedersen.raidaid.Data;

import org.json.JSONObject;

import dk.nicolajpedersen.raidaid.Logic.HTTPLogic;

/**
 * Created by Nicolaj on 08-04-2015.
 */
public class Guild extends Clan {
    Faction faction;


    public Guild(JSONObject newClan) {
        super(newClan);
    }


    public boolean createNewClan(Game game,String newClanName, String setPassword, Faction setFaction){
        HTTPLogic httpLogic=new HTTPLogic();
        boolean isSuccess=false;
        return isSuccess;
    }


    private enum Faction{
        ALLIANCE,HORDE
    }

}
