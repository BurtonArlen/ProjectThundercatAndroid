package com.inboundrx.thundercatmki;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.learnMoreButton) Button mLearnMoreButton;
    @Bind(R.id.landingLogo) ImageView mLandingLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mLearnMoreButton.setOnClickListener(this);
        openAnimationLogo();
    }

    private void openAnimationLogo(){
        mLandingLogo.animate().setDuration(2000).alpha(1f);
        openAnimationButton();
    }

    private void openAnimationButton(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLearnMoreButton.animate().setDuration(2000).alpha(1f);
            }
        }, 2000);
    }

    @Override
    public void onClick(View v){
        if (v == mLearnMoreButton){
            Intent intent = new Intent(MainActivity.this, SelectionActivity.class);
            startActivity(intent);
        }
    }
}
