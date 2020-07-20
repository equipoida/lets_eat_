package com.example.lets_eat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lets_eat.databinding.ActivityMainBinding;
import com.example.lets_eat.databinding.ActivitySubBinding;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;

import uk.co.senab.photoview.PhotoViewAttacher;

public class SubActivity extends AppCompatActivity {
    private ActivitySubBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ImageView imgView = binding.imageView;
        PhotoViewAttacher photoView = new PhotoViewAttacher(imgView);
        photoView.update();
        String imageUrl = "https://www.hansung.ac.kr/portlet-repositories/fckeditor/images/7qHyuRAiKcc=/1595204995118.png";
        Glide.with(this).load(imageUrl).into(imgView);



    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final Intent intent6 = new Intent(this, Recommendation.class);
        final Intent intent5 = new Intent(this, confusion.class);
        final Intent intent3 = new Intent(this, notification_list.class);
        final Intent intent4 = new Intent(this, Rating.class);
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "추천메뉴", Toast.LENGTH_SHORT).show();
                startActivity(intent6);
                return true;
            case R.id.item2:
                Toast.makeText(this, "혼잡도", Toast.LENGTH_SHORT).show();
                startActivity(intent5);
                return true;
            case R.id.item3:
                Toast.makeText(this, "알림", Toast.LENGTH_SHORT).show();
                startActivity(intent3);
                return true;
            case R.id.item4:
                Toast.makeText(this, "리뷰", Toast.LENGTH_SHORT).show();
                startActivity(intent4);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

}
