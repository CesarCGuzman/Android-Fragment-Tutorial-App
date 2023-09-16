package com.example.fragment_tutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements HomeFragment.HomeListener, SettingsFragment.SettingsListener, EducationSelectionFragment.EduSelectionListener {
    // Implements the interface
    public static final String TAG = "demo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.containerView, new HomeFragment(), "home-fragment")
                .commit();

        Log.d(TAG, "MainActivity : onCreate: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "MainActivity : onResume: ");
    }

    @Override
    public void sendProfile(Profile profile) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, ProfileFragment.newInstance(profile))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendUsername(String Username) {
        Toast.makeText(this, Username, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToSettings() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, new SettingsFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToEduSelection() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, new EducationSelectionFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goBackToHomeFragment() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void cancelEduSelection() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void sendSelectedEdu(String education) {
        HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag("home-fragment");
        if(homeFragment != null){
            homeFragment.setSelectedEdu(education);
            getSupportFragmentManager().popBackStack();
        }
    }
}