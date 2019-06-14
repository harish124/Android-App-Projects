package com.example.insta;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpActivity extends AppCompatActivity {
    private Button signUpbtn;
    private EditText sUserName,sPwd,semail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpbtn=findViewById(R.id.signUpbtn);
        sUserName=findViewById(R.id.sUserName);
        sPwd=findViewById(R.id.sPwd);
        semail=findViewById(R.id.semail);

        sPwd.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN)
                {
                    signUpbtn.callOnClick();
                }
                return false;
            }

        });

        signUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog=new ProgressDialog(SignUpActivity.this);
                progressDialog.setMessage("Processing Your Request!!!");
                progressDialog.show();
                signUpbtn.setClickable(false);
                ParseUser appUser=new ParseUser();
                appUser.setEmail(""+semail.getText());
                appUser.setUsername(""+sUserName.getText());
                appUser.setPassword(""+sPwd.getText());

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null) //meaning no error
                        {
                            FancyToast.makeText(SignUpActivity.this,"Signed Up Successfully", FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
                            //clear the contents of the textfields
                            sUserName.setText("");
                            sPwd.setText("");
                            ParseUser.logOut();

                            FancyToast.makeText(SignUpActivity.this,"Now You can Login\nUsing your created Account", FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
                            finish();
                        }
                        else
                        {
                            FancyToast.makeText(SignUpActivity.this,"Sign Up Failed\nError: "+e.getMessage(), FancyToast.LENGTH_SHORT,FancyToast.ERROR,true).show();
                        }

                        progressDialog.dismiss();
                        signUpbtn.setClickable(true);
                    }
                });

            }
        });


    }


    public void loginTapped(View view)
    {
        finish();       //Once signed up go to login page

    }

    public void rootLayoutTapped(View view)     //To hide the keyboard
    {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        catch(Exception e)
        {
            e.printStackTrace();    //Nothing to worry about this. It will be triggered when empty space is tapped when keyboard is not visible.
        }

    }
}
