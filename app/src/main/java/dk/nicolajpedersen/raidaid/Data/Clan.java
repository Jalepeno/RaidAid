package dk.nicolajpedersen.raidaid.Data;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Nicolaj on 13-03-2015.
 */
public class Clan {
    private Game game;
    private ArrayList<String> members;
    private ArrayList<Appointment> appointments;
    private String clanName,welcomeMessage;
    private ArrayList<WallShout> shouts;

    public Clan (JSONObject newClan){

    }
}
