package com.example.android.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String LIFECYCLE_EVENTS_TEXT_KEY = "lifecycleEventsText";

    private TextView mLifecycleEventsTV;

    private static final ArrayList<String> mLifecycleEvents = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLifecycleEventsTV = (TextView)findViewById(R.id.tv_lifecycle_events);

        if (savedInstanceState != null && savedInstanceState.containsKey(LIFECYCLE_EVENTS_TEXT_KEY)) {
            String lifecycleText = savedInstanceState.getString(LIFECYCLE_EVENTS_TEXT_KEY);
            mLifecycleEventsTV.setText(lifecycleText);
        }

        for (String event : mLifecycleEvents) {
            mLifecycleEventsTV.append(event + "\n");
        }
        mLifecycleEvents.clear();

        logAndDisplayLifecycleEvent("onCreate");
    }

    private void logAndDisplayLifecycleEvent(String lifecycleEvent) {
        Log.d(TAG, "Lifecycle Event: " + lifecycleEvent);
        mLifecycleEventsTV.append(lifecycleEvent + "\n");
    }

    @Override
    protected void onStart() {
        super.onStart();
        logAndDisplayLifecycleEvent("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        logAndDisplayLifecycleEvent("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        logAndDisplayLifecycleEvent("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        logAndDisplayLifecycleEvent("onStop");
        mLifecycleEvents.add("onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        logAndDisplayLifecycleEvent("onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logAndDisplayLifecycleEvent("onDestroy");
        mLifecycleEvents.add("onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        logAndDisplayLifecycleEvent("onSaveInstanceState");

        String lifecycleText = mLifecycleEventsTV.getText().toString();
        outState.putString(LIFECYCLE_EVENTS_TEXT_KEY, lifecycleText);
    }
}
