package dk.nicolajpedersen.raidaid.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.gc.materialdesign.views.ButtonFlat;

import java.util.ArrayList;
import java.util.Collections;

import dk.nicolajpedersen.raidaid.Data.Clan;
import dk.nicolajpedersen.raidaid.Data.Membership;
import dk.nicolajpedersen.raidaid.Logic.DialogPromoteMemberAdapter;
import dk.nicolajpedersen.raidaid.Logic.DialogPromoteRankAdapter;
import dk.nicolajpedersen.raidaid.Logic.HTTPLogic;
import dk.nicolajpedersen.raidaid.R;

/**
 * Created by Nicolaj on 18-05-2015.
 */
public class DialogPromoteMember extends DialogFragment implements AdapterView.OnItemSelectedListener {

    private Clan clan;
    private Membership chosenMember;
    private int chosenRank;

    public DialogPromoteMember() {
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.RaidAidDialog);
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View promptsView = inflater.inflate(R.layout.dialog_promote, null);

        final ArrayList<Membership> promotableMembers = findPromoteablemembers();

        //setting member spinner
        Spinner memberSpinner = (Spinner) promptsView.findViewById(R.id.promote_spinnerMember);
        DialogPromoteMemberAdapter memberAdapter = new DialogPromoteMemberAdapter(
                getActivity().getApplicationContext(),promotableMembers);
        memberAdapter.setDropDownViewResource(R.layout.spinner_element_promote_member);
        memberSpinner.setAdapter(memberAdapter);

        memberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                chosenMember = promotableMembers.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //setting Rank spinner -- limiting possibillity to rank to own level
        ArrayList<Integer> ranks = new ArrayList<>();
        for(int i=1;i <clan.getMyRank();i++){
            ranks.add(i);
        }
        Spinner RankSpinner = (Spinner) promptsView.findViewById(R.id.promote_spinnerRank);
        DialogPromoteRankAdapter RankAdapter = new DialogPromoteRankAdapter(
                getActivity().getApplicationContext(),ranks,clan.getMyRank());

        memberAdapter.setDropDownViewResource(R.layout.spinner_element_promote_member);
        RankSpinner.setAdapter(RankAdapter);
        RankSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chosenRank = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(promptsView)

                // Add action buttons
                .setPositiveButton("Promote", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if (chosenMember != null && chosenRank != 0) {
                            HTTPLogic.promoteMember(clan.getClanID(), chosenMember.getUserID(), chosenRank);
                            chosenMember.setRank(chosenRank);
                        }

                        DialogPromoteMember.this.getDialog().closeOptionsMenu();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DialogPromoteMember.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    private ArrayList<Membership> findPromoteablemembers() {
        ArrayList<Membership> list = new ArrayList<>();
        for(Membership m:clan.getMembers()){
            if(m.getRank() <clan.getMyRank()){
                list.add(m);
            }
        }
        Collections.sort(list, new Membership());

        return list;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
