package com.android.testcode.injection.data;

import android.annotation.SuppressLint;

import com.android.testcode.injection.data.model.User;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.schedulers.Schedulers;

@Singleton
public class DataManager {

//    private AngelService angelService;

    @Inject
    public DataManager(/*AngelService angelService*/) {
//        this.angelService = angelService;
    }

    @SuppressLint("CheckResult")
    public void requestContacts() {
//        angelService.getContacts("Bearer " + getToken())
//                .subscribeOn(Schedulers.io())
//                .subscribe(
//                        contacts -> {
//                            saveContacts(contacts);
//                            ContactsEvent contactsEvent = new ContactsEvent(true);
//                            contactsEvent.setContacts(contacts);
//                            EventBus.getDefault().post(contactsEvent);
//                        },
//                        throwable -> {
//                            ContactsEvent contactsEvent = new ContactsEvent(false);
//                            contactsEvent.setError(throwable);
//                            EventBus.getDefault().post(contactsEvent);
//                        });
    }


    public void saveContacts(List<User> contacts) {
        // save new user
    }


}