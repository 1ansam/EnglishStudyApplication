package com.night.app.business;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.night.api.database.DatabaseUtil;
import com.night.app.R;
import com.night.app.business.startup.guide.GuideActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseUtil.packDataBase(this);
        Intent intent = new Intent(MainActivity.this, GuideActivity.class);
        startActivity(intent);

    }
}
