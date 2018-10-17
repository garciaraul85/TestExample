package com.android.testcode.features.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_BAZ = "com.android.testcode.features.service.action.BAZ";

    // TODO: Rename parameters
    public static final String EXTRA_PARAM1 = "com.android.testcode.features.service.extra.PARAM1";
    public static final String EXTRA_PARAM2 = "com.android.testcode.features.service.extra.PARAM2";

    public static final String RESULT_RECEIVER = "RESULT_RECEIVER";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ResultReceiver resultReceiver = intent.getParcelableExtra(RESULT_RECEIVER);

        final String action = intent.getAction();
        if (ACTION_BAZ.equals(action)) {
            final String param1 = intent.getStringExtra(EXTRA_PARAM1);
            final String param2 = intent.getStringExtra(EXTRA_PARAM2);
            handleActionBaz(resultReceiver, param1, param2);
        }
    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void handleActionBaz(ResultReceiver resultReceiver, String param1, String param2) {
        Bundle bundle = new Bundle();
        int code;
        sleep(1000);
        if (param1.equals(param2)) {
            code = MyResultReceiver.RESULT_OK;
            bundle.putSerializable(MyResultReceiver.PARAM_RESULT_OK, "Success");
        } else {
            bundle.putSerializable(MyResultReceiver.PARAM_RESULT_ERROR, "Error");
            code = MyResultReceiver.RESULT_ERROR;

        }
        if (resultReceiver != null) {
            resultReceiver.send(code, bundle);
        }
    }

    public static void startServiceForBaz(Context context, String param1, String param2, MyResultReceiver.ResultReceiverCallBack resultReceiverCallBack) {
        MyResultReceiver resultReceiver = new MyResultReceiver(new Handler(context.getMainLooper()));
        resultReceiver.setReceiver(resultReceiverCallBack);

        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        intent.putExtra(RESULT_RECEIVER, resultReceiver);
        context.startService(intent);

    }

}
