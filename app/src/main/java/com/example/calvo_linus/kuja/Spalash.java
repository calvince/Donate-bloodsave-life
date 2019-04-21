package com.example.calvo_linus.kuja;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Spalash extends AppCompatActivity {
    TextView u;
    ImageView x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalash);
        u = findViewById(R.id.iv);
        x = findViewById(R.id.tv);
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.mytransition);
        x.startAnimation(myanim);
        u.setAnimation(myanim);
        final Intent i= new Intent(this,BaseActivity.class);
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();


    }
}
