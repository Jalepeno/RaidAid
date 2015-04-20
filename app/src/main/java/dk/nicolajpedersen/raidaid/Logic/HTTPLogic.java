package dk.nicolajpedersen.raidaid.Logic;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;

import java.util.ArrayList;
import java.util.UUID;

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

    public int getProfile(String username,String password){
        getClanList();
        getFriendList();
        getFriendRequests();
        getClanInvites();
        return 1;
    }
    public int getClan(){
        return 1;
    }

    public int getClanList() {
        getDummyClans();
        return 1;

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

    public int postSignup() { return 1;
    }

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
}
