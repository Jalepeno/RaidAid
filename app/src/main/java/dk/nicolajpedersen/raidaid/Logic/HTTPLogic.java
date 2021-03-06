package dk.nicolajpedersen.raidaid.Logic;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.UUID;

import dk.nicolajpedersen.raidaid.Data.Appointment;
import dk.nicolajpedersen.raidaid.Data.Clan;
import dk.nicolajpedersen.raidaid.Data.Friend;
import dk.nicolajpedersen.raidaid.Data.Game;
import dk.nicolajpedersen.raidaid.Data.Membership;
import dk.nicolajpedersen.raidaid.Data.Profile;
import dk.nicolajpedersen.raidaid.Data.User;
import dk.nicolajpedersen.raidaid.Data.WallShout;

/**
 * Created by Nicolaj on 13-03-2015.
 */

/**
 * Save HTTP Call files instead of setting up local database.
 */


public class HTTPLogic {



    public HTTPLogic(){

    }
    public static AsyncHttpClient getClient(){
        AsyncHttpClient client  = new AsyncHttpClient();
        return client;
    }

    public static int getProfileByLogin(final String username, final String password, final Context context){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("Usename", username);
        params.put("Password", password);
        final int[] isSuccess = new int[1];

        client.addHeader("Content-Type","application/json");

        try{
            JSONObject loginModel = new JSONObject();

            loginModel.put("Username",username);
            loginModel.put("Password",password);

            StringEntity entity = new StringEntity(loginModel.toString());
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

            client.post(context, getLoginUrl(), entity, "application/json", new JsonHttpResponseHandler() {

                // When the response returned by REST has Http response code '200'
                @Override
                public void onSuccess(int i, Header[] headers, JSONObject response) {
                    Profile profile = new Profile(response);
                    if (profile.userID.equals(UUID.fromString("00000000-0000-0000-0000-000000000000"))) {
                        // login failed

                        isSuccess[0] = 2;
                    } else {
                        // login success
                        //upon success save credentials in sharedPreferences.
                        isSuccess[0] = 1;

                        // start getting extra information once login has been confirmed.
                        getClansByLogin(username, password);
                        getFriendsByLogin(username, password);

                    }
                }

                @Override
                public void onFailure(int statusCode,
                                      Header[] headers,
                                      Throwable throwable,
                                      JSONObject errorResponse) {

//                    login failed
                    isSuccess[0] = 2;
                    throwable.printStackTrace();
                }

            });

        } catch (JSONException e) {
            e.printStackTrace();
            isSuccess[0] = 0;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            isSuccess[0] = 0;
        }

    return isSuccess[0];
    }


