package dk.nicolajpedersen.raidaid.Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Nicolaj on 13-03-2015.
 */
public class Appointment{
    private Calendar date;
    private Clan clan;
    private ArrayList<Friend> inviteList;
    private String headline, description;
    private boolean amIReady,Expanded;


    public Appointment (JSONObject jsonAppoint){
        date = Calendar.getInstance();
        inviteList = new ArrayList<>();
        Expanded = false;

        try {
            date.setTimeInMillis(jsonAppoint.getLong("Date"));
            clan = Profile.getClanByID(UUID.fromString(jsonAppoint.getString("ClanID")));
            headline = jsonAppoint.getString("Headline");
            description = jsonAppoint.getString("Description");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        try{
            JSONArray readyList = jsonAppoint.getJSONArray("Invited");
            for(int i=0;i< readyList.length();i++){
                Friend f = new Friend(readyList.getJSONObject(i));
                inviteList.add(f);
                if(f.userID.contentEquals(Profile.userID)){
                    amIReady = f.isInvited();

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
            amIReady =false;
        }

    }

    public String getDateString(){
        String stringDate="";
        if(date.get(Calendar.DATE)<10){
            stringDate +="0"+date.get(Calendar.DATE);
        }else {
            stringDate +=+date.get(Calendar.DATE);
        }
        stringDate += "/";

        if((date.get(Calendar.MONTH)+1)<10){
            stringDate +="0"+(date.get(Calendar.MONTH)+1);
        }else {
            stringDate +=+(date.get(Calendar.MONTH)+1);
        }
        return stringDate;
    }

    public String getTimeSring(){
        String timeString ="";
        if(date.get(Calendar.HOUR_OF_DAY)<10){
            timeString +="0"+date.get(Calendar.HOUR_OF_DAY);
        }else {
            timeString +=+date.get(Calendar.HOUR_OF_DAY);
        }
        timeString += ".";

        if(date.get(Calendar.MINUTE)<10){
            timeString +="0"+date.get(Calendar.MINUTE);
        }else {
            timeString +=+date.get(Calendar.MINUTE);
        }
        return timeString;
    }

    public boolean isAmIReady() {
        return amIReady;
    }

    public void setAmIReady(boolean amIReady) {
        this.amIReady = amIReady;
    }

    public boolean isExpanded() {
        return Expanded;
    }

    public void setExpanded(boolean isExpanded) {
        this.Expanded = isExpanded;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }


    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public ArrayList<Friend> getInviteList() {
        return inviteList;
    }


    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMembersReady() {
        int returnInt = 0;
        for(Friend f : inviteList){
            if(f.isInvited()){
                returnInt++;
            }
        }
        return returnInt;
    }

    public Friend getMe(){
        for(Friend f :inviteList){
            if(f.userID.contentEquals(Profile.userID)){
                return f;
            }
        }
    return null;
    }
}
