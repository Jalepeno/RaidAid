package dk.nicolajpedersen.raidaid.ViewpageFragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;

import com.gc.materialdesign.views.ButtonFloat;

import dk.nicolajpedersen.raidaid.Data.Friend;
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
    private ListView clanList;
    public static CheckBox cbMarkAll;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friendslist, container, false);
        clanList = (ListView) view.findViewById(R.id.friendListView);
        friendsAA = new FriendArrayAdapter(getActivity(), Profile.myFriends);
        clanList.setAdapter(friendsAA);

        cbMarkAll = (CheckBox) view.findViewById(R.id.markAllFriends);
        cbMarkAll.setChecked(idAllFriendsChecked());
        cbMarkAll.setOnClickListener(this);

        btnFloat = (ButtonFloat) view.findViewById(R.id.buttonFloatListFriends);
        btnFloat.setBackgroundColor(Color.parseColor("#FF9800"));
        btnFloat.setOnClickListener(this);

        return view;

    }

    private boolean idAllFriendsChecked() {
        for(Friend f : Profile.myFriends){
            if(!f.isInvited()){
                return false;
            }
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if(v == cbMarkAll){
            for(Friend f : Profile.myFriends){
                f.setIsInvited(cbMarkAll.isChecked());
            }
            // updates the listview
            friendsAA.notifyDataSetChanged();

        }else if(v == btnFloat){
            // make dialog here for invite message
        }

    }


}