    public static void getClansByLogin(String username, String password){
        AsyncHttpClient client = getLogInClient(username,password);

        try{
            client.get(getClanUrl(), new JsonHttpResponseHandler() {
                // When the response returned by REST has Http response code '200'
                @Override
                public void onSuccess(int i, Header[] headers, JSONArray response) {

                    for (int c = 0; c < response.length(); c++) {
                        try {
                            Profile.myClans.add(new Clan((JSONObject) response.get(c)));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    for (Clan c : Profile.myClans) {
                        System.out.println(c.getClanName());
                    }
                }

                @Override
                public void onFailure(int statusCode,
                                      Header[] headers,
                                      Throwable throwable,
                                      JSONObject errorResponse) {
//                    login failed
                    throwable.printStackTrace();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void getFriendsByLogin(String username, String password){
        AsyncHttpClient client  = getLogInClient(username,password);

        try{
            client.get(getProfileFriendsUrl(),new JsonHttpResponseHandler(){
                // When the response returned by REST has Http response code '200'
                @Override
                public void onSuccess(int i, Header[] headers, JSONArray response) {
                    Profile.myFriends.clear();
                    for (int u = 0; u < response.length(); u++) {
                        try {

                            Profile.myFriends.add(new Friend((JSONObject) response.get(u)));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    for (User u : Profile.myFriends) {
                        System.out.println(u.getUserName());
                    }
                }

                @Override
                public void onFailure(int statusCode,
                                      Header[] headers,
                                      Throwable throwable,
                                      JSONObject errorResponse) {
//                    login failed
                    throwable.printStackTrace();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private static AsyncHttpClient getLogInClient(String username,String password){
        AsyncHttpClient client  = new AsyncHttpClient();
        String auth = username+":"+password;
        String encodedAuth = Base64.encodeToString(auth.getBytes(),Base64.DEFAULT);

        client.addHeader("Authorization","Basic "+encodedAuth);
        client.addHeader("Content-Type", "application/json");
        return client;
    }

    public void sendShout(UUID clanID, String text) {
    }

    public void getClanShouts(UUID clanID) {

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

    public int postSignup() {
        return 1;
    }

    public static int leaveClan(UUID clanID) {
        return 1;
    }


    public static String getLoginUrl() {
        return "http://nicolajpedersen.dk/api/persons/login";
    }
    public static String getClanUrl() {
        return "http://nicolajpedersen.dk/api/clans";
    }
    public static String getProfileFriendsUrl() {
        return "http://nicolajpedersen.dk/api/persons";
    }
    public static String getEventUrl(){return "http://nicolajpedersen.dk/events";}








    public static int getDummyProfile(String username, String password, Context context){
        int success = 0;
        Profile.username = username;
        Profile.password = password;
        Profile.userID = UUID.randomUUID();
        SharedPreferences.Editor editor = context.getSharedPreferences("RaidAidPrefs",Context.MODE_PRIVATE).edit();
        editor.putString("Username", username);
        editor.putString("UserID", Profile.userID.toString());
        editor.putString("UserPassword", password);
        editor.commit();


        getDummyClans();
        getDummyFriends();
        getDummyAppointments();

        System.out.println("Profile completed");
        success =1;
        return success;
    }

    private static void getDummyAppointments() {
        if(Profile.myAppointments != null){
            Profile.myAppointments.clear();
        }

        JSONObject appont1 = makeDummyAppointment(2,"MC Raid","Its time for some more molten core.. dips on thunderfury");
        Profile.myAppointments.add(new Appointment(appont1));
        Profile.myAppointments.add(new Appointment(makeDummyAppointment(2,"MC Raid","Its time for some more molten core.. dips on thunderfury")));
        Profile.myAppointments.add(new Appointment(makeDummyAppointment(2,"MC Raid","Its time for some more molten core.. dips on thunderfury")));
        Profile.myAppointments.add(new Appointment(makeDummyAppointment(2,"MC Raid","Its time for some more molten core.. dips on thunderfury")));
        Profile.myAppointments.add(new Appointment(makeDummyAppointment(2,"Black Rock Spire","epic loot is under way.. please dont jenkins this up")));
        Profile.myAppointments.add(new Appointment(makeDummyAppointment(0,"PCW vs Abehatterne","er i klar til at kaste med bananer?")));
        Profile.myAppointments.add(new Appointment(makeDummyAppointment(3,"Epic Lan party","it aint over untill a mom brings the toilet to you")));
        Profile.myAppointments.add(new Appointment(makeDummyAppointment(1,"some turnament","yay guys.. we shall win this one")));
        Profile.myAppointments.add(new Appointment(makeDummyAppointment(2, "more turnaments", "please refrain all things from carrot")));
        Profile.myAppointments.add(new Appointment(makeDummyAppointment(3, "StarCraft turnament", "All AMPs should be over 9000!!!!")));
        Profile.myAppointments.add(new Appointment(makeDummyAppointment(0, "MC Raid", "Its time for some more molten core.. dips on thunderfury")));
        Profile.myAppointments.add(new Appointment(makeDummyAppointment(1, "MC Raid", "Its time for some more molten core.. dips on thunderfury")));
        Profile.myAppointments.add(new Appointment(makeDummyAppointment(2, "Alterac Marathon", "the ultimate pvp day is here")));
        Profile.myAppointments.add(new Appointment(makeDummyAppointment(1, "Arathi Basin", "Grand marshal status incomming for us all!")));

        Collections.sort(Profile.myAppointments, new AppointmentComparator());


    }
    private static JSONObject makeDummyAppointment(int clan, String eventName,String evenDesript){

        JSONObject returnAppointment = new JSONObject();
        long offsetday = ((long)1000*60*60*24*((long)(Math.random()*28.0)));
        long offsetHour = ((long)1000*60*60*((long)(Math.random()*24.0)));
        long offsetMinute = ((long)1000*60*60*((long)(Math.random()*60.0)));


        try {
            returnAppointment.put("Date", System.currentTimeMillis()+offsetday+offsetHour+offsetMinute);
            System.out.println();
            System.out.println("Clan: \"" + Profile.myClans.get(clan).getClanName() + " \"has been selected");
            System.out.println("Clan id: "+Profile.myClans.get(clan).getClanID().toString());

            returnAppointment.put("ClanID", Profile.myClans.get(clan).getClanID().toString());
            returnAppointment.put("Headline",eventName);
            returnAppointment.put("Description",evenDesript);
            JSONArray invitedUsers = new JSONArray();
            for(Membership m : Profile.myClans.get(clan).getMembers()){
                JSONObject invGuy = new JSONObject();
                invGuy.put("Username",m.getUserName());
                invGuy.put("UserID",m.getUserID());
                invGuy.put("IsReady",getRandomBoolean());
                invitedUsers.put(invGuy);
            }
            returnAppointment.put("Invited",invitedUsers);



        } catch (JSONException e) {
            e.printStackTrace();
        }
        return returnAppointment;
    }

    private static void getDummyFriends() {
        String[] friends = new String[]{
                "Bly","Happy","KingKong","Arthur","Prebs","soul","RiSK","MaFia","WhiteRa",
                "Demuslim","MarieKingPrime","Bunny","MMA","herO","Dark","Maru","Zest","Life",
                "Hydra","Rain","Byul","Naniwa","polt","Day9","Artosis","Totalbiscuit","Tasteless",
                "Rotterdam"};

        if(Profile.myFriends != null){
            Profile.myFriends.clear();
        }


        for(String s:friends){
            Friend friend = new Friend();
            friend.setUserName(s);
            friend.setUserID(UUID.randomUUID());
            Profile.myFriends.add(friend);
        }

    }

    private static void getDummyClans() {

        String[] clan1Members = new String[]{"robert","Gator","Pipsqueach","Bawser","Smeeth","Clayn"};
        Clan clan1 = new Clan(makeDummyClans("TestClan1",clan1Members));
        String[] clan2Members = new String[]{"MyMan","tractorGuy","MarineKing","BeetleJ","swiggity",
                "Lorem","ipsum","dolor","sitAmet","consecateur","Jalepeno","Icemoon","MadFace","CheasyBastard",
                "PringlesMaster","Hefeisters","Mailman","devined","SwagManJohn"};
        Clan clan2 = new Clan(makeDummyClans("ROBO people",clan2Members));
        String[] clan3Members = new String[]{"ChineseMan","GawdZilla","Sneglzilla","KiksMaster","SwaggaJay","Horse"};
        Clan clan3 = new Clan(makeDummyClans("Fizzle Masters",clan3Members));
        String[] clan4Members = new String[]{"BirthdayBoy","Jump-a-tron","YoloSwagMan","HipsterMaster","LumberJohn","MountainDewster","Frazzlejazz"};
        Clan clan4 = new Clan(makeDummyClans("Blackrock Raiders",clan4Members));

        if(Profile.myClans != null){
            Profile.myClans.clear();
        }

        Profile.myClans.add(clan1);
        Profile.myClans.add(clan2);
        Profile.myClans.add(clan3);
        Profile.myClans.add(clan4);

        System.out.println("Clans added");

    }

    private static JSONObject makeDummyClans(String clanName,String[] members){
        JSONObject firstClan = new JSONObject();
        String clan1ID=UUID.randomUUID().toString();
        JSONArray clan1Members = new JSONArray();


        try {
            firstClan.put("Game",(int)((float)Math.random()*(float)8)+1);
            int myRank =((int)((float)Math.random()*5.2))+1;
            firstClan.put("MyRank",myRank);
            firstClan.put("ClanID",clan1ID);
            firstClan.put("MessageOfDay","Hello "+clanName+"\nYour rank is "+myRank);
            firstClan.put("ClanName",clanName);

            for(String s:members){
                JSONObject clanMember = new JSONObject();
                clanMember.put("Username",s);
                clanMember.put("Rank",((int)((float)Math.random()*5.2))+1);
                clanMember.put("ClanID",clan1ID);
                clanMember.put("UserID",UUID.randomUUID().toString());
                clan1Members.put(clanMember);
            }

            // adding dummyprofile to clan
            JSONObject profileMembership = new JSONObject();
            profileMembership.put("Username", Profile.username);
            profileMembership.put("Rank", myRank);
            profileMembership.put("ClanID", clan1ID);
            profileMembership.put("UserID", Profile.userID);
            clan1Members.put(profileMembership);

            firstClan.put("Members",clan1Members);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return firstClan;
    }

    private static Random rnd = new Random();

    public static boolean getRandomBoolean() {
        return rnd.nextBoolean();
    }


    public static int promoteMember(UUID clanID, UUID userID, int newRank) {

        return 1;
    }
}

