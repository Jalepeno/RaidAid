package dk.nicolajpedersen.raidaid.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import dk.nicolajpedersen.raidaid.R;

/**
 * Created by Nicolaj on 14-04-2015.
 */
public class SignupFragment extends Fragment implements View.OnClickListener{
    EditText newUserName,newPassword1,newPassword2;
    Button btnSign;
    CheckBox terms;
    TextView seeTerms;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View signupView = inflater.inflate(R.layout.fragment_signup,container,false);
        newUserName = (EditText) signupView.findViewById(R.id.etNewUserName);
        newPassword1 = (EditText) signupView.findViewById(R.id.etNewPassword1);
        newPassword2  = (EditText) signupView.findViewById(R.id.etNewPassword2);
        btnSign = (Button) signupView.findViewById(R.id.btnSignUp);




        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onClick(View v) {

    }
}
