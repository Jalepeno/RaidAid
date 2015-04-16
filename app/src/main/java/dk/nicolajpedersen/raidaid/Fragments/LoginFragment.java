package dk.nicolajpedersen.raidaid.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dk.nicolajpedersen.raidaid.Activities.PageViewActivity;
import dk.nicolajpedersen.raidaid.Activities.StartupActivity;
import dk.nicolajpedersen.raidaid.Logic.HTTPLogic;
import dk.nicolajpedersen.raidaid.R;

/**
 * Created by Nicolaj on 14-04-2015.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{
    EditText username,password;
    Button signup,login;
    View loginView;
    SharedPreferences sharedPreferences;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loginView = inflater.inflate(R.layout.fragment_login,container,false);
        username =(EditText) loginView.findViewById(R.id.etUserName);
        password =(EditText) loginView.findViewById(R.id.etpassword);
        signup = (Button) loginView.findViewById(R.id.btnSignup);
        login = (Button) loginView.findViewById(R.id.btn_login);
        signup.setOnClickListener(this);
        login.setOnClickListener(this);

        sharedPreferences = getActivity().getSharedPreferences("RaidAid", Context.MODE_PRIVATE);

        return loginView;
    }

    @Override
    public void onClick(View v) {
        if(v == login){
            //login session
            HTTPLogic httpLogic = new HTTPLogic();
            int didStuffHappen = httpLogic.getProfile(username.getText().toString(),password.getText().toString());


            // if login successful
            if(didStuffHappen == 1){

                // save login valuables
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username",username.getText().toString());
                editor.putString("password",password.getText().toString());
                editor.commit();

                // change activity.
                Intent i = new Intent(getActivity()
                        .getApplicationContext(), PageViewActivity.class);
                startActivity(i);

            }else{

                // make toast to inform of bad login
                Context context = getActivity().getApplicationContext();
                CharSequence text = "Your username and password did not match";
                Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                toast.show();
            }


        }else if(v == signup){
            ((StartupActivity)getActivity()).setSignupFragment();
        }
    }
}
