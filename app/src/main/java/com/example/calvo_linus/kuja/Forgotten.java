package com.example.calvo_linus.kuja;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgotten extends Activity {
    private EditText inputEmail;
    private Button btnReset,btnBack;
    private FirebaseAuth auth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //android.support.v4.app.FragmentTransaction gt = getSupportFragmentManager().beginTransaction();
       // gt.replace(R.id.screen_view, new LoginFragment());
       // gt.commit();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotten);
        inputEmail = findViewById(R.id.ed_mail);
        btnReset = findViewById(R.id.btn_reset_password);
        btnBack = findViewById(R.id.bt_back);
        auth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),loginActivity.class);
                startActivity(intent);

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ed_mail = inputEmail.getText().toString().trim();
                if (TextUtils.isEmpty(ed_mail)){
                    Toast.makeText(getApplicationContext(),"Enter your registered email id",Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                auth.sendPasswordResetEmail(ed_mail)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "We have sent link to your Email!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Failed to send link to your Email!", Toast.LENGTH_SHORT).show();
                                }
                                progressBar.setVisibility(View.GONE);
                            }
                        });


            }
        });
    }
}
