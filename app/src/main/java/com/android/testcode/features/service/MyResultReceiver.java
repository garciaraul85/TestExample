package com.android.testcode.features.service;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public class MyResultReceiver<T> extends ResultReceiver {

    private ResultReceiverCallBack mReceiver;
    public final static int RESULT_OK = 100;
    public final static int RESULT_ERROR = 101;
    public final static String PARAM_RESULT_OK = "result";
    public final static String PARAM_RESULT_ERROR = "exception";

    public MyResultReceiver(Handler handler) {
        super(handler);
    }

    public void setReceiver(ResultReceiverCallBack<T> receiver) {
        this.mReceiver = receiver;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (mReceiver != null) {
            if (resultCode == RESULT_OK) {
                mReceiver.onSuccess(resultData.getSerializable(PARAM_RESULT_OK));
            } else {
                mReceiver.onSuccess(resultData.getSerializable(PARAM_RESULT_ERROR));
            }
        }
    }

    public interface  ResultReceiverCallBack<T> {
        void onSuccess(T data);
        void onError(Exception exception);
    }

}