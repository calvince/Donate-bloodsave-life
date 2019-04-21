package com.example.calvo_linus.kuja;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class loginActivity extends AppCompatActivity  implements View.OnClickListener{
    private EditText txEml,txpa;
    private Button login,txbt;
    private TextView reg,formw;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    public static final int LOGIN_REQUEST_CODE = 3;

    String pass;
    String email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        txEml = findViewById(R.id.ed_name);
        reg = findViewById(R.id.tx_create);
        txbt = findViewById(R.id.login_button);
        formw = findViewById(R.id.tx_forgotten);
        txpa = findViewById(R.id.ed_password);
        progressBar= findViewById(R.id.progressBar);
        login= findViewById(R.id.bt_login);
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null){
            startActivity(new Intent(loginActivity.this,BaseActivity.class));
        }


       login.setOnClickListener(this);
        txpa.setOnClickListener(this);
        txbt.setOnClickListener(this);
        txEml.setOnClickListener(this);
        reg.setOnClickListener(this);
        formw.setOnClickListener(this);


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FileInputStream inputStream = new FileInputStream(getApplicationContext()
                            .getFilesDir().getAbsolutePath().toString()+
                            File.separator+"register.txt");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder b = new StringBuilder();
                    String line;
                    Boolean firstLine = true;
                    while((line = reader.readLine())!=null){
                        if(firstLine) {
                            b.append(line);
                            firstLine = false;
                        }else{
                            b.append("\n").append(line);
                        }
                    }

                    String content = b.toString();
                    Log.d("LoginFragment ","content == "+content);
                    String[]values = content.split("\n");
                    email = values[0].split(":").toString().trim();
                    pass = values[1].split(":").toString().trim();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.run();



    }
    //Implement the Onclick action here
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
              case R.id.bt_login:
                  //Implementation of onclick for the login button

                  login.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          String email =txEml.getText().toString();
                          final  String password=txpa.getText().toString();
                          if (TextUtils.isEmpty(email)){
                              Toast.makeText(getApplication(),"Enter Your Email Address",Toast.LENGTH_SHORT).show();
                          }
                          if (TextUtils.isEmpty(password)){
                              Toast.makeText(getApplication(),"Input Your Password",Toast.LENGTH_SHORT).show();
                          }
                          progressBar.setVisibility(View.VISIBLE);

                          //Authenticate User

                      mAuth.signInWithEmailAndPassword(email,password)
                              .addOnCompleteListener(loginActivity.this, new OnCompleteListener<AuthResult>() {
                                  @Override
                                  public void onComplete(@NonNull Task<AuthResult> task) {
                                      //if the sign fails, a display message to the user. If sign in succeeds
                                      //the mAuth state listener will be notified and logic to handle the
                                      //Signed in User can be handled in the listener.
                               progressBar.setVisibility(View.GONE);
                               if (!task.isSuccessful()){
                                   //There was an error
                                   if (password.length() <8){
                                       txpa.setError(getString(R.string.minimum_password));

                                   }
                                   else {
                                       Toast.makeText(loginActivity.this,getString(R.string.auth_failed),Toast.LENGTH_LONG).show();
                                   }


                               }
                               else {
                                   Intent intent = new Intent(loginActivity.this,BaseActivity.class);
                                   startActivity(intent);
                                   finish();

                               }

                                  }
                              });


                      }
                  });

                break;

            case R.id.tx_create:
                Intent intent = new Intent(loginActivity.this,CreateFragment.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tx_forgotten:
                Intent yoo = new Intent(loginActivity.this,Forgotten.class);
                startActivity(yoo);
                finish();
                break;
            case R.id.login_button:


                break;

        }


        }
    }









