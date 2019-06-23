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

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity {

    private Button loginbtn;
    private EditText lUserName,lPwd;     //s-->signUp    l-->login

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing the components below

        loginbtn=findViewById(R.id.loginbtn);
        loginbtn.setClickable(true);
        lUserName=findViewById(R.id.lUserName);

        lPwd=findViewById(R.id.lPwd);

        lPwd.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN)
                {
                    loginbtn.callOnClick();
                }
                return false;
            }

        });



        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog=new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("Processing Your Request!!!");
                progressDialog.show();
                progressDialog.setCanceledOnTouchOutside(false);
                loginbtn.setClickable(false);
                ParseUser.logInInBackground(lUserName.getText().toString().trim(), lPwd.getText().toString().trim(),

                        new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {
                                if(user !=null && e==null)
                                {
                                    FancyToast.makeText(LoginActivity.this,user.get("username")+" Logged In Successfully", FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();

                                    //After Logging go to the welcome page

                                    Intent intent =new Intent(LoginActivity.this, SocialMediaActivity.class);
                                    startActivity(intent);
                                    loginbtn.setClickable(true);

                                    //clear the contents of the textfields
                                    lUserName.setText("");
                                    lPwd.setText("");
                                }
                                else
                                {
                                    FancyToast.makeText(LoginActivity.this,"Log In Failed\nError: "+e.getMessage(), FancyToast.LENGTH_SHORT,FancyToast.ERROR,true).show();
                                    loginbtn.setClickable(true);
                                }
                                progressDialog.dismiss();
                            }
                        });
            }
        });


    }//eOnCreate

    public void signUpSwitch(View view)
    {
        Intent intent =new Intent(LoginActivity.this,SignUpActivity.class);
        startActivity(intent);
    }

    public void rootLayoutTapped(View view) //To hide the keyboard
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
