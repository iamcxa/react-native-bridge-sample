
package com.reactlibrary;

import android.util.Log;
import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.HashMap;
import java.util.Map;

public class RNMyAndroidToastModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    // 宣告兩個常數儲存 Toast 的維持時間，以便輸出到 JavaScript
    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String DURATION_LONG_KEY = "LONG";


    // 預設建構子
    public RNMyAndroidToastModule(ReactApplicationContext reactContext) {
        super(reactContext);
        // reactContext 是特殊的 Android Context，是當前 React Native app 的上下文物件
        this.reactContext = reactContext;
    }

    // getName() 為必備方法，負責回傳此 library 名稱
    // 這裡的名稱一經產生後建議不要再修改，容易出問題
    @Override
    public String getName() {
        return "RNMyAndroidToast";
    }


    // 宣告可以在 JavaScript 端可以使用的常數
    // getConstants() 不是必備方法
    // 以 Toast 來說，就是它的維持顯示時間
    // Toast.LENGTH_SHORT / Toast.LENGTH_LONG
    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put(DURATION_SHORT_KEY, Toast.LENGTH_SHORT);
        constants.put(DURATION_LONG_KEY, Toast.LENGTH_LONG);
        return constants;
    }


    // 在 method 宣告 @ReactMethod 表示該方法將可以被 JavaScript 端存取
    // 由於 React Native 的 bridge 機制是非同步的，所以回傳必須是 void
    // 如果需要回傳，必須透過事件機制或是 callback 方法
    @ReactMethod
    public void show(String message, int duration) {
        // Java <----> JavaScript 參數對應類型：
        //        Boolean -> Bool
        //        Integer -> Number
        //        Double -> Number
        //        Float -> Number
        //        String -> String
        //        Callback -> function
        //        ReadableMap -> Object
        //        ReadableArray -> Array
        Log.d("toast_show", message + " - " + duration);
        Toast.makeText(getReactApplicationContext(), message, duration).show();
    }
}