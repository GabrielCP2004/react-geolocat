package com.geolocationback;

import android.os.Bundle;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactRootView;
import com.swmansion.gesturehandler.react.RNGestureHandlerEnabledRootView;

import expo.modules.splashscreen.singletons.SplashScreen;
import expo.modules.splashscreen.SplashScreenImageResizeMode;

// Add the following imports
import android.content.Intent;
import android.util.Log;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class MainActivity extends ReactActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // Set the theme to AppTheme BEFORE onCreate to support 
    // coloring the background, status bar, and navigation bar.
    // This is required for expo-splash-screen.
    setTheme(R.style.AppTheme);
    super.onCreate(null);
    // SplashScreen.show(...) has to be called after super.onCreate(...)
    // Below line is handled by '@expo/configure-splash-screen' command and it's discouraged to modify it manually
    SplashScreen.show(this, SplashScreenImageResizeMode.CONTAIN, ReactRootView.class, false);
  }


    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "main";
    }

    public boolean isOnNewIntent = false;

    @Override
    public void onNewIntent(Intent intent) {
      super.onNewIntent(intent);
      isOnNewIntent = true;
      ForegroundEmitter();
    }

    @Override
    protected void onStart() {
      super.onStart();
      if(isOnNewIntent == true){}else {
          ForegroundEmitter();
      }
    }

    public  void  ForegroundEmitter(){
      // this method is to send back data from java to javascript so one can easily
      // know which button from notification or the notification button is clicked
      String  main = getIntent().getStringExtra("mainOnPress");
      String  btn = getIntent().getStringExtra("buttonOnPress");
      WritableMap  map = Arguments.createMap();
      if (main != null) {
          map.putString("main", main);
      }
      if (btn != null) {
          map.putString("button", btn);
      }
      try {
          getReactInstanceManager().getCurrentReactContext()
          .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
          .emit("notificationClickHandle", map);
      } catch (Exception  e) {
      Log.e("SuperLog", "Caught Exception: " + e.getMessage());
      }
    }

    @Override
    protected ReactActivityDelegate createReactActivityDelegate() {
        return new ReactActivityDelegate(this, getMainComponentName()) {
            @Override
            protected ReactRootView createRootView() {
                return new RNGestureHandlerEnabledRootView(MainActivity.this);
            }
        };
    }
}
