package dk.nicolajpedersen.raidaid.Logic;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import dk.nicolajpedersen.raidaid.Data.Clan;
import dk.nicolajpedersen.raidaid.R;

/**
 * Created by Nicolaj on 15-04-2015.
 */
public class ClanArrayAdapter extends ArrayAdapter<Clan> {

    private final ArrayList<Clan> clans;


    public ClanArrayAdapter(Context context, ArrayList<Clan> clans) {
        super(context, R.layout.list_element_clan, clans);
        this.clans = clans;
    }
}
