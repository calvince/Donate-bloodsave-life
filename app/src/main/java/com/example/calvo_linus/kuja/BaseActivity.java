package com.example.calvo_linus.kuja;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;



public class BaseActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener,
        Need_Blood.Callback {
    Toolbar toolbars;
    String TAG = "BaseActivity";
    FragmentManager manager;
    SharedPreferences preference;
    BottomNavigationView baba;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baba = findViewById(R.id.bottom_navigation);
        toolbars = findViewById(R.id.toolbar);
        setSupportActionBar(toolbars);



        baba.setOnNavigationItemSelectedListener(this);
        manager = getSupportFragmentManager();
        preference = PreferenceManager.getDefaultSharedPreferences(this);

        if (savedInstanceState == null) {
            android.support.v4.app.FragmentTransaction gt = getSupportFragmentManager().beginTransaction();
            gt.replace(R.id.the_main_fragment, new Need_Blood());
            gt.commit();
            setTitle(getResources().getString(R.string.home_one));

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.want, menu);
        boolean isUSerLoggedIn = preference.getBoolean(SettingsActivity.KEY_USER_LOGGED_IN, false);
        MenuItem item = menu.findItem(R.id.priviledge_content);
        if (isUSerLoggedIn) {
            item.setTitle(R.string.logout);
        } else {
            item.setTitle(R.string.login);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.priviledge_content:
                boolean isUserLoggedIn = preference.getBoolean(SettingsActivity.KEY_USER_LOGGED_IN, false);
                if (isUserLoggedIn) {
                    //logout the user
                    preference.edit().putBoolean(SettingsActivity.KEY_USER_LOGGED_IN, false);
                    SharedPreferences.Editor edit = preference.edit();
                    edit.remove(SettingsActivity.KEY_USER_ID);
                    edit.remove(SettingsActivity.KEY_USERNAME);
                    edit.remove(SettingsActivity.KEY_USER_LOGGED_IN);
                    edit.apply();
                    Toast.makeText(getApplicationContext(), "You have been logged out", Toast.LENGTH_SHORT).show();
                    invalidateOptionsMenu();
                } else {
                    //login the user
                    startActivityForResult(new Intent(this, loginActivity.class), loginActivity.LOGIN_REQUEST_CODE);

                }
                break;

            case R.id.about:
                //About the App (About Activity)
                Intent intent = new Intent(this, AboutUsActivity.class);
                startActivity(intent);


                break;

            case R.id.share:
                String packageName = getPackageName();
                String shareURl = "https://play.google.com/store/apps/details?id=" + packageName;
                Intent txtIntent = new Intent(android.content.Intent.ACTION_SEND);
                txtIntent.setType("text/plain");
                txtIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareURl);
                startActivity(Intent.createChooser(txtIntent, "Donate Blood Save Life"));
                break;

            case R.id.helping:
                //send Feedback



                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction transaction = manager.beginTransaction();
        switch (item.getItemId()) {
            case R.id.nav_home: {
                Fragment fragment = getCurrentFragment();
                if (
                        fragment != null && fragment instanceof Need_Blood) {
                    return false;
                }
                setTitle(getResources().getString(R.string.need_blood));
                transaction.replace(R.id.the_main_fragment, new Need_Blood());
                transaction.addToBackStack(null);
                transaction.commit();


                return true;
            }
            case R.id.need_blood: {
                Fragment fragment = getCurrentFragment();
                if (fragment != null && fragment instanceof DonorListF) {
                    return false;
                }
                setTitle(getResources().getString(R.string.donor_list));
                transaction.replace(R.id.the_main_fragment, new DonorListF());
                transaction.addToBackStack(null);
                transaction.commit();

                return true;
            }

        }
        return false;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public Fragment getCurrentFragment() {
        Fragment fragment = manager.findFragmentById(R.id.the_main_fragment);
        return fragment;
    }



}
