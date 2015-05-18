package dk.nicolajpedersen.raidaid.Logic;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import dk.nicolajpedersen.raidaid.R;

/**
 * Created by Nicolaj on 18-05-2015.
 */
public class DialogPromoteRankAdapter extends ArrayAdapter<Integer> {
    private Context context;
    private ArrayList<Integer> ranks;

    public DialogPromoteRankAdapter(Context context,ArrayList<Integer> ranks, int myRank) {
        super(context, R.layout.spinner_element_promote_member, ranks);
        this.context = context;
        this.ranks = ranks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.spinner_element_promote_member, parent, false);

        TextView memberName = (TextView) rowView.findViewById(R.id.promoteMemberName);
        memberName.setText(ranks.get(position).toString());

        ImageView currentRank = (ImageView) rowView.findViewById(R.id.currentRank);
        switch (position+1){
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

    private class Rank{
        int rank;
        Drawable rankImg;

        public Rank(int rank, Drawable rankImg) {
            this.rank = rank;
            this.rankImg = rankImg;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public Drawable getRankImg() {
            return rankImg;
        }

        public void setRankImg(Drawable rankImg) {
            this.rankImg = rankImg;
        }
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
        TextView memberRank = (TextView) convertView.findViewById(R.id.promoteMemberName);
        memberRank.setText(""+(position+1));

        ImageView currentRank = (ImageView) convertView.findViewById(R.id.currentRank);
        switch ((position+1)){
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
