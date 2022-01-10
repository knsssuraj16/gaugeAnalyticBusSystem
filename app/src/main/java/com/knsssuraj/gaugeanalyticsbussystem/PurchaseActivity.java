package com.knsssuraj.gaugeanalyticsbussystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.knsssuraj.gaugeanalyticsbussystem.db.LocalDatabase;

public class PurchaseActivity extends AppCompatActivity {

    EditText name,amount;
    Button purchse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        name = findViewById(R.id.name);
        amount = findViewById(R.id.amount);
        purchse = findViewById(R.id.purchase);
        
        purchse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String na = name.getText().toString();
                int amoun = Integer.parseInt(amount.getText().toString().isEmpty() ? "0": amount.getText().toString());
                
                if(na.isEmpty()){
                    name.setError("Can't empty");
                }else if(amoun <10){
                    amount.setError("Increase Amount");
                }else{
                    saveToDatabase(na,amoun);
                }
            }
        });
    }

    private void saveToDatabase(String na, int amoun) {
        String userId = LocalDatabase.saveCardHolder(na,amoun);
        Toast.makeText(this, userId, Toast.LENGTH_SHORT).show();
    }
}