package dk.nicolajpedersen.raidaid.Data;

/**
 * Created by Nicolaj on 13-03-2015.
 */
public enum Game {
    COUNTERSTRIKE, DIABLO, DOTA2, LEAGUEOFLEGENDS, STARCRAFT,
    WORLDOFWARCRAFTHORDE,WORLDOFWARCRAFTALLIANCE, HEROESOFTHESTORM;

    public static Game fromInteger(int x) {
        switch(x) {
            case 0:
                return COUNTERSTRIKE;
            case 1:
                return DIABLO;
            case 2:
                return DOTA2;
            case 3:
                return LEAGUEOFLEGENDS;
            case 4:
                return STARCRAFT;
            case 5:
                return WORLDOFWARCRAFTHORDE;
            case 6:
                return WORLDOFWARCRAFTALLIANCE;
            case 7:
                return HEROESOFTHESTORM;

        }
        return null;
    }
}
