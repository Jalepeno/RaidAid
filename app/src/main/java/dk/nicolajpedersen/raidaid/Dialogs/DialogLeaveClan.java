package dk.nicolajpedersen.raidaid.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import java.util.UUID;

import dk.nicolajpedersen.raidaid.Data.Profile;
import dk.nicolajpedersen.raidaid.Logic.HTTPLogic;
import dk.nicolajpedersen.raidaid.R;

/**
 * Created by Nicolaj on 18-05-2015.
 */
public class DialogLeaveClan extends DialogFragment {

    private UUID clanID;

    public DialogLeaveClan() {

    }

    public void setClanID(UUID clanID){
        this.clanID = clanID;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.RaidAidDialog);
        builder.setMessage(R.string.leaveClan)
                .setTitle("Leave Clan")
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(clanID != null){
                            HTTPLogic.leaveClan(clanID);
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
