package com.sghp.newsassistance.rnmodules.script;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class ScriptModule extends ReactContextBaseJavaModule{

    private static final String MODULE_NAME="ScriptModule";
  
    public ScriptModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }
  
    @Override  
    public String getName() {  
        return MODULE_NAME;  
    }

    @ReactMethod
    public void startActivityFromJS(String params) {
        Log.d("DEBUG TOM-params:", params + ":params");
        Activity currentActivity = getCurrentActivity();
        Intent intent = new Intent(currentActivity, AndroidLuaActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        currentActivity.startActivity(intent);
    } 
}  