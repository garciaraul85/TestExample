package com.android.testcode.features;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.testcode.R;
import com.android.testcode.features.service.MyIntentService;
import com.android.testcode.features.service.MyResultReceiver;

import java.lang.ref.WeakReference;

import static com.android.testcode.features.service.MyIntentService.ACTION_BAZ;
import static com.android.testcode.features.service.MyIntentService.EXTRA_PARAM1;
import static com.android.testcode.features.service.MyIntentService.EXTRA_PARAM2;
import static com.android.testcode.features.service.MyIntentService.RESULT_RECEIVER;

public class MainActivity extends AppCompatActivity {

    private TextView label;
    private EditText valAlpha;
    private EditText valBeta;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        label = findViewById(R.id.label);
        button = findViewById(R.id.button);
        valAlpha = findViewById(R.id.alpha);
        valBeta = findViewById(R.id.beta);

        button.setOnClickListener(view -> {
            doBaz();
        });
    }

    private void doBaz() {
        MyResultReceiver resultReceiver = new MyResultReceiver(new Handler(getMainLooper()));
        resultReceiver.setReceiver(new MyResultReceiverResult(this));

        Intent intent = new Intent(getApplicationContext(), MyIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, valAlpha.getText().toString());
        intent.putExtra(EXTRA_PARAM2, valBeta.getText().toString());
        intent.putExtra(RESULT_RECEIVER, resultReceiver);
        startService(intent);

//        MyIntentService.startServiceForBaz(this, "1", "1", new MyResultReceiverResult(this));
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