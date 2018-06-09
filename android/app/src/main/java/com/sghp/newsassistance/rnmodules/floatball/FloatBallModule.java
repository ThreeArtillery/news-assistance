package com.sghp.newsassistance.rnmodules.floatball;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class FloatBallModule extends ReactContextBaseJavaModule {

    private static final String MODULE_NAME="FloatBallModule";

    private Button mShowBtn, mCloseBtn;
    private WindowManager mWindowMgr;
    private WindowManager.LayoutParams wmParams;
    private FloatBallActivity mFloatBall;
  
    public FloatBallModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }
  
    @Override  
    public String getName() {  
        return MODULE_NAME;  
    }

    @ReactMethod
    public void showFlatBallFromJS(String params) {
        Log.d("DEBUG TOM-params:", params + ":params");
//        Activity currentActivity = getCurrentActivity();
//        Intent intent = new Intent(currentActivity, FloatBallActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        currentActivity.startActivity(intent);
        showFloatingWindow();
    }

    public void showFloatingWindow() {

        final Activity currentActivity = getCurrentActivity();

        mWindowMgr = (WindowManager) currentActivity.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);

        mFloatBall = new FloatBallActivity(currentActivity);

        int screenWidth = mWindowMgr.getDefaultDisplay().getWidth();
        int screenHeight = mWindowMgr.getDefaultDisplay().getHeight();
        wmParams = new WindowManager.LayoutParams();
        String phoneModel = android.os.Build.MODEL.toLowerCase();
        if (phoneModel.startsWith("mi") || phoneModel.startsWith("mx")
                || phoneModel.startsWith("m1")) {
            wmParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        } else {
            wmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }
        wmParams.format = PixelFormat.RGBA_8888;
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        wmParams.gravity = Gravity.LEFT | Gravity.TOP;
        // keep 24px to edge
        wmParams.x = 24;
        wmParams.y = screenHeight / 2;
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mFloatBall.setWmParams(wmParams);
        mWindowMgr.addView(mFloatBall, wmParams);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        final Activity currentActivity = getCurrentActivity();

        if (requestCode != 0x01) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(currentActivity)) {
                Toast.makeText(currentActivity, "权限授予失败，无法开启悬浮窗", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(currentActivity, "权限授予成功！", Toast.LENGTH_SHORT).show();
                //有悬浮窗权限开启
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showFloatingWindow();
                        currentActivity.onBackPressed();
                    }
                }, 2000);
            }
        }
    }
}  