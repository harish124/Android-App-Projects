package com.example.insta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLogin extends AppCompatActivity {

    private Button signUpbtn,loginbtn;
    private EditText sUserName,lUserName,sPwd,lPwd;     //s-->signUp    l-->login

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing the components below
        signUpbtn=findViewById(R.id.signUpbtn);
        loginbtn=findViewById(R.id.loginbtn);
        sUserName=findViewById(R.id.sUserName);
        lUserName=findViewById(R.id.lUserName);
        sPwd=findViewById(R.id.sPwd);
        lPwd=findViewById(R.id.lPwd);

        signUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser appUser=new ParseUser();

                appUser.setUsername(""+sUserName.getText());
                appUser.setPassword(""+sPwd.getText());

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null) //meaning no error
                        {
                            FancyToast.makeText(SignUpLogin.this,"Signed Up Successfully", FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
                            //clear the contents of the textfields
                            sUserName.setText("");
                            sPwd.setText("");
                            lUserName.setText("");
                            lPwd.setText("");

                            FancyToast.makeText(SignUpLogin.this,"Now You can Login\nUsing your created Account", FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
                        }
                        else
                        {
                            FancyToast.makeText(SignUpLogin.this,"Sign Up Failed\nError: "+e.getMessage(), FancyToast.LENGTH_SHORT,FancyToast.ERROR,true).show();
                        }
                    }
                });

            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(lUserName.getText().toString(), lPwd.getText().toString(),
                        new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {
                                if(user !=null && e==null)
                                {
                                    FancyToast.makeText(SignUpLogin.this,user.get("username")+" Logged In Successfully", FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();

                                    //After Logging go to the welcome page
                                    Intent intent =new Intent(SignUpLogin.this,WelcomeActivity.class);
                                    startActivity(intent);

                                    //clear the contents of the textfields
                                    sUserName.setText("");
                                    sPwd.setText("");
                                    lUserName.setText("");
                                    lPwd.setText("");
                                }
                                else
                                {
                                    FancyToast.makeText(SignUpLogin.this,"Log In Failed\nError: "+e.getMessage(), FancyToast.LENGTH_SHORT,FancyToast.ERROR,true).show();
                                }
                            }
                        });
            }
        });
    }//eOnCreate


}
