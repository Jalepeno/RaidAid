package dk.nicolajpedersen.raidaid.Logic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import dk.nicolajpedersen.raidaid.Data.Membership;
import dk.nicolajpedersen.raidaid.R;

/**
 * Created by Nicolaj on 18-05-2015.
 */
public class DialogPromoteMemberAdapter extends ArrayAdapter<Membership> {
    private ArrayList<Membership> members;
    private Context context;

    public DialogPromoteMemberAdapter(Context context, ArrayList<Membership> members) {
        super(context, R.layout.spinner_element_promote_member, members);
        this.members = members;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.spinner_element_promote_member, parent, false);
        Membership thisMember = members.get(position);
        TextView memberName = (TextView) rowView.findViewById(R.id.promoteMemberName);
        memberName.setText(thisMember.getUserName());

        ImageView currentRank = (ImageView) rowView.findViewById(R.id.currentRank);
        switch (thisMember.getRank()){
            case 1: currentRank.setImageResource(R.drawable.rank1);
                break;
            case 2: currentRank.setImageResource(R.drawable.rank2);
                break;
            case 3: currentRank.setImageResource(R.drawable.rank3);
                break;
            case 4: currentRank.setImageResource(R.drawable.rank4);
                break;
            case 5: currentRank.setImageResource(R.drawable.rank5);
                break;
            case 6: currentRank.setImageResource(R.drawable.rank6);
                break;
            default: currentRank.setImageResource(R.drawable.rank0);
                break;
        }

        return rowView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return initView(position,convertView);
    }

    private View initView(int position, View convertView) {
        if(convertView == null){
            convertView = View.inflate(getContext(),
                    R.layout.spinner_element_promote_member,
                    null);
        }
        Membership thisMember = members.get(position);
        TextView memberName = (TextView) convertView.findViewById(R.id.promoteMemberName);
        memberName.setText(thisMember.getUserName());

        ImageView currentRank = (ImageView) convertView.findViewById(R.id.currentRank);
        switch (thisMember.getRank()){
            case 1: currentRank.setImageResource(R.drawable.rank1);
                break;
            case 2: currentRank.setImageResource(R.drawable.rank2);
                break;
            case 3: currentRank.setImageResource(R.drawable.rank3);
                break;
            case 4: currentRank.setImageResource(R.drawable.rank4);
                break;
            case 5: currentRank.setImageResource(R.drawable.rank5);
                break;
            case 6: currentRank.setImageResource(R.drawable.rank6);
                break;
            default: currentRank.setImageResource(R.drawable.rank0);
                break;
        }

        return convertView;
    }
}
