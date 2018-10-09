package com.android.testcode.data;

import android.annotation.SuppressLint;

import com.android.testcode.data.dao.UserDAO;
import com.android.testcode.data.model.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DataManager {

//    private AngelService angelService;
//    private AppDatabase appDatabase;
    private UserDAO userDAO;

    @Inject
    public DataManager(/*UserDAO userDAO*/) {
        this.userDAO = userDAO;
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