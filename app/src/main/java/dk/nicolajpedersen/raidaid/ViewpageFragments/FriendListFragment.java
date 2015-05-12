package dk.nicolajpedersen.raidaid.ViewpageFragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gc.materialdesign.views.ButtonFloat;

import dk.nicolajpedersen.raidaid.Data.Profile;
import dk.nicolajpedersen.raidaid.Logic.ClanArrayAdapter;
import dk.nicolajpedersen.raidaid.Logic.FriendArrayAdapter;
import dk.nicolajpedersen.raidaid.R;

/**
 * Created by Nicolaj on 13-03-2015.
 */
public class FriendListFragment extends Fragment implements View.OnClickListener{
    private FriendArrayAdapter friendsAA;
    private ButtonFloat btnFloat;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friendslist, container, false);
        ListView clanList = (ListView) view.findViewById(R.id.friendListView);
        friendsAA = new FriendArrayAdapter(getActivity(), Profile.myFriends);
        clanList.setAdapter(friendsAA);


        btnFloat = (ButtonFloat) view.findViewById(R.id.buttonFloatListFriends);
        btnFloat.setBackgroundColor(Color.parseColor("#FF9800"));
        btnFloat.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View v) {

    }
}
