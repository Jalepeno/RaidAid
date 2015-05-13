package dk.nicolajpedersen.raidaid.Data;

/**
 * Created by Nicolaj on 13-03-2015.
 */
public enum Game {
    COUNTERSTRIKE, DIABLO, DOTA2, LEAGUEOFLEGENDS, STARCRAFT,
    WORLDOFWARCRAFTHORDE,WORLDOFWARCRAFTALLIANCE, HEROESOFTHESTORM;

    public static Game fromInteger(int x) {
        switch(x) {
            case 1:
                return COUNTERSTRIKE;
            case 2:
                return DIABLO;
            case 3:
                return DOTA2;
            case 4:
                return LEAGUEOFLEGENDS;
            case 5:
                return STARCRAFT;
            case 6:
                return HEROESOFTHESTORM;
            case 7:
                return WORLDOFWARCRAFTHORDE;
            case 8:
                return WORLDOFWARCRAFTALLIANCE;

            default:
                return null;

        }
    }

    public static int gameToInt(Game g){
        switch(g){
            case COUNTERSTRIKE:
                return 1;
            case DIABLO:
                return 2;
            case DOTA2:
                return 3;
            case LEAGUEOFLEGENDS:
                return 4;
            case STARCRAFT:
                return 5;
            case HEROESOFTHESTORM:
                return 6;
            case WORLDOFWARCRAFTALLIANCE:
                return 7;
            case WORLDOFWARCRAFTHORDE:
                return 8;
            default:
                return 0;

        }
    }
}
