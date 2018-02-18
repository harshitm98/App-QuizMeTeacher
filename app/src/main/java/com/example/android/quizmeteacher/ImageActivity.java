package com.example.android.quizmeteacher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageActivity extends AppCompatActivity {

    public ImageView qrCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        qrCode = (ImageView)findViewById(R.id.image);
        Glide.with(ImageActivity.this).load(MainActivity.photoUrl).into(qrCode);
    }
}
