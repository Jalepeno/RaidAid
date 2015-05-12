package dk.nicolajpedersen.raidaid.ViewpageFragments;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.ListView;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonFloat;

import dk.nicolajpedersen.raidaid.Data.Profile;
import dk.nicolajpedersen.raidaid.Logic.ClanArrayAdapter;
import dk.nicolajpedersen.raidaid.R;

/**
 * Created by Nicolaj on 13-03-2015.
 */
public class ClanListFragment extends Fragment implements View.OnClickListener{
    ClanArrayAdapter clanAA;
    ButtonFloat btnFloat;
    boolean timerFinished;

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clanlist, container, false);
        ListView clanList = (ListView) view.findViewById(R.id.clanListview);
        clanAA = new ClanArrayAdapter(getActivity(),Profile.myClans);
        clanList.setAdapter(clanAA);
        btnFloat = (ButtonFloat) view.findViewById(R.id.buttonFloatListClanPage);
        btnFloat.setBackgroundColor(Color.parseColor("#FF9800"));
        btnFloat.setOnClickListener(this);
        timerFinished =false;

        return view;
    }

    @Override
    public void onClick(View v) {

        if(v == btnFloat){


        }
    }
}

