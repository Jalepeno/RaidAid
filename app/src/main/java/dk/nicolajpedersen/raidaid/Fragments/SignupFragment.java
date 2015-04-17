package dk.nicolajpedersen.raidaid.Fragments;

import android.content.Context;
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
import android.widget.Toast;

import dk.nicolajpedersen.raidaid.Activities.StartupActivity;
import dk.nicolajpedersen.raidaid.Logic.HTTPLogic;
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
        terms =(CheckBox) signupView.findViewById(R.id.cbTerms);
        seeTerms =(TextView) signupView.findViewById(R.id.tvTerms);
        seeTerms.setOnClickListener(this);
        btnSign.setOnClickListener(this);




        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        if(v == btnSign){
            if(terms.isChecked()){
                if(newPassword1.equals(newPassword2)){
                    HTTPLogic httpLogic = new HTTPLogic();
                    int signupsucess = httpLogic.postSignup();

                    // delay here for internet communication

                    if(signupsucess == 1){
                        ((StartupActivity)getActivity()).setLoginFragment();
                    }else{
                        Context context = getActivity().getApplicationContext();
                        CharSequence text = "signup failed miserably!";
                        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }else{
                    Context context = getActivity().getApplicationContext();
                    CharSequence text = "Your passwords do not match";
                    Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }else{
                Context context = getActivity().getApplicationContext();
                CharSequence text = "You must accept terms";
                Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        if(v == terms){
            // something about a dialog with terms here!
        }

    }
}
