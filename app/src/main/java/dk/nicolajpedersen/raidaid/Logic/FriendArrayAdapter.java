package dk.nicolajpedersen.raidaid.Logic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import dk.nicolajpedersen.raidaid.Data.Friend;
import dk.nicolajpedersen.raidaid.Data.User;
import dk.nicolajpedersen.raidaid.R;
import dk.nicolajpedersen.raidaid.ViewpageFragments.FriendListFragment;

/**
 * Created by Nicolaj on 05-05-2015.
 */
public class FriendArrayAdapter extends ArrayAdapter<Friend> {

    private final ArrayList<Friend> friends;
    private final Context context;


    public FriendArrayAdapter(Context context, ArrayList<Friend> friends) {
        super(context, R.layout.list_element_friend, friends);
        this.friends = friends;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_element_friend, parent, false);

        TextView friendsName = (TextView) rowView.findViewById(R.id.listEle_friendName);
        friendsName.setText(friends.get(position).getUserName());

        CheckBox isInvited = (CheckBox) rowView.findViewById(R.id.listEle_friendInviteBox);
        isInvited.setChecked(friends.get(position).isInvited());
        isInvited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                friends.get(position).setIsInvited(!friends.get(position).isInvited());
                if (!friends.get(position).isInvited()) {
                    FriendListFragment.cbMarkAll.setChecked(false);
                }
            }
        });

        return rowView;
    }
}
