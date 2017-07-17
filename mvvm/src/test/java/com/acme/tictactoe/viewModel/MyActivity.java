package com.acme.tictactoe.viewModel;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ericmaxwell on 7/15/17.
 */

class MyActivity extends AppCompatActivity {
    private MyLocationListener myLocationListener;

    public void onCreate() {
        myLocationListener = new MyLocationListener(this, (Message location) -> {
            // update UI
        });
    }

    public void onStart() {
        super.onStart();
        myLocationListener.start();
    }

    public void onStop() {
        super.onStop();
        myLocationListener.stop();
    }
}