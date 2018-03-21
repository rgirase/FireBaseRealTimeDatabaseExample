package com.example.rahul.fribaserealtimedatabase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Rahul on 8/8/17.
 */

public class InstanceTokenIDService extends FirebaseInstanceIdService {

    private static final String REG_TOKEN="REG_TOKEN";
    @Override
    public void onTokenRefresh() {

        String instance_token= FirebaseInstanceId.getInstance().getToken();
        Log.d(REG_TOKEN, instance_token);
    }
}
