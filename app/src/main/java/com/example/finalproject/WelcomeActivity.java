package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.finalproject.Fragment.BrushFragment;
import com.example.finalproject.Fragment.EmojiFragment;
import com.example.finalproject.Interface.BrushInterface;
import com.example.finalproject.Interface.EmojiInterface;

import ja.burhanrashid52.photoeditor.PhotoEditor;
import ja.burhanrashid52.photoeditor.PhotoEditorView;

public class WelcomeActivity extends AppCompatActivity implements BrushInterface, EmojiInterface {
    private static final int PICK_IMAGE =  11;
    private static Bitmap bitmap_transfer;
    static
    {
        System.loadLibrary("NativeImageProcessor");
    }
    PhotoEditorView photoEditorView;
    PhotoEditor photoEditor;
    Bitmap originalImage;
    Uri imageUri;

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
                openGallery();
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
        Button addEmojiBtn = (Button) findViewById(R.id.add_emoji_button);
        Button cancelBtn = (Button) findViewById(R.id.cancel_button);
        Button addMoreBtn = (Button) findViewById(R.id.addmore_button);
        Button brushBtn = (Button) findViewById(R.id.brush_button);

        brushBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                photoEditor.setBrushDrawingMode(true);
                BrushFragment brushfragment = BrushFragment.getInstance();
                brushfragment.setListener(WelcomeActivity.this);
                brushfragment.show(getSupportFragmentManager(),brushfragment.getTag());
            }
        });

        addEmojiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EmojiFragment emojiFragment = EmojiFragment.getInstance();
                emojiFragment.setListener(WelcomeActivity.this);
                emojiFragment.show(getSupportFragmentManager(),emojiFragment.getTag());
            }
        });


        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoEditorView.buildDrawingCache();                //do not remove, use for copy image
                setBitmap_transfer(photoEditorView.getDrawingCache()); //do not remove, use for copy image

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

    //Load METHOD
    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            photoEditorView =(PhotoEditorView) findViewById(R.id.imageAdded);
            photoEditor =new PhotoEditor.Builder(this,photoEditorView)
                    .setPinchTextScalable(true)
                    .setDefaultEmojiTypeface(Typeface.createFromAsset(getAssets(),"emojione-android.ttf"))
                    .build();
            imageUri = data.getData();
            photoEditorView.getSource().setImageURI(imageUri);
            BitmapDrawable drawable = (BitmapDrawable) photoEditorView.getSource().getDrawable();
            originalImage = drawable.getBitmap();

        }
    }

    @Override
    public void onBrushSizeChangeListener(float size) {
        photoEditor.setBrushSize(size);
    }

    @Override
    public void onBrushOpacityListener(int opacity) {
        photoEditor.setOpacity(opacity);
    }

    @Override
    public void onBrushColorChangedListener(int color) {
        photoEditor.setBrushColor(color);
    }

    @Override
    public void onBrushStateChangedListener(boolean isEraser) {
        if(isEraser)
            photoEditor.brushEraser();
        else
            photoEditor.setBrushDrawingMode(true);
    }


    @Override
    public void onEmojiSelected(String emoji) {
        photoEditor.addEmoji(emoji);
    }
}