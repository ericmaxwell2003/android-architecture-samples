package com.acme.tictactoe.viewModel;

import android.content.Context;
import android.os.Handler;

/**
 * Created by ericmaxwell on 7/15/17.
 */

class MyLocationListener {
    public MyLocationListener(Context context, Handler.Callback callback) {
        // ...
    }

    void start() {
        // connect to system location service
    }

    void stop() {
        // disconnect from system location service
    }

    public static void main(String args) {
        MyLocationListener listener = new MyLocationListener(null, null);
        listener.start();
        listener.stop();
    }
}
