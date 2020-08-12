package com.example.lets_eat;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lets_eat.databinding.ActivityNotificationListBinding;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class notification_list extends AppCompatActivity {
    private static ActivityNotificationListBinding binding;
    private ChipNavigationBar chipnavigationbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
        jsoupAsyncTask.execute();
        chipnavigationbar = binding.chipbar;
        chipnavigationbar.setItemSelected(R.id.chipbar,true);
        bottonMenu();
    }
    private void bottonMenu() {
        chipnavigationbar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            //final Intent intent6 = new Intent(notification_list.this, Recommendation.class);
            //final Intent intent5 = new Intent(notification_list.this, confusion.class);
            //final Intent intent3 = new Intent(notification_list.this, notification_list.class);
            //final Intent intent4 = new Intent(notification_list.this, Rating.class);
            //final Intent intent8 = new Intent(notification_list.this, PersonActivity.class);
            //final Intent intent9 = new Intent(notification_list.this, SubActivity.class);
            @Override
            public void onItemSelected(int i) {
                switch(i){
                    case R.id.item0:
                        Toast.makeText(notification_list.this, "홈", Toast.LENGTH_SHORT).show();
                        //startActivity(intent9);
                        break;
                    case R.id.item1:
                        Toast.makeText(notification_list.this, "추천메뉴", Toast.LENGTH_SHORT).show();
                        //startActivity(intent6);
                        break;
                    case R.id.item2:
                        Toast.makeText(notification_list.this, "한줄 건의함", Toast.LENGTH_SHORT).show();
                        //startActivity(intent5); //혼잡도 창으로 가기
                        break;
                    case R.id.item3:
                        Toast.makeText(notification_list.this, "알림", Toast.LENGTH_SHORT).show();
                        //startActivity(intent3); //알림 창으로 가기
                        break;
                    case R.id.item4:
                        Toast.makeText(notification_list.this, "리뷰", Toast.LENGTH_SHORT).show();
                        //startActivity(intent4); //리뷰 창으로 가기
                        break;
                    case R.id.item5:
                        Toast.makeText(notification_list.this, "계정 정보", Toast.LENGTH_SHORT).show();
                        //startActivity(intent8); //계정 정보 창으로 가기
                        break;

                }
            }
        });
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