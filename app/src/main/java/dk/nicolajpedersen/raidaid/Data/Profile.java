package dk.nicolajpedersen.raidaid.Data;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Nicolaj on 15-04-2015.
 */
public class Profile {
    public static ArrayList<Clan> myClans;
    public static ArrayList<Appointment> myAppointments;
    public static ArrayList<User> myFriends;
    public static String username, password;
    public static String userID;

    public Profile(JSONObject loginObject) {
        myAppointments = new ArrayList<Appointment>();
        myClans = new ArrayList<Clan>();
        myFriends = new ArrayList<User>();
        username = "";
        password = "";
        userID = UUID.randomUUID().toString();
    }

    public ArrayList<Clan> getMyClans() {
        return myClans;
    }

    public void setMyClans(ArrayList<Clan> myClans) {
        this.myClans = myClans;
    }

    public ArrayList<Appointment> getMyAppointments() {
        return myAppointments;
    }

    public void setMyAppointments(ArrayList<Appointment> myAppointments) {
        this.myAppointments = myAppointments;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
