package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
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
    }

    /**Load copied image from another activities */
    private void setImage(){
        ImageView showImg = (ImageView) findViewById(R.id.showImg);
        showImg.setImageDrawable(new BitmapDrawable(getResources(), WelcomeActivity.getBitmap_transfer()));



    }
}