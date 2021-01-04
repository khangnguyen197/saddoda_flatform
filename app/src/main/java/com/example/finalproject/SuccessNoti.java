package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SuccessNoti extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_noti);
        Globals globals = new Globals();
        globals.transStatus(getWindow());

        /** Return home */
        Button returnBtn = (Button) findViewById(R.id.return_button);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuccessNoti.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });
    }
}