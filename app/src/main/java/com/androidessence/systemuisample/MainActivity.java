package com.androidessence.systemuisample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mDimBars;
    private Button mRevealBars;
    private Button mHideStatus;
    private Button mHideNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getButtons();
        setClickListeners();
    }

    private void getButtons() {
        mDimBars = (Button) findViewById(R.id.dim_bars);
        mRevealBars = (Button) findViewById(R.id.reveal_bars);
        mHideStatus = (Button) findViewById(R.id.hide_status);
        mHideNavigation = (Button) findViewById(R.id.hide_navigation);
    }

    private void setClickListeners() {
        mDimBars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get decor view returns the status bar and navigation bar.
                // Dim them by setting visibility to low profile.
                View statusView = getWindow().getDecorView();
                statusView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
            }
        });
        mRevealBars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStatusBars();
            }
        });
        mHideStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View statusView = getWindow().getDecorView();

                // Set full screen flag
                statusView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

                // If the status bar is hidden we should also hide the ActionBar.
                if(getSupportActionBar() != null) {
                    getSupportActionBar().hide();
                }
            }
        });
        mHideNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View statusView = getWindow().getDecorView();

                // When hiding the navigation bar, it's good to hide both navigation and status bars.
                // This means we need to use both flags.
                statusView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN);

                // If the status bar is hidden we should also hide the ActionBar.
                if(getSupportActionBar() != null) {
                    getSupportActionBar().hide();
                }
            }
        });
    }

    private void showStatusBars() {
        // Setting visibility to 0 clears any existing flags.
        View statusView = getWindow().getDecorView();
        statusView.setSystemUiVisibility(0);

        // If action bar was hidden show it again
        if(getSupportActionBar() != null) {
            getSupportActionBar().show();
        }
    }
}
