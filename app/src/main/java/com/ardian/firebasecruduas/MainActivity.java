package com.ardian.firebasecruduas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btCreateDb,btViewdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btCreateDb= findViewById(R.id.bt_createdata);
        btViewdb= findViewById(R.id.bt_viewdata);

        btCreateDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(DBCreateAct.getActIntent(MainActivity.this));
            }
        });
        btViewdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(DBReadAct.getActIntent(MainActivity.this));
            }
        });
    }
}
