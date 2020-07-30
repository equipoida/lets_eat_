
package com.example.lets_eat;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.lets_eat.databinding.ActivityNotificationListBinding;
import com.example.lets_eat.databinding.ActivitySubBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import uk.co.senab.photoview.PhotoViewAttacher;

public class notification_list extends AppCompatActivity {
    private static ActivityNotificationListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


  JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
        jsoupAsyncTask.execute();

    }

    static class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {
        private String htmlPageUrl = "https://www.hansung.ac.kr/web/www/life_03_01_t1"; //파싱할 홈페이지의 URL주소
        private String htmlContentInStringFormat = "";
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat weekdayFormat = new SimpleDateFormat("EE", Locale.ENGLISH);

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
            // Elements titles = doc.select("td"); //2: 월요일 3: 화요일

            // for (Element e : titles) {
            String weekday = weekdayFormat.format(currentTime);
            htmlContentInStringFormat = doc.select("span").get(204).text();
Log.i("TAG: value is ",htmlContentInStringFormat);
            return null;


        }

        @Override
        protected void onPostExecute(Void result) {

            binding.textView4.setText(htmlContentInStringFormat);
        }
    }
}