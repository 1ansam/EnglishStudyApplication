package com.night.app.business;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.night.app.R;
import com.night.app.business.home.HomeActivity;
import com.night.business.database.DatabaseUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseUtil.packDataBase(this);
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);



    }
}
