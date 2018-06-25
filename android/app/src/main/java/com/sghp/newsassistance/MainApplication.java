package com.sghp.newsassistance;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;
import com.sghp.newsassistance.rnmodules.floatball.FloatBallPackage;
import com.sghp.newsassistance.rnmodules.script.AndroidLuaActivity;
import com.sghp.newsassistance.rnmodules.script.ScriptPackage;

import java.util.Arrays;
import java.util.List;

public class MainApplication extends Application implements ReactApplication {

  private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
    @Override
    public boolean getUseDeveloperSupport() {
      return BuildConfig.DEBUG;
    }

    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
          new MainReactPackage(),
          new ScriptPackage(),
          new FloatBallPackage()
      );
    }

    @Override
    protected String getJSMainModuleName() {
      return "index";
    }
  };

  @Override
  public ReactNativeHost getReactNativeHost() {
    return mReactNativeHost;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    SoLoader.init(this, /* native exopackage */ false);

//    Intent intent = new Intent(this, AndroidLuaActivity.class);
//    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//    this.startActivity(intent);
  }
}
