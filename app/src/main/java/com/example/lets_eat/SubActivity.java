package com.example.lets_eat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lets_eat.databinding.ActivityMainBinding;
import com.example.lets_eat.databinding.ActivitySubBinding;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.IOException;
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

        binding.facultiesMenu.setMovementMethod(new ScrollingMovementMethod()); //스

        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
        jsoupAsyncTask.execute();

    }

    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {
        private String htmlPageUrl = "https://www.hansung.ac.kr/web/www/life_03_01_t2"; //파싱할 홈페이지의 URL주소
        private String htmlContentInStringFormat = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            Document doc = null;
            try {
                doc = Jsoup.connect(htmlPageUrl).get();
            } catch (IOException e) {
                e.printStackTrace();
            }


            //테스트1
            Elements titles = doc.select("td"); //2: 월요일 3: 화요일

            for (Element e : titles) {
                System.out.println(e.text());

                htmlContentInStringFormat += e.text().trim() + "\n";//.trim 공백제거

                String humi = e.text();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            binding.facultiesMenu.setText(htmlContentInStringFormat);
        }
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
