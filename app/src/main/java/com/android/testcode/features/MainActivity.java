package com.android.testcode.features;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.testcode.R;
import com.android.testcode.features.service.MyIntentService;
import com.android.testcode.features.service.MyResultReceiver;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    TextView label;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        label = findViewById(R.id.label);
        button = findViewById(R.id.button);

        button.setOnClickListener(view -> {
            doBaz();
        });
    }

    private void doBaz() {
        MyIntentService.startServiceForBaz(this, "1", "1", new MyResultReceiverResult(this));
    }

    private static class MyResultReceiverResult implements MyResultReceiver.ResultReceiverCallBack<String> {

        private WeakReference<MainActivity> activityWeakReference;

        public MyResultReceiverResult(MainActivity mainActivity) {
            activityWeakReference = new WeakReference<>(mainActivity);
        }

        @Override
        public void onSuccess(String data) {
            if (activityWeakReference != null && activityWeakReference.get() != null) {
                activityWeakReference.get().label.setText(data);
            }
        }

        @Override
        public void onError(Exception exception) {
            if (activityWeakReference != null && activityWeakReference.get() != null) {
                activityWeakReference.get().label.setText("Error");
            }
        }
    }
}