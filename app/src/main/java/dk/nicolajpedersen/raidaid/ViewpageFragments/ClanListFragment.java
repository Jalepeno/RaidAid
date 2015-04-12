package dk.nicolajpedersen.raidaid.ViewpageFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.TextView;

import dk.nicolajpedersen.raidaid.R;

/**
 * Created by Nicolaj on 13-03-2015.
 */
public class ClanListFragment extends Fragment {
    // Store instance variables
    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static ClanListFragment newInstance(int page, String title) {
        ClanListFragment fragmentFirst = new ClanListFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        TextView tvLabel = (TextView) view.findViewById(R.id.tvLabel);

        tvLabel.setText(page + " -- " + title);
        return view;
    }
}
}
