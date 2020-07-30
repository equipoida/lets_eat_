package com.example.lets_eat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.lets_eat.databinding.ActivityConfusionBinding;
import com.example.lets_eat.databinding.ActivityRatingBinding;
import com.example.lets_eat.databinding.ActivityRecommendationBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Recommendation extends AppCompatActivity {
    private ActivityRecommendationBinding mBinding;
    FirebaseDatabase rootNode;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityRecommendationBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        // 빈 데이터 리스트 생성. 리스트의 모든 데이터를 items에 복사.
        final ArrayList<String> items = new ArrayList<>();


        // ArrayAdapter 생성. 아이템 View를 선택(single choice)가능하도록 만듦.
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
        final EditText menu = mBinding.menu;
        final ListView listview = (ListView) findViewById(R.id.listview);

        // 리스트뷰에 아답터를 연결
        listview.setAdapter(adapter);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseRef = database.getReference("review");


        // Read from the database
        databaseRef.child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();
                // 클래스 모델이 필요?
                for (DataSnapshot user : dataSnapshot.getChildren()) {
                    //MyFiles filename = (MyFiles) fileSnapshot.getValue(MyFiles.class);
                    // reviw 안에 있는 user 안의 review를 listview에 넣기
                    String str = user.child("menuname").getValue(String.class);
                    String strs = user.child("review").getValue(String.class);
                    String strss = user.child("star").getValue(String.class);
                    Log.i("TAG: value is ", str);
                    items.add(str + "\n리뷰: " + strs + "\n별점: " + strss);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        /*
    public void mOnClick(View v) {
        //Intent intent = new Intent (this,subActivity.class);
        //intent.putExtra("menu", menuchoice.getText());
        //intent.putExtra("review",edittext.getText());
        //startActivityForResult(intent,0);

        finish();

    }


         */
    }
}