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
import java.util.UUID;

import dk.nicolajpedersen.raidaid.Data.Appointment;
import dk.nicolajpedersen.raidaid.Data.Clan;
import dk.nicolajpedersen.raidaid.Data.Game;
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

    public int getProfileByLogin(final String username, final String password, final Context context){
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
            final ProgressDialog dialog = new ProgressDialog(context);
            dialog.setMessage("Logging you in..");
            dialog.show();

            client.post(context, getLoginUrl(), entity, "application/json", new JsonHttpResponseHandler() {

                // When the response returned by REST has Http response code '200'
                @Override
                public void onSuccess(int i, Header[] headers, JSONObject response) {
                    Profile profile = new Profile(response);
                    if (profile.userID.equals(UUID.fromString("00000000-0000-0000-0000-000000000000"))) {
                        // login failed
                        CharSequence text = "Login failed";
                        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                        toast.show();
                        isSuccess[0] = 0;
                    } else {
                        // login success
                        //upon success save credentials in sharedPreferences.
                        SharedPreferences.Editor editor = context.getSharedPreferences("RaidAidPrefs", Context.MODE_PRIVATE).edit();
                        editor.putString("Username", username);
                        editor.putString("UserID", profile.userID);
                        editor.putString("UserPassword", password);
                        editor.commit();
                        isSuccess[0] = 1;

                        // start getting extra information once login has been confirmed.
                        getClansByLogin(context, username, password);
                        getFriendsByLogin(context, username, password);

                    }
                    dialog.dismiss();
                }

                @Override
                public void onFailure(int statusCode,
                                      Header[] headers,
                                      Throwable throwable,
                                      JSONObject errorResponse) {

//                    login failed
                    isSuccess[0] = 0;
                    throwable.printStackTrace();
                    dialog.dismiss();
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


    public void getClansByLogin(Context context, String username,String password){
        AsyncHttpClient client = getLogInClient(username,password);

        try{
            client.get(context, getClanUrl(), new JsonHttpResponseHandler() {
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

    public  void getFriendsByLogin(Context context, String username, String password){
        AsyncHttpClient client  = getLogInClient(username,password);

        try{
            client.get(context,getProfileFriendsUrl(),new JsonHttpResponseHandler(){
                // When the response returned by REST has Http response code '200'
                @Override
                public void onSuccess(int i, Header[] headers, JSONArray response) {
                    Profile.myFriends.clear();
                    for (int u = 0; u < response.length(); u++) {
                        try {

                            Profile.myFriends.add(new User((JSONObject) response.get(u)));
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

    private AsyncHttpClient getLogInClient(String username,String password){
        AsyncHttpClient client  = new AsyncHttpClient();
        String auth = username+":"+password;
        String encodedAuth = Base64.encodeToString(auth.getBytes(),Base64.DEFAULT);

        client.addHeader("Authorization","Basic "+encodedAuth);
        client.addHeader("Content-Type", "application/json");
        return client;
    }

    public void sendShout(UUID clanID, String text) {
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
    private String getLoginUrl() {
        return "http://nicolajpedersen.dk/api/persons/login";
    }
    private String getClanUrl() {
        return "http://nicolajpedersen.dk/api/clans";
    }
    private String getProfileFriendsUrl() {
        return "http://nicolajpedersen.dk/api/persons";
    }
    private String getEventUrl(){return "http://nicolajpedersen.dk/events";}


    public void getDummyProfile(String username, String password, Context context){
        Profile.username = username;
        Profile.password = password;
        Profile.userID = UUID.randomUUID().toString();
        SharedPreferences.Editor editor = context.getSharedPreferences("RaidAidPrefs",Context.MODE_PRIVATE).edit();
        editor.putString("Username", username);
        editor.putString("UserID", Profile.userID);
        editor.putString("UserPassword", password);
        editor.commit();


        getDummyClans();
        getDummyFriends();
        getDummyAppointments();

        System.out.println("Profile completed");
        for(Clan c :Profile.myClans){
            System.out.println("clan: "+c.getClanName()+" exists");
        }
    }

    private void getDummyAppointments() {
        if(Profile.myAppointments != null){
            Profile.myAppointments.clear();
        }
        JSONObject appont1 = makeDummyAppointment(2,"MC Raid","Its time for some more molten core.. dips on thunderfury");
        Profile.myAppointments.add(new Appointment(appont1));
        Profile.myAppointments.add(new Appointment(makeDummyAppointment(2,"MC Raid","Its time for some more molten core.. dips on thunderfury")));
        Profile.myAppointments.add(new Appointment(makeDummyAppointment(2,"MC Raid","Its time for some more molten core.. dips on thunderfury")));
        Profile.myAppointments.add(new Appointment(makeDummyAppointment(2,"MC Raid","Its time for some more molten core.. dips on thunderfury")));
        Profile.myAppointments.add(new Appointment(makeDummyAppointment(2,"Black Rock Spire","epic loot is under way.. please dont jenkins this up")));
        Profile.myAppointments.add(new Appointment(makeDummyAppointment(3,"PWC vs Abehatterne","er i klar til at kaste med bananer?")));
        Profile.myAppointments.add(new Appointment(makeDummyAppointment(3,"Epic Lan party","it aint over untill a mom brings the toilet to you")));
        Profile.myAppointments.add(new Appointment(makeDummyAppointment(1,"some turnament","yay guys.. we shall win this one")));
        Profile.myAppointments.add(new Appointment(makeDummyAppointment(2,"more turnaments","please refrain all things from carrot")));
        Profile.myAppointments.add(new Appointment(makeDummyAppointment(3,"StarCraft turnament","All AMPs should be over 9000!!!!")));
        Profile.myAppointments.add(new Appointment(makeDummyAppointment(4,"MC Raid","Its time for some more molten core.. dips on thunderfury")));
        Profile.myAppointments.add(new Appointment(makeDummyAppointment(1,"MC Raid","Its time for some more molten core.. dips on thunderfury")));
        Profile.myAppointments.add(new Appointment(makeDummyAppointment(2,"Alterac Marathon","the ultimate pvp day is here")));
        Profile.myAppointments.add(new Appointment(makeDummyAppointment(1,"Arathi Basin", "Grand marshal status incomming for us all!")));

        Collections.sort(Profile.myAppointments, new AppointmentComparator());
        System.out.println("Appointments added");

    }
    private JSONObject makeDummyAppointment(int clan, String eventName,String evenDesript){
        JSONObject returnAppointment = new JSONObject();
        long offsetDays = (long) ((long)1000*60*60*24*(Math.random()*28));
        long offsetHours = (long) ((1000*60*60)*(Math.random()*24));
        long offsetMinutes = (long) ((1000*60)*(Math.random()*60));
        try {
            returnAppointment.put("Date", System.currentTimeMillis()+offsetMinutes+offsetHours+offsetDays);
            returnAppointment.put("ClanID",Profile.myClans.get(clan%Profile.myClans.size()).getClanID().toString());
            returnAppointment.put("Headline",eventName);
            returnAppointment.put("Description",evenDesript);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return returnAppointment;
    }

    private void getDummyFriends() {
        String[] friends = new String[]{
                "Bly","Happy","KingKong","Arthur","Prebs","soul","RiSK","MaFia","WhiteRa",
                "Demuslim","MarieKingPrime","Bunny","MMA","herO","Dark","Maru","Zest","Life",
                "Hydra","Rain","Byul","Naniwa","polt","Day9","Artosis","Totalbiscuit","Tasteless",
                "Rotterdam"};

        if(Profile.myFriends != null){
            Profile.myFriends.clear();
        }


        for(String s:friends){
            User friend = new User();
            friend.setUserName(s);
            friend.setUserID(UUID.randomUUID().toString());
            Profile.myFriends.add(friend);
        }

        System.out.println("Friends added");
    }

    private void getDummyClans() {

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

    private JSONObject makeDummyClans(String clanName,String[] members){
        JSONObject firstClan = new JSONObject();
        String clan1ID=UUID.randomUUID().toString();
        JSONArray clan1Members = new JSONArray();


        try {
            firstClan.put("Game",(int)((float)Math.random()*(float)8));
            firstClan.put("MyRank",((int)((float)Math.random()*5.2))+1);
            firstClan.put("ClanID",clan1ID);
            firstClan.put("MessageOfDay","Hello to test Clan1");
            firstClan.put("ClanName",clanName);

            for(String s:members){
                JSONObject clanMember = new JSONObject();
                clanMember.put("Username",s);
                clanMember.put("Rank",((int)((float)Math.random()*5.2))+1);
                clanMember.put("ClanID",clan1ID);
                clanMember.put("UserID",UUID.randomUUID().toString());
                clan1Members.put(clanMember);
            }

            firstClan.put("Members",clan1Members);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return firstClan;
    }


    /*
    public void getDummyClans() {
        if(Profile.myClans.size() == 0){
            ArrayList<User> membersClan1 = new ArrayList<User>();
            membersClan1.add(new User("Henning",2));
            membersClan1.add(new User("AwesomeJack",6));
            membersClan1.add(new User("FlackJack",1));
            membersClan1.add(new User("BlackJack",1));
            membersClan1.add(new User("GunJack",2));
            membersClan1.add(new User("PetJack",1));
            membersClan1.add(new User("SvendJack",3));
            membersClan1.add(new User("HiJack",3));
            membersClan1.add(new User("FlackJack",1));

            ArrayList<WallShout> shoutsClan1 = new ArrayList<WallShout>();
            shoutsClan1.add(new WallShout("last night was awesome",membersClan1.get(7)));
            shoutsClan1.add(new WallShout("thats what she said ;D",membersClan1.get(2)));
            shoutsClan1.add(new WallShout("Daayyyyym",membersClan1.get(4)));
            shoutsClan1.add(new WallShout("pwned",membersClan1.get(6)));

            UUID clanID = UUID.randomUUID();


            Clan clan1 = new Clan(Game.COUNTERSTRIKE,4,membersClan1,"The Jacks",
                    "welcome to the jacks! some very inspiring text that will make you wanna game more",
                    shoutsClan1 ,clanID);


            ArrayList<User> membersClan2 = new ArrayList<User>();
            membersClan2.add(new User("Preben",2));
            membersClan2.add(new User("Jakob",1));
            membersClan2.add(new User("Grete",3));
            membersClan2.add(new User("Henrik",3));
            membersClan2.add(new User("Ninjakillaah",6));

            ArrayList<WallShout> shoutsClan2 = new ArrayList<WallShout>();
            shoutsClan2.add(new WallShout("Es war einmal ein pudelhund",membersClan2.get(3)));
            shoutsClan2.add(new WallShout("...ein pudelhund",membersClan2.get(2)));
            shoutsClan2.add(new WallShout("..ein pudelhund",membersClan1.get(4)));
            shoutsClan2.add(new WallShout("Der war einmal ein pudelhund",membersClan1.get(1)));
            shoutsClan2.add(new WallShout("ein kleines pudelhund",membersClan1.get(1)));

            UUID clan2ID = UUID.randomUUID();


            Clan clan2 = new Clan(Game.DIABLO,1,membersClan2,"Rift Raiders",
                    "Dagens sang er Es war einmal ein pudelhund",
                    shoutsClan2 ,clan2ID);


            ArrayList<User> membersClan3 = new ArrayList<User>();
            membersClan3.add(new User("swaezy",2));
            membersClan3.add(new User("timo",1));
            membersClan3.add(new User("Kongo",3));
            membersClan3.add(new User("Lobotomizer",3));
            membersClan3.add(new User("Ninjakillaah",6));

            ArrayList<WallShout> shoutsClan3 = new ArrayList<WallShout>();


            UUID clan3ID = UUID.randomUUID();


            Clan clan3 = new Clan(Game.STARCRAFT,6,membersClan3,"Sejler folket",
                    "kan i heller ikke sove?",
                    shoutsClan3 ,clan3ID);

            Profile.myClans.add(clan1);
            Profile.myClans.add(clan2);
            Profile.myClans.add(clan3);
        }
    }
    */

}

