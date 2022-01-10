package com.knsssuraj.gaugeanalyticsbussystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.knsssuraj.gaugeanalyticsbussystem.db.LocalDatabase;
import com.knsssuraj.gaugeanalyticsbussystem.pojo.Session;

public class SwipeInOutActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    TextView msg;
    Button swipe;
    EditText id;
    SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_in_out);

        msg= findViewById(R.id.msg);
        swipe= findViewById(R.id.swipe);
        id= findViewById(R.id.id);
        seekBar= findViewById(R.id.station);

        seekBar.setOnSeekBarChangeListener(this);
        swipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!id.getText().toString().isEmpty()){
                    swipeInOrOut(id.getText().toString());
                }else{
                    Toast.makeText(SwipeInOutActivity.this, "Enter ID Please", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void swipeInOrOut(String id) {
        boolean yes = LocalDatabase.checkSessionStartOrNot(id);
        if(yes){
            new AlertDialog.Builder(SwipeInOutActivity.this)
                    .setTitle("messege")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Session session =  LocalDatabase.StopSession(id,seekbarcount);
                            msg.setText(session.toString());
                        }
                    })
                    .setMessage("Do you really want to checkout")
                    .show();
        }else{
          boolean status =  LocalDatabase.checkCardDetailAndBalance(id);
          if(status){
              Toast.makeText(this, "Session start", Toast.LENGTH_SHORT).show();
              LocalDatabase.startSession(id,seekbarcount);
          }else{
              Toast.makeText(this, "Sorry Id Not match or your balance is less than 10", Toast.LENGTH_SHORT).show();
          }
        }
    }
    int seekbarcount = 1;
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        seekbarcount = (int)(i/6.66) == 0 ? 1:(int)(i/6.66) ;

        swipe.setText("Start from "+seekbarcount);

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}