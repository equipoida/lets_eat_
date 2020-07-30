
package com.example.lets_eat;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lets_eat.databinding.ActivityConfusionBinding;
import com.example.lets_eat.databinding.ActivityNotificationListBinding;
import com.example.lets_eat.databinding.ActivityRatingBinding;
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

public class notification_list extends AppCompatActivity {
    private ActivityNotificationListBinding mBinding;
    private FirebaseAuth mAuth;
    FirebaseDatabase rootNode;
    DatabaseReference mDatabase;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityNotificationListBinding.inflate(getLayoutInflater());
        ArrayList<String> items = new ArrayList<String>();
        // ArrayAdapter 생성. 아이템 View를 선택(single choice)가능하도록 만듦.
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, items);
        setContentView(mBinding.getRoot());
        // 빈 데이터 리스트 생성.

        final ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);

        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
        jsoupAsyncTask.execute();


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseRef = database.getReference("notification");

        // Read from the database
        databaseRef.child("notification").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();
                // 클래스 모델이 필요?
                for (DataSnapshot notification : dataSnapshot.getChildren()) {
                    //MyFiles filename = (MyFiles) fileSnapshot.getValue(MyFiles.class);
                    //하위키들의 value를 어떻게 가져오느냐???
                    String str = notification.child("notification").getValue(String.class);
                    Log.i("TAG: value is ", str);
                    items.add(str);
                }
               // adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        // listview 생성 및 adapter 지정.
    }

   class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {
        private String htmlPageUrl = "https://www.hansung.ac.kr/web/www/life_03_01_t1"; //파싱할 홈페이지의 URL주소
        private String htmlContentInStringFormats = "";
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat weekdayFormat = new SimpleDateFormat("EE", Locale.getDefault());

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
            htmlContentInStringFormats =doc.select("span").get(204).text();
            items.add(htmlContentInStringFormats);
            adapter.notifyDataSetChanged();
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {




        }
    }
}

