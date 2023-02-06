package com.example.androidstructure;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.example.library1api.ILibrary1Service;
import com.example.library1impl.Library1Service;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ILibrary1Service library1Service = new Library1Service();
        Log.i(TAG, library1Service.getServiceName());
    }
}