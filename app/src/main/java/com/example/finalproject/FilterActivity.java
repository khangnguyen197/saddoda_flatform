package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import ja.burhanrashid52.photoeditor.PhotoEditorView;

public class FilterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Globals globals = new Globals();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        globals.transStatus(getWindow());
        setImage();
        setClick();
    }

    /**Load copied image from another activities */
    private void setImage(){
        ImageView showImg = (ImageView) findViewById(R.id.showImg);
        showImg.setImageDrawable(new BitmapDrawable(getResources(), WelcomeActivity.getBitmap_transfer()));
    }

    /**Click event */
    private void setClick(){
        Button backBtn = (Button) findViewById(R.id.back_button);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FilterActivity.this, WelcomeActivity.class);
               // setContentView(R.layout.edit_option);
                startActivity(intent);
            }
        });
    }
}