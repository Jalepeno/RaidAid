package dk.nicolajpedersen.raidaid.Data;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Nicolaj on 13-03-2015.
 */
public class Appointment implements Comparator<Appointment>{
    private Date date;
    private Game game;
    private Clan clan;
    private ArrayList<User> readyppl;
    private String headline,Description;


    public Appointment (JSONObject jsonAppoint){

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public ArrayList<User> getReadyppl() {
        return readyppl;
    }

    public void setReadyppl(ArrayList<User> readyppl) {
        this.readyppl = readyppl;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public int compare(Appointment lhs, Appointment rhs) {
        return lhs.getDate().compareTo(rhs.getDate());
    }
}
