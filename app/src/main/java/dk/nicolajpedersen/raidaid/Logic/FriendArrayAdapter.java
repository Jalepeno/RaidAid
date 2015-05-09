package dk.nicolajpedersen.raidaid.Logic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import dk.nicolajpedersen.raidaid.Data.User;
import dk.nicolajpedersen.raidaid.R;

/**
 * Created by Nicolaj on 05-05-2015.
 */
public class FriendArrayAdapter extends ArrayAdapter<User> {

    private final ArrayList<User> friends;
    private final Context context;


    public FriendArrayAdapter(Context context, ArrayList<User> friends) {
        super(context, R.layout.fragment_friendslist, friends);
        this.friends = friends;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.fragment_friendslist, parent, false);


        return super.getView(position, convertView, parent);
    }
}
