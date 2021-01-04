package com.example.finalproject;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import com.zomato.photofilters.imageprocessors.Filter;
import com.zomato.photofilters.imageprocessors.subfilters.BrightnessSubFilter;
import com.zomato.photofilters.imageprocessors.subfilters.ColorOverlaySubFilter;
import com.zomato.photofilters.imageprocessors.subfilters.ContrastSubFilter;
import com.zomato.photofilters.imageprocessors.subfilters.SaturationSubFilter;
import java.io.File;
import java.io.FileOutputStream;

public class FilterActivity extends AppCompatActivity {

    ImageView showImg;
    Bitmap originalBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Globals globals = new Globals();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        globals.transStatus(getWindow());
        ActivityCompat.requestPermissions(FilterActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        ActivityCompat.requestPermissions(FilterActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        setImage();
        call();
    }

    /**Load copied image from another activities */
    private void setImage(){
        showImg = (ImageView) findViewById(R.id.showImg);
        showImg.setImageDrawable(new BitmapDrawable(getResources(), WelcomeActivity.getBitmap_transfer()));
        BitmapDrawable drawable = (BitmapDrawable) showImg.getDrawable();
        originalBitmap = drawable.getBitmap();
    }

    /** Call id */
    private void call(){
        ImageButton filter1 = (ImageButton) findViewById(R.id.filter1);
        ImageButton filter2 = (ImageButton) findViewById(R.id.filter2);
        ImageButton filter3 = (ImageButton) findViewById(R.id.filter3);
        ImageButton filter4 = (ImageButton) findViewById(R.id.filter4);
        ImageButton filter5 = (ImageButton) findViewById(R.id.filter5);
        Button export_button = (Button) findViewById(R.id.export_button);
        Button cancelBtn = (Button) findViewById(R.id.back_button);

    /** Add filter effects option */
        filter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Filter fil1 = new Filter();
                fil1.addSubFilter(new SaturationSubFilter(2.3f));
                Bitmap image = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);  // Convert original image to bitmap to process
                Bitmap outputImage = fil1.processFilter(image);                              //Processing filter to image
                showImg.setImageBitmap(outputImage);
            }
        });

        filter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Filter filt2 = new Filter();
                filt2.addSubFilter(new SaturationSubFilter(-100f));
                filt2.addSubFilter(new ContrastSubFilter(1.3f));
                filt2.addSubFilter(new BrightnessSubFilter(20));
                Bitmap image = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);
                Bitmap outputImage = filt2.processFilter(image);
                showImg.setImageBitmap(outputImage);
            }
        });

        filter3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Filter filt3 = new Filter();
                filt3.addSubFilter(new BrightnessSubFilter(30));
                filt3.addSubFilter(new ContrastSubFilter(1.1f));
                Bitmap image = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);
                Bitmap outputImage = filt3.processFilter(image);
                showImg.setImageBitmap(outputImage);
            }
        });

        filter4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Filter filt4 = new Filter();
                filt4.addSubFilter(new BrightnessSubFilter(5));
                filt4.addSubFilter(new SaturationSubFilter(0.8f));
                filt4.addSubFilter(new ContrastSubFilter(1.9f));
                filt4.addSubFilter(new ColorOverlaySubFilter(100, .6f, .5f, .1f));
                Bitmap image = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);
                Bitmap outputImage = filt4.processFilter(image);
                showImg.setImageBitmap(outputImage);
            }
        });

        filter5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImg.setImageBitmap(originalBitmap);
            }
        });

        /** Export button activity */
        export_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToGallery();
                Toast.makeText(FilterActivity.this,"Successfully Saved",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FilterActivity.this, SuccessNoti.class);
                startActivity(intent);
            }
        });

        /** Cancel button activity */
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FilterActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });

    }

    /** Export image to gallery */
    private void saveToGallery(){
        BitmapDrawable bitmapDrawable = (BitmapDrawable) showImg.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();

        FileOutputStream outputStream = null;
        File file = Environment.getExternalStorageDirectory();
        File dir = new File(file.getAbsolutePath() + "/MyPics");
        dir.mkdirs();

        String filename = String.format("%d.png",System.currentTimeMillis());
        File outFile = new File(dir,filename);
        try{
            outputStream = new FileOutputStream(outFile);   // Write file variable
        }catch (Exception e){
            e.printStackTrace();
        }

        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        try{
            outputStream.flush();     //Delete cache data in output stream and write data to final destination
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            outputStream.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}