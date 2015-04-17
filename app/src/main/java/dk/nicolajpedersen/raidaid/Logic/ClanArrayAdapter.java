package dk.nicolajpedersen.raidaid.Logic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dk.nicolajpedersen.raidaid.Data.Clan;
import dk.nicolajpedersen.raidaid.Data.Game;
import dk.nicolajpedersen.raidaid.Data.Guild;
import dk.nicolajpedersen.raidaid.R;

/**
 * Created by Nicolaj on 15-04-2015.
 */
public class ClanArrayAdapter extends ArrayAdapter<Clan> {

    private final ArrayList<Clan> clans;
    private final Context context;


    public ClanArrayAdapter(Context context, ArrayList<Clan> clans) {
        super(context, R.layout.list_element_clan, clans);
        this.clans = clans;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_element_clan, parent, false);

        Clan thisClan = clans.get(position);
        TextView clanName = (TextView) rowView.findViewById(R.id.listClanName);
        TextView members = (TextView) rowView.findViewById(R.id.tvListMembers);
        clanName.setText(thisClan.getClanName());
        members.setText("members: "+thisClan.getMembers().size());
        Game thisClanGame = thisClan.getGame();
        LinearLayout background = (LinearLayout) rowView.findViewById(R.id.outerLayout);
        switch (thisClanGame){
            case COUNTERSTRIKE:
                background.setBackground(); //R.drawable. - some couterstringe image
                break;
            case DIABLO:
                background.setBackground(); //R.drawable. - some diablo image
                break;
            case DOTA2:
                background.setBackground(); //R.drawable. - some dota2 image
                break;
            case LEAGUEOFLEGENDS:
                background.setBackground(); //R.drawable. - some lol image
                break;
            case STARCRAFT:
                background.setBackground(); //R.drawable. - some starcraft image
                break;
            case WORLDOFWARCRAFT:
                Guild faction = (Guild) thisClan;
                if(faction.getFaction() == Guild.Faction.ALLIANCE) {
                    background.setBackground(); //R.drawable. - some wow Alliance image
                }else{
                    background.setBackground(); //R.drawable. - some wow Horde image
                }
                break;
            default:
                break;
        }

        return rowView;

    }
}
