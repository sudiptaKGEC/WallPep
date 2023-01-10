package com.sudiptahaldarkgec.wallpep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.BitSet;

public class SetWallpaper extends AppCompatActivity {
    ImageView imageView;
    AppCompatButton button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_wallpaper);

        imageView = findViewById(R.id.ImgVIewSetWallId);
        button  = findViewById(R.id.setWallpaperButtonId);

        getSupportActionBar().hide();

        final WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());

        Intent intent = getIntent();
        String url = intent.getStringExtra("image");
        Glide.with(getApplicationContext()).load(url).into(imageView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                    wallpaperManager.setBitmap(bitmap);
                    Toast.makeText(SetWallpaper.this, "Done", Toast.LENGTH_SHORT).show();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }
}