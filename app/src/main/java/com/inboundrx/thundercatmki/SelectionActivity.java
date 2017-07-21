package com.inboundrx.thundercatmki;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SelectionActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.mapDemoButton) Button mMapDemoButton;
    @Bind(R.id.rewardDemoButton) Button mRewardDemoButton;
    @Bind(R.id.userDemoButton) Button mUserDemoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        ButterKnife.bind(this);
        mMapDemoButton.setOnClickListener(this);
        mRewardDemoButton.setOnClickListener(this);
        mUserDemoButton.setOnClickListener(this);
        openAnimation1();
    }

    private void openAnimation4(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mUserDemoButton.animate().translationY(0).setDuration(1200);

            }
        }, 300);
    }

    private void openAnimation3(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRewardDemoButton.animate().translationY(0).setDuration(1200);
                openAnimation4();
            }
        }, 300);
    }

    private void openAnimation2(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mMapDemoButton.animate().translationY(0).setDuration(1200);
                openAnimation3();
            }
        }, 300);
    }

    private void openAnimation1(){
        mMapDemoButton.animate().translationY(3000).setDuration(1);
        mRewardDemoButton.animate().translationY(3000).setDuration(1);
        mUserDemoButton.animate().translationY(3000).setDuration(1);
        openAnimation2();
    }

    @Override
    public void onClick(View v){
        if (v == mMapDemoButton){
            Intent intent = new Intent(SelectionActivity.this, MapActivity.class);
            startActivity(intent);
        }
        if (v == mRewardDemoButton){
            Intent intent = new Intent(SelectionActivity.this, DurationRewardActivity.class);
            startActivity(intent);
        }
        if (v == mMapDemoButton){
            Intent intent = new Intent(SelectionActivity.this, ProfileActivity.class);
            startActivity(intent);
        }
    }
}
