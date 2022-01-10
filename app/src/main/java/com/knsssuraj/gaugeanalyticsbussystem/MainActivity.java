package com.knsssuraj.gaugeanalyticsbussystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.knsssuraj.gaugeanalyticsbussystem.db.LocalDatabase;

public class MainActivity extends AppCompatActivity {

    TextView purchase, swipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocalDatabase l = new LocalDatabase();
        purchase = findViewById(R.id.purchase);
        swipe = findViewById(R.id.swipe);
        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,PurchaseActivity.class));
            }
        });

        swipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,SwipeInOutActivity.class));
            }
        });
    }
}