package dk.nicolajpedersen.raidaid.Logic;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;

import java.io.IOException;
import java.util.ArrayList;

import dk.nicolajpedersen.raidaid.Activities.ClanPageActivity;
import dk.nicolajpedersen.raidaid.Data.Clan;
import dk.nicolajpedersen.raidaid.Data.Game;
import dk.nicolajpedersen.raidaid.R;

/**
 * Created by Nicolaj on 15-04-2015.
 */
public class ClanArrayAdapter extends ArrayAdapter<Clan> {

    private ArrayList<Clan> clans;
    private Context context;


    public ClanArrayAdapter(Context context, ArrayList<Clan> clans) {
        super(context, R.layout.list_element_clan, clans);
        this.clans = clans;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_element_clan, parent, false);

        final Clan thisClan = clans.get(position);
        TextView clanName = (TextView) rowView.findViewById(R.id.listClanName);
        TextView members = (TextView) rowView.findViewById(R.id.tvClanListMemberCount);
        ImageView gameImg = (ImageView) rowView.findViewById(R.id.gameImageClanList);
        clanName.setText(thisClan.getClanName());
        members.setText("members: "+thisClan.getMembers().size());
        Game thisClanGame = thisClan.getGame();

        LinearLayout background = (LinearLayout) rowView.findViewById(R.id.outerLayout);




        switch (thisClanGame){
            case COUNTERSTRIKE:
                background.setBackgroundResource(R.drawable.background_cs); //R.drawable. - some couterstrike image
                gameImg.setImageResource(R.drawable.logo_cs);
                break;
            case DIABLO:
                background.setBackgroundResource(R.drawable.background_diablo); //R.drawable. - some diablo image
                gameImg.setImageResource(R.drawable.logo_diablo);
                break;
            case DOTA2:
                background.setBackgroundResource(R.drawable.background_dota); //R.drawable. - some dota2 image
                gameImg.setImageResource(R.drawable.logo_dota);
                break;
            case LEAGUEOFLEGENDS:
                background.setBackgroundResource(R.drawable.background_lol); //R.drawable. - some lol image
                gameImg.setImageResource(R.drawable.logo_lol);
                break;
            case STARCRAFT:
                background.setBackgroundResource(R.drawable.background_sc); //R.drawable. - some starcraft image
                gameImg.setImageResource(R.drawable.logo_starcraft);
                break;
            case WORLDOFWARCRAFTALLIANCE:
                background.setBackgroundResource(R.drawable.background_alliance); //R.drawable. - some wow alliance image
                gameImg.setImageResource(R.drawable.logo_wow);
                break;
            case WORLDOFWARCRAFTHORDE:
                    background.setBackgroundResource(R.drawable.background_horde); //R.drawable. - some wow horde image
                gameImg.setImageResource(R.drawable.logo_wow);
                break;
            case HEROESOFTHESTORM:
                    background.setBackgroundResource(R.drawable.background_hots); //R.drawable. - some wow horde image
                gameImg.setImageResource(R.drawable.logo_hots);
                break;
            default:
                    background.setBackgroundColor(Color.DKGRAY);
                break;
        }

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, ClanPageActivity.class);
                i.putExtra("clanNr",position);
                context.startActivity(i);
            }
        });



        return rowView;

    }

}
