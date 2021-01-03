package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class WelcomeActivity extends AppCompatActivity {
    private static Bitmap bitmap_transfer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Globals globals = new Globals();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addImage();
        globals.transStatus(getWindow());
    }

    /**Load image to app */
    private void addImage() {
        ImageButton addBtn = (ImageButton) findViewById(R.id.addImg);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.edit_option);
                setClick();
            }
        });
    }

    /**Copy image to another activities */
    public static Bitmap getBitmap_transfer() {
        return bitmap_transfer;
    }

    public static void setBitmap_transfer(Bitmap bitmap_transfer_param) {
        bitmap_transfer = bitmap_transfer_param;
    }

    /**Click event */
    private void setClick() {
        Button filterBtn = (Button) findViewById(R.id.filter_button);
        Button addImgBtn = (Button) findViewById(R.id.add_emoji_button);
        Button cancelBtn = (Button) findViewById(R.id.cancel_button);
        Button addMoreBtn = (Button) findViewById(R.id.addmore_button);

        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView img = (ImageView) findViewById(R.id.imageAdded);
                img.buildDrawingCache();                //do not remove, use for copy image
                setBitmap_transfer(img.getDrawingCache()); //do not remove, use for copy image
                Intent filterIntent = new Intent(WelcomeActivity.this, FilterActivity.class);
                startActivity(filterIntent);
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this,WelcomeActivity.class);
                startActivity(intent);
            }
        });
    }
}