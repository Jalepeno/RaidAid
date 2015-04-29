package dk.nicolajpedersen.raidaid.ViewpageFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.ListView;
import android.widget.TextView;

import dk.nicolajpedersen.raidaid.Data.Profile;
import dk.nicolajpedersen.raidaid.Logic.ClanArrayAdapter;
import dk.nicolajpedersen.raidaid.R;

/**
 * Created by Nicolaj on 13-03-2015.
 */
public class ClanListFragment extends Fragment {
    ClanArrayAdapter clanAA;

    public ClanListFragment(){
        clanAA = new ClanArrayAdapter(getActivity(),Profile.myClans);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ListView clanList = new ListView(getActivity());
        clanList.setAdapter(clanAA);
        View view = inflater.inflate(R.layout.list_element_clan, container, false);


        return view;
    }
}

