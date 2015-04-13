package dk.nicolajpedersen.raidaid.Logic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import dk.nicolajpedersen.raidaid.Data.Clan;
import dk.nicolajpedersen.raidaid.Data.User;
import dk.nicolajpedersen.raidaid.R;

/**
 * Created by Nicolaj on 13-04-2015.
 */
public class ShoutArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;
    private final Clan clan;

    public ShoutArrayAdapter(Context context, String[] values, Clan clan) {
        super(context, R.layout.list_element_shout, values);
        this.context = context;
        this.values = values;
        this.clan = clan;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_element_shout, parent, false);
        TextView tvMessage = (TextView) rowView.findViewById(R.id.tvShoutMessage);
        TextView tvName = (TextView) rowView.findViewById(R.id.tvShoutName);
        ImageView imgRank = (ImageView) rowView.findViewById(R.id.imgShoutRank);
        tvName.setText(values[position]);

        // Change icon based on name
        String s = values[position];
        User thisGuy = clan.findMember(s);

        if(thisGuy != null) {

            System.out.println(thisGuy.getUserName());
            switch (thisGuy.getMemberRank()){
                case 1: imgRank.setImageResource(R.drawable.rank1);
                        break;
                case 2: imgRank.setImageResource(R.drawable.rank2);
                        break;
                case 3: imgRank.setImageResource(R.drawable.rank3);
                        break;
                case 4: imgRank.setImageResource(R.drawable.rank4);
                        break;
                case 5: imgRank.setImageResource(R.drawable.rank5);
                        break;
                case 6: imgRank.setImageResource(R.drawable.rank6);
                        break;
                default: imgRank.setImageResource(R.drawable.rank0);
                        break;
            }

        }else{
            imgRank.setImageResource(R.drawable.rank0);
        }
        return rowView;
    }
}
